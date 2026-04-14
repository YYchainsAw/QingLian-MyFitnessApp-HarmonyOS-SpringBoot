package com.yychainsaw.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "UserVO模型")
public class UserVO {
    @Schema(description = "userId")
    private UUID userId;
    private String username;
    @Schema(description = "nickname")
    private String nickname;
    private String avatarUrl;
    @Schema(description = "height")
    private Integer height;
    private BigDecimal weight;
    @Schema(description = "gender")
    private String gender;
    private LocalDateTime lastLoginTime;
    // 注意：绝对不包含 passwordHash
}
