package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.pojo.entity.CommentData;
import com.cclucky.metabubble.server.repository.IBaseDao;
import com.cclucky.metabubble.server.repository.ICommentDataDao;
import com.cclucky.metabubble.server.service.ICommentDataService;
import org.springframework.stereotype.Service;

@Service
public class ICommentDataServiceImpl extends BaseServiceImpl<CommentData, Long> implements ICommentDataService {

    private final ICommentDataDao commentDataDao;

    public ICommentDataServiceImpl(final ICommentDataDao commentDataDao) {
        this.commentDataDao = commentDataDao;
    }


    @Override
    public IBaseDao<CommentData, Long> getBaseDao() {
        return this.commentDataDao;
    }
}
