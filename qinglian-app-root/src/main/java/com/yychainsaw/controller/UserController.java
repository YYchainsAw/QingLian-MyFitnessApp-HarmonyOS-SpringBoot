package com.yychainsaw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.pojo.dto.UserUpdateDTO;
import com.yychainsaw.pojo.vo.UserSocialDashboardVO;
import com.yychainsaw.pojo.vo.UserVO;
import com.yychainsaw.service.UserService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "User管理", description = "User相关的API接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 获取当前用户信息
    @Operation(summary = "getUserInfo", description = "getUserInfo 接口")
    @GetMapping("/info")
    public Result<UserVO> getUserInfo() {
        UserVO userVO = userService.getUserInfo();
        return Result.success(userVO);
    }

    @Operation(summary = "updateProfile", description = "updateProfile 接口")
    @PutMapping("/update")
    public Result updateProfile(@RequestBody @Validated UserUpdateDTO updateDTO) {
        userService.updateProfile(updateDTO);
        return Result.success();
    }

    @Operation(summary = "updateAvatar", description = "updateAvatar 接口")
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @Operation(summary = "deleteAccount", description = "deleteAccount 接口")
    @DeleteMapping("/delete")
    public Result deleteAccount() {
        userService.deleteUser();
        return Result.success();
    }

    @Operation(summary = "searchUsers", description = "searchUsers 接口")
    @GetMapping("/search")
    public Result<List<UserVO>> searchUsers(@RequestParam(required = false) String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Result.success(Collections.emptyList());
        }
        List<UserVO> users = userService.searchUsers(keyword.trim());
        return Result.success(users);
    }

    @Operation(summary = "getSocialDashboard", description = "getSocialDashboard 接口")
    @GetMapping("/dashboard")
    public Result<UserSocialDashboardVO> getSocialDashboard() {
        UserSocialDashboardVO dashboard = userService.getUserSocialDashboard();
        return Result.success(dashboard);
    }
}
