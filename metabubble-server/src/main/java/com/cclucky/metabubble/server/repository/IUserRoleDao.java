package com.cclucky.metabubble.server.repository;

import com.cclucky.metabubble.server.pojo.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRoleDao extends IBaseDao<UserRole, Long> {
    List<UserRole> findUserRolesByUserId(Long id);
}
