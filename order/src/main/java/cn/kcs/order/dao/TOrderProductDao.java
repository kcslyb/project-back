package cn.kcs.order.dao;

import cn.kcs.order.entity.TOrderProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TOrderProduct)表数据库访问层
 *
 * @author makejava
 * @since 2019-04-28 22:46:39
 */
public interface TOrderProductDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderProductId 主键
     * @return 实例对象
     */
    TOrderProduct queryById(@Param("orderProductId") String orderProductId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TOrderProduct> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tOrderProduct 实例对象
     * @return 对象列表
     */
    List<TOrderProduct> queryAll(TOrderProduct tOrderProduct);

    /**
     * 新增数据
     *
     * @param tOrderProduct 实例对象
     * @return 影响行数
     */
    int insert(TOrderProduct tOrderProduct);

    /**
     * 修改数据
     *
     * @param tOrderProduct 实例对象
     * @return 影响行数
     */
    int update(TOrderProduct tOrderProduct);

    /**
     * 通过主键删除数据
     *
     * @param orderProductId 主键
     * @return 影响行数
     */
    int deleteById(@Param("orderProductId") String orderProductId);

}