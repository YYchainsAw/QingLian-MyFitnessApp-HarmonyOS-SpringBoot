package com.yychainsaw.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "MovementDifficultyDTO模型")
public class MovementDifficultyDTO {
    @NotBlank(message = "类别不能为空")
    @Schema(description = "category")
    private String category;

    @NotNull(message = "难度等级不能为空")
    @Schema(description = "difficultyLevel")
    private Integer difficultyLevel;
}