package com.cclucky.metabubble.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostActionEnum {

    LIKE("LIKE", "点赞"),

    COMMENT("COMMENT", "评论"),

    COLLECT("COLLECT", "收藏"),

    USER_COLLECT("USER_COLLECT", "用户收藏"),

    USER_LIKE("USER_LIKE", "用户喜欢"),

    PUBLISH ("PUBLISH", "发布");

    private final String action;

    private final String desc;

}
