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
        return Result.success(postService.findPostVoList(), "获取成功");
    }

    /**
     * 暂时废弃，没用到
     *
     * @param id 帖子id
     * @return Result<PostDTO>
     */
    @GetMapping("/list/{id}")
    @ApiOperation("查询所有帖子相关信息")
    public Result<PostDTO> listById(@PathVariable("id") Long id) {
        return Result.success(postService.findPostVoListById(id), "获取成功");
    }

    @PostMapping("/add")
    @ApiOperation("发帖")
    public Result<Post> add(@RequestBody Post post) {
        return postService.add(post);
    }

    @PostMapping("/like/{id}")
    @ApiOperation("点赞")
    public Result<Long> like(@PathVariable("id") Long postId) {
        List<String> res = postService.likeOrUnlike(postId);
        return Result.success(Long.parseLong(res.get(0)), res.get(1));
    }

    @PostMapping("/collect/{id}")
    @ApiOperation("收藏")
    public Result<Long> collect(@PathVariable("id") Long postId) {
        List<String> res = postService.collectOrUnCollect(postId);
        return Result.success(Long.parseLong(res.get(0)), res.get(1));
    }

    @GetMapping("/allCollect")
    @ApiOperation("根据id获取收藏栏")
    public Result<List<PostDTO>> allCollect() {
        return Result.success(postService.findAllCollect(), "获取成功");
    }

    @GetMapping("/allLike/{id}")
    @ApiOperation("根据id获取喜爱栏")
    public Result<List<PostDTO>> allLike(@PathVariable("id") Long id) {
        return Result.success(postService.findAllLike(id), "获取成功");
    }

    @GetMapping("/allPost/{id}")
    @ApiOperation("根据id获取个人所有帖子")
    public Result<List<PostDTO>> allPost(@PathVariable("id") Long id) {
        return Result.success(postService.findPostsByUserId(id), "获取成功");
    }

}