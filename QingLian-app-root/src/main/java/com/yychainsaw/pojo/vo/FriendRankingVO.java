package com.yychainsaw.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendRankingVO {
    private String username;
    private Integer totalWorkouts;    // 总健身次数
    private LocalDateTime lastWorkout; // 最后健身时间
}