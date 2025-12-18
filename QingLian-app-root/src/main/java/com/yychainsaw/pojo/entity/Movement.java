package com.yychainsaw.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "movements")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id")
    private Long movementId;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "video_url")
    private String videoUrl;

    private String category;

    @Column(name = "difficulty_level")
    private Integer difficultyLevel;
}
