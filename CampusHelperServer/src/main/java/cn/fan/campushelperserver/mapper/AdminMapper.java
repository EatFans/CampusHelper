package cn.fan.campushelperserver.mapper;

import cn.fan.campushelperserver.model.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    void insertAdmin(Admin admin);

    boolean checkUsernameExists(String username);

    boolean checkPhoneNumberExists(String phoneNumber);

    boolean checkRealNameExists(String realName);

    boolean checkUuidExists(String uuid);
}
