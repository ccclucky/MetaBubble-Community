package com.cclucky.metabubble.server.repository;

import com.cclucky.metabubble.server.model.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cclucky
 */
@Repository
public interface IPostDao extends IBaseDao<Post, Long> {
    List<Post> findPostsByUserIdOrderByCreateTimeDesc(Long userId);
}
