package cn.kcs.note.service;

import cn.kcs.note.entity.Tease;

import java.util.List;

/**
 * (Tease)表服务接口
 *
 * @author makejava
 * @since 2019-01-06 20:33:36
 */
public interface TeaseService {

    /**
     * 通过ID查询单条数据
     *
     * @param teaseId 主键
     * @return 实例对象
     */
    Tease queryById(String teaseId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Tease> queryAllByLimit(int offset, int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tease 实例对象
     * @return 对象列表
     */
    List<Tease> queryAll(Tease tease);

    /**
     * 新增数据
     *
     * @param tease 实例对象
     * @return 实例对象
     */
    Tease insert(Tease tease);

    /**
     * 修改数据
     *
     * @param tease 实例对象
     * @return 实例对象
     */
    Tease update(Tease tease);

    /**
     * 通过主键删除数据
     *
     * @param teaseId 主键
     * @return 是否成功
     */
    boolean deleteById(String teaseId);

}