package com.yychainsaw.pojo;

import lombok.Data;

@Data
public class Movement {
    private Long movementId;
    private String title;
    private String description;
    private String videoUrl;
    private String category;
    private Integer difficultyLevel; // 1-5
}
