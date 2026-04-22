package com.yychainsaw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yychainsaw.dto.MessageSendDTO;
import com.yychainsaw.dto.PageBean;
import com.yychainsaw.dto.Result;
import com.yychainsaw.entity.Message;
import com.yychainsaw.vo.MessageVO;
import com.yychainsaw.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Tag(name = "Message管理", description = "Message相关的API接口")
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Operation(summary = "sendMessage", description = "sendMessage 接口")
    @PostMapping
    public Result sendMessage(@RequestBody @Validated MessageSendDTO dto) {

        if (dto.getReceiverId() == null && dto.getGroupId() == null) {
            return Result.error("接收者或群组ID不能为空");
        }

        MessageVO messageVO = messageService.sendMessage(dto);

        if (dto.getGroupId() != null) {

            messagingTemplate.convertAndSend(
                    "/topic/group." + dto.getGroupId(),
                    messageVO
            );
        } else {

            messagingTemplate.convertAndSendToUser(
                    dto.getReceiverId(),
                    "/queue/messages",
                    messageVO
            );
        }

        return Result.success(messageVO);
    }

    // 标记群消息已读 (前端进入群聊页面时调用)
    @Operation(summary = "markGroupAsRead", description = "markGroupAsRead 接口")
    @PutMapping("/group/read")
    public Result markGroupAsRead(@RequestParam Long groupId, @RequestParam Long lastMsgId) {
        messageService.markGroupAsRead(groupId, lastMsgId);
        return Result.success();
    }

    @Operation(summary = "markAsRead", description = "markAsRead 接口")
    @PutMapping("/read/{senderId}")
    public Result markAsRead(@PathVariable String senderId) {
        messageService.markAsRead(UUID.fromString(senderId));
        return Result.success();
    }

    @Operation(summary = "getUnreadCount", description = "getUnreadCount 接口")
    @GetMapping("/unread/count")
    public Result<Long> getUnreadCount() {
        Long count = messageService.getUnreadCount();
        return Result.success(count);
    }

    @Operation(summary = "getChatHistory", description = "getChatHistory 接口")
    @GetMapping("/history/{friendId}")
    public Result<PageBean<MessageVO>> getChatHistory(
            @PathVariable String friendId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Message> history = messageService.getChatHistory(UUID.fromString(friendId));
        PageInfo<Message> pageInfo = new PageInfo<>(history);

        List<Message> resultList = pageInfo.getList();
        Collections.reverse(resultList);

        List<MessageVO> voList = messageService.transferToVOList(resultList);

        PageBean<MessageVO> pageBean = new PageBean<>(pageInfo.getTotal(), voList);
        return Result.success(pageBean);
    }

    @Operation(summary = "getGroupChatHistory", description = "getGroupChatHistory 接口")
    @GetMapping("/groups/{groupId}/history")
    public Result<PageBean<MessageVO>> getGroupChatHistory(
            @PathVariable Long groupId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Message> history = messageService.getGroupChatHistory(groupId);
        PageInfo<Message> pageInfo = new PageInfo<>(history);

        List<Message> resultList = pageInfo.getList();
        Collections.reverse(resultList);

        List<MessageVO> voList = messageService.transferToVOList(resultList);

        PageBean<MessageVO> pageBean = new PageBean<>(pageInfo.getTotal(), voList);
        return Result.success(pageBean);
    }
}
