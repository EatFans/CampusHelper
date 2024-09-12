package cn.fan.campushelperserver.controller;

import cn.fan.campushelperserver.model.dao.GetTokenRequest;
import cn.fan.campushelperserver.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * UserController类是作为相关用户数据操作的控制器类
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


}
