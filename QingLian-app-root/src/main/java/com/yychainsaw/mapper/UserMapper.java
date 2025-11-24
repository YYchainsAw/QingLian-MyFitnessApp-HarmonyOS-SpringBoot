package com.yychainsaw.mapper;

import com.yychainsaw.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * " +
            "FROM users WHERE username = #{username}")
    User findByUsername(String username);
}
