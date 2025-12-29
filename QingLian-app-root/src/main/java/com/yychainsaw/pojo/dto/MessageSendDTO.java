package com.yychainsaw.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MessageSendDTO {

    private String receiverId;

    private Long groupId;

    @NotBlank(message = "消息内容不能为空")
    private String content;

    @NotBlank(message = "消息类型不能为空")
    private String type;
}
