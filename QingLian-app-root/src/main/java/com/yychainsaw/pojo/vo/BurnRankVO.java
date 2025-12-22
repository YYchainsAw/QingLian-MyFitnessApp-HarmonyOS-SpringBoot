package com.yychainsaw.pojo.vo;

import lombok.Data;
import java.util.UUID;

@Data
public class BurnRankVO {
    private UUID userId;
    private Long totalCalories;
    private Integer rank;
}
