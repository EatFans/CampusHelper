package cn.fan.campushelperserver.service.intf;

import cn.fan.campushelperserver.model.dao.WeChatSessionResponse;

public interface WeChatService {
    WeChatSessionResponse getWeChatSession(String code);
}
