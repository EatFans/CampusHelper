package cn.fan.campushelperserver.service.impl;

import cn.fan.campushelperserver.model.dao.CheckTokenRequest;
import cn.fan.campushelperserver.model.dao.GetTokenRequest;
import cn.fan.campushelperserver.model.dao.WeChatSessionResponse;
import cn.fan.campushelperserver.service.intf.AuthService;
import cn.fan.campushelperserver.service.intf.RedisService;
import cn.fan.campushelperserver.service.intf.WeChatService;
import cn.fan.campushelperserver.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * AuthServiceImpl 验证业务实现类
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final WeChatService weChatService;
    private final RedisService redisService;

    @Autowired
    public AuthServiceImpl(WeChatService weChatService,RedisService redisService){
        this.weChatService = weChatService;
        this.redisService = redisService;
    }

    @Override
    public String getToken(GetTokenRequest getTokenRequest){
        Logger.info("接受getToken请求，code："+getTokenRequest.getCode());
        // 前端小程序发送过来code，在这里将code请求发送给微信服务器进行验证获取openid和会话密钥
        WeChatSessionResponse weChatSession = weChatService.getWeChatSession(getTokenRequest.getCode());
        // 将openId和sessionKey关联起来
        String openid = weChatSession.getOpenid();
        String sessionKey = weChatSession.getSession_key();

        String userAuthInfo = openid + "|" + sessionKey;

        // 生成一个token
        String token = UUID.randomUUID().toString();
        // redis存储token-userinfo
        redisService.set(token,userAuthInfo,7,TimeUnit.DAYS);
        Logger.info("已经存储一个key为 "+token+" value为 "+userAuthInfo+" 到Redis中");
        return token;
    }

    @Override
    public boolean checkToken(CheckTokenRequest checkTokenRequest){
        Logger.info("接受CheckToken请求，验证Token: "+checkTokenRequest.getToken());
        if (redisService.exists(checkTokenRequest.getToken())){
            Logger.info("Token: "+checkTokenRequest.getToken()+" 验证成功");
            return true;
        } else {
            Logger.info("Token: "+checkTokenRequest.getToken()+" 验证失败");
            return false;
        }
    }
}
