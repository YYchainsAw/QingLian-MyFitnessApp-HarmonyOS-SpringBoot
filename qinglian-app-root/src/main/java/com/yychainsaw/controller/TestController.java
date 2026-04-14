package com.yychainsaw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test管理", description = "Test相关的API接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @Operation(summary = "hello", description = "hello 接口")
    @GetMapping("/hello")
    public String hello() {
        return "Hello World! The QingLian backend is running successfully on your server.";
    }
}

