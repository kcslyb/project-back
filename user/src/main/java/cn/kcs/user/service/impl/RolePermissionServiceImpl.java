package cn.kcs.user.service.impl;

import cn.kcs.user.dao.RolePermissionDao;
import cn.kcs.user.entity.RolePermission;
import cn.kcs.user.service.RolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RolePermission)表服务实现类
 *
 * @author kcs
 * @since 2019-10-14 10:25:07
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {
    @Resource
    private RolePermissionDao rolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rolePermissionId 主键
     * @return 实例对象
     */
    @Override
    public RolePermission queryById(String rolePermissionId) {
        return this.rolePermissionDao.queryById(rolePermissionId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<RolePermission> queryAllByLimit(int offset, int limit) {
        return this.rolePermissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param rolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public RolePermission insert(RolePermission rolePermission) {
        this.rolePermissionDao.insert(rolePermission);
        return rolePermission;
    }

    /**
     * 修改数据
     *
     * @param rolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public RolePermission update(RolePermission rolePermission) {
        this.rolePermissionDao.update(rolePermission);
        return this.queryById(rolePermission.getRolePermissionId());
    }

    /**
     * 通过主键删除数据
     *
     * @param rolePermissionId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String rolePermissionId) {
        return this.rolePermissionDao.deleteById(rolePermissionId) > 0;
    }
}