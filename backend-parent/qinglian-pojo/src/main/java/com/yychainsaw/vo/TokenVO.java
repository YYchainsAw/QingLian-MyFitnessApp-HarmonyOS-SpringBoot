package com.yychainsaw.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "TokenVO模型")
public class TokenVO {
    @Schema(description = "token")
    private String token;
    private UserVO userInfo;
}
