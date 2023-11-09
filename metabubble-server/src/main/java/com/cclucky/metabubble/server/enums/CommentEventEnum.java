package com.cclucky.metabubble.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentEventEnum {

    LIKE("LIKE", "点赞"),

    COMMENT("COMMENT", "评论"),

    COMMENTED_REPLIED("COMMENTED_REPLIED", "被评论或被回复");

    private final String action;

    private final String desc;

}