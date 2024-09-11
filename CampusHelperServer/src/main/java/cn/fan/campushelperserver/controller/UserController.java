package cn.fan.campushelperserver.controller;

import cn.fan.campushelperserver.model.dao.GetTokenRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * UserController类是作为相关用户数据操作的控制器类
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/getToken")
    public ResponseEntity<?> getToken(@RequestBody GetTokenRequest getTokenRequest){

        return ResponseEntity.ok("Hello World");
    }
}
