package com.cclucky.metabubble.server.service;

import com.cclucky.metabubble.server.pojo.dto.UserDTO;
import com.cclucky.metabubble.server.pojo.entity.Follow;

import java.util.List;

public interface IFollowService extends IBaseService<Follow, Long> {
    String followOrUnfollow(Long id);

    List<UserDTO> allFollows(Long id);

    List<UserDTO> allFans(Long id);
}
