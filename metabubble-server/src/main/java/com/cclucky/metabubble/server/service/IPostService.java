package com.cclucky.metabubble.server.service;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.pojo.entity.Post;
import com.cclucky.metabubble.server.pojo.vo.PostVo;

import java.util.List;

public interface IPostService extends IBaseService<Post, Long> {
    Result<List<Post>> list();

    Result<Post> add(Post post);

    List<PostVo> findPostVoList();
}
