package com.yychainsaw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.yychainsaw.dto.MovementDTO;
import com.yychainsaw.dto.MovementDifficultyDTO;
import com.yychainsaw.dto.PageBean;
import com.yychainsaw.dto.Result;
import com.yychainsaw.vo.MovementAnalyticsVO;
import com.yychainsaw.vo.MovementVO;
import com.yychainsaw.service.movementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Movement管理", description = "Movement相关的API接口")
@RestController
@RequestMapping("/movements")
public class MovementController {
    @Autowired
    private movementService movementService;

    @Operation(summary = "addMovement", description = "addMovement 接口")
    @PostMapping("/add")
    public Result addMovement(@RequestBody @Validated MovementDTO movementDTO) {
        movementService.addMovement(movementDTO);
        return Result.success();
    }

    @Operation(summary = "searchMovements", description = "searchMovements 接口")
    @GetMapping("/search")
    public Result<PageBean<MovementVO>> searchMovements(@RequestParam(required = false) String keyword,
                                                        Integer pageNum,
                                                        Integer pageSize){

        PageBean<MovementVO> movements = movementService.search(keyword, pageNum, pageSize);
        return Result.success(movements);
    }

    @Operation(summary = "changeDifficultyLevel", description = "changeDifficultyLevel 接口")
    @PostMapping("/change-difficulty")
    public Result changeDifficultyLevel(@RequestBody @Validated MovementDifficultyDTO movementDTO){
        movementService.changeDifficultyLevel(movementDTO);
        return Result.success();
    }

    @Operation(summary = "deleteMovement", description = "deleteMovement 接口")
    @DeleteMapping("/deleteUnused")
    public  Result deleteMovement(){
        movementService.deleteUnusedMovement();
        return Result.success();
    }

    @Operation(summary = "接口", description = "接口 接口")
    @GetMapping("/countCategories")
    public Result<List<Map<String, Object>>> countCategories() {
        List<Map<String, Object>> movements = movementService.countCategories();
        return Result.success(movements);
    }

    @Operation(summary = "getHardcoreMovements", description = "getHardcoreMovements 接口")
    @GetMapping("/hardcore")
    public Result<List<MovementVO>> getHardcoreMovements() {
        return Result.success(movementService.getHardcoreMovements());
    }

    @Operation(summary = "getMovementAnalytics", description = "getMovementAnalytics 接口")
    @GetMapping("/analytics")
    public Result<List<MovementAnalyticsVO>> getMovementAnalytics() {
        return Result.success(movementService.getMovementAnalytics());
    }

}
