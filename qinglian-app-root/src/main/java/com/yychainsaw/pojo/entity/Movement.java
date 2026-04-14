package com.yychainsaw.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("movements")
@Schema(description = "Movement模型")
public class Movement {

    @TableId(value = "movement_id", type = IdType.AUTO)
    @Schema(description = "movementId")
    private Long movementId;

    @Schema(description = "title")
    private String title;

    @Schema(description = "description")
    private String description;

    @TableField("video_url")
    @Schema(description = "videoUrl")
    private String videoUrl;

    @Schema(description = "category")
    private String category;

    @TableField("difficulty_level")
    @Schema(description = "difficultyLevel")
    private Integer difficultyLevel;
}
