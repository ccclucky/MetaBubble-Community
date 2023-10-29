package com.cclucky.metabubble.server.repository;

import com.cclucky.metabubble.server.pojo.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentDao extends IBaseDao<Comment, Long> {
}
