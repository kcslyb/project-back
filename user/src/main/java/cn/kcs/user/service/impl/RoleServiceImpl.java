package cn.kcs.user.service.impl;

import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.RoleDao;
import cn.kcs.user.dao.RolePermissionDao;
import cn.kcs.user.entity.Role;
import cn.kcs.user.entity.RolePermission;
import cn.kcs.user.entity.dto.RoleDto;
import cn.kcs.user.entity.dto.RolePermissionRequest;
import cn.kcs.user.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * (Role)表服务实现类
 *
 * @author kcs
 * @since 2019-10-14 10:24:42
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;
    @Resource
    private RolePermissionDao rolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public RoleDto queryById(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            return new RoleDto();
        }
        Role role = this.roleDao.queryById(roleId);
        if (role == null) {
            return new RoleDto();
        }
        return assemblyDto(role);
    }

    private RoleDto assemblyDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleName(role.getRoleName());
        roleDto.setRoleDescription(role.getRoleDescription());
        roleDto.setRoleType(role.getRoleType());
        roleDto.setRoleDeleteFlag(role.getRoleDeleteFlag());
        roleDto.setRoleCreateTime(role.getRoleCreateTime());
        roleDto.setRoleUpdateTime(role.getRoleUpdateTime());
        roleDto.setRoleCreateBy(role.getRoleCreateBy());
        roleDto.setRoleUpdateBy(role.getRoleUpdateBy());
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(role.getRoleId());
        List<RolePermission> rolePermissions = rolePermissionDao.queryAll(rolePermission);
        if (!CollectionUtils.isEmpty(rolePermissions)) {
            roleDto.setRolePermissionList(rolePermissions);
        } else {
            roleDto.setRolePermissionList(new ArrayList<>());
        }
        return roleDto;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return this.roleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public ResponseEntity insert(Role role) {
        role.setRoleId(ShortUUID.generate());
        role.setRoleDeleteFlag("1");
        role.setRoleCreateBy(LoginInfo.getUserId());
        role.setRoleCreateTime(CustomDateUtil.currentFormatDate());
        role.setRoleType("1");
        boolean flag = this.roleDao.insert(role) > 0;
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public ResponseEntity update(Role role) {
        role.setRoleUpdateBy(LoginInfo.getUserId());
        role.setRoleUpdateTime(CustomDateUtil.currentFormatDate());
        int updateLength = this.roleDao.update(role);
        boolean flag = updateLength > 0;
        if (!flag) {
            return new ResponseEntity<>("操作失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public ResponseEntity deleteById(String roleId) {
        Role role = roleDao.queryById(roleId);
        role.setRoleDeleteFlag("0");
        return update(role);
    }

    @Override
    public ResponseEntity updateRolePermission(RolePermissionRequest rolePermissionRequest) {
        if (rolePermissionRequest.getRoleId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boolean flagAdd;
        boolean flagDelete;
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(rolePermissionRequest.getRoleId());
        List<RolePermission> rolePermissions = rolePermissionDao.queryAll(rolePermission);
        if (!CollectionUtils.isEmpty(rolePermissions)) {
            int temDelete = rolePermissionDao.batchDeleteByRoleId(rolePermissionRequest.getRoleId());
            flagDelete = temDelete > 0;
            if (!flagDelete) {
                return new ResponseEntity<>("修改权限失败", HttpStatus.BAD_REQUEST);
            }
        }
        if (!CollectionUtils.isEmpty(rolePermissionRequest.getPermissionList())) {
            List<RolePermission> permissionList = rolePermissionRequest.getPermissionList();
            List<RolePermission> permissions = permissionList.stream().collect(
                    Collectors.collectingAndThen(
                            Collectors.toCollection(() -> new TreeSet<>(
                                    Comparator.comparing(RolePermission::getRolePermissionLabel))), ArrayList::new)
            );
            permissions.forEach(value -> {
                value.setRolePermissionId(ShortUUID.generate());
                if (StringUtils.isEmpty(value.getRoleId())) {
                    value.setRoleId(rolePermissionRequest.getRoleId());
                }
            });
            int temAdd = rolePermissionDao.batchInsertByRolePermission(permissions);
            flagAdd = temAdd > 0;
            if (!flagAdd) {
                return new ResponseEntity<>("修改权限失败", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public List<Role> queryAll(Role role) {
        role.setRoleDeleteFlag("1");
        return roleDao.queryAll(role);
    }
}
