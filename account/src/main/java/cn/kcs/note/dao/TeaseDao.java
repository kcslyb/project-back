package cn.kcs.note.dao;

import cn.kcs.note.entity.Tease;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Tease)表数据库访问层
 *
 * @author makejava
 * @since 2019-01-06 20:33:36
 */
public interface TeaseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param teaseId 主键
     * @return 实例对象
     */
    Tease queryById(@Param("teaseId") String teaseId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Tease> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tease 实例对象
     * @return 对象列表
     */
    List<Tease> queryAll(Tease tease);

    /**
     * 新增数据
     *
     * @param tease 实例对象
     * @return 影响行数
     */
    int insert(Tease tease);

    /**
     * 修改数据
     *
     * @param tease 实例对象
     * @return 影响行数
     */
    int update(Tease tease);

    /**
     * 通过主键删除数据
     *
     * @param teaseId 主键
     * @return 影响行数
     */
    int deleteById(@Param("teaseId") String teaseId);

}