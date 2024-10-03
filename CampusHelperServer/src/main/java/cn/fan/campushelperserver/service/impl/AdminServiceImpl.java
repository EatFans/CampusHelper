package cn.fan.campushelperserver.service.impl;

import cn.fan.campushelperserver.constant.consist.ResponseStatus;
import cn.fan.campushelperserver.mapper.AdminMapper;
import cn.fan.campushelperserver.model.dao.request.CreateAdminRequest;
import cn.fan.campushelperserver.model.dao.response.ApiResponse;
import cn.fan.campushelperserver.model.entity.Admin;
import cn.fan.campushelperserver.service.intf.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper){
        this.adminMapper = adminMapper;
    }


    @Override
    public ApiResponse login(){
        // 检查管理员账号是否存在

        // 检查管理员密码是否正确

        // 生存一个登录令牌用于登录


        return new ApiResponse(ResponseStatus.SUCCESS,"");
    }

    @Override
    public ApiResponse createAdmin(CreateAdminRequest createAdminRequest){
        Admin admin = new Admin();
        // 检查username是否存在
        if (adminMapper.checkUsernameExists(createAdminRequest.getUsername())){
            return new ApiResponse(ResponseStatus.ERROR,"该管理用户已经存在");
        }

        // 检查phoneNumber是否存在
        if (adminMapper.checkPhoneNumberExists(createAdminRequest.getPhoneNumber())){
            return new ApiResponse(ResponseStatus.ERROR,"该手机号已经被使用");
        }

        // 检查realName是否已经存在
        if (adminMapper.checkRealNameExists(createAdminRequest.getRealName())){
            return new ApiResponse(ResponseStatus.ERROR,"该用户已经存在");
        }

        admin.setUsername(createAdminRequest.getUsername());
        admin.setPassword(createAdminRequest.getPassword());
        admin.setPhoneNumber(createAdminRequest.getPhoneNumber());
        admin.setRealName(createAdminRequest.getRealName());
        // 设置默认角色身份
        admin.setRole("普通管理员");

        // 检查uuid是否存在，生成uuid
        String uuid = generateUniqueUUID();
        admin.setUuid(uuid);


        // 将admin数据对象插入保存到数据库中
        adminMapper.insertAdmin(admin);

        return new ApiResponse(ResponseStatus.SUCCESS, "成功注册新的管理用户",admin);
    }

    private String generateUniqueUUID() {
        String uniqueUUID;
        boolean isDuplicate = true;

        do {
            // 生成 36 位的 UUID
            uniqueUUID = UUID.randomUUID().toString();

            // 检查数据库中是否已经存在该 UUID
            // 如果不存在，则说明 UUID 是唯一的
            if (!adminMapper.checkUuidExists(uniqueUUID)) {
                isDuplicate = false;
            }

        } while (isDuplicate);

        return uniqueUUID;
    }
}
