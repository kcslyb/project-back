package cn.kcs.order.service.impl;

import cn.kcs.common.daoUtil.ShortUUID;
import cn.kcs.common.loginInfo.LoginInfo;
import cn.kcs.common.util.DataUtil;
import cn.kcs.note.dao.UserAccountDao;
import cn.kcs.order.dao.DeskDao;
import cn.kcs.order.dao.OrderDao;
import cn.kcs.order.dao.ProductDao;
import cn.kcs.order.dao.TOrderProductDao;
import cn.kcs.order.entity.Desk;
import cn.kcs.order.entity.Order;
import cn.kcs.order.entity.Product;
import cn.kcs.order.entity.TOrderProduct;
import cn.kcs.order.entity.dto.OrderDto;
import cn.kcs.order.entity.dto.SimpleProduct;
import cn.kcs.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2019-04-24 14:42:58
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TOrderProductDao tOrderProductDao;

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private DeskDao deskDao;

    @Autowired
    private ProductDao productDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public OrderDto queryById(String orderId) {
        return toDto(this.orderDao.queryById(orderId));
    }

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public List<Product> queryOrderGoodsByOrderId(String orderId) {
        TOrderProduct orderProduct = new TOrderProduct();
        orderProduct.setOrderId(orderId);
        List<TOrderProduct> list = tOrderProductDao.queryAll(orderProduct);
        List<Product> products = new ArrayList<>();
        for (TOrderProduct tOrderProduct : list) {
            Product product = productDao.queryById(tOrderProduct.getProductId());
            product.setProductSalesNumber(tOrderProduct.getOrderProductNumber().toString());
            products.add(product);
        }
        return products;
    }

    private OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setOrderNumber(order.getOrderNumber());
        orderDto.setOrderCustomer(order.getOrderCustomer());
        orderDto.setOrderCustomerName(userAccountDao.queryById(order.getOrderCustomer()).getUserName());
        orderDto.setOrderSerialNumber(order.getOrderSerialNumber());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setOrderType(order.getOrderType());
        orderDto.setOrderDesk(order.getOrderDesk());
        orderDto.setDesk(deskDao.queryById(order.getOrderDesk()));
        orderDto.setOrderCreateTime(order.getOrderCreateTime());
        orderDto.setOrderSettlementTime(order.getOrderSettlementTime());
        TOrderProduct tOrderProduct = new TOrderProduct();
        tOrderProduct.setOrderId(order.getOrderId());
        orderDto.setOrderProductSize(tOrderProductDao.queryAll(tOrderProduct).size());
        return orderDto;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<OrderDto> queryAllByLimit(Order order, int offset, int limit) {
        List<Order> orders = this.orderDao.queryAllByLimit(order, offset, limit);
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order o : orders) {
            orderDtos.add(toDto(o));
        }
        return orderDtos;
    }

    /**
     * 查询多条数据
     *
     * @param order
     * @return
     */
    @Override
    public int queryAll(Order order) {
        return this.orderDao.queryAll(order).size();
    }

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public OrderDto insert(Order order) {
        order.setOrderId(ShortUUID.generate());
        order.setOrderCustomer(LoginInfo.getUserId());
        order.setOrderSerialNumber("SO" + DataUtil.currentTimeStamp());
        order.setOrderNumber(Integer.toString(orderDao.queryAll(new Order()).size() + 1));
        order.setOrderStatus("0");
        order.setOrderCreateTime(DataUtil.currentFormatDate());
        updateDeskStatus(order.getOrderDesk(), "1");
        this.orderDao.insert(order);
        if (CollectionUtil.isNotEmpty(order.getProducts())) {
            for (SimpleProduct product : order.getProducts()) {
                Product p = productDao.queryById(product.getProductId());
                Integer integer = Integer.parseInt(p.getProductSalesNumber()) + product.getProductNumber();
                p.setProductSalesNumber(integer.toString());
                productDao.update(p);
                addSimplyProduct(product, order.getOrderId());
            }
        }
        return this.queryById(order.getOrderId());
    }

    private boolean updateDeskStatus(String orderDesk, String s) {
        Desk desk = new Desk();
        desk.setDeskId(orderDesk);
        desk.setDeskStatus(s);
        return deskDao.update(desk) > 0;
    }


    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public OrderDto update(Order order) {
        this.orderDao.update(order);
        orderDao.deleteOrderAllGoods(order.getOrderId());
        if (CollectionUtil.isNotEmpty(order.getProducts())) {
            for (SimpleProduct product : order.getProducts()) {
                addSimplyProduct(product, order.getOrderId());
            }
        }
        return this.queryById(order.getOrderId());
    }

    private boolean addSimplyProduct(SimpleProduct product, String orderId) {
        TOrderProduct orderProduct = new TOrderProduct();
        orderProduct.setOrderProductId(ShortUUID.generate());
        orderProduct.setOrderId(orderId);
        orderProduct.setProductId(product.getProductId());
        orderProduct.setOrderProductNumber(product.getProductNumber());
        return tOrderProductDao.insert(orderProduct) > 0;
    }

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public boolean simpleUpdate(Order order) {
        order = orderDao.queryById(order.getOrderId());
        order.setOrderStatus("2");
        this.updateDeskStatus(order.getOrderDesk(), "0");
        order.setOrderSettlementTime(DataUtil.currentFormatDate());
        return this.orderDao.update(order) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String orderId) {
        orderDao.deleteOrderAllGoods(orderId);
        return this.orderDao.deleteById(orderId) > 0;
    }

    @Override
    public boolean insertByOrderIdAndProductId(String orderId, SimpleProduct simpleProduct) {
        return addSimplyProduct(simpleProduct, orderId);
    }

    @Override
    public boolean deleteByOrderIdAndProductId(String orderId, SimpleProduct simpleProduct) {
        return this.orderDao.deleteOrderGoods(orderId, simpleProduct.getProductId()) > 0;
    }

    @Override
    public boolean serving(Order order) {
        order = orderDao.queryById(order.getOrderId());
        order.setOrderStatus("1");
        return this.orderDao.update(order) > 0;
    }

}