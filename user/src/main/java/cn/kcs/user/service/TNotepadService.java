package cn.kcs.user.service;

import cn.kcs.user.entity.TNotepad;

import java.util.List;

/**
 * (TNotepad)表服务接口
 *
 * @author makejava
 * @since 2018-12-28 14:44:25
 */
public interface TNotepadService {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     * @return 实例对象
     */
    TNotepad queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TNotepad> queryAllByLimit(TNotepad tNotepad, int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param tNotepad 实例对象
     * @return 对象列表
     */
    List<TNotepad> queryAll(TNotepad tNotepad);

    /**
     * 新增数据
     *
     * @param tNotepad 实例对象
     * @return 实例对象
     */
    TNotepad insert(TNotepad tNotepad);

    /**
     * 修改数据
     *
     * @param tNotepad 实例对象
     * @return 实例对象
     */
    TNotepad update(TNotepad tNotepad);

    /**
     * 通过主键删除数据
     *
     * @param id
     * @return 是否成功
     */
    boolean deleteById(String id);

}