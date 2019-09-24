package cn.kcs.order.service.impl;

import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.order.dao.DeskDao;
import cn.kcs.order.dao.OrderDao;
import cn.kcs.order.dao.ProductDao;
import cn.kcs.order.dao.TOrderProductDao;
import cn.kcs.order.entity.Desk;
import cn.kcs.order.entity.Order;
import cn.kcs.order.entity.Product;
import cn.kcs.order.entity.TOrderProduct;
import cn.kcs.order.entity.dto.OrderDto;
import cn.kcs.order.entity.dto.OrderGoodsDto;
import cn.kcs.order.entity.dto.OrderGoodsSimpleDto;
import cn.kcs.order.entity.dto.SimpleProduct;
import cn.kcs.order.service.OrderService;
import cn.kcs.user.dao.AddressDao;
import cn.kcs.user.dao.UserAccountDao;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private AddressDao addressDao;

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
    public List<OrderGoodsDto> queryOrderGoodsByOrderId(String orderId) {
        Order order = orderDao.queryById(orderId);
        TOrderProduct orderProduct = new TOrderProduct();
        orderProduct.setOrderId(orderId);
        List<TOrderProduct> list = tOrderProductDao.queryAll(orderProduct);
        List<OrderGoodsDto> orderGoodsDtos = new ArrayList<>();
        for (TOrderProduct tOrderProduct : list) {
            Product product = productDao.queryById(tOrderProduct.getProductId());
            OrderGoodsDto orderGoodsDto = new OrderGoodsDto();
            orderGoodsDto.setOrderId(orderId);
            orderGoodsDto.setOrderSerialNumber(order.getOrderSerialNumber());
            orderGoodsDto.setProductId(product.getProductId());
            orderGoodsDto.setProductName(product.getProductName());
            if ("0".equals(order.getOrderType())) {
                Desk desk = deskDao.queryById(order.getOrderDesk());
                orderGoodsDto.setProductDeskNumber(desk.getDeskSerialNumber());
            }
            orderGoodsDto.setOrderProductNumber(Integer.toString(tOrderProduct.getOrderProductNumber()));
            orderGoodsDto.setOrderProductPrise(product.getProductPrise());
            orderGoodsDto.setOrderProductStatus(Integer.toString(tOrderProduct.getOrderProductStatus()));
            orderGoodsDtos.add(orderGoodsDto);
        }
        return orderGoodsDtos;
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
        if ("0".equals(orderDto.getOrderType())) {
            orderDto.setOrderDesk(order.getOrderDesk());
            orderDto.setDesk(deskDao.queryById(order.getOrderDesk()));
        }
        if ("1".equals(orderDto.getOrderType())) {
            orderDto.setOrderDesk(order.getOrderDesk());
            orderDto.setAddress(addressDao.queryById(order.getOrderDesk()));
        }
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
        order.setOrderSerialNumber("SO" + CustomDateUtil.currentTimeStamp());
        order.setOrderNumber(Integer.toString(orderDao.queryAll(new Order()).size() + 1));
        order.setOrderStatus("0");
        order.setOrderCreateTime(CustomDateUtil.currentFormatDate());
        updateDeskStatus(order.getOrderDesk(), "1");
        this.orderDao.insert(order);
        if (CollectionUtils.isNotEmpty(order.getProducts())) {
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
        if (CollectionUtils.isNotEmpty(order.getProducts())) {
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
        orderProduct.setOrderProductStatus(0);
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
        order.setOrderSettlementTime(CustomDateUtil.currentFormatDate());
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
    public boolean insertByOrderIdAndProductId(OrderGoodsSimpleDto orderGoodsSimpleDto) {
        int i = 0;
        for (String orderProductId : orderGoodsSimpleDto.getOrderProductIds()) {
            TOrderProduct tOrderProduct = new TOrderProduct();
            tOrderProduct.setOrderId(orderGoodsSimpleDto.getOrderId());
            tOrderProduct.setProductId(orderProductId);
            tOrderProduct.setOrderProductNumber(1);
            tOrderProduct.setOrderProductStatus(0);
            tOrderProduct.setOrderProductId(ShortUUID.generate());
            i += tOrderProductDao.insert(tOrderProduct);
        }
        Order order = orderDao.queryById(orderGoodsSimpleDto.getOrderId());
        order.setOrderStatus("0");
        int j = orderDao.update(order);
        return i > 0 && j > 0;
    }

    @Override
    public boolean updateByOrderIdAndProductId(OrderGoodsSimpleDto orderGoodsSimpleDto) {
        TOrderProduct product = new TOrderProduct();
        product.setOrderId(orderGoodsSimpleDto.getOrderId());
        product.setOrderProductStatus(0);
        int i = 0;
        for (String productId : orderGoodsSimpleDto.getOrderProductIds()) {
            TOrderProduct tOrderProduct = new TOrderProduct();
            tOrderProduct.setOrderId(orderGoodsSimpleDto.getOrderId());
            tOrderProduct.setProductId(productId);
            List<TOrderProduct> tOrderProducts = tOrderProductDao.queryAll(tOrderProduct);
            TOrderProduct orderProduct = tOrderProducts.get(0);
            if (orderGoodsSimpleDto.getOrderProductNumber() != null) {
                orderProduct.setOrderProductNumber(Integer.parseInt(orderGoodsSimpleDto.getOrderProductNumber()));
            }
            if (orderGoodsSimpleDto.getOrderProductStatus() != null) {
                orderProduct.setOrderProductStatus(Integer.parseInt(orderGoodsSimpleDto.getOrderProductStatus()));
            }
            i += tOrderProductDao.update(orderProduct);
        }
        List<TOrderProduct> products = tOrderProductDao.queryAll(product);
        if (products.size() == 0) {
            Order order = new Order();
            order.setOrderId(orderGoodsSimpleDto.getOrderId());
            this.serving(order);
        }
        return i > 0;
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