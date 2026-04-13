package com.yychainsaw.pojo.vo;


import lombok.Data;

@Data
public class GenderStatVO {
    private String gender;
    private Integer weightRangeStart; // 体重分段起始值 (如 60, 70)
    private Integer userCount;
    private Double avgPosts;          // 平均发帖数
}