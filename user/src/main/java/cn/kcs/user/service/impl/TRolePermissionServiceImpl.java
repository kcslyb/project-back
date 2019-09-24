package cn.kcs.user.service.impl;

import cn.kcs.user.dao.TRolePermissionDao;
import cn.kcs.user.entity.TRolePermission;
import cn.kcs.user.service.TRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TRolePermission)表服务实现类
 *
 * @author makejava
 * @since 2019-03-23 18:45:08
 */
@Service("tRolePermissionService")
public class TRolePermissionServiceImpl implements TRolePermissionService {
    @Resource
    private TRolePermissionDao tRolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rolePermissionId 主键
     * @return 实例对象
     */
    @Override
    public TRolePermission queryById(String rolePermissionId) {
        return this.tRolePermissionDao.queryById(rolePermissionId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TRolePermission> queryAllByLimit(int offset, int limit) {
        return this.tRolePermissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public TRolePermission insert(TRolePermission tRolePermission) {
        this.tRolePermissionDao.insert(tRolePermission);
        return tRolePermission;
    }

    /**
     * 修改数据
     *
     * @param tRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public TRolePermission update(TRolePermission tRolePermission) {
        this.tRolePermissionDao.update(tRolePermission);
        return this.queryById(tRolePermission.getRolePermissionId());
    }

    /**
     * 通过主键删除数据
     *
     * @param rolePermissionId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String rolePermissionId) {
        return this.tRolePermissionDao.deleteById(rolePermissionId) > 0;
    }
}