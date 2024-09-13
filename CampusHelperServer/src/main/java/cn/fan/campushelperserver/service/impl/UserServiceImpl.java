package cn.fan.campushelperserver.service.impl;

import cn.fan.campushelperserver.mapper.UserMapper;
import cn.fan.campushelperserver.model.dao.CheckUserRequest;
import cn.fan.campushelperserver.model.dao.WeChatSessionResponse;
import cn.fan.campushelperserver.model.entity.User;
import cn.fan.campushelperserver.service.intf.RedisService;
import cn.fan.campushelperserver.service.intf.UserService;
import cn.fan.campushelperserver.service.intf.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * UserServiceImpl 用户业务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    private final WeChatService weChatService;
    private final UserMapper userMapper;
    private final RedisService redisService;
    @Autowired
    public UserServiceImpl(WeChatService weChatService,
                           UserMapper userMapper,
                           RedisService redisService){
        this.weChatService = weChatService;
        this.userMapper = userMapper;
        this.redisService = redisService;
    }

    @Override
    public User checkUser(String code) {
        // 通过前端传输过来的code，去请求访问微信服务，获取用户的openId
        WeChatSessionResponse weChatSession = weChatService.getWeChatSession(code);
        if (weChatSession == null){
            return null;
        }
        String openid = weChatSession.getOpenid();
        return userMapper.findUserByOpenId(openid);

    }

    @Override
    public User createUser(String code) {
        WeChatSessionResponse weChatSession = weChatService.getWeChatSession(code);
        if (weChatSession == null){
            return null;
        }
        User user = new User();
        String openid = weChatSession.getOpenid();
        String uuid = UUID.randomUUID().toString();
        int userCount = userMapper.getUserCount();
        String username = "微信用户" + (userCount + 1 );

        if (openid == null){
            return null;
        }

        // 检查UUID是否已经存在数据库中
        if (!userMapper.checkUuidExists(uuid)){
            // 检查用户名是否已经被用了
            if (!userMapper.checkNameExists(username)){
                user.setUuid(uuid);
                user.setOpenId(openid);
                user.setAvatarUrl("https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0");
                user.setNickname(username);

                userMapper.insertUser(user);

            }

        }

        return user;
    }

    /**
     * 获取User
     * @param token 登录状态令牌
     * @return 如果返回null，说明登录状态令牌可能失效了
     */
    @Override
    public User getUser(String token){
        // 通过token从Redis中获取到
        // 检查token是否有效，是否还在redis中，如果不在就直接return null
        if (!redisService.exists(token)){
            return null;
        }
        String userAuthInfo = (String) redisService.get(token);
        String[] strings = userAuthInfo.split("\\|");
        String openId = strings[0];

        User user = userMapper.findUserByOpenId(openId);
        if (user == null){
            return null;
        }

        return user;
    }

    @Override
    public boolean updateUserUniversity(String token, String university){
        if (!redisService.exists(token)){
            return false;
        }
        String userAuthInfo = (String) redisService.get(token);
        String[] strings = userAuthInfo.split("\\|");
        String openId = strings[0];

        return userMapper.updateUniversity(openId,university);
    }


}
