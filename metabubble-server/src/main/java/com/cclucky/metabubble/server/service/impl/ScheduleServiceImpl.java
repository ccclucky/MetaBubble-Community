package com.cclucky.metabubble.server.service.impl;

import com.cclucky.metabubble.server.common.utils.RedisCache;
import com.cclucky.metabubble.server.enums.CommentEventEnum;
import com.cclucky.metabubble.server.enums.PostActionEnum;
import com.cclucky.metabubble.server.pojo.entity.CommentData;
import com.cclucky.metabubble.server.pojo.entity.PostData;
import com.cclucky.metabubble.server.repository.ICommentDataDao;
import com.cclucky.metabubble.server.repository.IPostDataDao;
import com.cclucky.metabubble.server.service.ICommentDataService;
import com.cclucky.metabubble.server.service.IPostDataService;
import com.cclucky.metabubble.server.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private RedisCache redisCache;
    @Autowired
    private IPostDataDao postDataDao;
    @Autowired
    private ICommentDataDao commentDataDao;

    @Scheduled(fixedRate = 3600000 * 24) // 每隔24h执行一次，可以根据需求调整时间间隔
    @Transactional
    public void saveDataToDatabase() {
        log.info("开始持久化redis数据");
        // 获取所有帖子的数据并保存
        String postLikePattern = "*-" + PostActionEnum.LIKE.getAction();
        String postCollectPattern = PostActionEnum.COLLECT.getAction();
        List<PostData> postDataList = redisCache.keys(postLikePattern).stream().map(item -> {
            Set<Long> postLike = redisCache.getCacheSet(item);
            Long postId = Long.valueOf(item.split("-")[0]);
            Set<Long> postCollect = redisCache.getCacheSet(postId + "-" + postCollectPattern);
            // 删除原有数据
            if (postDataDao.existsByPostId(postId)) {
                postDataDao.removePostDataByPostId(postId);
            }
            return PostData.builder()
                    .postId(postId)
                    .likeCount(postLike.size())
                    .collectCount(postCollect.size())
                    .build();
        }).collect(Collectors.toList());
        postDataDao.saveAll(postDataList);

        // 获取所有评论的数据并保存
        String commentLikePattern = "*-" + CommentEventEnum.LIKE.getAction();
        List<CommentData> commentDataList = redisCache.keys(commentLikePattern).stream().map(item -> {
            Set<Long> commentLike = redisCache.getCacheSet(item);
            Long commentId = Long.valueOf(item.split("-")[0]);
            // 删除原有数据
            if (commentDataDao.existsByCommentId(commentId)) {
                commentDataDao.readCommentDataByCommentId(commentId);
            }
            return CommentData.builder().commentId(commentId).likeCount(commentLike.size()).build();
        }).collect(Collectors.toList());
        commentDataDao.saveAll(commentDataList);
    }
}

