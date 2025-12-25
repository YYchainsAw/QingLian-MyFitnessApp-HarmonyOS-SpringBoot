package com.yychainsaw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有路径的跨域请求
        registry.addMapping("/**")
                // 允许所有来源 (使用 allowedOriginPatterns 比 allowedOrigins 更灵活)
                .allowedOriginPatterns("*")
                // 允许的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许的头信息
                .allowedHeaders("*")
                // 允许携带凭证 (如 Cookies)
                .allowCredentials(true)
                // 预检请求缓存时间 (1小时)
                .maxAge(3600);
    }
}
