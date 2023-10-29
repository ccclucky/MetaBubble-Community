package com.cclucky.metabubble.server.controller;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.pojo.dto.UserDTO;
import com.cclucky.metabubble.server.pojo.entity.User;
import com.cclucky.metabubble.server.pojo.vo.UserVo;
import com.cclucky.metabubble.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("/add")
    public Result<UserVo> add(@Valid @RequestBody UserDTO userDTO) {
        return Result.success(userService.saveUser(userDTO), "添加成功");
    }

    @GetMapping("/info")
    public Result<UserVo> getInfo(Principal principal) {
        String schoolId = principal.getName();
        return Result.success(userService.getInfo(schoolId), "查询成功");
    }
}
