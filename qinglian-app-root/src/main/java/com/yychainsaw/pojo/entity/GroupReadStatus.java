package com.yychainsaw.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.UUID;

@Data
@TableName("group_read_status")
@Schema(description = "GroupReadStatus模型")
public class GroupReadStatus {
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "id")
    private Long id;

    @Schema(description = "groupId")
    private Long groupId;
    private UUID userId;
    @Schema(description = "lastReadMsgId")
    private Long lastReadMsgId;
}
