package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.common.utils.RedisCache;
import com.cclucky.metabubble.server.enums.CommentEventEnum;
import com.cclucky.metabubble.server.enums.PostActionEnum;
import com.cclucky.metabubble.server.pojo.dto.CommentDTO;
import com.cclucky.metabubble.server.pojo.entity.Post;
import com.cclucky.metabubble.server.pojo.entity.User;
import com.cclucky.metabubble.server.pojo.vo.CommentVo;
import com.cclucky.metabubble.server.pojo.entity.Comment;
import com.cclucky.metabubble.server.pojo.entity.LoginUser;
import com.cclucky.metabubble.server.repository.IBaseDao;
import com.cclucky.metabubble.server.repository.ICommentDao;
import com.cclucky.metabubble.server.repository.IPostDao;
import com.cclucky.metabubble.server.repository.IUserDao;
import com.cclucky.metabubble.server.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ICommentServiceImpl extends BaseServiceImpl<Comment, Long> implements ICommentService {
    private final ICommentDao commentDao;

    public ICommentServiceImpl(final ICommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IPostDao postDao;

    @Resource
    private RedisCache redisCache;

    @Override
    public IBaseDao<Comment, Long> getBaseDao() {
        return this.commentDao;
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> all = commentDao.findAllByPostIdOrderByCreateTimeDesc(postId);
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 获取所有父评论并转成DTO存进 commentDTOS
        List<CommentDTO> parentComments = all.stream()
                .filter(item -> Objects.isNull(item.getParentId()) && item.getType().equals(0))
                .map(item -> {
                    // 创建评论的CommentDTO
                    User user = userDao.findById(item.getUserId()).orElse(new User());
                    CommentDTO commentDTO = new CommentDTO();
                    BeanUtils.copyProperties(item, commentDTO);
                    commentDTO.setUsername(user.getUsername());
                    commentDTO.setAvatar(user.getAvatar());
                    commentDTO.setUserId(user.getId());
                    // 获取reply_user
                    String replyUsername = userDao.findById(item.getReplyUserId()).orElse(new User()).getUsername();
                    commentDTO.setReplyUsername(replyUsername);
                    // 获取点赞状态和点赞数
                    Set<Long> cacheSet = redisCache.getCacheSet(item.getId() + CommentEventEnum.LIKE.getAction());
                    commentDTO.setLikeCount(cacheSet.size());
                    commentDTO.setLike(cacheSet.contains(loginUser.getUser().getId()));
                    return commentDTO;
                })
                .collect(Collectors.toList());

        // 根据每个父评论获取回复并添加进 parentComments
        List<CommentDTO> allCommentInfo = parentComments.stream().peek(parentComment -> {
            // 根据每个父评论获取回复
            List<CommentDTO> replyDTOList = all.stream()
                    // 过滤获取回复
                    .filter(comment -> !Objects.isNull(comment.getParentId()) && comment.getParentId().equals(parentComment.getId()))
                    .map(childrenComment -> {
                        // 创建回复的CommentDTO
                        CommentDTO reply = new CommentDTO();
                        BeanUtils.copyProperties(childrenComment, reply);
                        User user = userDao.findById(childrenComment.getUserId()).orElse(new User());
                        reply.setUsername(user.getUsername());
                        reply.setAvatar(user.getAvatar());
                        reply.setId(user.getId());
                        // 获取回复的reply_user
                        // 获取reply_user
                        String replyUsername = userDao.findById(childrenComment.getReplyUserId()).orElse(new User()).getUsername();
                        reply.setReplyUsername(replyUsername);
                        // 获取点赞状态和点赞数
                        Set<Long> cacheSet = redisCache.getCacheSet(childrenComment.getId() + CommentEventEnum.LIKE.getAction());
                        reply.setLikeCount(cacheSet.size());
                        reply.setLike(cacheSet.contains(loginUser.getUser().getId()));
                        return reply;
                    }).collect(Collectors.toList());
            parentComment.setReplies(replyDTOList);
        }).collect(Collectors.toList());

        return allCommentInfo;
    }

    @Transactional
    @Override
    public Comment toSave(CommentVo commentVo) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentVo, comment);
        comment.setUserId(loginUser.getUser().getId());
        // 当前帖子的用户则是评论的reply_user
        Long replyUserId = postDao.findById(commentVo.getPostId()).orElse(new Post()).getUserId();
        comment.setReplyUserId(replyUserId);
        commentDao.save(comment);
        // 被评论用户添加被评论记录
        String key = replyUserId + "-" + CommentEventEnum.COMMENTED_REPLIED.getAction();
        this.addCommentedOrReplied(key, comment.getId());
        return comment;
    }

    @Override
    @Transactional
    public Comment reply(CommentVo commentVo) {
        // 判断是否携带父评论id
        if (Objects.isNull(commentVo.getParentId())) {
            // TODO: 2023/10/31 统一返回code
            throw new RuntimeException("缺少参数");
        }
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment reply = new Comment();
        BeanUtils.copyProperties(commentVo, reply);
        reply.setUserId(loginUser.getUser().getId());
        reply.setType(1);
        // 根据parentId获取被回复用户id
        Long userId = commentDao.findById(reply.getParentId()).orElse(new Comment()).getUserId();
        reply.setReplyUserId(userId);
        commentDao.save(reply);
        // 被回复用户添加被回复记录
        String key = userId + "-" + CommentEventEnum.COMMENTED_REPLIED.getAction();
        this.addCommentedOrReplied(key, reply.getId());
        return reply;
    }

    /**
     * 为用户添加评论或回复记录
     * @param key key
     * @param id 评论或回复数据id
     */
    private void addCommentedOrReplied(String key, Long id) {
        Set<Long> cacheSet = redisCache.getCacheSet(key);
        Set<Long> newSet = new LinkedHashSet<>();
        if (CollectionUtils.isEmpty(cacheSet)) {
            cacheSet.add(id);
        } else {
            newSet.add(id);
            newSet.addAll(cacheSet);
            cacheSet = newSet;
        }
        redisCache.deleteObject(key);
        redisCache.setCacheSet(key, cacheSet);
    }

    @Override
    public List<String> likeOrUnlike(Long commentId) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String key = commentId + "-" + CommentEventEnum.LIKE.getAction();
        // 获取当前文章情况 key 为帖子id value为set，set的value为userid
        Set<Long> cacheSet = redisCache.getCacheSet(key);
        HashSet<Long> hashSet = new HashSet<>();
        Set<Long> commentSet = CollectionUtils.isEmpty(cacheSet) ? hashSet : cacheSet;
        // 判断是否有过
        List<String> res = new ArrayList<>();
        Long userId = loginUser.getUser().getId();
        if (!commentSet.contains(userId)) {
            commentSet.add(userId);
            res.add(String.valueOf(commentSet.size()));
            res.add(CommentEventEnum.LIKE.getDesc() + "成功");
        } else {
            commentSet.remove(userId);
            res.add(String.valueOf(commentSet.size()));
            res.add("已取消" + CommentEventEnum.LIKE.getDesc());
        }
        redisCache.deleteObject(key);
        redisCache.setCacheSet(key, commentSet);
        return res;
    }

    @Override
    public List<CommentDTO> findAllReply() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = loginUser.getUser();
        List<Comment> replies = commentDao.findByUserIdAndParentIdIsNotNullOrderByIdDesc(user.getId());
        // 将回复转换为dto形式
        return replies.stream().map(this::comment2DTO).collect(Collectors.toList());
    }

    private CommentDTO comment2DTO(Comment comment) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = loginUser.getUser();
        CommentDTO dto = new CommentDTO();
        BeanUtils.copyProperties(comment, dto);
        dto.setUsername(user.getUsername());
        dto.setAvatar(user.getAvatar());
        // 根据reply_user_id查询父评论
        String username = userDao.findById(comment.getReplyUserId()).orElse(new User()).getUsername();
        dto.setReplyUsername(username);
        // 获取点赞状态和点赞数
        Set<Long> cacheSet = redisCache.getCacheSet(comment.getId() + CommentEventEnum.LIKE.getAction());
        dto.setLikeCount(cacheSet.size());
        dto.setLike(cacheSet.contains(loginUser.getUser().getId()));
        return dto;
    }

    @Override
    public List<CommentDTO> allNotifies() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String key = loginUser.getUser().getId() + "-" + CommentEventEnum.COMMENTED_REPLIED.getAction();
        Set<Long> cacheSet = redisCache.getCacheSet(key);
        return commentDao.findAllByIdIsInOrderByIdDesc(new ArrayList<>(cacheSet))
                .stream().map(this::comment2DTO).collect(Collectors.toList());
    }
}