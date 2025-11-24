package com.yychainsaw.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkoutRecord {
    private Long recordId;
    private User user;
    private Plan plan;
    private Integer durationSeconds;
    private Integer caloriesBurned;
    private LocalDateTime workoutDate;
    private String notes;
}
