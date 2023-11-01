package com.cclucky.metabubble.server.service;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.pojo.entity.LoginUser;
import com.cclucky.metabubble.server.pojo.entity.Post;
import com.cclucky.metabubble.server.pojo.dto.PostDTO;

import java.util.List;

public interface IPostService extends IBaseService<Post, Long> {
    Result<List<Post>> list();

    Result<Post> add(Post post);

    List<PostDTO> findPostVoList(LoginUser loginUser);

    List<String> likeOrUnlike(Long postId, LoginUser loginUser);

    List<String> collectOrUnCollect(Long postId, LoginUser loginUser);
}
