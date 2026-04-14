package com.yychainsaw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.pojo.dto.UserLoginDTO;
import com.yychainsaw.pojo.dto.UserRegisterDTO;
import com.yychainsaw.pojo.vo.TokenVO;
import com.yychainsaw.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth管理", description = "Auth相关的API接口")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "register", description = "register 接口")
    @PostMapping("/register")
    public Result register(@RequestBody @Validated UserRegisterDTO dto) {
        authService.register(dto);
        return Result.success();
    }

    @Operation(summary = "login", description = "login 接口")
    @PostMapping("/login")
    public Result login(@RequestBody @Validated UserLoginDTO dto) {
        TokenVO tokenVO = authService.login(dto);
        return Result.success(tokenVO);
    }

    @Operation(summary = "logout", description = "logout 接口")
    @PostMapping("/logout")
    public Result logout(@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return Result.success();
    }

    @Operation(summary = "refresh", description = "refresh 接口")
    @PostMapping("/refresh")
    public Result refresh(@RequestHeader("Authorization") String token) {
        String newToken = authService.refreshToken(token);
        return Result.success(newToken);
    }
}
