package com.cclucky.metabubble.server.model.vo;

import com.cclucky.metabubble.server.group.AddValidationGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVo {
    private Long id;
    @NotBlank
    private String schoolId;
    private String username;
    @NotBlank(groups = {AddValidationGroup.class})
    private String password;
    private String email;
    private String phone;
    private String avatar;
    @NotNull
    private List<Long> roleId;
}
