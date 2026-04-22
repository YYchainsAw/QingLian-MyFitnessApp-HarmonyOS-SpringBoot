package com.yychainsaw.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Data
@Schema(description = "UserSocialDashboardVO模型")
public class UserSocialDashboardVO {
    @Schema(description = "friendCount")
    private Integer friendCount;     // 好友数量
    private Integer ranking;         // 排名
    @Schema(description = "unreadMessages")
    private Integer unreadMessages;  // 未读消息
    // 其他你需要查询的字段...
}
