package com.cclucky.metabubble.server.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String schoolId;
    private String username;
    private String email;
    private String phone;
    private String avatar;
    private List<String> roleName;
    @JsonFormat(pattern = "yyyy-MM")
    private Date createTime;
}
