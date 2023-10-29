package com.cclucky.metabubble.server.controller;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.pojo.entity.Post;
import com.cclucky.metabubble.server.pojo.vo.PostVo;
import com.cclucky.metabubble.server.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 帖子管理
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    IPostService postService;

    @GetMapping("/list")
    public Result<List<PostVo>> list() {
        return Result.success(postService.findPostVoList(), "获取成功");
    }

    @PostMapping("/add")
    public Result<Post> add(@RequestBody Post post) {
        return postService.add(post);
    }
}
