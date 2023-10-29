package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.common.result.Result;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IPostServiceImpl extends BaseServiceImpl<Post, Long> implements IPostService {
    private final IPostDao postDao;

    public IPostServiceImpl(final IPostDao postDao) {
        this.postDao = postDao;
    }

    @Autowired
    private IUserDao userDao;

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
    public List<PostVo> findPostVoList() {
        return postDao.findAll(Sort.by("createTime").descending()).stream().map(item -> {
            PostVo postVo = new PostVo();
            BeanUtils.copyProperties(item, postVo);
            User user = userDao.findById(item.getUserId()).orElse(new User());
            postVo.setAvatar(user.getAvatar());
            postVo.setUsername(user.getUsername());
            return postVo;
        }).collect(Collectors.toList());
    }
}
