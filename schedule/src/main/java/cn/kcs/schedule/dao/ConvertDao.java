package cn.kcs.schedule.dao;

import cn.kcs.schedule.entity.Convert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Convert)表数据库访问层
 *
 * @author kcs
 * @since 2020-09-17 20:11:31
 */
public interface ConvertDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Convert queryById(@Param("id")String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Convert> queryAllByLimit(@Param("convert") Convert convert, @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param convert 实例对象
     * @return 对象列表
     */
    List<Convert> queryAll(Convert convert);

    /**
     * 新增数据
     *
     * @param convert 实例对象
     * @return 影响行数
     */
    int insert(Convert convert);

    /**
     * 修改数据
     *
     * @param convert 实例对象
     * @return 影响行数
     */
    int update(Convert convert);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id")String id);

}