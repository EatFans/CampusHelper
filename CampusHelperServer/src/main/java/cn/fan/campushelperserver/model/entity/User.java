package cn.fan.campushelperserver.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String uuid;  // 用户uuid，唯一的，作为主键，做索引
    private String openId; // 用户微信openId  唯一的，做索引
    private String avatarUrl;  // 用户头像url地址
    private String nickname;  // 用户昵称
    private String gender;    // 用户性别
    private Date birthday;      // 用户生日
    private String phoneNumber; // 用户手机号
    private String realName;    // 真实名字
    private String university;  // 用户大学
    private boolean isOrderTaker;  // 是不是接单员
    private boolean hasRealNameAuthentication;  // 是否已经实名认证
    private String status;   // 用户账号的状态
    private Date createTime;    // 用户创建时间
    private Date lastLoginTime;     // 上次登录上线的时间

}
