package cn.kcs.note.dao;

import cn.kcs.note.entity.TPermissionMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TPermissionMenu)表数据库访问层
 *
 * @author makejava
 * @since 2019-03-08 09:55:53
 */
public interface TPermissionMenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param permid 主键
     * @return 实例对象
     */
    TPermissionMenu queryById(@Param("permid") String permid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TPermissionMenu> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tPermissionMenu 实例对象
     * @return 对象列表
     */
    List<TPermissionMenu> queryAll(TPermissionMenu tPermissionMenu);

    /**
     * 新增数据
     *
     * @param tPermissionMenu 实例对象
     * @return 影响行数
     */
    int insert(TPermissionMenu tPermissionMenu);

    /**
     * 修改数据
     *
     * @param tPermissionMenu 实例对象
     * @return 影响行数
     */
    int update(TPermissionMenu tPermissionMenu);

    /**
     * 通过主键删除数据
     *
     * @param permid 主键
     * @return 影响行数
     */
    int deleteById(@Param("permid") String permid);

    /**
     * 根据角色ID查询角色权限
     *
     * @param roleId 主键
     * @return 影响行数
     */
    List<TPermissionMenu> queryAllPermissionByRole(@Param("roleId") String roleId);
}