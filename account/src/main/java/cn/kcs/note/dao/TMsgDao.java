package cn.kcs.note.dao;

import cn.kcs.note.entity.TMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TMsg)表数据库访问层
 *
 * @author makejava
 * @since 2018-12-30 19:47:03
 */
public interface TMsgDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     * @return 实例对象
     */
    TMsg queryById(@Param("msg_id") String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TMsg> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 查询数据
     *
     * @param sender
     * @param receiver
     * @param offset
     * @param limit
     * @return
     */
    List<TMsg> queryAll(@Param("sender") String sender, @Param("receiver") String receiver, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增数据
     *
     * @param tMsg 实例对象
     * @return 影响行数
     */
    int insert(TMsg tMsg);

    /**
     * 修改数据
     *
     * @param tMsg 实例对象
     * @return 影响行数
     */
    int update(TMsg tMsg);

    /**
     * 通过主键删除数据
     *
     * @param id
     * @return 影响行数
     */
    int deleteById(@Param("msg_id") String id);

}