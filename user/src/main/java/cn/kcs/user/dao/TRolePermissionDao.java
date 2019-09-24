package cn.kcs.user.dao;

import cn.kcs.user.entity.TRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TRolePermission)表数据库访问层
 *
 * @author makejava
 * @since 2019-03-23 18:45:08
 */
public interface TRolePermissionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param rolePermissionId 主键
     * @return 实例对象
     */
    TRolePermission queryById(@Param("rolePermissionId") String rolePermissionId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TRolePermission> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tRolePermission 实例对象
     * @return 对象列表
     */
    List<TRolePermission> queryAll(TRolePermission tRolePermission);

    /**
     * 新增数据
     *
     * @param tRolePermission 实例对象
     * @return 影响行数
     */
    int insert(TRolePermission tRolePermission);

    /**
     * 修改数据
     *
     * @param tRolePermission 实例对象
     * @return 影响行数
     */
    int update(TRolePermission tRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param rolePermissionId 主键
     * @return 影响行数
     */
    int deleteById(@Param("rolePermissionId") String rolePermissionId);

}