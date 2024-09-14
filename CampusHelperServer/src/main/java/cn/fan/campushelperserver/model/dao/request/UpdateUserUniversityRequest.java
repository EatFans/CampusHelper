package cn.fan.campushelperserver.model.dao.request;

import lombok.Getter;

@Getter
public class UpdateUserUniversityRequest {
    private String token;
    private String university;
}
