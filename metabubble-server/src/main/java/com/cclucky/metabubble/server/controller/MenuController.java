package com.cclucky.metabubble.server.controller;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.model.entity.Menu;
import com.cclucky.metabubble.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限菜单管理
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    IMenuService menuService;

    @ApiOperation("添加权限")
    @PostMapping("/add")
    public Result<Menu> add(@RequestBody Menu menu) {
        menuService.save(menu);
        return Result.success(menu, "添加成功");
    }
}
