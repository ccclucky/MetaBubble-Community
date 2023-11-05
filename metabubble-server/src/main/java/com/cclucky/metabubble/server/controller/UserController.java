package com.cclucky.metabubble.server.controller;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.group.AddValidationGroup;
import com.cclucky.metabubble.server.group.EditValidationGroup;
import com.cclucky.metabubble.server.pojo.entity.User;
import com.cclucky.metabubble.server.pojo.vo.UserVo;
import com.cclucky.metabubble.server.pojo.dto.UserDTO;
import com.cclucky.metabubble.server.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    @ApiOperation("添加用户")
    public Result<UserDTO> add(@Validated(AddValidationGroup.class) @RequestBody UserVo userVo) {
        return Result.success(userService.saveUser(userVo), "添加成功");
    }

    @GetMapping("/info")
    @ApiOperation("获取登录用户信息")
    public Result<UserDTO> getInfo(Principal principal) {
        String schoolId = principal.getName();
        return Result.success(userService.getInfo(schoolId), "查询成功");
    }

    @PutMapping("/update")
    @ApiOperation("更新用户信息")
    public Result<User> update(@Validated(EditValidationGroup.class) @RequestBody UserVo userVo) {
        return Result.success(userService.updateById(userVo), "修改成功");
    }
}
