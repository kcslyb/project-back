//package cn.kcs.controller.action;
//
//import cn.kcs.encrypt.anno.Decrypt;
//import cn.kcs.encrypt.anno.Encrypt;
//import cn.kcs.service.inter.DeskService;
//import cn.kcs.service.inter.dto.DeskDto;
//import cn.kcs.service.inter.util.PageRequest;
//import cn.kcs.service.inter.util.PageResponse;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.tinygroup.commons.tools.StringUtil;
//
///**
// * @description: log info
// * @author: kcs
// * @create: 2018-11-06 15:55
// **/
//@RequestMapping(value = "desk")
//@Api(value = "desk", description = "desk API")
//@RestController
//public class DeskController {
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    @Autowired
//    private DeskService deskService;
//
//    @Decrypt
//    @Encrypt
//    @ApiOperation(value = "添加deskDto", response = DeskDto.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 400, message = "添加失败", response = void.class),
//            @ApiResponse(code = 200, message = "添加成功", response = DeskDto.class)})
//    @PostMapping("add")
//    public ResponseEntity add(@RequestBody DeskDto deskDto) {
//        DeskDto dto = deskService.add(deskDto);
//        if (dto != null) {
//            return new ResponseEntity(dto, HttpStatus.OK);
//        }
//        return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
//    }
//
//    @Decrypt
//    @Encrypt
//    @ApiOperation(value = "编辑deskDto")
//    @PutMapping(value = "{key}")
//    public ResponseEntity edit(@PathVariable String key, @RequestBody DeskDto dto) {
//        if (StringUtil.isEmpty(key)) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        dto.setDeskId(key);
//        deskService.edit(dto);
//        return new ResponseEntity(dto, HttpStatus.OK);
//    }
//
//    @Decrypt
//    @Encrypt
//    @ApiOperation(value = "删除deskDto")
//    @DeleteMapping(value = "{key}")
//    public ResponseEntity delete(@PathVariable String key) {
//        if (StringUtil.isEmpty(key)) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        int i = deskService.deleteByKey(key);
//        return new ResponseEntity(i, HttpStatus.OK);
//    }
//
//    @Decrypt
//    @Encrypt
//    @ApiOperation(value = "分页查询所有信息", response = PageResponse.class)
//    @PostMapping("list")
//    public ResponseEntity queryPager(@RequestBody DeskDto deskDto, Integer pageNo, Integer pageSize) {
//        if (pageSize == null || pageSize < 0) {
//            pageSize = 10;
//        }
//        logger.info("---" + deskDto.getDeskStatus());
//        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
//        PageResponse pageResponse = deskService.queryPager(pageRequest.getStart(), pageSize, deskDto);
//        return new ResponseEntity(pageResponse, HttpStatus.OK);
//    }
//}
