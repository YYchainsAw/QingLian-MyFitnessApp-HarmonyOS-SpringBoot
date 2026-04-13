package com.yychainsaw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yychainsaw.pojo.entity.User;
import com.yychainsaw.pojo.vo.UserSocialDashboardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;
import java.util.UUID;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    UserSocialDashboardVO selectUserSocialDashboard(UUID userId);
}
