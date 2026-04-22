package com.yychainsaw.service;

import com.yychainsaw.dto.UserLoginDTO;
import com.yychainsaw.dto.UserRegisterDTO;
import com.yychainsaw.vo.TokenVO;

public interface AuthService {
    void register(UserRegisterDTO userRegisterDTO);

    TokenVO login(UserLoginDTO loginDTO);

    void logout(String token);

    String refreshToken(String oldToken);
}
