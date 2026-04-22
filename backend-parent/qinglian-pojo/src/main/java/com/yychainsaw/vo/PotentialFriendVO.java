package com.yychainsaw.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "PotentialFriendVO模型")
public class PotentialFriendVO {
    @Schema(description = "userId")
    private UUID userId;
    private String nickname;
    @Schema(description = "avatarUrl")
    private String avatarUrl;
    private String similarContent; // 相似的帖子内容片段
}