package cn.fan.campushelperserver.service.intf;

import cn.fan.campushelperserver.model.dao.request.CreateAdminRequest;
import cn.fan.campushelperserver.model.dao.response.ApiResponse;

public interface AdminService {
    public ApiResponse login();

    public ApiResponse createAdmin(CreateAdminRequest createAdminRequest);
}
