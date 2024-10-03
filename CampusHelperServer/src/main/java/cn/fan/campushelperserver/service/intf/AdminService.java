package cn.fan.campushelperserver.service.intf;

import cn.fan.campushelperserver.model.dao.request.AdminLoginRequest;
import cn.fan.campushelperserver.model.dao.request.CreateAdminRequest;
import cn.fan.campushelperserver.model.dao.response.ApiResponse;

public interface AdminService {
    ApiResponse adminLogin(AdminLoginRequest request);

    ApiResponse createAdmin(CreateAdminRequest createAdminRequest);
}
