package com.yychainsaw.controller;

import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.pojo.dto.UserLoginDTO;
import com.yychainsaw.pojo.dto.UserRegisterDTO;
import com.yychainsaw.pojo.vo.TokenVO;
import com.yychainsaw.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Result register(@RequestBody @Validated UserRegisterDTO dto) {
        authService.register(dto);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody @Validated UserLoginDTO dto) {
        TokenVO tokenVO = authService.login(dto);
        return Result.success(tokenVO);
    }

    @PostMapping("/logout")
    public Result logout(@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return Result.success();
    }

    @PostMapping("/refresh")
    public Result refresh(@RequestHeader("Authorization") String token) {
        String newToken = authService.refreshToken(token);
        return Result.success(newToken);
    }
}
