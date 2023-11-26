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
@Table(name = "t_post_data")
public class PostData implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "post_id",columnDefinition="Long not null COMMENT '帖子id'")
    private Long postId;

    @Column(name = "like_count",columnDefinition="int not null COMMENT '喜爱数'")
    private Integer likeCount;

    @Column(name = "collect_count",columnDefinition="int not null COMMENT '收藏数'")
    private Integer collectCount;
}
