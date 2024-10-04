package cn.fan.campushelperserver.controller;

import cn.fan.campushelperserver.model.dao.request.GetUniversitiesRequest;
import cn.fan.campushelperserver.model.dao.response.AddUniversityResponse;
import cn.fan.campushelperserver.model.dao.request.AddUniversityRequest;
import cn.fan.campushelperserver.model.entity.University;
import cn.fan.campushelperserver.service.intf.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> getUniversitiesByPage(@RequestBody GetUniversitiesRequest request){
        Page<University> universityPage = universityService.getUniversities(request.getPage(), request.getSize());

        // 构造返回的数据
        Map<String, Object> response = new HashMap<>();
        response.put("total",universityPage.getTotal());
        response.put("pages",universityPage.getPages());
        response.put("currentPage",universityPage.getPageNum());
        response.put("pageSize",universityPage.getPageSize());
        response.put("universities",universityPage.getResult());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUniversityAmount")
    public int getUniversityAmount(){
        return universityService.getUniversityAmount();
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUniversity(@RequestBody University university){
        return ResponseEntity.ok(universityService.deleteUniversity(university));
    }
}
