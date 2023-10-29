package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.pojo.dto.RoleMenuDTO;
import com.cclucky.metabubble.server.pojo.entity.Menu;
import com.cclucky.metabubble.server.pojo.entity.Role;
import com.cclucky.metabubble.server.pojo.entity.RoleMenu;
import com.cclucky.metabubble.server.repository.IBaseDao;
import com.cclucky.metabubble.server.repository.IMenuDao;
import com.cclucky.metabubble.server.repository.IRoleDao;
import com.cclucky.metabubble.server.repository.IRoleMenuDao;
import com.cclucky.metabubble.server.service.IMenuService;
import com.cclucky.metabubble.server.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IRoleMenuServiceImpl  extends BaseServiceImpl<RoleMenu, Long> implements IRoleMenuService {

    @Autowired
    private IMenuDao menuDao;
    @Autowired
    private IRoleDao roleDao;

    private final IRoleMenuDao roleMenuDao;

    public IRoleMenuServiceImpl(final IRoleMenuDao roleMenuDao) {
        this.roleMenuDao = roleMenuDao;
    }

    @Override
    public IBaseDao<RoleMenu, Long> getBaseDao() {
        return this.roleMenuDao;
    }

    /**
     *
     * @param roleMenuDTO 角色和多个权限信息
     * @return List<RoleMenu>
     */
    @Override
    @Transactional
    public List<RoleMenu> saveAll(RoleMenuDTO roleMenuDTO) {
        // 创建RoleMenu对象
        List<RoleMenu> collect = roleMenuDTO.getMenuIds().stream()
                .map(item -> RoleMenu.builder().roleId(roleMenuDTO.getRoleId()).menuId(item).build())
                .collect(Collectors.toList());
        // 查询已存在的RoleMenu对象并从数据消除
        List<RoleMenu> list = collect.stream().filter(item -> {
            Example<RoleMenu> example = Example.of(item);
            return !roleMenuDao.exists(example);
        }).collect(Collectors.toList());
        // 将新数据写进数据库
        roleMenuDao.saveAll(list);
        return list;
    }

    @Override
    public List<String> findRoleNameList(String path) {
        // url匹配器实例化
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 查询所有路径
        List<Menu> menus = menuDao.findAll();
        // 获取与请求路径适配的的所有菜单数据
        List<Menu> collect = menus.stream()
                .filter(item -> antPathMatcher.match(item.getPermit(), path)).collect(Collectors.toList());
        // 根据菜单数据获取所有角色名称
        HashSet<String> set = new HashSet<>();
        collect.forEach(item -> {
            List<String> strings = roleDao.findAllById(roleMenuDao.findRoleMenusByMenuId(item.getId())
                    .stream().map(RoleMenu::getRoleId).collect(Collectors.toList())
            ).stream().map(Role::getRoleName).collect(Collectors.toList());
            set.addAll(strings);
        });
        return new ArrayList<>(set);
    }

}
