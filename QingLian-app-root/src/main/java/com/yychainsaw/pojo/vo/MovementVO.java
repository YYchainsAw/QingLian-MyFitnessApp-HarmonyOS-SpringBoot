package com.yychainsaw.pojo.vo;

import lombok.Data;

@Data
public class MovementVO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Integer difficultyLevel;
}
