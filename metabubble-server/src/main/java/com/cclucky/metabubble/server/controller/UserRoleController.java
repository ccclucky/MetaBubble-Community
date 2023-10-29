package com.cclucky.metabubble.server.controller;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.pojo.entity.UserRole;
import com.cclucky.metabubble.server.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户关联角色管理
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    @Autowired
    IUserRoleService userRoleService;

    @PostMapping("/add")
    public Result<UserRole> add(@Valid @RequestBody UserRole userRole) {
        userRoleService.save(userRole);
        return Result.success(userRole, "添加成功");
    }
}