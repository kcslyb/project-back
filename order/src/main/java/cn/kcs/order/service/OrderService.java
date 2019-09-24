package cn.kcs.order.service;

import cn.kcs.order.entity.Order;
import cn.kcs.order.entity.dto.OrderDto;
import cn.kcs.order.entity.dto.OrderGoodsDto;
import cn.kcs.order.entity.dto.OrderGoodsSimpleDto;
import cn.kcs.order.entity.dto.SimpleProduct;

import java.util.List;

/**
 * (Order)表服务接口
 *
 * @author makejava
 * @since 2019-04-24 14:42:57
 */
public interface OrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    OrderDto queryById(String orderId);

    List<OrderGoodsDto> queryOrderGoodsByOrderId(String orderId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<OrderDto> queryAllByLimit(Order order, int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param order
     * @return 对象列表
     */
    int queryAll(Order order);

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    OrderDto insert(Order order);

    /**
     * 修改数据
     *
     * @param order 实例对象 改关联表
     * @return 实例对象
     */
    OrderDto update(Order order);

    /**
     * 修改数据 不改关联表
     *
     * @param order 实例对象
     * @return 实例对象
     */
    boolean simpleUpdate(Order order);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(String orderId);

    /**
     * 通过OrderId 和 ProductId 添加订单货品
     *
     * @param orderGoodsSimpleDto
     * @return
     */
    boolean insertByOrderIdAndProductId(OrderGoodsSimpleDto orderGoodsSimpleDto);

    /**
     * 通过OrderId 和 ProductId 修改订单货品
     *
     * @param orderGoodsSimpleDto
     * @return
     */
    boolean updateByOrderIdAndProductId(OrderGoodsSimpleDto orderGoodsSimpleDto);

    /**
     * 通过主键删除数据
     *
     * @param orderId       主键
     * @param simpleProduct
     * @return 是否成功
     */
    boolean deleteByOrderIdAndProductId(String orderId, SimpleProduct simpleProduct);

    boolean serving(Order order);
}