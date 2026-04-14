package com.yychainsaw.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "UserRegisterDTO模型")
public class UserRegisterDTO {
    @Pattern(regexp = "^[a-zA-Z_][a-zA-Z0-9_]{5,9}$", message = "用户名必须是6-10位字母、数字或下划线")
    @Schema(description = "username")
    private String username;

    @Pattern(regexp = "^\\S{6,}$", message = "密码至少6位且不能包含空格")
    @Schema(description = "password")
    private String password;

    @Schema(description = "nickname")
    private String nickname;
}
