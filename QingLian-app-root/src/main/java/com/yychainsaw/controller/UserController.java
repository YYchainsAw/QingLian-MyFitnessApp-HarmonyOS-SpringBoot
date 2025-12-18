package com.yychainsaw.controller;

import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.pojo.dto.UserUpdateDTO;
import com.yychainsaw.pojo.vo.UserVO;
import com.yychainsaw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 获取当前用户信息
    @GetMapping("/info")
    public Result<UserVO> getUserInfo(@RequestAttribute("id") String userIdStr) {
        // 假设拦截器已经解析 Token 并将 id 放入 RequestAttribute
        UUID userId = UUID.fromString(userIdStr);
        UserVO userVO = userService.getUserInfo(userId);
        return Result.success(userVO);
    }

    @PutMapping("/update")
    public Result updateProfile(@RequestAttribute("id") String userIdStr,
                                @RequestBody @Validated UserUpdateDTO updateDTO) {
        UUID userId = UUID.fromString(userIdStr);
        userService.updateProfile(userId, updateDTO);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result deleteAccount(@RequestAttribute("id") String userIdStr) {
        UUID userId = UUID.fromString(userIdStr);
        userService.deleteUser(userId);
        return Result.success();
    }
}
