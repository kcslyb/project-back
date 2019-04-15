package cn.kcs.controller.action;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.service.inter.ProService;
import cn.kcs.service.inter.dto.ProDto;
import cn.kcs.service.inter.util.PageRequest;
import cn.kcs.service.inter.util.PageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.StringUtil;

/**
 * @description:
 * @author: kcs
 * @create: 2018-11-24 18:01
 **/
@RequestMapping(value = "pro")
@Api(value = "pro", description = "pro API")
@RestController
public class ProController {
    @Autowired
    private ProService proService;

    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加Por", response = ProDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "添加失败", response = void.class),
            @ApiResponse(code = 200, message = "添加成功", response = ProDto.class)})
    @PostMapping("add")
    public ResponseEntity add(@RequestBody ProDto proDto) {
        ProDto dto = proService.add(proDto);
        if (dto != null) {
            return new ResponseEntity(dto, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "编辑Pro")
    @PutMapping(value = "{key}")
    public ResponseEntity edit(@PathVariable String key, @RequestBody ProDto proDto) {
        if (StringUtil.isEmpty(key)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        proDto.setProId(key);
        proService.edit(proDto);
        return new ResponseEntity(proDto, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "分页查询所有pro信息", response = PageResponse.class)
    @GetMapping("list")
    public ResponseEntity queryPager(ProDto proDto, Integer pageNo, Integer pageSize, String keyWord) {
        if (pageSize == null || pageSize < 0) {
            pageSize = 20;
        }
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
        PageResponse<ProDto> pageResponse = proService.queryPager(pageRequest.getStart(), pageSize, proDto);
        return new ResponseEntity(pageResponse, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "根据主键查询Pro信息", response = ProDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "查无此用户信息", response = Void.class),
            @ApiResponse(code = 200, message = "查询成功", response = ProDto.class)})
    @GetMapping(value = "{key}")
    public ResponseEntity queryByKey(@PathVariable String key) {
        ProDto userDto = proService.getByKey(key);
        if (userDto != null) {
            return new ResponseEntity(userDto, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "删除Pro")
    @DeleteMapping(value = "{key}")
    public ResponseEntity delete(@PathVariable String key) {
        if (StringUtil.isEmpty(key)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        int i = proService.deleteByKey(key);
        return new ResponseEntity(i, HttpStatus.OK);
    }
}
