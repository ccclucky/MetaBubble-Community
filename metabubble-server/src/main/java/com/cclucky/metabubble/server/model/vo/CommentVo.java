package com.cclucky.metabubble.server.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentVo {
    @NotNull
    private Long postId;
    private Long parentId;
    @NotNull
    private Long replyUserId;
    @NotNull
    private String content;
}
