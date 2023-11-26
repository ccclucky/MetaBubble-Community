package com.cclucky.metabubble.server.service;

import com.cclucky.metabubble.server.model.dto.CommentDTO;
import com.cclucky.metabubble.server.model.vo.CommentVo;
import com.cclucky.metabubble.server.model.entity.Comment;

import java.util.List;

public interface ICommentService extends IBaseService<Comment, Long> {
    List<CommentDTO> getCommentsByPostId(Long postId);

    Comment toSave(CommentVo commentVo);

    Comment reply(CommentVo commentVo);

    List<String> likeOrUnlike(Long commentId);

    List<CommentDTO> findAllReply();

    List<CommentDTO> allNotifies();
}
