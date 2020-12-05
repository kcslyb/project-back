package cn.kcs.order.dao;

import cn.kcs.order.entity.Desk;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Desk)表数据库访问层
 *
 * @author kcs
 * @since 2019-04-24 14:35:14
 */
public interface DeskDao {

    /**
     * 通过ID查询单条数据
     *
     * @param deskId 主键
     * @return 实例对象
     */
    Desk queryById(@Param("deskId") String deskId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Desk> queryAllByLimit(@Param("desk") Desk desk, @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param desk 实例对象
     * @return 对象列表
     */
    List<Desk> queryAll(Desk desk);

    /**
     * 新增数据
     *
     * @param desk 实例对象
     * @return 影响行数
     */
    int insert(Desk desk);

    /**
     * 修改数据
     *
     * @param desk 实例对象
     * @return 影响行数
     */
    int update(Desk desk);

    /**
     * 通过主键删除数据
     *
     * @param deskId 主键
     * @return 影响行数
     */
    int deleteById(@Param("deskId") String deskId);

}