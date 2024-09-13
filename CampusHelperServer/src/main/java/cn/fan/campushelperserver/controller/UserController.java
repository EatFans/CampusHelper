package cn.fan.campushelperserver.controller;

import cn.fan.campushelperserver.constant.consist.ResponseStatus;
import cn.fan.campushelperserver.model.dao.*;
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
        User user = userService.checkUser(checkUserRequest.getCode());
        if (user != null){
            return ResponseEntity.ok(new ApiResponse(ResponseStatus.SUCCESS,"用户验证成功！",user));
        } else {
            return ResponseEntity.ok(new ApiResponse(ResponseStatus.ERROR,"用户验证失败！"));
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest){
        User user = userService.createUser(createUserRequest.getCode());
        if (user != null){
            return ResponseEntity.ok(new ApiResponse(ResponseStatus.SUCCESS,"用户创建正常!",user));
        } else {
            return ResponseEntity.ok(new ApiResponse(ResponseStatus.ERROR,"用户创建失败！"));
        }
    }

    @PostMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestBody GetUserRequest getUserRequest){
        User user = userService.getUser(getUserRequest.getToken());
        if (user != null){
            return ResponseEntity.ok(new ApiResponse(ResponseStatus.SUCCESS,"获取用户成功！",user));
        } else {
            return ResponseEntity.ok(new ApiResponse(ResponseStatus.ERROR,"获取用户失败，可能Token令牌已经失效！"));
        }
    }
}
