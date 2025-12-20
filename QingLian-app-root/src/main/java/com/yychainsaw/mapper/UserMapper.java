package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yychainsaw.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.UUID;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    Map<String, Object> selectUserSocialDashboard(UUID userId);
}
