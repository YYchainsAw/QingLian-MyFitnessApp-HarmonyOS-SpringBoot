package com.yychainsaw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

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

@Tag(name = "WorkoutRecord管理", description = "WorkoutRecord相关的API接口")
@RestController
@RequestMapping("/workout-records")
public class WorkoutRecordController {
    @Autowired
    private WorkoutRecordService workoutRecordService;

    @Operation(summary = "addWorkoutRecord", description = "addWorkoutRecord 接口")
    @PostMapping("/add")
    public Result addWorkoutRecord(@RequestBody @Validated WorkoutRecordDTO dto) {
        workoutRecordService.addWorkoutRecord(dto);
        return Result.success();
    }

    @Operation(summary = "getWorkoutHistory", description = "getWorkoutHistory 接口")
    @GetMapping("/history")
    public Result<List<WorkoutRecord>> getWorkoutHistory() {
        return Result.success(workoutRecordService.getWorkoutHistory());
    }

    @Operation(summary = "updateCalories", description = "updateCalories 接口")
    @PutMapping("/update/{recordId}")
    public Result updateCalories(@PathVariable Long recordId, @RequestParam Integer calories) {
        workoutRecordService.updateCalories(recordId, calories);
        return Result.success();
    }

    @Operation(summary = "deleteInvalidRecords", description = "deleteInvalidRecords 接口")
    @DeleteMapping("/invalid")
    public Result deleteInvalidRecords() {
        workoutRecordService.deleteInvalidRecords();
        return Result.success();
    }

    @Operation(summary = "getTodayCalories", description = "getTodayCalories 接口")
    @GetMapping("/today-calories")
    public Result<Integer> getTodayCalories() {
        return Result.success(workoutRecordService.getTodayCalories());
    }

    @Operation(summary = "logWorkoutByMovement", description = "logWorkoutByMovement 接口")
    @PostMapping("/log-by-movement")
    public Result logWorkoutByMovement(@RequestBody @Validated LogWorkoutByMovementDTO dto) {
        workoutRecordService.logWorkoutByMovement(dto);
        return Result.success();
    }

    @Operation(summary = "接口", description = "接口 接口")
    @GetMapping("/leaderboard")
    public Result<List<Map<String, Object>>> getLeaderboard() {
        return Result.success(workoutRecordService.getLeaderboard());
    }
}
