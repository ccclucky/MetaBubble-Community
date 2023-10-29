package com.cclucky.metabubble.server.controller;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.pojo.dto.RoleMenuDTO;
import com.cclucky.metabubble.server.pojo.entity.RoleMenu;
import com.cclucky.metabubble.server.service.IRoleMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色关联权限管理
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {
    @Autowired
    IRoleMenuService roleMenuService;

    @ApiOperation("为角色添加一个权限")
    @PostMapping("/add")
    public Result<RoleMenu> add(@RequestBody RoleMenu roleMenu) {
        roleMenuService.save(roleMenu);
        return Result.success(roleMenu, "添加成功");
    }

    @ApiOperation("为角色添加多个权限")
    @PostMapping("/rolePermit")
    public Result<List<RoleMenu>> addList(@Valid @RequestBody RoleMenuDTO roleMenuDTO) {
        return Result.success(roleMenuService.saveAll(roleMenuDTO), "添加成功");
    }
}
