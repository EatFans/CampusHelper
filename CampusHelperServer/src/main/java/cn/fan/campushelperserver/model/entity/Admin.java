package cn.fan.campushelperserver.model.entity;

import lombok.Data;

@Data
public class Admin {
    private String uuid;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String realName;
    private String role;
}
