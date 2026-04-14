package com.yychainsaw.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(description = "LogWorkoutByMovementDTO模型")
public class LogWorkoutByMovementDTO {
    @NotNull(message = "动作ID不能为空")
    @Schema(description = "movementId")
    private Long movementId;

    @NotNull(message = "运动时长不能为空")
    @Positive(message = "运动时长必须大于0")
    @Schema(description = "durationSeconds")
    private Integer durationSeconds;

    @Schema(description = "notes")
    private String notes;
}
