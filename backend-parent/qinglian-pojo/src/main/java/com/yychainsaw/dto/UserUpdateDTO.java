package com.yychainsaw.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import com.yychainsaw.anno.Gender; // 假设你有这个注解
import lombok.Data;
import java.math.BigDecimal;

@Data
@Schema(description = "UserUpdateDTO模型")
public class UserUpdateDTO {
    @Schema(description = "nickname")
    private String nickname;
    private String avatarUrl;
    
    @Gender
    @Schema(description = "gender")
    private String gender;
    
    @Schema(description = "height")
    private Integer height;
    private BigDecimal weight;
}
