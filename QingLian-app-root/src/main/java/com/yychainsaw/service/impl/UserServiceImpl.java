package com.yychainsaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yychainsaw.mapper.UserMapper;
import com.yychainsaw.pojo.dto.UserUpdateDTO;
import com.yychainsaw.pojo.entity.User;
import com.yychainsaw.pojo.vo.UserVO;
import com.yychainsaw.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        // MyBatis-Plus 写法：使用 Wrapper 构造查询条件
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public void registerUser(User user) {
        if (user.getUserId() == null) {
            user.setUserId(UUID.randomUUID());
        }
        userMapper.insert(user);
    }

    @Override
    public UserVO getUserInfo(UUID userId) {
        // JPA: findById() -> MP: selectById()
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    @Transactional
    public void updateProfile(UUID userId, UserUpdateDTO dto) {
        // MyBatis-Plus 的 updateById 默认只更新非空字段
        // 所以我们不需要先查出来再 set，直接创建一个只包含要修改字段的对象即可
        User user = new User();
        user.setUserId(userId);

        // 复制属性，BeanUtils 会把 dto 里的 null 也复制过去，
        // 但 MP 的 updateById 会忽略 null 值，所以这样写是安全的，
        // 前提是 dto 里不想改的字段确实是 null
        BeanUtils.copyProperties(dto, user);

        // 如果你想更严谨，可以手动 set
        // if (dto.getNickname() != null) user.setNickname(dto.getNickname());

        userMapper.updateById(user);
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        // JPA: deleteById() -> MP: deleteById()
        userMapper.deleteById(userId);
    }

    @Override
    public void updateLastLoginTime(UUID userId) {
        User user = new User();
        user.setUserId(userId);
        user.setLastLoginTime(LocalDateTime.now());
        // 只更新 lastLoginTime 字段，其他字段不变
        userMapper.updateById(user);
    }
}
