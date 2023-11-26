package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.common.utils.RedisCache;
import com.cclucky.metabubble.server.enums.PostActionEnum;
import com.cclucky.metabubble.server.model.entity.LoginUser;
import com.cclucky.metabubble.server.model.entity.Post;
import com.cclucky.metabubble.server.model.entity.User;
import com.cclucky.metabubble.server.model.dto.PostDTO;
import com.cclucky.metabubble.server.repository.*;
import com.cclucky.metabubble.server.service.IPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IPostServiceImpl extends BaseServiceImpl<Post, Long> implements IPostService {
    private final IPostDao postDao;

    public IPostServiceImpl(final IPostDao postDao) {
        this.postDao = postDao;
    }

    @Autowired
    private IUserDao userDao;
    @Resource
    private RedisCache redisCache;
    @Autowired
    private ICommentDao commentDao;
    @Autowired
    private IPostDataDao postDataDao;

    @Override
    public IBaseDao<Post, Long> getBaseDao() {
        return this.postDao;
    }

    public Result<List<Post>> list() {
        List<Post> all = postDao.findAll(Sort.by("createTime").descending());
        return Result.success(all, "所有数据");
    }

    @Override
    @Transactional
    public Result<Post> add(Post post) {
        Post save = postDao.save(post);
        return Result.success(save, "发布成功");
    }

    private PostDTO PostToDTO(Post post) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = loginUser.getUser();
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        // 获取post用户信息
        User postUser = userDao.findById(post.getUserId()).orElse(new User());
        postDTO.setUsername(postUser.getUsername());
        postDTO.setAvatar(postUser.getAvatar());
        // 获取点赞状态和点赞数
        Set<Long> likeSet = redisCache.getCacheSet(post.getId() + "-" + PostActionEnum.LIKE.getAction());
        postDTO.setLikeCount(likeSet.size());
        postDTO.setLike(likeSet.contains(user.getId()));
        // 获取收藏状态和收藏数
        likeSet = redisCache.getCacheSet(post.getId() + "-" + PostActionEnum.COLLECT.getAction());
        postDTO.setCollectCount(likeSet.size());
        postDTO.setCollect(likeSet.contains(user.getId()));
        return postDTO;
    }

    @Override
    public List<PostDTO> findPostVoList() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return postDao.findAll(Sort.by("createTime").descending()).stream().map(item -> {
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(item, postDTO);
            User user = userDao.findById(item.getUserId()).orElse(new User());
            postDTO.setUserId(user.getId());
            postDTO.setAvatar(user.getAvatar());
            postDTO.setUsername(user.getUsername());
            // 获取点赞状态和点赞数
            Set<Long> cacheSet = redisCache.getCacheSet(item.getId() + "-" + PostActionEnum.LIKE.getAction());
            postDTO.setLikeCount(cacheSet.size());
            postDTO.setLike(cacheSet.contains(loginUser.getUser().getId()));
            // 获取收藏状态和收藏数
            cacheSet = redisCache.getCacheSet(item.getId() + "-" + PostActionEnum.COLLECT.getAction());
            postDTO.setCollectCount(cacheSet.size());
            postDTO.setCollect(cacheSet.contains(loginUser.getUser().getId()));
            // 获取评论状态和评论数
            int size = (int) commentDao.findCommentsByPostId(item.getId()).stream()
                    .filter(comment -> Objects.isNull(comment.getParentId())).count();
            postDTO.setCommentCount(size);
            return postDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public PostDTO findPostVoListById(Long id) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.findById(id).orElse(new Post());
        User user = loginUser.getUser();
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        postDTO.setUserId(user.getId());
        postDTO.setUsername(user.getUsername());
        postDTO.setAvatar(user.getAvatar());
        // 获取点赞状态和点赞数
        Set<Long> cacheSet = redisCache.getCacheSet(id + "-" + PostActionEnum.LIKE.getAction());
        postDTO.setLikeCount(cacheSet.size());
        postDTO.setLike(cacheSet.contains(user.getId()));
        // 获取收藏状态和收藏数
        cacheSet = redisCache.getCacheSet(id + "-" + PostActionEnum.COLLECT.getAction());
        postDTO.setCollectCount(cacheSet.size());
        postDTO.setCollect(cacheSet.contains(user.getId()));
        // 获取评论状态和评论数
        int size = (int) commentDao.findCommentsByPostId(id).stream()
                .filter(comment -> Objects.isNull(comment.getParentId())).count();
        postDTO.setCommentCount(size);
        return postDTO;
    }

    @Override
    public List<PostDTO> findAllCollect() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = loginUser.getUser();
        String key = user.getId() + "-" + PostActionEnum.USER_COLLECT.getAction();
        Set<Long> cacheSet = redisCache.getCacheSet(key);
        return cacheSet.stream().map(item -> {
            Post post = postDao.findById(item).orElse(new Post());
            return PostToDTO(post);
        }).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> findPostsByUserId(Long id) {
        List<Post> posts = postDao.findPostsByUserIdOrderByCreateTimeDesc(id);
        return posts.stream().map(this::PostToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> findAllLike(Long id) {
        String key = id + "-" + PostActionEnum.USER_LIKE.getAction();
        // 逆序
        Set<Long> cacheSet = redisCache.getCacheSet(key);
        return cacheSet.stream().map(item -> {
            Post post = postDao.findById(item).orElse(new Post());
            return PostToDTO(post);
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> likeOrUnlike(Long postId) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loginUser.getUser().getId();
        String key = postId + "-" + PostActionEnum.LIKE.getAction();
        String event = userId + "-" + PostActionEnum.USER_LIKE.getAction();
        return actionToPost(key, userId, PostActionEnum.LIKE.getDesc(), event);
    }

    @Override
    public List<String> collectOrUnCollect(Long postId) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loginUser.getUser().getId();
        String key = postId + "-" + PostActionEnum.COLLECT.getAction();
        String event = userId + "-" + PostActionEnum.USER_COLLECT.getAction();
        return actionToPost(key, userId, PostActionEnum.COLLECT.getDesc(), event);
    }

    public List<String> actionToPost(String key, Long userId, String msg, String event) {
        // 获取当前文章情况 key 为帖子id value为set，set的value为userid
        Set<Long> cacheSet = redisCache.getCacheSet(key);
        HashSet<Long> hashSet = new HashSet<>();
        Set<Long> postSet = CollectionUtils.isEmpty(cacheSet) ? hashSet : cacheSet;

        // 为用户添加收藏和点赞记录
        Set<Long> userEvent = redisCache.getCacheSet(event);
        long postId = Long.parseLong(key.split("-")[0]);

        // 判断是否有过
        List<String> res = new ArrayList<>();
        Set<Long> newSet = new LinkedHashSet<>();
        if (!postSet.contains(userId)) {
            postSet.add(userId);
            res.add(String.valueOf(postSet.size()));
            res.add(msg + "成功");
            // set的头部插入
            newSet.add(postId);
            newSet.addAll(userEvent);
            userEvent = newSet;
        } else {
            postSet.remove(userId);
            res.add(String.valueOf(postSet.size()));
            res.add("已取消" + msg);
            userEvent.remove(postId);
        }
        redisCache.deleteObject(key);
        if (postSet.isEmpty() && postDataDao.existsByPostId(postId)) {
            postDataDao.removePostDataByPostId(postId);
        }
        redisCache.setCacheSet(key, postSet);
        redisCache.deleteObject(event);
        redisCache.setCacheSet(event, userEvent);
        return res;
    }
}
