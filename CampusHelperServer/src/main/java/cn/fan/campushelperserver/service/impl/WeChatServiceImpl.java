package cn.fan.campushelperserver.service.impl;

import cn.fan.campushelperserver.model.dao.WeChatSessionResponse;
import cn.fan.campushelperserver.service.intf.WeChatService;
import cn.fan.campushelperserver.util.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeChatServiceImpl implements WeChatService {

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.appsecret}")
    private String appSecret;
    private final RestTemplate restTemplate;

    @Autowired
    public WeChatServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public WeChatSessionResponse getWeChatSession(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        // 发起 GET 请求并将结果解析为 WeChatSessionResponse
        try {
            // 获取响应为字符串
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String responseBody = response.getBody();

            // 将字符串解析为 WeChatSessionResponse 对象
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(responseBody, WeChatSessionResponse.class);

        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }
}
