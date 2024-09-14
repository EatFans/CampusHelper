package cn.fan.campushelperserver.mapper;

import cn.fan.campushelperserver.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(User user);
    User findUserByOpenId(String openId);

    boolean checkUuidExists(String uuid);

    int getUserCount();

    boolean checkNameExists(String name);

    boolean updateUniversity(String openId, String university);

    boolean updateNickname(String openId, String nickname);

    boolean updateAvatarUrl(String openId, String avatarUrl);

    String getUniversity(String openId);

}
