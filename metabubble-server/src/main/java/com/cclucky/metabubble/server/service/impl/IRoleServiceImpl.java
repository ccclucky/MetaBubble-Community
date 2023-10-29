package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.pojo.entity.Role;
import com.cclucky.metabubble.server.repository.IBaseDao;
import com.cclucky.metabubble.server.repository.IRoleDao;
import com.cclucky.metabubble.server.service.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class IRoleServiceImpl extends BaseServiceImpl<Role, Long> implements IRoleService {
    private final IRoleDao roleDao;

    public IRoleServiceImpl(final IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public IBaseDao<Role, Long> getBaseDao() {
        return this.roleDao;
    }
}
