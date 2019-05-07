package cn.kcs.note.service.impl;

import cn.kcs.common.daoUtil.ShortUUID;
import cn.kcs.note.dao.TPermissionMenuDao;
import cn.kcs.note.dao.TRoleDao;
import cn.kcs.note.dao.TRolePermissionDao;
import cn.kcs.note.entity.TPermissionMenu;
import cn.kcs.note.entity.TRole;
import cn.kcs.note.entity.TRolePermission;
import cn.kcs.note.entity.dto.RolePermission;
import cn.kcs.note.service.TRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (TRole)表服务实现类
 *
 * @author makejava
 * @since 2019-03-22 10:37:46
 */
@Service("tRoleService")
public class TRoleServiceImpl implements TRoleService {
    @Resource
    private TRoleDao tRoleDao;

    @Resource
    private TRolePermissionDao tRolePermissionDao;

    @Resource
    private TPermissionMenuDao tPermissionMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public TRole queryById(String roleId) {
        return this.tRoleDao.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TRole> queryAllByLimit(int offset, int limit) {
        return this.tRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @param role
     * @return 对象列表
     */
    @Override
    public List<TRole> queryAll(TRole role) {
        return this.tRoleDao.queryAll(role);
    }

    /**
     * 通过实体作为筛选条件查询权限
     *
     * @param role 实例对象
     * @return 对象列表
     */
    public List<RolePermission> queryAllPermissionByRole(TRole role) {
        List<RolePermission> rolePermissions = new ArrayList<>();
        if (role.getRoleId() != null && !"".equals(role.getRoleId())) {
            RolePermission rolePermission = new RolePermission();
            TPermissionMenu permissionMenu = new TPermissionMenu();
            permissionMenu.setPermchild(role.getRoleId());
            role = tRoleDao.queryById(role.getRoleId());
            List<TPermissionMenu> tPermissionMenus = tPermissionMenuDao.queryAllPermissionByRole(role.getRoleId());
            rolePermission.setRoleId(role.getRoleId());
            rolePermission.setRoleName(role.getRoleName());
            rolePermission.setRoleDescription(role.getRoleDescription());
            rolePermission.setPermissions(tPermissionMenus);
            rolePermissions.add(rolePermission);
            return rolePermissions;
        } else {
            List<TRole> tRoles = tRoleDao.queryAll(new TRole());
            if (!tRoles.isEmpty()) {
                for (TRole r : tRoles) {
                    RolePermission rolePermission = new RolePermission();
                    List<TPermissionMenu> tPermissionMenus = tPermissionMenuDao.queryAllPermissionByRole(r.getRoleId());
                    rolePermission.setRoleId(r.getRoleId());
                    rolePermission.setRoleName(r.getRoleName());
                    rolePermission.setRoleDescription(r.getRoleDescription());
                    rolePermission.setPermissions(tPermissionMenus);
                    rolePermissions.add(rolePermission);
                }
                return rolePermissions;
            }
        }
        return null;
    }

    /**
     * 新增数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    @Override
    public TRole insert(TRole tRole) {
        this.tRoleDao.insert(tRole);
        return tRole;
    }

    /**
     * 修改数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    @Override
    public TRole update(TRole tRole) {
        this.tRoleDao.update(tRole);
        return this.queryById(tRole.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String roleId) {
        return this.tRoleDao.deleteById(roleId) > 0;
    }

    @Override
    public void deletePermissionByIds(List<String> ids, String roleId) {
        List<String> list = new ArrayList<>();
        for (String s : ids) {
            TRolePermission tRolePermission = new TRolePermission();
            tRolePermission.setRoleId(roleId);
            tRolePermission.setPermissionId(s);
            List<TRolePermission> tRolePermissions = tRolePermissionDao.queryAll(tRolePermission);
            if (tRolePermissions.size() > 0) {
                list.add(tRolePermissions.get(0).getRolePermissionId());
            }
        }
        for (String s : list) {
            this.tRolePermissionDao.deleteById(s);
        }
    }

    @Override
    public void addPermissionByIds(List<String> ids, String roleId) {
        for (String s : ids) {
            TRolePermission rolePermission = new TRolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(s);
            rolePermission.setRolePermissionId(ShortUUID.generate());
            this.tRolePermissionDao.insert(rolePermission);
        }
    }
}