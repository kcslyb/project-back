package cn.kcs.note.dao;

import cn.kcs.note.entity.TNotepad;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TNotepad)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-28 14:44:25
 */
public interface TNotepadDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     * @return 实例对象
     */
    TNotepad queryById(@Param("noteId") String id);

    /**
     * 查询指定行数据
     * @param noteType 实例对象id
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TNotepad> queryAllByLimit(@Param("noteType") String noteType, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tNotepad 实例对象
     * @return 对象列表
     */
    List<TNotepad> queryAll(TNotepad tNotepad);

    /**
     * 新增数据
     *
     * @param tNotepad 实例对象
     * @return 影响行数
     */
    int insert(TNotepad tNotepad);

    /**
     * 修改数据
     *
     * @param tNotepad 实例对象
     * @return 影响行数
     */
    int update(TNotepad tNotepad);

    /**
     * 通过主键删除数据
     *
     * @param id
     * @return 影响行数
     */
    int deleteById(@Param("noteId") String id);

}