package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.common.utils.RedisCache;
import com.cclucky.metabubble.server.common.utils.SpringBeanUtil;
import com.cclucky.metabubble.server.pojo.vo.LoginVo;
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
import com.cclucky.metabubble.server.service.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    @Resource
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
        // 密码加密
        String password = userDTO.getPassword();
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        userDTO.setPassword(bcryptPasswordEncoder.encode(password));
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

    @Override
    public User updateById(UserVo userVo) {
        if (Objects.isNull(userVo.getId())) {
            throw new RuntimeException("id不能为空");
        }
        // 根据 id 查询数据
        Optional<User> user = userDao.findById(userVo.getId());
        if (user.isPresent()) {
            //加密密码
            String password = userVo.getPassword();
            if (StringUtils.hasText(password)) {
                BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
                userVo.setPassword(bcryptPasswordEncoder.encode(password));
            }
            User newUser = user.get();
            SpringBeanUtil.copyPropertiesIgnoreNull(userVo, newUser);
            // 修改数据
            userDao.saveAndFlush(newUser);
            // 同步更新权限信息
            LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            loginUser.setUser(newUser);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser,null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            // 更新redis
            redisCache.setCacheObject("login:" + newUser.getId(), loginUser);
            return newUser;
        }
        return new User();
    }

    @Override
    public UserDTO getInfoById(Long id) {
        User user = userDao.findById(id).orElse(null);
        if (Objects.isNull(user)) {
            throw new RuntimeException("不存在用户");
        } else {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            return userDTO;
        }
    }
}
