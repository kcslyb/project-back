package cn.kcs.user.service;

import cn.kcs.user.entity.TRolePermission;

import java.util.List;

/**
 * (TRolePermission)表服务接口
 *
 * @author makejava
 * @since 2019-03-23 18:45:08
 */
public interface TRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param rolePermissionId 主键
     * @return 实例对象
     */
    TRolePermission queryById(String rolePermissionId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TRolePermission> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tRolePermission 实例对象
     * @return 实例对象
     */
    TRolePermission insert(TRolePermission tRolePermission);

    /**
     * 修改数据
     *
     * @param tRolePermission 实例对象
     * @return 实例对象
     */
    TRolePermission update(TRolePermission tRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param rolePermissionId 主键
     * @return 是否成功
     */
    boolean deleteById(String rolePermissionId);

}