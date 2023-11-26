package com.cclucky.metabubble.server.repository;

import com.cclucky.metabubble.server.model.entity.CommentData;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentDataDao extends IBaseDao<CommentData, Long> {
    void readCommentDataByCommentId(Long commentId);

    // 检查数据库是否存在具有给定 commentId 的数据
    boolean existsByCommentId(Long commentId);
}
