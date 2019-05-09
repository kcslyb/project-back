package cn.kcs.order.controller;

import cn.kcs.common.util.CommonUtil;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.order.entity.Order;
import cn.kcs.order.entity.dto.SimpleProduct;
import cn.kcs.order.service.OrderService;
import cn.kcs.rabbitmq.MsgSender;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Order)表控制层
 *
 * @author kcs
 * @data 2019-04-24 14:42:59
 */
@RestController
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;

    @Autowired
    private MsgSender msgSender;

    @Autowired
    private Environment environment;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("")
    public JSONObject selectById(String id) {
        return CommonUtil.successJson(this.orderService.queryById(id));
    }

    /**
     * 通过主键查询订单货品
     *
     * @param id order ID
     * @return
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "通过主键查询订单货品")
    @GetMapping("/info")
    public JSONObject queryOrderGoodsByOrderId(String id) {
        return CommonUtil.successJson(this.orderService.queryOrderGoodsByOrderId(id));
    }

    /**
     * 添加数据
     *
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加数据")
    @PostMapping()
    public JSONObject add(@RequestBody Order order) {
        return CommonUtil.successJson(this.orderService.insert(order));
    }

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "删除单条数据")
    @DeleteMapping("/{id}")
    public JSONObject delete(@PathVariable String id) {
        return CommonUtil.successJson(this.orderService.deleteById(id));
    }

    /**
     * 通过OrderId 和 ProductId 添加订单货品
     *
     * @param orderId
     * @param simpleProduct
     * @return
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加订单货品")
    @DeleteMapping("product/add")
    public JSONObject insertByOrderIdAndProductId(String orderId, SimpleProduct simpleProduct) {
        if (orderService.insertByOrderIdAndProductId(orderId, simpleProduct)) {
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson();

    }

    /**
     * 通过主键删除单条数据
     *
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "删除订单货品")
    @DeleteMapping("product/delete")
    public JSONObject deleteByOrderIdAndProductId(String orderId, SimpleProduct simpleProduct) {
        if (orderService.deleteByOrderIdAndProductId(orderId, simpleProduct)) {
            return CommonUtil.successJson();
        }
        return CommonUtil.errorJson();
    }

    /**
     * 修改单条数据
     *
     * @param order
     * @return
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "修改单条数据")
    @PutMapping()
    public JSONObject edit(@RequestBody Order order) {
        return CommonUtil.successJson(this.orderService.update(order));
    }

    /**
     * 结算订单
     *
     * @param orderId
     * @return
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "结算订单")
    @PostMapping("/settle")
    public JSONObject simpleEdit(String orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        return CommonUtil.successJson(this.orderService.simpleUpdate(order));
    }

    /**
     * 结算订单
     *
     * @param orderId
     * @return
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "结算订单")
    @PostMapping("/serving")
    public JSONObject serving(String orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        try {
            msgSender.sender(environment.getProperty("mq_msg_exchange_name"), environment.getProperty("mq_msg_routing_key_name"), orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonUtil.successJson(this.orderService.serving(order));
    }

    /**
     * 分页查询所以数据
     *
     * @return List数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询所以数据")
    @GetMapping("query/pager")
    public JSONObject query(Order order, Integer offset, Integer limit) {
        if (offset == null || "".equals(offset) || offset <= 0) {
            offset = 0;
        } else {
            offset = (offset - 1) * limit;
        }
        if (limit == null || "".equals(limit) || limit <= 0) {
            limit = 10;
        }
        int size = this.orderService.queryAll(order);
        return CommonUtil.successPage(this.orderService.queryAllByLimit(order, offset, limit), size);
    }

}