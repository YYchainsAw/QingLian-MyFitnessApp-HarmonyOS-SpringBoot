package com.yychainsaw.service.impl;

import com.yychainsaw.mapper.AuthMapper;
import com.yychainsaw.service.AuthService;
import com.yychainsaw.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;

    @Override
    public void register(String username, String password, String nickname) {
        String md5String = Md5Util.getMD5String(password);

        authMapper.register(username, md5String, nickname);
    }
}
