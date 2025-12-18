package com.yychainsaw.service.impl;

import com.yychainsaw.pojo.dto.UserLoginDTO;
import com.yychainsaw.pojo.dto.UserRegisterDTO;
import com.yychainsaw.pojo.entity.User;
import com.yychainsaw.pojo.vo.TokenVO;
import com.yychainsaw.pojo.vo.UserVO;
import com.yychainsaw.service.AuthService;
import com.yychainsaw.service.UserService;
import com.yychainsaw.utils.JwtUtil;
import com.yychainsaw.utils.Md5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public void register(UserRegisterDTO dto) {
        if (userService.findByUsername(dto.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }

        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setPasswordHash(Md5Util.getMD5String(dto.getPassword()));
        user.setGender("OTHER");

        userService.registerUser(user);
    }

    @Override
    public TokenVO login(UserLoginDTO loginDTO) {
        User user = userService.findByUsername(loginDTO.getUsername());

        if (user == null || !user.getPasswordHash().equals(Md5Util.getMD5String(loginDTO.getPassword()))) {
            throw new RuntimeException("用户名或密码错误");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", String.valueOf(user.getUserId()));
        claims.put("username", user.getUsername());
        String token = JwtUtil.genToken(claims);

        stringRedisTemplate.opsForValue().set(token, token, 60 * 60 * 24 * 30);

        userService.updateLastLoginTime(user.getUserId());

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        return new TokenVO(token, userVO);
    }

    @Override
    public void logout(String token) {
        stringRedisTemplate.delete(token);
    }

    @Override
    public String refreshToken(String oldToken) {
        String value = stringRedisTemplate.opsForValue().get(oldToken);
        if (value == null) {
            throw new RuntimeException("无效的token");
        }

        Map<String, Object> oldClaims = JwtUtil.parseToken(oldToken);
        Map<String, Object> newClaims = new HashMap<>();

        if (oldClaims.get("id") != null) {
            newClaims.put("id", oldClaims.get("id").toString());
        }
        if (oldClaims.get("username") != null) {
            newClaims.put("username", oldClaims.get("username").toString());
        }

        String newToken = JwtUtil.genToken(newClaims);

        stringRedisTemplate.delete(oldToken);
        stringRedisTemplate.opsForValue().set(newToken, newToken, 60 * 60 * 24 * 30);

        return newToken;
    }
}
