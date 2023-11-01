package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.common.result.Result;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @Override
    public IBaseDao<Comment, Long> getBaseDao() {
        return this.commentDao;
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> all = commentDao.findAllByPostIdOrderByCreateTimeDesc(postId);
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
        return commentDao.save(comment);
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
        return reply;
    }
}
