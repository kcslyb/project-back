package cn.kcs.controller.action;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.service.inter.PermissionService;
import cn.kcs.service.inter.dto.PermissionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.StringUtil;

import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-31 15:12
 **/
@RequestMapping(value = "permission")
@Api(value = "权限", description = "权限API")
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加", response = PermissionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "添加失败", response = void.class),
            @ApiResponse(code = 200, message = "添加成功", response = PermissionDto.class)})
    @PostMapping("add")
    public ResponseEntity addPermission(@RequestBody PermissionDto roleDto) {
        PermissionDto dto = permissionService.add(roleDto);
        if (dto != null) {
            return new ResponseEntity(dto, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "编辑")
    @PutMapping(value = "{key}")
    public ResponseEntity editPermission(@PathVariable String key, @RequestBody PermissionDto permissionDto) {
        if (StringUtil.isEmpty(key)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        permissionDto.setPermissionId(key);
        permissionService.edit(permissionDto);
        return new ResponseEntity(permissionDto, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/delete/{key}")
    public ResponseEntity deletePermission(@PathVariable String key) {
        if (StringUtil.isEmpty(key)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        permissionService.deleteByKey(key);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询")
    @GetMapping("list")
    public ResponseEntity queryPermission() {
        List<PermissionDto> permissionDtoList = permissionService.query(new PermissionDto());
        return new ResponseEntity(permissionDtoList, HttpStatus.OK);
    }
}
