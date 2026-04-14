package com.yychainsaw.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.math.BigDecimal;

// 对应 getMovementAnalytics
@Data
@Schema(description = "MovementAnalyticsVO模型")
public class MovementAnalyticsVO {
    @Schema(description = "category")
    private String category;
    private Integer movementCount;
    @Schema(description = "avgDifficulty")
    private BigDecimal avgDifficulty; // 平均难度
    private String hardestMovement;   // 最难动作名称
}
