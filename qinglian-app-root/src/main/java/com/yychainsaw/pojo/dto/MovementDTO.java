package com.yychainsaw.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "MovementDTO模型")
public class MovementDTO {
    @NotBlank(message = "标题不能为空")
    @Schema(description = "title")
    private String title;

    @NotBlank(message = "描述不能为空")
    @Schema(description = "description")
    private String description;

    @NotBlank(message = "类别不能为空")
    @Schema(description = "category")
    private String category;

    @NotNull(message = "难度等级不能为空")
    @Schema(description = "difficultyLevel")
    private Integer difficultyLevel;
}

