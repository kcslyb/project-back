package cn.kcs.order.service;


import cn.kcs.order.entity.Product;
import cn.kcs.order.entity.dto.ProductDto;

import java.util.List;

/**
 * (Product)表服务接口
 *
 * @author kcs
 * @since 2019-04-23 14:38:33
 */
public interface ProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param productId 主键
     * @return 实例对象
     */
    ProductDto queryById(String productId);

    /**
     * 查询多条数据
     *
     * @param product
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    List<ProductDto> queryAllByLimit(Product product, int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param product
     * @return 对象列表
     */
    int queryAll(Product product);

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    ProductDto insert(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    ProductDto update(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    ProductDto updateSales(Product product);

    /**
     * 通过主键删除数据
     *
     * @param productId 主键
     * @return 是否成功
     */
    boolean deleteById(String productId);

}