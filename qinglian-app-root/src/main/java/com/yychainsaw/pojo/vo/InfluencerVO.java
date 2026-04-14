package com.yychainsaw.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.util.UUID;


@Data
@Schema(description = "InfluencerVO模型")
public class InfluencerVO {
    @Schema(description = "userId")
    private UUID userId;
    private String nickname;
    @Schema(description = "avatarUrl")
    private String avatarUrl;
    private Long totalPosts;
    @Schema(description = "totalLikes")
    private Long totalLikes;
}


