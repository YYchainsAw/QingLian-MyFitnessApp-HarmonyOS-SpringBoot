package com.yychainsaw.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

// 对应 selectFriendsActivePlans
@Data
@Schema(description = "FriendPlanVO模型")
public class FriendPlanVO {
    @Schema(description = "username")
    private String username;
    private String title;      // 计划标题
    @Schema(description = "status")
    private String status;     // 计划状态
    private LocalDate endDate; // 结束时间
}


