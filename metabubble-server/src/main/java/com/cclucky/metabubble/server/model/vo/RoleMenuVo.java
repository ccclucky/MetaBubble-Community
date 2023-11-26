package com.cclucky.metabubble.server.model.vo;

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
public class RoleMenuVo {
    @NotNull
    private Long roleId;
    @NotNull
    private List<Long> menuIds;
}
