package cn.kcs.controller.action;

import cn.kcs.common.util.CommonUtil;
import cn.kcs.common.util.MD5Utils;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.service.inter.UserService;
import cn.kcs.service.inter.dto.UserDto;
import cn.kcs.service.inter.util.PageRequest;
import cn.kcs.service.inter.util.PageResponse;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.StringUtil;

import java.util.List;

@RequestMapping(value = "/user")
@Api(value = "用户", description = "用户API")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public UserController() {
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加用户", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "添加失败", response = void.class),
            @ApiResponse(code = 200, message = "添加成功", response = UserDto.class)})
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserDto userDto) {
        if (StringUtil.isEmpty(userDto.getUserStatus())){
            userDto.setUserStatus("1");
        }
        UserDto dto = userService.add(userDto);
        if (dto != null) {
            return new ResponseEntity(dto, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Decrypt
    @Encrypt
    @RequiresRoles("SuperAdmin")
    @ApiOperation(value = "编辑用户")
    @PutMapping(value = "{key}")
    public ResponseEntity editUser(@PathVariable String key, @RequestBody UserDto userDto) {
        if (StringUtil.isEmpty(key)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userDto.setUuid(key);
        if ((userDto.getRoleDto().getRoleId()) != null) {
            userService.editUserRolePerm(userDto.getRoleDto().getRoleId(), key);
        }
        userService.edit(userDto);
        return new ResponseEntity(userDto, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @RequiresUser
    @ApiOperation(value = "编辑当前用户")
    @PutMapping(value = "/current/{key}")
    public ResponseEntity editCurrentUser(@PathVariable String key, @RequestBody UserDto userDto) {
        if (StringUtil.isEmpty(key)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userDto.setUuid(key);
        if (userDto.getUserPassword() != null) {
            userDto.setUserPassword(MD5Utils.GetMD5Code(MD5Utils.GetMD5Code(userDto.getUserPassword())));
        }
        userService.edit(userDto);
        return new ResponseEntity(userDto, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @RequiresRoles("SuperAdmin")
    @ApiOperation(value = "禁用用户")
    @DeleteMapping(value = "{key}/disable")
    public ResponseEntity disableByKey(@PathVariable String key) {
        UserDto userDto = userService.getByUserId(key);
        if ("0".equals(userDto.getUserStatus())) {
            userDto.setUserStatus("1");
        } else {
            userDto.setUserStatus("0");
        }
        userService.edit(userDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @RequiresRoles("SuperAdmin")
    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "{key}/delete")
    public ResponseEntity deleteByKey(@PathVariable String key) {
        int i = userService.deleteByKey(key);
        return new ResponseEntity(i, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "分页查询所有用户信息", response = PageResponse.class)
    @GetMapping("list")
    public ResponseEntity queryPager(UserDto userDto, Integer pageNo, Integer pageSize, String keyWord) {
        if (pageSize == null || pageSize < 0) {
            pageSize = 20;
        }
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
        PageResponse<UserDto> pageResponse = userService.queryPager(pageRequest.getStart(), pageSize, userDto);
        return new ResponseEntity(pageResponse, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询所有用户信息", response = PageResponse.class)
    @GetMapping("listAll")
    public ResponseEntity queryAll(UserDto userDto) {
        List<UserDto> query = userService.query(userDto);
        return new ResponseEntity(query, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "根据主键查询用户信息", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "查无此用户信息", response = Void.class),
            @ApiResponse(code = 200, message = "查询成功", response = UserDto.class)})
    @GetMapping(value = "{key}")
    public ResponseEntity queryByKey(@PathVariable String key) {
        UserDto userDto = userService.getByKey(key);
        if (userDto != null) {
            return new ResponseEntity(userDto, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "编辑用户角色")
    @PutMapping(value = "/role")
    public ResponseEntity editUserRolePerm(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "roleId,userId");
        String roleId = requestJson.getString("roleId");
        String userId = requestJson.getString("userId");
        if (StringUtil.isEmpty(roleId) || StringUtil.isEmpty(userId)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userService.editUserRolePerm(roleId, userId);
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
