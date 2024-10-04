package cn.fan.campushelperserver.service.impl;

import cn.fan.campushelperserver.constant.consist.ResponseStatus;
import cn.fan.campushelperserver.mapper.UniversityMapper;
import cn.fan.campushelperserver.model.dao.request.AddUniversityRequest;
import cn.fan.campushelperserver.model.dao.response.AddUniversityResponse;
import cn.fan.campushelperserver.model.entity.University;
import cn.fan.campushelperserver.service.intf.UniversityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
        // 设置university数据对象
        university.setUniversity(addUniversityRequest.getUniversity());
        university.setProvince(addUniversityRequest.getProvince());
        university.setCity(addUniversityRequest.getCity());
        university.setUpdatedByName(addUniversityRequest.getUpdatedByName());
        university.setUpdatedByUuid(addUniversityRequest.getUpdatedByUuid());
        university.setUpdatedAt(addUniversityRequest.getUpdatedAt());
        // 将university数据对象插入到数据库中
        universityMapper.insertUniversity(university);
        // 检查university数据对象是否成功插入数据库中
        if (universityMapper.checkUniversityExists(university.getUniversity())){
            response.setStatus(ResponseStatus.SUCCESS);
            response.setMessage("新的大学数据已经添加保存!");
            response.setData(university);
            return response;
        } else {
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage("新的大学数据保存失败!");
            return response;
        }

    }



    @Override
    public Page<University> getUniversities(int page, int size){
        PageHelper.startPage(page,size);
        List<University> universityList = universityMapper.findAll();
        return (Page<University>) universityList;
    }

    @Override
    public int getUniversityAmount() {
        return universityMapper.getUniversityAmount();
    }
}
