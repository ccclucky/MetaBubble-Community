package com.cclucky.metabubble.server.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_comment")
public class Comment implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id",columnDefinition="int not null COMMENT '评论用户id'")
    private String username;

    @Column(name = "reply_user_id",columnDefinition="int not null COMMENT '回复用户id'")
    private String replyUserId;

    @Column(name = "parent_id",columnDefinition="int default null COMMENT '父评论id'")
    private String parentId;

    @Column(name = "content",columnDefinition="text not null COMMENT '内容'")
    private String content;

    @Column(name = "is_delete",columnDefinition="tinyint(1) default 0 COMMENT '逻辑删除字段 1 为删除'")
    private Integer isDelete;
}
