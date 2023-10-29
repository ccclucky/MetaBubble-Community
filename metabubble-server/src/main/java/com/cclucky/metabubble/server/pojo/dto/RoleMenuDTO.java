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
public class RoleMenuDTO {
    @NotNull
    private Long roleId;
    @NotNull
    private List<Long> menuIds;
}
