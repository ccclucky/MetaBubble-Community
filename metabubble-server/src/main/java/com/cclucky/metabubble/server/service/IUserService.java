package com.cclucky.metabubble.server.service;

import com.cclucky.metabubble.server.pojo.vo.UserVo;
import com.cclucky.metabubble.server.pojo.entity.User;
import com.cclucky.metabubble.server.pojo.dto.UserDTO;

public interface IUserService extends IBaseService<User, Long> {
    UserDTO saveUser(UserVo userVo);

    UserDTO getInfo(String schoolId);

    User updateById(UserVo userVo);
}
