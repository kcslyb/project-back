package cn.kcs.schedule.service;

import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.schedule.entity.ConvertProduct;

import java.util.List;

/**
 * (ConvertProduct)表服务接口
 *
 * @author kcs
 * @since 2020-09-17 20:19:17
 */
public interface ConvertProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ConvertProduct queryById(String id);

    /**
     * 查询多条数据
     *
     * @param convertProduct 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ConvertProduct> queryAllByLimit(ConvertProduct convertProduct, Integer offset, Integer limit);

    List<ConvertProduct> queryAll(ConvertProduct convertProduct);

    /**
     * 新增数据
     *
     * @param convertProduct 实例对象
     * @return 实例对象
     */
    boolean insert(ConvertProduct convertProduct);

    /**
     * 修改数据
     *
     * @param convertProduct 实例对象
     * @return 实例对象
     */
    boolean update(ConvertProduct convertProduct);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    ResponseDto queryPager(ConvertProduct convertProduct, PageRequest pageRequest);
}