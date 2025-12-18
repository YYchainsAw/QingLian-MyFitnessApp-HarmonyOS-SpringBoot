package com.yychainsaw.service.impl;

import com.yychainsaw.pojo.dto.UserUpdateDTO;
import com.yychainsaw.pojo.entity.User;
import com.yychainsaw.pojo.vo.UserVO;
import com.yychainsaw.repository.UserRepository; // 假设你有 Repository
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
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserVO getUserInfo(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    @Transactional
    public void updateProfile(UUID userId, UserUpdateDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        // 使用 BeanUtils 或手动 set 非空值
        if (dto.getNickname() != null) user.setNickname(dto.getNickname());
        if (dto.getAvatarUrl() != null) user.setAvatarUrl(dto.getAvatarUrl());
        if (dto.getGender() != null) user.setGender(dto.getGender());
        if (dto.getHeight() != null) user.setHeight(dto.getHeight());
        if (dto.getWeight() != null) user.setWeight(dto.getWeight());

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        // 实际项目中通常做软删除（update status = 'DELETED'）
        userRepository.deleteById(userId);
    }

    @Override
    public void updateLastLoginTime(UUID userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setLastLoginTime(LocalDateTime.now());
            userRepository.save(user);
        }
    }
}