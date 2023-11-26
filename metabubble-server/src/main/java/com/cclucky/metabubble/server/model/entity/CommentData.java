package com.cclucky.metabubble.server.model.entity;

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
@Table(name = "t_comment_data")
public class CommentData implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "comment_id",columnDefinition="Long not null COMMENT '评论id'")
    private Long commentId;

    @Column(name = "like_count",columnDefinition="int not null COMMENT '喜爱数'")
    private Integer likeCount;
}

