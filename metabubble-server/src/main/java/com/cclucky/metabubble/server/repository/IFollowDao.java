package com.cclucky.metabubble.server.repository;

import com.cclucky.metabubble.server.model.entity.Follow;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFollowDao extends IBaseDao<Follow, Long> {
    List<Follow> findFollowsByUserId(Long userId);

    Follow findFollowByUserIdAndFollowId(Long userId, Long followId);
}
