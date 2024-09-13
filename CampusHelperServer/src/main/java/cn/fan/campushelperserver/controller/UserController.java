package cn.fan.campushelperserver.controller;

import cn.fan.campushelperserver.constant.consist.ResponseStatus;
import cn.fan.campushelperserver.model.dao.ApiResponse;
import cn.fan.campushelperserver.model.dao.CheckUserRequest;
import cn.fan.campushelperserver.model.dao.GetTokenRequest;
import cn.fan.campushelperserver.model.entity.User;
import cn.fan.campushelperserver.service.intf.UserService;
import com.google.protobuf.Api;
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


    @PostMapping("/checkUser")
    public ResponseEntity<?> checkUser(@RequestBody CheckUserRequest checkUserRequest){
        User user = userService.checkUser(checkUserRequest);
        if (user != null){
            return ResponseEntity.ok(new ApiResponse(ResponseStatus.SUCCESS,"用户验证成功！",user));
        } else {
            return ResponseEntity.ok(new ApiResponse(ResponseStatus.ERROR,"用户验证失败！"));
        }
    }
}
