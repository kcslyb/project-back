package cn.kcs.user.service;

import cn.kcs.user.entity.TPermissionMenu;

import java.util.List;

/**
 * (TPermissionMenu)表服务接口
 *
 * @author makejava
 * @since 2019-03-08 09:55:53
 */
public interface TPermissionMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param permid 主键
     * @return 实例对象
     */
    TPermissionMenu queryById(String permid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TPermissionMenu> queryAllByLimit(int offset, int limit);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<TPermissionMenu> queryAll(TPermissionMenu tPermissionMenu);

    /**
     * 新增数据
     *
     * @param tPermissionMenu 实例对象
     * @return 实例对象
     */
    TPermissionMenu insert(TPermissionMenu tPermissionMenu);

    /**
     * 修改数据
     *
     * @param tPermissionMenu 实例对象
     * @return 实例对象
     */
    TPermissionMenu update(TPermissionMenu tPermissionMenu);

    /**
     * 通过主键删除数据
     *
     * @param permid 主键
     * @return 是否成功
     */
    boolean deleteById(String permid);

}