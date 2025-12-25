package com.yychainsaw.controller;

import com.yychainsaw.pojo.dto.GroupCreateDTO;
import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.pojo.entity.ChatGroup;
import com.yychainsaw.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/groups")
@CrossOrigin
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public Result<ChatGroup> createGroup(@RequestBody @Validated GroupCreateDTO dto) {
        ChatGroup group = groupService.createGroup(dto);
        return Result.success(group);
    }

    // 添加拉人入群的接口
    @PostMapping("/{groupId}/members")
    public Result addMember(@PathVariable Long groupId, @RequestParam String userId) {
        groupService.addMember(groupId, UUID.fromString(userId));
        return Result.success();
    }

}
