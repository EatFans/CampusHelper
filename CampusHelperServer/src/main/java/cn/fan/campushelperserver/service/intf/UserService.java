package cn.fan.campushelperserver.service.intf;

import cn.fan.campushelperserver.model.dao.GetTokenRequest;

/**
 * UserService 用户业务的接口类
 */
public interface UserService {
    String getToken(GetTokenRequest getTokenRequest);
}
