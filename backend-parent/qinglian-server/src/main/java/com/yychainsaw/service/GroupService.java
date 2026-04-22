package com.yychainsaw.service;

import com.yychainsaw.dto.GroupCreateDTO;
import com.yychainsaw.entity.ChatGroup;
import com.yychainsaw.entity.GroupMember;
import com.yychainsaw.vo.GroupListVO;

import java.util.List;
import java.util.UUID;

public interface GroupService {
    ChatGroup createGroup(GroupCreateDTO dto);

    // 在接口中添加方法定义
    void addMember(Long groupId, UUID userId);

    List<GroupMember> getGroupMembers(Long groupId);

    List<GroupListVO> getUserGroups();
}
