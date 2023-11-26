package com.cclucky.metabubble.server.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_user")
public class User implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "school_id",columnDefinition="char(13) not null COMMENT '学号'")
    private String schoolId;

    @Column(name = "username",columnDefinition="varchar(20) COMMENT '用户姓名'")
    private String username;

    @Column(name = "password",columnDefinition="varchar(255) not null COMMENT '密码'")
    private String password;

    @Column(name = "email",columnDefinition="varchar(255) default null COMMENT '邮箱'")
    @Email
    private String email;

    @Column(name = "phone",columnDefinition="char(11) default null COMMENT '手机号'")
    @Pattern(regexp = "^1[3456789][0-9]{9}$", message = "手机号格式错误")
    private String phone;

    @Column(name = "avatar",columnDefinition="varchar(255) default null COMMENT '头像'")
    private String avatar;

    @Column(name = "is_delete",columnDefinition="tinyint(1) default 0 COMMENT '逻辑删除字段 1 为删除'")
    private Integer isDelete = 0;

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
