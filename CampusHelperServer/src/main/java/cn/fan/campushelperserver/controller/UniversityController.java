package cn.fan.campushelperserver.controller;

import cn.fan.campushelperserver.constant.consist.ResponseStatus;
import cn.fan.campushelperserver.model.dao.response.AddUniversityResponse;
import cn.fan.campushelperserver.model.dao.request.AddUniversityRequest;
import cn.fan.campushelperserver.model.dao.response.ApiResponse;
import cn.fan.campushelperserver.service.intf.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {
    private final UniversityService universityService;
    @Autowired
    public UniversityController(UniversityService universityService){
        this.universityService = universityService;
    }

    @GetMapping("/getAllSupportedUniversities")
    public List<String> getAllSupportedUniversities(){
        return universityService.getAllSupportedUniversityNames();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUniversity(@RequestBody AddUniversityRequest addUniversityRequest){
        // 将请求体的数据传递给UniversityService业务类中，处理完返回是否成功执行
        AddUniversityResponse addUniversityResponse = universityService.addUniversity(addUniversityRequest);
        return ResponseEntity.ok(addUniversityResponse);
    }

    @PostMapping("/getUniversities")
    public ResponseEntity<?> getUniversitiesByPage(@RequestParam int page, @RequestParam int size){

        return ResponseEntity.ok(new ApiResponse(ResponseStatus.SUCCESS, "测试接口"));
    }

}
