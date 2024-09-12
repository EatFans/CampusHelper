package cn.fan.campushelperserver.service.impl;

import cn.fan.campushelperserver.mapper.UniversityMapper;
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
}
