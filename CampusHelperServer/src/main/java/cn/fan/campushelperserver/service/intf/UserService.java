package cn.fan.campushelperserver.service.intf;

import cn.fan.campushelperserver.model.dao.CheckUserRequest;
import cn.fan.campushelperserver.model.dao.CreateUserRequest;
import cn.fan.campushelperserver.model.dao.GetTokenRequest;
import cn.fan.campushelperserver.model.entity.User;

/**
 * UserService 用户业务的接口类
 */
public interface UserService {
    User checkUser(String code);

    User createUser(String code);

    User getUser(String token);
}
