package com.cclucky.metabubble.server.controller;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.pojo.entity.Role;
import com.cclucky.metabubble.server.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @PostMapping("/add")
    public Result<Role> add(@RequestBody Role role) {
        roleService.save(role);
        return Result.success(role, "添加成功");
    }

}
