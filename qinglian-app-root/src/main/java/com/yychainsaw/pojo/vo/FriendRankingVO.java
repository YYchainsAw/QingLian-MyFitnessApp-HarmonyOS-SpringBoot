package com.yychainsaw.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "FriendRankingVO模型")
public class FriendRankingVO {
    @Schema(description = "username")
    private String username;
    private Integer totalWorkouts;    // 总健身次数
    @Schema(description = "lastWorkout")
    private LocalDateTime lastWorkout; // 最后健身时间
}