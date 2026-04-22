package com.yychainsaw.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AliOssProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
}
