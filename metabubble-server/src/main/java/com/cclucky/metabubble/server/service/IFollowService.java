package com.cclucky.metabubble.server.service;

import com.cclucky.metabubble.server.model.dto.UserDTO;
import com.cclucky.metabubble.server.model.entity.Follow;

import java.util.List;

public interface IFollowService extends IBaseService<Follow, Long> {
    String followOrUnfollow(Long id);

    List<UserDTO> allFollows(Long id);

    List<UserDTO> allFans(Long id);
}
