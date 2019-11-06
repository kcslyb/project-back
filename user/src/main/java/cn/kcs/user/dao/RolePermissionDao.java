package cn.kcs.user.dao;

import cn.kcs.user.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (RolePermission)表数据库访问层
 *
 * @author kcs
 * @since 2019-10-14 10:25:07
 */
public interface RolePermissionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param rolePermissionId 主键
     * @return 实例对象
     */
    RolePermission queryById(String rolePermissionId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RolePermission> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param rolePermission 实例对象
     * @return 对象列表
     */
    List<RolePermission> queryAll(RolePermission rolePermission);

    /**
     * 新增数据
     *
     * @param rolePermission 实例对象
     * @return 影响行数
     */
    int insert(RolePermission rolePermission);

    /**
     * 修改数据
     *
     * @param rolePermission 实例对象
     * @return 影响行数
     */
    int update(RolePermission rolePermission);

    /**
     * 通过主键删除数据
     *
     * @param rolePermissionId 主键
     * @return 影响行数
     */
    int deleteById(String rolePermissionId);

    /**
     * roleId
     *
     * @param roleId
     * @return
     */
    int batchDeleteByRoleId(String roleId);

    /**
     * permissionList
     *
     * @param permissionList
     * @return
     */
    int batchInsertByRolePermission(List<RolePermission> permissionList);
}