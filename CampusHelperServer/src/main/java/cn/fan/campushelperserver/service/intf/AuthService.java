package cn.fan.campushelperserver.service.intf;

import cn.fan.campushelperserver.model.dao.request.CheckTokenRequest;
import cn.fan.campushelperserver.model.dao.request.GetTokenRequest;

/**
 * AuthService 验证业务接口类
 */
public interface AuthService {
    String getToken(GetTokenRequest getTokenRequest);

    boolean checkToken(CheckTokenRequest checkTokenRequest);
}
