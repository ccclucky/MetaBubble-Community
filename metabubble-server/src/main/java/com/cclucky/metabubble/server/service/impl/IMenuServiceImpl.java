package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.model.entity.Menu;
import com.cclucky.metabubble.server.repository.IBaseDao;
import com.cclucky.metabubble.server.repository.IMenuDao;
import com.cclucky.metabubble.server.service.IMenuService;
import org.springframework.stereotype.Service;

@Service
public class IMenuServiceImpl extends BaseServiceImpl<Menu, Long> implements IMenuService  {
    private final IMenuDao menuDao;

    public IMenuServiceImpl(final IMenuDao menuDao) {
        this.menuDao = menuDao;
    }
    @Override
    public IBaseDao<Menu, Long> getBaseDao() {
        return this.menuDao;
    }
}
