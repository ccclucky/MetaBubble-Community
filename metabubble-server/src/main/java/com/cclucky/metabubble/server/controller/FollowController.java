package com.cclucky.metabubble.server.controller;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.model.dto.UserDTO;
import com.cclucky.metabubble.server.service.IFollowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关注管理
 */
@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private IFollowService followService;

    @PostMapping("/{id}")
    @ApiOperation("关注或取消关注")
    public Result<String> followOrUnfollow(@PathVariable("id") Long id) {
        return Result.success(followService.followOrUnfollow(id));
    }

    @GetMapping("/allFollow/{id}")
    @ApiOperation("根据id获取用户的所有关注")
    public Result<List<UserDTO>> allFollow(@PathVariable("id") Long id) {
        return Result.success(followService.allFollows(id));
    }

    @GetMapping("/allFans/{id}")
    @ApiOperation("根据id获取用户的所有粉丝")
    public Result<List<UserDTO>> allFans(@PathVariable("id") Long id) {
        return Result.success(followService.allFans(id));
    }
}
