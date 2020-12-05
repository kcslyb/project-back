package cn.kcs.schedule.dao;

import cn.kcs.schedule.entity.ConvertProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ConvertProduct)表数据库访问层
 *
 * @author kcs
 * @since 2020-09-17 20:19:17
 */
public interface ConvertProductDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ConvertProduct queryById(@Param("id") String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ConvertProduct> queryAllByLimit(@Param("convertProduct") ConvertProduct convertProduct, @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param convertProduct 实例对象
     * @return 对象列表
     */
    List<ConvertProduct> queryAll(ConvertProduct convertProduct);

    /**
     * 新增数据
     *
     * @param convertProduct 实例对象
     * @return 影响行数
     */
    int insert(ConvertProduct convertProduct);

    /**
     * 修改数据
     *
     * @param convertProduct 实例对象
     * @return 影响行数
     */
    int update(ConvertProduct convertProduct);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

}