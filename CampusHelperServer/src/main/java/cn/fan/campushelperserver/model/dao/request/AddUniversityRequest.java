package cn.fan.campushelperserver.model.dao.request;

import lombok.Getter;

import java.util.Date;

@Getter
public class AddUniversityRequest {
    private String university;
    private String province;
    private String city;
    private String updatedByName;
    private String updatedByUuid;
    private Date updatedAt;
}
