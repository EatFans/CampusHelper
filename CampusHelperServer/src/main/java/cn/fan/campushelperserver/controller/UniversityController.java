package cn.fan.campushelperserver.controller;

import cn.fan.campushelperserver.service.intf.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
