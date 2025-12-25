package com.yychainsaw.controller;

import com.yychainsaw.pojo.dto.MessageSendDTO;
import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.pojo.entity.Message;
import com.yychainsaw.pojo.vo.MessageVO;
import com.yychainsaw.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public Result sendMessage(@RequestBody @Validated MessageSendDTO dto) {

        if (dto.getReceiverId() == null && dto.getGroupId() == null) {
            return Result.error("接收者或群组ID不能为空");
        }

        MessageVO messageVO = messageService.sendMessage(dto);

        if (dto.getGroupId() != null) {
            // === 群聊模式 ===
            // 推送到 Topic: /topic/group.{groupId}
            // 所有订阅该群的客户端都会收到
            messagingTemplate.convertAndSend(
                    "/topic/group." + dto.getGroupId(),
                    messageVO
            );
        } else {
            // === 私聊模式 ===
            // 推送到 User Queue: /user/{userId}/queue/messages
            messagingTemplate.convertAndSendToUser(
                    dto.getReceiverId(),
                    "/queue/messages",
                    messageVO
            );
        }

        return Result.success(messageVO);
    }

    // 标记群消息已读 (前端进入群聊页面时调用)
    @PutMapping("/group/read")
    public Result markGroupAsRead(@RequestParam Long groupId, @RequestParam Long lastMsgId) {
        messageService.markGroupAsRead(groupId, lastMsgId);
        return Result.success();
    }

    @PutMapping("/read/{senderId}")
    public Result markAsRead(@PathVariable String senderId) {
        messageService.markAsRead(UUID.fromString(senderId));
        return Result.success();
    }

    @GetMapping("/unread/count")
    public Result<Long> getUnreadCount() {
        Long count = messageService.getUnreadCount();
        return Result.success(count);
    }

    @GetMapping("/history/{friendId}")
    public Result<List<Message>> getChatHistory(@PathVariable String friendId) {
        List<Message> history = messageService.getChatHistory(UUID.fromString(friendId));
        return Result.success(history);
    }
}
