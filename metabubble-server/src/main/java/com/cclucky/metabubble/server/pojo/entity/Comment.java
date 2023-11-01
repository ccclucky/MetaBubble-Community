package com.cclucky.metabubble.server.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "user_id",columnDefinition="Long not null COMMENT '评论用户id'")
    private Long userId;

    @Column(name = "reply_user_id",columnDefinition="Long not null COMMENT '回复用户id'")
    private Long replyUserId;

    @Column(name = "post_id",columnDefinition="Long not null COMMENT '帖子id'")
    private Long postId;

    @Column(name = "parent_id",columnDefinition="Long default null COMMENT '父评论id'")
    private Long parentId;

    @Column(name = "content",columnDefinition="text not null COMMENT '内容'")
    private String content;

    @Column(name = "type",columnDefinition="tinyint default 0 COMMENT '评论类型 0 为评论 1 回复'")
    private Integer type = 0;

    @Column(name = "is_delete",columnDefinition="tinyint default 0 COMMENT '逻辑删除字段 1 为删除'")
    private Integer isDelete = 0;

    @Column(name = "create_time",columnDefinition="timestamp DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
