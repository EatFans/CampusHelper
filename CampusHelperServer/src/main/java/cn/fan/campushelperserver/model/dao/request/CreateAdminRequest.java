package cn.fan.campushelperserver.model.dao.request;

import lombok.Getter;

@Getter
public class CreateAdminRequest {
    private String username;
    private String password;
    private String nickName;
    private String wechat;
    private String email;
    private String phoneNumber;
    private String realName;
    private String role;
}
