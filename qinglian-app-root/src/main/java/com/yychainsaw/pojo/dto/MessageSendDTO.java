package com.yychainsaw.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "MessageSendDTO模型")
public class MessageSendDTO {

    @Schema(description = "receiverId")
    private String receiverId;

    @Schema(description = "groupId")
    private Long groupId;

    @NotBlank(message = "消息内容不能为空")
    @Schema(description = "content")
    private String content;

    @NotBlank(message = "消息类型不能为空")
    @Schema(description = "type")
    private String type;
}
