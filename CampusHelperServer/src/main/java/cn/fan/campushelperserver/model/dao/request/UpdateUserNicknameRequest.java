package cn.fan.campushelperserver.model.dao.request;

import lombok.Getter;

@Getter
public class UpdateUserNicknameRequest {
    private String token;
    private String nickname;
}
