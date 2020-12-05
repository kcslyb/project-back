package cn.kcs.schedule.dao;

import cn.kcs.schedule.entity.ProductWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ProductWork)表数据库访问层
 *
 * @author kcs
 * @since 2020-09-17 20:16:25
 */
public interface ProductWorkDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductWork queryById(@Param("id") String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ProductWork> queryAllByLimit(@Param("productWork")ProductWork productWork, @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param productWork 实例对象
     * @return 对象列表
     */
    List<ProductWork> queryAll(ProductWork productWork);

    /**
     * 新增数据
     *
     * @param productWork 实例对象
     * @return 影响行数
     */
    int insert(ProductWork productWork);

    /**
     * 修改数据
     *
     * @param productWork 实例对象
     * @return 影响行数
     */
    int update(ProductWork productWork);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") String id);

}