package cn.kcs.note.controller;

import cn.kcs.common.util.ListUtil;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.note.dao.TRolePermissionDao;
import cn.kcs.note.entity.TPermissionMenu;
import cn.kcs.note.entity.TRole;
import cn.kcs.note.entity.dto.RolePermission;
import cn.kcs.note.service.TRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (TRole)表控制层
 *
 * @author makejava
 * @since 2019-03-22 10:37:46
 */
@Api(value = "user role", description = "用户角色 API")
@RestController
@RequestMapping("user/role")
public class TRoleController {
    /**
     * 服务对象
     */
    @Resource
    private TRoleService tRoleService;

    @Resource
    private TRolePermissionDao tRolePermissionDao;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping()
    public TRole selectOne(String id) {
        return this.tRoleService.queryById(id);
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
    public List<TRole> queryAll(TRole role) {
        return this.tRoleService.queryAll(role);
    }

    /**
     * 通过实体作为筛选条件查询权限
     *
     * @param role 实例对象
     * @return 对象列表
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "通过实体查询角色权限")
    @GetMapping("query/permission")
    public List<RolePermission> queryAllPermissionByRole(TRole role) {
        return this.tRoleService.queryAllPermissionByRole(role);
    }

    /**
     * 修改角色权限
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "修改角色权限")
    @PostMapping("update/permission")
    public void updateRolePermission(TRole role) {
        if (role.getRoleId() == null) return;
        List<RolePermission> rolePermissions = this.tRoleService.queryAllPermissionByRole(role);
        if (ListUtil.notNullAadEmpty(rolePermissions) && ListUtil.notNullAadEmpty(rolePermissions.get(0).getPermissions())) {
            List<String> list = new ArrayList<>();
            for (TPermissionMenu permission : rolePermissions.get(0).getPermissions()) {
                if (permission != null) {
                    list.add(permission.getPermid());
                }
            }
            tRoleService.deletePermissionByIds(list, role.getRoleId());
        }
        if (ListUtil.notNullAadEmpty(role.getPermissionIds())) {
            tRoleService.addPermissionByIds(role.getPermissionIds(), role.getRoleId());
        }

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
    public TRole addAccount(@RequestBody TRole role) {
        return this.tRoleService.insert(role);
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
    public boolean delete(@PathVariable String id) {
        return this.tRoleService.deleteById(id);
    }

    /**
     * 修改单条数据
     *
     * @param role
     * @return
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "修改单条数据")
    @PutMapping("/{id}")
    public TRole editAccount(@RequestBody TRole role, @PathVariable String id) {
        return this.tRoleService.update(role);
    }

    /**
     * 查询所以数据
     *
     * @return List数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询所以数据")
    @GetMapping("query/pager")
    public List<TRole> query(String offset, String limit) {
        if (offset == null || "".equals(offset)) {
            offset = "0";
        }
        if (limit == null || "".equals(limit)) {
            limit = "10";
        }
        List<TRole> tAccounts = this.tRoleService.queryAllByLimit(Integer.parseInt(offset), Integer.parseInt(limit));
        return tAccounts;
    }

}