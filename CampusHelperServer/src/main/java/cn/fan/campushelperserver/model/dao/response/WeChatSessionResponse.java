package cn.fan.campushelperserver.model.dao.response;

import lombok.Getter;

@Getter
public class WeChatSessionResponse {
    private String openid;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;
}
