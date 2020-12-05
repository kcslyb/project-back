package cn.kcs.user.service;

import cn.kcs.user.entity.Notepad;

import java.util.List;

/**
 * (TNotepad)表服务接口
 *
 * @author kcs
 * @since 2018-12-28 14:44:25
 */
public interface NotepadService {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     * @return 实例对象
     */
    Notepad queryById(String id);

    /**
     * 查询多条数据
     *
     * @param notepad notepad
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Notepad> queryAllByLimit(Notepad notepad, int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param notepad 实例对象
     * @return 对象列表
     */
    List<Notepad> queryAll(Notepad notepad);

    /**
     * 新增数据
     *
     * @param notepad 实例对象
     * @return 实例对象
     */
    boolean insert(Notepad notepad);

    /**
     * 修改数据
     *
     * @param notepad 实例对象
     * @return 实例对象
     */
    Notepad update(Notepad notepad);

    /**
     * 通过主键删除数据
     *
     * @param id
     * @return 是否成功
     */
    boolean deleteById(String id);

}