package cn.kcs.user.dao;

import cn.kcs.user.entity.DictGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (DictGroup)表数据库访问层
 *
 * @author kcs
 * @since 2019-11-01 11:37:54
 */
public interface DictGroupDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DictGroup queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DictGroup> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dictGroup 实例对象
     * @param keyWord   keyWord
     * @return 对象列表
     */
    List<DictGroup> queryAll(@Param("dictGroup") DictGroup dictGroup, @Param("keyWord") String keyWord);

    /**
     * 新增数据
     *
     * @param dictGroup 实例对象
     * @return 影响行数
     */
    int insert(DictGroup dictGroup);

    /**
     * 修改数据
     *
     * @param dictGroup 实例对象
     * @return 影响行数
     */
    int update(DictGroup dictGroup);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}