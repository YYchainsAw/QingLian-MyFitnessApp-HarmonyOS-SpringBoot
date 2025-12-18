package com.yychainsaw.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "workout_records")
public class WorkoutRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @Column(name = "calories_burned")
    private Integer caloriesBurned;

    @CreationTimestamp
    @Column(name = "workout_date")
    private LocalDateTime workoutDate;

    private String notes;
}
