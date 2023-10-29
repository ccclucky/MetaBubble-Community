package com.cclucky.metabubble.server.repository;

import com.cclucky.metabubble.server.pojo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao extends IBaseDao<Role, Long> {
}
