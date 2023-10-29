package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.pojo.entity.UserRole;
import com.cclucky.metabubble.server.repository.IBaseDao;
import com.cclucky.metabubble.server.repository.IUserRoleDao;
import com.cclucky.metabubble.server.service.IUserRoleService;
import org.springframework.stereotype.Service;

@Service
public class IUserRoleServiceImpl extends BaseServiceImpl<UserRole, Long> implements IUserRoleService {
    private final IUserRoleDao userRoleDao;

    public IUserRoleServiceImpl(final IUserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    public IBaseDao<UserRole, Long> getBaseDao() {
        return this.userRoleDao;
    }
}
