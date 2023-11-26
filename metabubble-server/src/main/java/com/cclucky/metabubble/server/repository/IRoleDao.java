package com.cclucky.metabubble.server.repository;

import com.cclucky.metabubble.server.model.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDao extends IBaseDao<Role, Long> {
}
