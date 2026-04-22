package com.yychainsaw.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "UserLoginDTO模型")
public class UserLoginDTO {
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "username")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "password")
    private String password;
}
