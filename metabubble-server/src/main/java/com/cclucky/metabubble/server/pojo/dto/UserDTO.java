package com.cclucky.metabubble.server.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    @NotNull
    private String schoolId;
    private String username;
    @NotNull
    private String password;
    private String email;
    private String phone;
    private String avatar;
    @NotNull
    private List<Long> roleId;
}
