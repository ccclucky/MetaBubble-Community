package com.cclucky.metabubble.server.service;

import com.cclucky.metabubble.server.pojo.dto.CommentDTO;
import com.cclucky.metabubble.server.pojo.entity.LoginUser;
import com.cclucky.metabubble.server.pojo.vo.CommentVo;
import com.cclucky.metabubble.server.pojo.entity.Comment;

import java.util.List;

public interface ICommentService extends IBaseService<Comment, Long> {
    List<CommentDTO> getCommentsByPostId(Long postId);

    Comment toSave(CommentVo commentVo);

    Comment reply(CommentVo commentVo);

    List<String> likeOrUnlike(Long commentId);

    List<CommentDTO> findAllReply();

    List<CommentDTO> allNotifies();
}
