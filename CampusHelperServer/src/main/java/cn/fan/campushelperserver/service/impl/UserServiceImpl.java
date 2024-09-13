package cn.fan.campushelperserver.service.impl;

import cn.fan.campushelperserver.mapper.UserMapper;
import cn.fan.campushelperserver.model.dao.CheckUserRequest;
import cn.fan.campushelperserver.model.dao.WeChatSessionResponse;
import cn.fan.campushelperserver.model.entity.User;
import cn.fan.campushelperserver.service.intf.UserService;
import cn.fan.campushelperserver.service.intf.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * UserServiceImpl 用户业务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    private final WeChatService weChatService;
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(WeChatService weChatService,
                           UserMapper userMapper){
        this.weChatService = weChatService;
        this.userMapper = userMapper;
    }

    @Override
    public User checkUser(CheckUserRequest checkUserRequest) {
        // 通过前端传输过来的code，去请求访问微信服务，获取用户的openId
        WeChatSessionResponse weChatSession = weChatService.getWeChatSession(checkUserRequest.getCode());
        String openid = weChatSession.getOpenid();
        return userMapper.findUserByOpenId(openid);

    }
}
