package cn.fan.campushelperserver.model.dao.request;

import lombok.Data;
import lombok.Getter;

/**
 *  GetTokenRequest 获取token请求传输数据对象
 */
@Data
public class GetTokenRequest {
    private String code;
}
