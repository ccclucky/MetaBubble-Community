package com.cclucky.metabubble.server.controller;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.pojo.entity.LoginUser;
import com.cclucky.metabubble.server.pojo.entity.Post;
import com.cclucky.metabubble.server.pojo.dto.PostDTO;
import com.cclucky.metabubble.server.service.IPostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @ApiOperation("查询所有帖子相关信息")
    public Result<List<PostDTO>> list() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Result.success(postService.findPostVoList(loginUser), "获取成功");
    }

    @PostMapping("/add")
    @ApiOperation("发帖")
    public Result<Post> add(@RequestBody Post post) {
        return postService.add(post);
    }

    @PostMapping("/like/{id}")
    @ApiOperation("点赞")
    public Result<Long> like(@PathVariable("id") Long postId) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> res = postService.likeOrUnlike(postId, loginUser);
        return Result.success(Long.parseLong(res.get(0)), res.get(1));
    }

    @PostMapping("/collect/{id}")
    @ApiOperation("点赞")
    public Result<Long> collect(@PathVariable("id") Long postId) {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> res = postService.collectOrUnCollect(postId, loginUser);
        return Result.success(Long.parseLong(res.get(0)), res.get(1));
    }
}
