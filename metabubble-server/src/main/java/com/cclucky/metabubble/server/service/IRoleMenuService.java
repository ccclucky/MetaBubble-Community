package com.cclucky.metabubble.server.service;

import com.cclucky.metabubble.server.model.vo.RoleMenuVo;
import com.cclucky.metabubble.server.model.entity.RoleMenu;

import java.util.List;

public interface IRoleMenuService extends IBaseService<RoleMenu, Long> {
    List<RoleMenu> saveAll(RoleMenuVo roleMenuVo);

    List<String> findRoleNameList(String path);

}
