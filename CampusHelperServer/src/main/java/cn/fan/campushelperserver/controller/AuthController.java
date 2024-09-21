package cn.fan.campushelperserver.controller;

import cn.fan.campushelperserver.constant.consist.ResponseStatus;
import cn.fan.campushelperserver.model.dao.response.ApiResponse;
import cn.fan.campushelperserver.model.dao.request.CheckTokenRequest;
import cn.fan.campushelperserver.model.dao.request.GetTokenRequest;
import cn.fan.campushelperserver.service.intf.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/getToken")
    public ResponseEntity<?> getToken(@RequestBody GetTokenRequest getTokenRequest){
        String token = authService.getToken(getTokenRequest);
        return ResponseEntity.ok(new ApiResponse(ResponseStatus.SUCCESS,"成功获取token",token));
    }

    @PostMapping("/checkToken")
    public ResponseEntity<?> checkToken(@RequestBody CheckTokenRequest checkTokenRequest){
        // 检查Token是否有效
        if (authService.checkToken(checkTokenRequest)){
            // 如果token有效
            return ResponseEntity.ok(new ApiResponse(ResponseStatus.SUCCESS,"Token验证成功"));
        } else {
            // 如果token无效
            return ResponseEntity.ok(new ApiResponse(ResponseStatus.ERROR,"Token验证失败"));
        }
    }
}
