package cn.kcs.note.service;

import cn.kcs.note.entity.TRole;
import cn.kcs.note.entity.dto.RolePermission;

import java.util.List;

/**
 * (TRole)表服务接口
 *
 * @author makejava
 * @since 2019-03-22 10:37:46
 */
public interface TRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    TRole queryById(String roleId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TRole> queryAllByLimit(int offset, int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param role 实例对象
     * @return 对象列表
     */
    List<TRole> queryAll(TRole role);

    /**
     * 通过实体作为筛选条件查询权限
     *
     * @param role 实例对象
     * @return 对象列表
     */
    List<RolePermission> queryAllPermissionByRole(TRole role);

    /**
     * 新增数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    TRole insert(TRole tRole);

    /**
     * 修改数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    TRole update(TRole tRole);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(String roleId);

    void deletePermissionByIds(List<String> ids, String roleId);

    void addPermissionByIds(List<String> ids, String roleId);
}