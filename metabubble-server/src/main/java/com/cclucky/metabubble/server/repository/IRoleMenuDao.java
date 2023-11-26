package com.cclucky.metabubble.server.repository;

import com.cclucky.metabubble.server.model.entity.RoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleMenuDao extends IBaseDao<RoleMenu, Long> {
    List<RoleMenu> findAllByRoleId(Long roleId);

    List<RoleMenu> findRoleMenusByMenuId(Long menuId);
}
