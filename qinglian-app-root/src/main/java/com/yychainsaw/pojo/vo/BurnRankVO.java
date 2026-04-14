package com.yychainsaw.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.util.UUID;

@Data
@Schema(description = "BurnRankVO模型")
public class BurnRankVO {
    @Schema(description = "userId")
    private UUID userId;
    private Long totalCalories;
    @Schema(description = "rank")
    private Integer rank;
}
