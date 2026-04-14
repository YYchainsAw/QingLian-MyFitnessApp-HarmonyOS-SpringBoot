package com.yychainsaw.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Data
@Schema(description = "MovementVO模型")
public class MovementVO {
    @Schema(description = "id")
    private Long id;
    private String title;
    @Schema(description = "description")
    private String description;
    private String category;
    @Schema(description = "difficultyLevel")
    private Integer difficultyLevel;
}
