package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.common.utils.RedisCache;
import com.cclucky.metabubble.server.enums.PostActionEnum;
import com.cclucky.metabubble.server.pojo.entity.LoginUser;
import com.cclucky.metabubble.server.pojo.entity.Post;
import com.cclucky.metabubble.server.pojo.entity.User;
import com.cclucky.metabubble.server.pojo.vo.PostVo;
import com.cclucky.metabubble.server.repository.IBaseDao;
import com.cclucky.metabubble.server.repository.IPostDao;
import com.cclucky.metabubble.server.repository.IUserDao;
import com.cclucky.metabubble.server.service.IPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    RedisCache redisCache;
    @Autowired
    RedisTemplate redisTemplate;

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

    @Override
    public List<PostVo> findPostVoList(LoginUser loginUser) {
        return postDao.findAll(Sort.by("createTime").descending()).stream().map(item -> {
            PostVo postVo = new PostVo();
            BeanUtils.copyProperties(item, postVo);
            User user = userDao.findById(item.getUserId()).orElse(new User());
            postVo.setAvatar(user.getAvatar());
            postVo.setUsername(user.getUsername());
            // 获取点赞状态和点赞数
            Set<Long> cacheSet = redisCache.getCacheSet(item.getId() + PostActionEnum.LIKE.getAction());
            postVo.setLikeCount(cacheSet.size());
            postVo.setLike(cacheSet.contains(loginUser.getUser().getId()));
            // 获取收藏状态和收藏数
            cacheSet = redisCache.getCacheSet(item.getId() + PostActionEnum.COLLECT.getAction());
            postVo.setCollectCount(cacheSet.size());
            postVo.setCollect(cacheSet.contains(loginUser.getUser().getId()));
            // 获取评论状态和评论数
            return postVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> likeOrUnlike(Long postId, LoginUser loginUser) {
        Long userId = loginUser.getUser().getId();
        String key = postId + PostActionEnum.LIKE.getAction();
        return actionToPost(key, userId, PostActionEnum.LIKE.getDesc());
    }

    @Override
    public List<String> collectOrUnCollect(Long postId, LoginUser loginUser) {
        Long userId = loginUser.getUser().getId();
        String key = postId + PostActionEnum.COLLECT.getAction();
        return actionToPost(key, userId, PostActionEnum.COLLECT.getDesc());
    }

    public List<String> actionToPost(String key, Long userId, String msg) {
        // 获取当前文章情况 key 为帖子id value为set，set的value为userid
        Set<Long> cacheSet = redisCache.getCacheSet(key);
        HashSet<Long> hashSet = new HashSet<>();
        Set<Long> postSet = CollectionUtils.isEmpty(cacheSet) ? hashSet : cacheSet;
        // 判断是否有过
        List<String> res = new ArrayList<>();
        if (!postSet.contains(userId)) {
            postSet.add(userId);
            res.add(String.valueOf(postSet.size()));
            res.add(msg + "成功");
        } else {
            postSet.remove(userId);
            res.add(String.valueOf(postSet.size()));
            res.add("已取消" + msg);
        }
        redisCache.deleteObject(key);
        redisCache.setCacheSet(key, postSet);
        return res;
    }
}
