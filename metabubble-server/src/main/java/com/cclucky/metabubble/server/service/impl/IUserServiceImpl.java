package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.common.utils.RedisCache;
import com.cclucky.metabubble.server.pojo.vo.UserVo;
import com.cclucky.metabubble.server.pojo.entity.LoginUser;
import com.cclucky.metabubble.server.pojo.entity.Role;
import com.cclucky.metabubble.server.pojo.entity.User;
import com.cclucky.metabubble.server.pojo.entity.UserRole;
import com.cclucky.metabubble.server.pojo.dto.UserDTO;
import com.cclucky.metabubble.server.repository.IBaseDao;
import com.cclucky.metabubble.server.repository.IRoleDao;
import com.cclucky.metabubble.server.repository.IUserDao;
import com.cclucky.metabubble.server.repository.IUserRoleDao;
import com.cclucky.metabubble.server.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class IUserServiceImpl extends BaseServiceImpl<User, Long> implements IUserService {
    private final IUserDao userDao;

    public IUserServiceImpl(final IUserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    IUserRoleDao userRoleDao;
    @Autowired
    IRoleDao roleDao;
    @Autowired
    private RedisCache redisCache;

    @Override
    public IBaseDao<User, Long> getBaseDao() {
        return this.userDao;
    }

    @Override
    @Transactional
    public UserDTO saveUser(UserVo userDTO) {
        // 查询用户是否已存在
        if (!Objects.isNull(userDao.findBySchoolId(userDTO.getSchoolId()))) {
            // TODO: 2023/10/26 管理
            throw new RuntimeException("用户已存在");
        }
        // 创建用户实例并写进数据库
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userDao.save(user);
        // 创建UserRole实例
        List<UserRole> collect = userDTO.getRoleId().stream()
                .map(item -> UserRole.builder().userId(user.getId()).roleId(item).build()).collect(Collectors.toList());
        List<UserRole> userRoles = userRoleDao.saveAll(collect);
        // 创建返回vo
        UserDTO userVo = new UserDTO();
        BeanUtils.copyProperties(user, userVo);
        List<String> roleNames = new ArrayList<>();
        userRoles.forEach(item -> {
            Role role = roleDao.findById(item.getRoleId()).orElse(new Role());
            roleNames.add(role.getRoleName());
        });
        userVo.setRoleName(roleNames);
        return userVo;
    }

    @Override
    public UserDTO getInfo(String schoolId) {
        // 根据登录账号获取用户信息
        User user = userDao.findBySchoolId(schoolId);
        LoginUser userInfo = redisCache.getCacheObject("login:" + user.getId());
        // 创建userVO实例返回
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo.getUser(), userDTO);
        List<String> list = userInfo.getPermissions();
        userDTO.setRoleName(list);
        return userDTO;
    }
}
