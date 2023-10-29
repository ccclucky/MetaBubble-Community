package com.cclucky.metabubble.server.repository;

import com.cclucky.metabubble.server.pojo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends IBaseDao<User, Long> {
    User findBySchoolId(String schoolId);
}
