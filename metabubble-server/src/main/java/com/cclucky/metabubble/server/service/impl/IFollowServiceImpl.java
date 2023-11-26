package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.common.utils.RedisCache;
import com.cclucky.metabubble.server.enums.UserEventEnum;
import com.cclucky.metabubble.server.model.dto.UserDTO;
import com.cclucky.metabubble.server.model.entity.Follow;
import com.cclucky.metabubble.server.model.entity.LoginUser;
import com.cclucky.metabubble.server.repository.IBaseDao;
import com.cclucky.metabubble.server.repository.IFollowDao;
import com.cclucky.metabubble.server.service.IFollowService;
import com.cclucky.metabubble.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IFollowServiceImpl extends BaseServiceImpl<Follow, Long> implements IFollowService {

    @Autowired
    private IUserService userService;

    @Resource
    private RedisCache redisCache;

    private final IFollowDao followDao;

    public IFollowServiceImpl(final IFollowDao followDao) {
        this.followDao = followDao;
    }

    @Override
    public IBaseDao<Follow, Long> getBaseDao() {
        return this.followDao;
    }

    @Override
    @Transactional
    public String followOrUnfollow(Long id) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 从redis获取关注列表
        String key = loginUser.getUser().getId() + "-" + UserEventEnum.FOLLOW.getAction();
        String key2 = id + "-" + UserEventEnum.FOLLOWER.getAction();
        Set<Long> cacheSet = redisCache.getCacheSet(key);
        Set<Long> follower = redisCache.getCacheSet(key2);
        // 判断是否已关注
        Set<Long> newSet = new LinkedHashSet<>();
        Set<Long> newFollower = new LinkedHashSet<>();
        String res;
        if (cacheSet.contains(id)) {
            cacheSet.remove(id);
            res = "取消关注成功";
            // 帖主的粉丝栏将当前用户删除
            follower.remove(loginUser.getUser().getId());
        } else {
            newSet.add(id);
            newSet.addAll(cacheSet);
            cacheSet = newSet;
            res = "关注成功";
            // 帖主的粉丝栏添加当前用户
            newFollower.add(loginUser.getUser().getId());
            newFollower.addAll(follower);
            follower = newFollower;
        }
        redisCache.deleteObject(key);
        redisCache.setCacheSet(key, cacheSet);
        redisCache.deleteObject(key2);
        redisCache.setCacheSet(key2, follower);
        return res;
    }

    @Override
    public List<UserDTO> allFollows(Long id) {
        String key = id + "-" + UserEventEnum.FOLLOW.getAction();
        return event(key);
    }

    @Override
    public List<UserDTO> allFans(Long id) {
        String key = id + "-" + UserEventEnum.FOLLOWER.getAction();
        return event(key);
    }

    private List<UserDTO> event(String key) {
        Set<Long> cacheSet = redisCache.getCacheSet(key);
        return  cacheSet.stream().map(item -> userService.getInfoById(item)).collect(Collectors.toList());
    }
}
