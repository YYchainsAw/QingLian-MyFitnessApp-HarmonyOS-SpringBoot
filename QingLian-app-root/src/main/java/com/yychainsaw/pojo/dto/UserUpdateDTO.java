package com.yychainsaw.pojo.dto;

import com.yychainsaw.anno.Gender; // 假设你有这个注解
import lombok.Data;
import java.math.BigDecimal;

@Data
public class UserUpdateDTO {
    private String nickname;
    private String avatarUrl;
    
    @Gender
    private String gender;
    
    private Integer height;
    private BigDecimal weight;
}
