package com.cclucky.metabubble.server.repository;

import com.cclucky.metabubble.server.pojo.entity.Post;
import org.springframework.stereotype.Repository;

/**
 * @author cclucky
 */
@Repository
public interface IPostDao extends IBaseDao<Post, Long> {

}
