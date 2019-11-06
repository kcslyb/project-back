package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.Role;
import cn.kcs.user.entity.dto.RoleDto;
import cn.kcs.user.entity.dto.RolePermissionRequest;
import cn.kcs.user.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Role)表控制层
 *
 * @author kcs
 * @since 2019-10-14 10:24:42
 */
@RestController
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping()
    public ResponseEntity queryById(String id) {
        RoleDto roleDto = this.roleService.queryById(id);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param role 实例对象
     * @return 对象列表
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "通过实体查询所有数据")
    @GetMapping("query")
    public ResponseEntity queryAll(Role role) {
        List<Role> roleList = this.roleService.queryAll(role);
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    /**
     * 修改角色权限
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "修改角色权限")
    @PutMapping("update/permission")
    public ResponseEntity updateRolePermission(@RequestBody RolePermissionRequest rolePermissionRequest) {
        return roleService.updateRolePermission(rolePermissionRequest);
    }

    /**
     * 添加角色
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加角色")
    @PostMapping()
    public ResponseEntity addRole(@RequestBody Role role) {
        return roleService.insert(role);
    }

    /**
     * 修改角色
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "修改角色")
    @PutMapping()
    public ResponseEntity updateRole(@RequestBody Role role) {
        return roleService.update(role);
    }

    /**
     * 删除角色
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "删除角色")
    @DeleteMapping()
    public ResponseEntity updateRolePermission(String roleId) {
        return roleService.deleteById(roleId);
    }

    /**
     * 查询所以数据
     *
     * @return List数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询所有数据")
    @GetMapping("query/pager")
    public ResponseEntity query(String offset, String limit) {
        if (offset == null || "".equals(offset)) {
            offset = "0";
        }
        if (limit == null || "".equals(limit)) {
            limit = "10";
        }
        List<Role> roles = this.roleService.queryAllByLimit(Integer.parseInt(offset), Integer.parseInt(limit));
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

}