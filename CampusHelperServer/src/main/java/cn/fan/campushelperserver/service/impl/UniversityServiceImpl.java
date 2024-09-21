package cn.fan.campushelperserver.service.impl;

import cn.fan.campushelperserver.constant.consist.ResponseStatus;
import cn.fan.campushelperserver.mapper.UniversityMapper;
import cn.fan.campushelperserver.model.dao.request.AddUniversityRequest;
import cn.fan.campushelperserver.model.dao.response.AddUniversityResponse;
import cn.fan.campushelperserver.model.entity.University;
import cn.fan.campushelperserver.service.intf.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UniversityServiceImpl 大学业务逻辑实现类
 */
@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityMapper universityMapper;

    @Autowired
    public UniversityServiceImpl(UniversityMapper universityMapper){
        this.universityMapper = universityMapper;
    }
    @Override
    public List<String> getAllSupportedUniversityNames() {
        return universityMapper.getAllUniversityNames();
    }

    @Override
    public AddUniversityResponse addUniversity(AddUniversityRequest addUniversityRequest){
        AddUniversityResponse response = new AddUniversityResponse();
        // 检查该大学是否已经存在
        if (universityMapper.checkUniversityExists(addUniversityRequest.getUniversity())){
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage("该大学已经存在！");
            return response;
        }

        University university = new University();
        // TODO: 添加单独大学信息接口待完成
        return null;
    }
}
