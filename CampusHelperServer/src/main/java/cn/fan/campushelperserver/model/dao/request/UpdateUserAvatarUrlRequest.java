package cn.fan.campushelperserver.model.dao.request;

import lombok.Getter;

@Getter
public class UpdateUserAvatarUrlRequest {
    private String token;
    private String avatarUrl;
}
