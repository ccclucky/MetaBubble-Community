package com.cclucky.metabubble.server.service;

import com.cclucky.metabubble.server.pojo.dto.UserDTO;
import com.cclucky.metabubble.server.pojo.entity.User;
import com.cclucky.metabubble.server.pojo.vo.UserVo;

public interface IUserService extends IBaseService<User, Long> {
    UserVo saveUser(UserDTO userDTO);

    UserVo getInfo(String schoolId);
}
