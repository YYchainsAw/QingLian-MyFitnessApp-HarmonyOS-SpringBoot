package com.yychainsaw.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "GroupCreateDTO模型")
public class GroupCreateDTO {
    @NotBlank(message = "群名称不能为空")
    @Schema(description = "name")
    private String name;
    
    @Schema(description = "avatarUrl")
    private String avatarUrl;
    private String notice;
}
