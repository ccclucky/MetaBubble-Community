package com.cclucky.metabubble.server.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_post")
public class Post implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id",columnDefinition="int not null COMMENT '用户id'")
    private Long userId;

    @Column(name = "content",columnDefinition="text not null COMMENT '内容'")
    private String content;

    @Column(name = "type",columnDefinition="tinyint(1) default 0 COMMENT '0 已发布 1 草稿'")
    private Integer type;

    @Column(name = "is_delete",columnDefinition="tinyint(1) default 0 COMMENT '逻辑删除字段 1 为删除'")
    private Integer isDelete;

    @Column(name = "create_time",columnDefinition="timestamp DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "update_time",columnDefinition=" timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}