package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.model.entity.PostData;
import com.cclucky.metabubble.server.repository.IBaseDao;
import com.cclucky.metabubble.server.repository.IPostDataDao;
import com.cclucky.metabubble.server.service.IPostDataService;
import org.springframework.stereotype.Service;

@Service
public class PostDataServiceImpl  extends BaseServiceImpl<PostData, Long> implements IPostDataService {
    private final IPostDataDao postDataDao;

    public PostDataServiceImpl(final IPostDataDao postDataDao) {
        this.postDataDao = postDataDao;
    }

    @Override
    public IBaseDao<PostData, Long> getBaseDao() {
        return this.postDataDao;
    }
}
