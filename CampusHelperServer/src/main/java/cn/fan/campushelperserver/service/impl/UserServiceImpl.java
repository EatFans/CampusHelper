package cn.fan.campushelperserver.service.impl;

import cn.fan.campushelperserver.model.dao.GetTokenRequest;
import cn.fan.campushelperserver.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * UserServiceImpl 用户业务实现类
 */
@Service
public class UserServiceImpl implements UserService {

//    @Autowired
    public UserServiceImpl(){

    }

    @Override
    public String getToken(GetTokenRequest getTokenRequest){
        System.out.println(getTokenRequest.getCode());
        // 前端小程序发送过来code，在这里将code请求发送给微信服务器进行验证获取openid和会话密钥

        return "Hello";
    }
}
