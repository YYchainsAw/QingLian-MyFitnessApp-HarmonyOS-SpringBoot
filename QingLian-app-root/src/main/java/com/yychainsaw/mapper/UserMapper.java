package com.yychainsaw.mapper;

import com.yychainsaw.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT user_id, username, password_hash, nickname, avatar_url, gender, " +
            "       height_cm, weight_kg, created_at " +
            "FROM users WHERE username = #{username}")
    User findByUsername(String username);
}
