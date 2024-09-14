package cn.fan.campushelperserver.model.dao.request;

import lombok.Getter;

/**
 * 验证Token请求的数据对象
 */
@Getter
public class CheckTokenRequest {
    private String token;
}
