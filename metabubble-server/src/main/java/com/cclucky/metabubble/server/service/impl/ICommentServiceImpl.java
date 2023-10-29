package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.pojo.entity.Comment;
import com.cclucky.metabubble.server.repository.IBaseDao;
import com.cclucky.metabubble.server.repository.ICommentDao;
import com.cclucky.metabubble.server.service.ICommentService;
import org.springframework.stereotype.Service;

@Service
public class ICommentServiceImpl extends BaseServiceImpl<Comment, Long> implements ICommentService {
    private final ICommentDao commentDao;

    public ICommentServiceImpl(final ICommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public IBaseDao<Comment, Long> getBaseDao() {
        return this.commentDao;
    }
}
