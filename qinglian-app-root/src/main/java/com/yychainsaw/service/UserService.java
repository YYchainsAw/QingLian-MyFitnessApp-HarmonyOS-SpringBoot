package com.yychainsaw.service;

import com.yychainsaw.pojo.dto.UserUpdateDTO;
import com.yychainsaw.pojo.entity.User;
import com.yychainsaw.pojo.vo.UserSocialDashboardVO;
import com.yychainsaw.pojo.vo.UserVO;
import org.hibernate.validator.constraints.URL;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;
import java.util.UUID;


public interface UserService {
    User findByUsername(String username);

    void registerUser(User user);// 仅供 AuthService 调用底层保存

    @Cacheable(value = "user_info", key = "#userId")
    UserVO getUserInfo();

    @CacheEvict(value = "user_info", key = "#updateDTO.id")
    void updateProfile(UserUpdateDTO userUpdateDTO);

    void deleteUser();

    void updateLastLoginTime(UUID userId);

    List<UserVO> searchUsers(String trim);

    List<Map<String, Object>> getGenderWeightStats();

    UserSocialDashboardVO getUserSocialDashboard();

    @CacheEvict(value = "user_info", key = "#updateDTO.id")
    void updateAvatar(@URL String avatarUrl);
}
