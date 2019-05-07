//package cn.kcs.controller.action;
//
//import cn.kcs.encrypt.anno.Decrypt;
//import cn.kcs.encrypt.anno.Encrypt;
//import cn.kcs.service.inter.OrderService;
//import cn.kcs.service.inter.dto.OrderDto;
//import cn.kcs.service.inter.util.PageRequest;
//import cn.kcs.service.inter.util.PageResponse;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.tinygroup.commons.tools.StringUtil;
//
///**
// * @description:
// * @author: kcs
// * @create: 2018-11-24 18:01
// **/
//@RequestMapping(value = "order")
//@Api(value = "OrderDto", description = "OrderDto API")
//@RestController
//public class OrderController {
//    @Autowired
//    private OrderService orderService;
//
//    @Decrypt
//    @Encrypt
//    @ApiOperation(value = "添加OrderDto", response = OrderDto.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 400, message = "添加失败", response = void.class),
//            @ApiResponse(code = 200, message = "添加成功", response = OrderDto.class)})
//    @PostMapping("add")
//    public ResponseEntity add(@RequestBody OrderDto orderDto) {
//        OrderDto dto = orderService.add(orderDto);
//        if (dto != null) {
//            return new ResponseEntity(dto, HttpStatus.OK);
//        }
//        return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
//    }
//
//    @Decrypt
//    @Encrypt
//    @ApiOperation(value = "编辑OrderDto")
//    @PutMapping(value = "{key}")
//    public ResponseEntity edit(@PathVariable String key, @RequestBody OrderDto orderDto) {
//        if (StringUtil.isEmpty(key)) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        orderDto.setOrderId(key);
//        orderService.edit(orderDto);
//        return new ResponseEntity(orderDto, HttpStatus.OK);
//    }
//
//    @Decrypt
//    @Encrypt
//    @ApiOperation(value = "分页查询所有OrderDto信息", response = PageResponse.class)
//    @PostMapping("list")
//    public ResponseEntity queryPager(@RequestBody OrderDto orderDto, Integer pageNo, Integer pageSize) {
//        if (pageSize == null || pageSize < 0) {
//            pageSize = 20;
//        }
//        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
//        PageResponse<OrderDto> pageResponse = orderService.queryPager(pageRequest.getStart(), pageSize, orderDto);
//        return new ResponseEntity(pageResponse, HttpStatus.OK);
//    }
//
//    @Decrypt
//    @Encrypt
//    @ApiOperation(value = "根据主键查询OrderDto信息", response = OrderDto.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 404, message = "查无此用户信息", response = Void.class),
//            @ApiResponse(code = 200, message = "查询成功", response = OrderDto.class)})
//    @GetMapping(value = "{key}")
//    public ResponseEntity queryByKey(@PathVariable String key) {
//        OrderDto orderDto = orderService.getByKey(key);
//        if (orderDto != null) {
//            return new ResponseEntity(orderDto, HttpStatus.OK);
//        }
//        return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }
//
//    @Decrypt
//    @Encrypt
//    @ApiOperation(value = "删除OrderDto")
//    @DeleteMapping(value = "{key}")
//    public ResponseEntity delete(@PathVariable String key) {
//        if (StringUtil.isEmpty(key)) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        int i = orderService.deleteByKey(key);
//        return new ResponseEntity(i, HttpStatus.OK);
//    }
//
//    @Decrypt
//    @Encrypt
//    @ApiOperation(value = "结算")
//    @PostMapping(value = "/account/{key}")
//    public ResponseEntity account(@PathVariable String key) {
//        if (StringUtil.isEmpty(key)) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        orderService.account(key);
//        return new ResponseEntity("success", HttpStatus.OK);
//    }
//}
