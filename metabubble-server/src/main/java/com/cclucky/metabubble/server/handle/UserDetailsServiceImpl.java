package com.cclucky.metabubble.server.handle;

import com.cclucky.metabubble.server.model.entity.*;
import com.cclucky.metabubble.server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IUserRoleDao userRoleDao;
    @Autowired
    private IMenuDao menuDao;
    @Autowired
    private IRoleMenuDao roleMenuDao;
    @Autowired
    private IRoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String schoolId) throws UsernameNotFoundException {
        //根据学号查询用户信息
        User user = userDao.findBySchoolId(schoolId);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("学号或密码错误");
        }
        // 根据用户查询角色信息 添加到LoginUser中
        List<UserRole> userRoleList = userRoleDao.findUserRolesByUserId(user.getId());
        List<String> rolesName = userRoleList.stream().map(item ->
                roleDao.findById(item.getRoleId()).orElse(new Role()).getRoleName()
        ).collect(Collectors.toList());
        //封装成UserDetails对象返回
        return new LoginUser(user, rolesName);
    }
}

