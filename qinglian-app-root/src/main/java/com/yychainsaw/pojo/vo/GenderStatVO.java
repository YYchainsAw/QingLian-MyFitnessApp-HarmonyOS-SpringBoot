package com.yychainsaw.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;


import lombok.Data;

@Data
@Schema(description = "GenderStatVO模型")
public class GenderStatVO {
    @Schema(description = "gender")
    private String gender;
    private Integer weightRangeStart; // 体重分段起始值 (如 60, 70)
    @Schema(description = "userCount")
    private Integer userCount;
    private Double avgPosts;          // 平均发帖数
}