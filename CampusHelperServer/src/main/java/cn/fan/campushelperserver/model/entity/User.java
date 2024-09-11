package cn.fan.campushelperserver.model.entity;

import lombok.Data;

@Data
public class User {
    private String uuid;
    private String openId;
    private String username;
    private String avatarUrl;
    private String phoneNumber;
    private String university;
    private String Location;

}
