package com.yychainsaw.pojo.vo;

import lombok.Data;
import java.math.BigDecimal;

// 对应 getMovementAnalytics
@Data
public class MovementAnalyticsVO {
    private String category;
    private Integer movementCount;
    private BigDecimal avgDifficulty; // 平均难度
    private String hardestMovement;   // 最难动作名称
}
