package cn.fan.campushelperserver.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UniversityMapper 接口类，是university数据表Mapper映射
 */
@Mapper
public interface UniversityMapper {
    /**
     * 查询获取所有学校的名称
     * @return 返回所有学校的名称，类型为List<String>
     */
    List<String> getAllUniversityNames();
}
