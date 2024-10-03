package cn.fan.campushelperserver.service.intf;

import cn.fan.campushelperserver.model.dao.request.AddUniversityRequest;
import cn.fan.campushelperserver.model.dao.response.AddUniversityResponse;
import cn.fan.campushelperserver.model.entity.University;

import java.util.List;

/**
 * UniversityService 大学业务接口类
 */
public interface UniversityService {
    List<String> getAllSupportedUniversityNames();

    AddUniversityResponse addUniversity(AddUniversityRequest addUniversityRequest);

    List<University> getUniversity();
}
