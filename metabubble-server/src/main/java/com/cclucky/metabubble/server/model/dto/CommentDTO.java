package com.cclucky.metabubble.server.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private Long id;
    private Long userId;
    private String username;
    private String replyUsername;
    private String avatar;
    private Long parentId;
    private Long postId;
    private String content;
    private Integer likeCount;
    private boolean isLike;
    @JsonFormat(pattern = "MM-dd HH:mm")
    private Date createTime;
    private List<CommentDTO> replies;
}
