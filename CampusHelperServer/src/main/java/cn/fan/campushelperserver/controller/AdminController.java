package cn.fan.campushelperserver.controller;

import cn.fan.campushelperserver.model.dao.request.AdminLoginRequest;
import cn.fan.campushelperserver.model.dao.request.CreateAdminRequest;
import cn.fan.campushelperserver.model.dao.response.ApiResponse;
import cn.fan.campushelperserver.service.intf.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequest request){
        return ResponseEntity.ok(adminService.adminLogin(request));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody CreateAdminRequest createAdminRequest){
        return ResponseEntity.ok(adminService.createAdmin(createAdminRequest));
    }

    @PostMapping("/getAdmin")
    public ResponseEntity<?> getAdmins(){
        return null;
    }
}
