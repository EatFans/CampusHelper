package cn.fan.campushelperserver.service.impl;

import cn.fan.campushelperserver.constant.consist.ResponseStatus;
import cn.fan.campushelperserver.mapper.AdminMapper;
import cn.fan.campushelperserver.model.dao.request.AdminLoginRequest;
import cn.fan.campushelperserver.model.dao.request.CreateAdminRequest;
import cn.fan.campushelperserver.model.dao.request.GetAdminRequest;
import cn.fan.campushelperserver.model.dao.request.TokenRequest;
import cn.fan.campushelperserver.model.dao.response.ApiResponse;
import cn.fan.campushelperserver.model.entity.Admin;
import cn.fan.campushelperserver.service.intf.AdminService;
import cn.fan.campushelperserver.service.intf.RedisService;
import cn.fan.campushelperserver.util.TokenEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    private final RedisService redisService;
    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper,RedisService redisService){
        this.adminMapper = adminMapper;
        this.redisService = redisService;
    }


    @Override
    public ApiResponse adminLogin(AdminLoginRequest request){
        // 检查管理员账号是否存在
        String username = request.getUsername();
        if (!adminMapper.checkUsernameExists(username)){
            return new ApiResponse(ResponseStatus.ERROR,"不存在该账号");
        }
        // 通过username获取该用户
        Admin admin = adminMapper.findAdminByUsername(username);
        if (admin == null){
            return new ApiResponse(ResponseStatus.ERROR,"该用户不存在");
        }

        if (!request.getPassword().equalsIgnoreCase(admin.getPassword())){
            return new ApiResponse(ResponseStatus.ERROR,"密码错误");
        }

        // 生成一个登录令牌用于登录
        String token = TokenEncryptor.generateToken(admin.getUuid());
        // 将token临时存储到redis中，设置过期时间,这里是将token作为value值缓存进入redis
        redisService.set(admin.getUuid(),token,6, TimeUnit.HOURS);

        return new ApiResponse(ResponseStatus.SUCCESS,"登录成功！token过期时间为6小时",token);
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
        admin.setNickName(createAdminRequest.getNickName());
        admin.setWechat(createAdminRequest.getWechat());
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

    @Override
    public ApiResponse checkToken(TokenRequest request){
        String token = request.getToken();
        // 验证token是否有效
        if (redisService.valueExists(token))
            return new ApiResponse(ResponseStatus.SUCCESS,"token令牌有效");
        else
            return new ApiResponse(ResponseStatus.ERROR,"token令牌已经失效");
    }

    @Override
    public ApiResponse getAdminByToken(GetAdminRequest request) {
        // 检查token是否有效
        if (!redisService.valueExists(request.getToken())){
            return new ApiResponse(ResponseStatus.ERROR,"token已经失效了");
        }

        //然后通过token在redis缓存中获取管理员用户的uuid
        String uuid = redisService.getKey(request.getToken());
        // 通过uuid来查找管理员用户数据
        Admin admin = adminMapper.findAdminByUuid(uuid);
        if (admin == null){
            return new ApiResponse(ResponseStatus.ERROR,"无法获取管理员用户数据！admin为null");
        }
        return new ApiResponse(ResponseStatus.SUCCESS,"成功获取管理员用户数据",admin);
    }
}
