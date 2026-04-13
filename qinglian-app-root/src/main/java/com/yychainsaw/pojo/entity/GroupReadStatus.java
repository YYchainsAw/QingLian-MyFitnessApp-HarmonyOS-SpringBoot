package com.yychainsaw.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.UUID;

@Data
@TableName("group_read_status")
public class GroupReadStatus {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long groupId;
    private UUID userId;
    private Long lastReadMsgId;
}
