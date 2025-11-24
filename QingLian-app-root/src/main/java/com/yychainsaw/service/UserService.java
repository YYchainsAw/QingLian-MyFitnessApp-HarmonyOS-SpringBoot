package com.yychainsaw.service;

import com.yychainsaw.pojo.User;

public interface UserService {
    User findByUsername(String username);
}
