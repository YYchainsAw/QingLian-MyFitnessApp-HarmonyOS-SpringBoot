package com.yychainsaw.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@TableName("group_members")
public class GroupMember {
    private Long groupId;
    private UUID userId;
    private String role; // OWNER, ADMIN, MEMBER
    private LocalDateTime joinedAt;
}
