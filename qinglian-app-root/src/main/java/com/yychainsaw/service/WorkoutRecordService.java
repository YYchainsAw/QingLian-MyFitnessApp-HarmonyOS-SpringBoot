package com.yychainsaw.service;

import com.yychainsaw.pojo.dto.LogWorkoutByMovementDTO;
import com.yychainsaw.pojo.dto.WorkoutRecordDTO;
import com.yychainsaw.pojo.entity.WorkoutRecord;

import java.util.List;
import java.util.Map;

public interface WorkoutRecordService {
    void addWorkoutRecord(WorkoutRecordDTO dto);

    List<WorkoutRecord> getWorkoutHistory();

    void updateCalories(Long recordId, Integer calories);

    void deleteInvalidRecords();

    Integer getTodayCalories();

    void logWorkoutByMovement(LogWorkoutByMovementDTO dto);

    List<Map<String, Object>> getLeaderboard();
}
