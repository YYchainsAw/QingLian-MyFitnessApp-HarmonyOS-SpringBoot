package com.yychainsaw.controller;

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

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 获取当前用户信息
    @GetMapping("/info")
    public Result<UserVO> getUserInfo() {
        UserVO userVO = userService.getUserInfo();
        return Result.success(userVO);
    }

    @PutMapping("/update")
    public Result updateProfile(@RequestBody @Validated UserUpdateDTO updateDTO) {
        userService.updateProfile(updateDTO);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result deleteAccount() {
        userService.deleteUser();
        return Result.success();
    }

    @GetMapping("/search")
    public Result<List<UserVO>> searchUsers(@RequestParam(required = false) String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Result.success(Collections.emptyList());
        }
        List<UserVO> users = userService.searchUsers(keyword.trim());
        return Result.success(users);
    }

    @GetMapping("/dashboard")
    public Result<UserSocialDashboardVO> getSocialDashboard() {
        UserSocialDashboardVO dashboard = userService.getUserSocialDashboard();
        return Result.success(dashboard);
    }
}
