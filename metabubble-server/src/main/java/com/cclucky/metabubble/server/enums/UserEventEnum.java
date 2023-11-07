package com.cclucky.metabubble.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserEventEnum {

    FOLLOW("FOLLOW", "关注"),

    FOLLOWER("FOLLOWING", "追随者");

    private final String action;

    private final String desc;

}