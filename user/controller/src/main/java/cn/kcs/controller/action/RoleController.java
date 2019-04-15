package cn.kcs.controller.action;

import cn.kcs.common.util.CommonUtil;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.service.inter.RoleService;
import cn.kcs.service.inter.dto.RoleDto;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: role controller
 * @author: kcs
 * @create: 2018-10-29 08:58
 **/
@RequestMapping(value = "role")
@Api(value = "角色", description = "角色API")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加角色", response = RoleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "添加失败", response = void.class),
            @ApiResponse(code = 200, message = "添加成功", response = RoleDto.class)})
    @PostMapping("add")
    public ResponseEntity addRole(@RequestBody RoleDto roleDto) {
        RoleDto dto = roleService.add(roleDto);
        if (dto != null) {
            return new ResponseEntity(dto, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "编辑角色")
    @PutMapping(value = "{key}")
    public ResponseEntity editRole(@PathVariable String key, @RequestBody RoleDto roleDto) {
        if (StringUtil.isEmpty(key)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        roleDto.setRoleId(key);
        roleService.edit(roleDto);
        return new ResponseEntity(roleDto, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加角色权限")
    @PutMapping(value = "/perm/add")
    public ResponseEntity addRolePerm(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "roleId,permIds");
        String roleId = requestJson.getString("roleId");
        String permIds = requestJson.getString("permIds");
        if (StringUtil.isEmpty(roleId) || StringUtil.isEmpty(permIds)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        String[] strings = permIds.substring(0, permIds.length() - 1).substring(1).split(",");
        List<String> list = new ArrayList<>();
        for (String s : strings) {
            list.add(s.trim());
        }
        roleService.editRolePerm(roleId, list);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "删除角色权限")
    @PutMapping(value = "/perm/delete")
    public ResponseEntity deleteRolePerm(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "roleId,permIds");
        String roleId = requestJson.getString("roleId");
        String permIds = requestJson.getString("permIds");
        if (StringUtil.isEmpty(roleId) || StringUtil.isEmpty(permIds)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        String[] strings = permIds.substring(0, permIds.length() - 1).substring(1).split(",");
        List<String> list = new ArrayList<>();
        for (String s : strings) {
            list.add(s.trim());
        }
        roleService.deleteRolePerm(roleId, list);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "删除角色")
    @DeleteMapping(value = "/delete/{key}")
    public ResponseEntity deletePermission(@PathVariable String key) {
        if (StringUtil.isEmpty(key)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        roleService.deleteByKey(key);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询角色")
    @GetMapping("list")
    public ResponseEntity queryRole() {
        List<RoleDto> roleDtos = roleService.query(new RoleDto());
        return new ResponseEntity(roleDtos, HttpStatus.OK);
    }
}
