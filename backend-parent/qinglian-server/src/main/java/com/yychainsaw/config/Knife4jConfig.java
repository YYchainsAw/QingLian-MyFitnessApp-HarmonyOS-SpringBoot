package com.yychainsaw.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("QingLian-MyFitnessApp 接口文档")
                        .version("1.0")
                        .description("轻练 (QingLian) Android和Spring Boot服务接口文档")
                        .termsOfService("http://doc.xiaominfo.com")
                        .contact(new Contact().name("developer").url("https://github.com/").email("developer@email.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}

