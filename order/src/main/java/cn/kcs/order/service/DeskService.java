package cn.kcs.order.service;

import cn.kcs.order.entity.Desk;

import java.util.List;

/**
 * (Desk)表服务接口
 *
 * @author kcs
 * @since 2019-04-24 14:35:14
 */
public interface DeskService {

    /**
     * 通过ID查询单条数据
     *
     * @param deskId 主键
     * @return 实例对象
     */
    Desk queryById(String deskId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Desk> queryAllByLimit(Desk desk, int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param desk
     * @return 对象列表
     */
    int queryAll(Desk desk);

    /**
     * 新增数据
     *
     * @param desk 实例对象
     * @return 实例对象
     */
    Desk insert(Desk desk);

    /**
     * 修改数据
     *
     * @param desk 实例对象
     * @return 实例对象
     */
    Desk update(Desk desk);

    /**
     * 通过主键删除数据
     *
     * @param deskId 主键
     * @return 是否成功
     */
    boolean deleteById(String deskId);

}