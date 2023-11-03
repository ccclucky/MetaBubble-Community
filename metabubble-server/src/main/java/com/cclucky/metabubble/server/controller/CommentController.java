package com.cclucky.metabubble.server.controller;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.pojo.dto.CommentDTO;
import com.cclucky.metabubble.server.pojo.vo.CommentVo;
import com.cclucky.metabubble.server.pojo.entity.Comment;
import com.cclucky.metabubble.server.service.ICommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 评论管理
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    ICommentService commentService;

    @PostMapping("/add")
    @ApiOperation("添加评论")
    public Result<Comment> add(@Valid @RequestBody CommentVo commentVo) {
        return Result.success(commentService.toSave(commentVo), "成功发送评论");
    }

    @PostMapping("/reply")
    @ApiOperation("回复评论")
    public Result<Comment> reply(@Valid @RequestBody CommentVo commentVo) {
        return Result.success(commentService.reply(commentVo), "成功回复评论");
    }

    @GetMapping("/comments/{postId}")
    @ApiOperation("添加评论")
    public Result<List<CommentDTO>> getComments(@PathVariable("postId") Long postId) {
        return Result.success(commentService.getCommentsByPostId(postId), "成功获取评论");
    }

    @PostMapping("/like/{id}")
    @ApiOperation("点赞")
    public Result<Long> like(@PathVariable("id") Long commentId) {
        List<String> res = commentService.likeOrUnlike(commentId);
        return Result.success(Long.parseLong(res.get(0)), res.get(1));
    }
}
