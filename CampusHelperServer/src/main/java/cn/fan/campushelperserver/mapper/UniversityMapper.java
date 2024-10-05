package cn.fan.campushelperserver.mapper;

import cn.fan.campushelperserver.model.entity.University;
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

    /**
     * 检查是否存在该大学
     * @param university 大学名称
     * @return 返回最后查询的结果
     */
    boolean checkUniversityExists(String university);

    /**
     * 插入一条大学信息数据
     * @param university 大学信息
     */
    void insertUniversity(University university);

    List<University> findAll();

    int getUniversityAmount();

    /**
     * 删除大学
     * @param id 大学id编号
     */
    void deleteUniversity(int id);

    /**
     * 更新自增键
     * @param id id
     */
    void adjustId(int id);

    /**
     * 获取最大的id
     * @return 返回id
     */
    int findMaxId();

    void resetAutoIncrement(int newAutoIncrementValue);
}
