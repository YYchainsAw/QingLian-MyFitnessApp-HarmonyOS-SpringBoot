package com.yychainsaw.pojo.vo;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

// 对应 selectFriendsActivePlans
@Data
public class FriendPlanVO {
    private String username;
    private String title;      // 计划标题
    private String status;     // 计划状态
    private LocalDate endDate; // 结束时间
}


