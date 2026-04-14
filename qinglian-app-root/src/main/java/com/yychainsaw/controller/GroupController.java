package com.yychainsaw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.yychainsaw.pojo.dto.GroupCreateDTO;
import com.yychainsaw.pojo.dto.Result;
import com.yychainsaw.pojo.entity.ChatGroup;
import com.yychainsaw.pojo.entity.GroupMember;
import com.yychainsaw.pojo.vo.GroupListVO;
import com.yychainsaw.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "Group管理", description = "Group相关的API接口")
@RestController
@RequestMapping("/groups")
@CrossOrigin
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Operation(summary = "createGroup", description = "createGroup 接口")
    @PostMapping
    public Result<Long> createGroup(@RequestBody @Validated GroupCreateDTO dto) {
        ChatGroup group = groupService.createGroup(dto);
        return Result.success(group.getGroupId());
    }

    // 【新增】获取群组列表 (解决 GET /groups 报 405 的问题)
    @Operation(summary = "getUserGroups", description = "getUserGroups 接口")
    @GetMapping
    public Result<List<GroupListVO>> getUserGroups() {
        return Result.success(groupService.getUserGroups());
    }

    @Operation(summary = "getGroupMembers", description = "getGroupMembers 接口")
    @GetMapping("/{groupId}/members")
    public Result<List<GroupMember>> getGroupMembers(@PathVariable Long groupId) {
        // 需要在 Service 层实现 getGroupMembers 方法
        List<GroupMember> members = groupService.getGroupMembers(groupId);
        return Result.success(members);
    }

    // 添加拉人入群的接口
    @Operation(summary = "addMember", description = "addMember 接口")
    @PostMapping("/{groupId}/members")
    public Result addMember(@PathVariable Long groupId, @RequestBody Map<String, Object> params) {

        if (params.containsKey("userIds")) {
            Object userIdsObj = params.get("userIds");
            if (userIdsObj instanceof List) {
                List<String> userIds = (List<String>) userIdsObj;
                for (String userId : userIds) {
                    groupService.addMember(groupId, UUID.fromString(userId));
                }
                return Result.success();
            }
        }

        if (params.containsKey("userId")) {
            String userId = (String) params.get("userId");
            groupService.addMember(groupId, UUID.fromString(userId));
            return Result.success();
        }

        return Result.error("参数错误: 需要 userIds 列表或 userId 字符串");
    }
}
