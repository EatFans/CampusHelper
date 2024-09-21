package cn.fan.campushelperserver.model.dao.response;

import lombok.Data;

@Data
public class AddUniversityResponse {
    private String status;
    private String message;
    private Object data;
}
