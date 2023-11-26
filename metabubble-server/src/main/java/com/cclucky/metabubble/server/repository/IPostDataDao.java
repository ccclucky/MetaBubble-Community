package com.cclucky.metabubble.server.repository;

import com.cclucky.metabubble.server.model.entity.PostData;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostDataDao extends IBaseDao<PostData, Long> {
    void removePostDataByPostId(Long postId);

    // 检查数据库是否存在具有给定 postId 的数据
    boolean existsByPostId(Long postId);
}
