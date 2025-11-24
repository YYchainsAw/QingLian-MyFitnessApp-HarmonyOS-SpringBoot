package com.yychainsaw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String passwordHash;
    private String nickname;
    private String avatarUrl;
    private String gender;
    private Integer height;
    private BigDecimal weight;
    private LocalDate createAt;
}
