package cn.fan.campushelperserver.service.intf;

import cn.fan.campushelperserver.model.entity.User;

/**
 * UserService 用户业务的接口类
 */
public interface UserService {
    User checkUser(String code);

    User createUser(String code);

    User getUser(String token);

    boolean updateUserUniversity(String token, String university);

    boolean updateUserNickname(String token, String nickname);

    boolean updateUserAvatarUrl(String token, String avatarUrl);

    String getUserUniversity(String token);

    String getUserAvatarUrl(String token);
}
