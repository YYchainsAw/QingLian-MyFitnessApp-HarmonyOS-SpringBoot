package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yychainsaw.entity.User;
import com.yychainsaw.vo.UserSocialDashboardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.UUID;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    UserSocialDashboardVO selectUserSocialDashboard(UUID userId);
}
