package com.yychainsaw.pojo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LogWorkoutByMovementDTO {
    @NotNull(message = "动作ID不能为空")
    private Long movementId;

    @NotNull(message = "运动时长不能为空")
    @Positive(message = "运动时长必须大于0")
    private Integer durationSeconds;

    private String notes;
}
