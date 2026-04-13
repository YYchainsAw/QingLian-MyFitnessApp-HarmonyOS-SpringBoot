package com.yychainsaw.pojo.vo;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserVO {
    private UUID userId;
    private String username;
    private String nickname;
    private String avatarUrl;
    private Integer height;
    private BigDecimal weight;
    private String gender;
    private LocalDateTime lastLoginTime;
    // 注意：绝对不包含 passwordHash
}
