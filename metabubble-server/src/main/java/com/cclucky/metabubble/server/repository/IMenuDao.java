package com.cclucky.metabubble.server.repository;

import com.cclucky.metabubble.server.pojo.entity.Menu;
import org.springframework.stereotype.Repository;

@Repository
public interface IMenuDao extends IBaseDao<Menu, Long> {
    Menu findByPermit(String path);
}
