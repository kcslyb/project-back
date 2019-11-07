package cn.kcs.order.controller;

import cn.kcs.common.util.ResponseDto;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.order.entity.Order;
import cn.kcs.order.entity.dto.OrderDto;
import cn.kcs.order.entity.dto.OrderGoodsDto;
import cn.kcs.order.entity.dto.OrderGoodsSimpleDto;
import cn.kcs.order.entity.dto.SimpleProduct;
import cn.kcs.order.service.OrderService;
import cn.kcs.rabbitmq.MsgSender;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    @GetMapping()
    public ResponseEntity selectById(String id) {
        OrderDto orderDto = this.orderService.queryById(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
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
    public ResponseEntity queryOrderGoodsByOrderId(String id) {
        List<OrderGoodsDto> orderGoodsList = this.orderService.queryOrderGoodsByOrderId(id);
        return new ResponseEntity<>(orderGoodsList, HttpStatus.OK);
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
    public ResponseEntity add(@RequestBody Order order) {
        OrderDto insert = this.orderService.insert(order);
        return new ResponseEntity<>(insert, HttpStatus.OK);
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
    public ResponseEntity delete(@PathVariable String id) {
        boolean delete = this.orderService.deleteById(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    /**
     * 通过OrderId 和 ProductId 添加订单货品
     *
     * @param orderGoodsSimpleDto
     * @return
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加订单货品")
    @PostMapping("product/add")
    public ResponseEntity insertByOrderIdAndProductId(@RequestBody OrderGoodsSimpleDto orderGoodsSimpleDto) {
        if (CollectionUtils.isEmpty(orderGoodsSimpleDto.getOrderProductIds())) {
            return new ResponseEntity<>("OrderProductId list is null", HttpStatus.BAD_REQUEST);
        }
        boolean flag = orderService.insertByOrderIdAndProductId(orderGoodsSimpleDto);
        return new ResponseEntity<>(flag, HttpStatus.OK);

    }

    /**
     * 通过OrderId 和 ProductId 添加订单货品
     *
     * @param orderGoodsSimpleDto
     * @return
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "修改订单货品")
    @PutMapping("product/update")
    public ResponseEntity updateByOrderIdAndProductId(@RequestBody OrderGoodsSimpleDto orderGoodsSimpleDto) {
        boolean flag = orderService.updateByOrderIdAndProductId(orderGoodsSimpleDto);
        return new ResponseEntity<>(flag, HttpStatus.OK);

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
    public ResponseEntity deleteByOrderIdAndProductId(String orderId, SimpleProduct simpleProduct) {
        boolean flag = orderService.deleteByOrderIdAndProductId(orderId, simpleProduct);
        if (!flag) {
            return new ResponseEntity<>("操作失败，请稍后再试", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
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
    public ResponseEntity edit(@RequestBody Order order) {
        OrderDto update = this.orderService.update(order);
        return new ResponseEntity<>(update, HttpStatus.OK);
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
    public ResponseEntity simpleEdit(String orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        boolean flag = this.orderService.simpleUpdate(order);
        if (!flag) {
            return new ResponseEntity<>("操作失败，请稍后再试", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * 上菜
     *
     * @param orderId
     * @return
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "上菜")
    @PostMapping("/serving")
    public ResponseEntity serving(String orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        boolean flag = this.orderService.serving(order);
        if (!flag) {
            return new ResponseEntity<>("操作失败，请稍后再试", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
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
    public ResponseEntity query(Order order, Integer offset, Integer limit) {
        if (offset == null || "".equals(offset) || offset <= 0) {
            offset = 0;
        } else {
            offset = (offset - 1) * limit;
        }
        if (limit == null || "".equals(limit) || limit <= 0) {
            limit = 10;
        }
        int size = this.orderService.queryAll(order);
        List<OrderDto> orderDtoList = this.orderService.queryAllByLimit(order, offset, limit);
        ResponseDto<OrderDto> orderDtoResponseDto = new ResponseDto<>(orderDtoList, size, limit, offset);
        return new ResponseEntity<>(orderDtoResponseDto, HttpStatus.OK);
    }

}