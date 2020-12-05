package cn.kcs.schedule.service;

import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.schedule.dto.ProductWorkDto;
import cn.kcs.schedule.entity.ProductWork;

import java.util.List;

/**
 * (ProductWork)表服务接口
 *
 * @author kcs
 * @since 2020-09-17 20:16:25
 */
public interface ProductWorkService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductWorkDto queryById(String id);

    /**
     * 查询多条数据
     *
     * @param productWork 实例对象
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ProductWork> queryAllByLimit(ProductWork productWork, Integer offset, Integer limit);

    /**
     * 新增数据
     *
     * @param productWorkDto 实例对象
     * @return 实例对象
     */
    boolean insert(ProductWorkDto productWorkDto);

    /**
     * 修改数据
     *
     * @param productWorkDto 实例对象
     * @return 实例对象
     */
    boolean update(ProductWorkDto productWorkDto);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    ResponseDto<ProductWorkDto> queryPager(ProductWork productWork, PageRequest pageRequest);
}