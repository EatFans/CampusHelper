package cn.fan.campushelperserver.mapper;

import cn.fan.campushelperserver.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findUserByOpenId(String openId);
}
