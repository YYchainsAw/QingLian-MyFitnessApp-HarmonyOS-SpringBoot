package com.yychainsaw.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("movements")
public class Movement {

    @TableId(value = "movement_id", type = IdType.AUTO)
    private Long movementId;

    private String title;

    private String description;

    @TableField("video_url")
    private String videoUrl;

    private String category;

    @TableField("difficulty_level")
    private Integer difficultyLevel;
}
