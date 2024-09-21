package cn.fan.campushelperserver.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class University {
    private int id;  // 学校id
    private String university; // 学校名称
    private String province;  // 学校所在的省份
    private String city;    // 学校所在的城市
    private String updatedByName; // 更新的管理员名称
    private String updatedByUuid; // 更新的管理员uuid
    private Date updatedAt;  // 更新时间
}
