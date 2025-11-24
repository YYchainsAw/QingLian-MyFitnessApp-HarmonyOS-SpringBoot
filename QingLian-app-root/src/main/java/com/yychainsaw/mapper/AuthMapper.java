package com.yychainsaw.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    @Insert("INSERT INTO users (username, password_hash, nickname) " +
            "VALUES (#{username}, #{password}, #{nickname})")
    void register(String username, String password, String nickname);
}
