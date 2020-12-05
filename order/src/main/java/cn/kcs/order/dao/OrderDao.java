package cn.kcs.order.dao;

import cn.kcs.order.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Order)表数据库访问层
 *
 * @author kcs
 * @since 2019-04-24 14:42:56
 */
public interface OrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    Order queryById(@Param("orderId") String orderId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Order> queryAllByLimit(@Param("order") Order order, @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param order 实例对象
     * @return 对象列表
     */
    List<Order> queryAll(Order order);

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 影响行数
     */
    int insert(Order order);

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 影响行数
     */
    int update(Order order);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(@Param("orderId") String orderId);

    /**
     * 通过主键删除订单货品
     *
     * @param orderId
     * @return
     */
    List<String> queryOrderAllGoods(@Param("orderId") String orderId);

    /**
     * 通过主键删除订单货品
     *
     * @param orderId
     * @param productId
     * @return
     */
    int deleteOrderGoods(@Param("orderId") String orderId, @Param("productId") String productId);

    /**
     * 通过主键删除订单货品
     *
     * @param orderId
     * @return
     */
    int deleteOrderAllGoods(@Param("orderId") String orderId);

    /**
     * 通过主键添加订单货品
     *
     * @param orderProductId
     * @param orderId
     * @param productId
     * @return
     */
    int addOrderGoods(@Param("orderProductId") String orderProductId, @Param("orderId") String orderId, @Param("productId") String productId);

}