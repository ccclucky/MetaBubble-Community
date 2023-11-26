package com.cclucky.metabubble.server.service;

import com.cclucky.metabubble.server.model.vo.UserVo;
import com.cclucky.metabubble.server.model.entity.User;
import com.cclucky.metabubble.server.model.dto.UserDTO;

public interface IUserService extends IBaseService<User, Long> {
    UserDTO saveUser(UserVo userVo);

    UserDTO getInfo(String schoolId);

    User updateById(UserVo userVo);

    UserDTO getInfoById(Long id);
}
