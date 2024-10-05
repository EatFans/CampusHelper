package cn.fan.campushelperserver.service.intf;

import cn.fan.campushelperserver.model.dao.request.AddUniversityRequest;
import cn.fan.campushelperserver.model.dao.response.AddUniversityResponse;
import cn.fan.campushelperserver.model.dao.response.ApiResponse;
import cn.fan.campushelperserver.model.entity.University;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * UniversityService 大学业务接口类
 */
public interface UniversityService {
    List<String> getAllSupportedUniversityNames();

    AddUniversityResponse addUniversity(AddUniversityRequest addUniversityRequest);
    Page<University> getUniversities(int page, int size);

    int getUniversityAmount();

    ApiResponse deleteUniversity(University university);
}
