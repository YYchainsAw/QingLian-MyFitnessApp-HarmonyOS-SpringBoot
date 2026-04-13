package com.yychainsaw.controller;

import com.yychainsaw.pojo.dto.LogWorkoutByMovementDTO;
import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.pojo.dto.WorkoutRecordDTO;
import com.yychainsaw.pojo.entity.WorkoutRecord;
import com.yychainsaw.service.WorkoutRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workout-records")
public class WorkoutRecordController {
    @Autowired
    private WorkoutRecordService workoutRecordService;

    @PostMapping("/add")
    public Result addWorkoutRecord(@RequestBody @Validated WorkoutRecordDTO dto) {
        workoutRecordService.addWorkoutRecord(dto);
        return Result.success();
    }

    @GetMapping("/history")
    public Result<List<WorkoutRecord>> getWorkoutHistory() {
        return Result.success(workoutRecordService.getWorkoutHistory());
    }

    @PutMapping("/update/{recordId}")
    public Result updateCalories(@PathVariable Long recordId, @RequestParam Integer calories) {
        workoutRecordService.updateCalories(recordId, calories);
        return Result.success();
    }

    @DeleteMapping("/invalid")
    public Result deleteInvalidRecords() {
        workoutRecordService.deleteInvalidRecords();
        return Result.success();
    }

    @GetMapping("/today-calories")
    public Result<Integer> getTodayCalories() {
        return Result.success(workoutRecordService.getTodayCalories());
    }

    @PostMapping("/log-by-movement")
    public Result logWorkoutByMovement(@RequestBody @Validated LogWorkoutByMovementDTO dto) {
        workoutRecordService.logWorkoutByMovement(dto);
        return Result.success();
    }

    @GetMapping("/leaderboard")
    public Result<List<Map<String, Object>>> getLeaderboard() {
        return Result.success(workoutRecordService.getLeaderboard());
    }
}
