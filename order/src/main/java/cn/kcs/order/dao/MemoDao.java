package cn.kcs.order.dao;

import cn.kcs.common.util.PageRequest;
import cn.kcs.order.entity.Memo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Memo)表数据库访问层
 *
 * @author kcs
 * @since 2019-11-08 18:23:51
 */
public interface MemoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Memo queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param memo        memo
     * @param pageRequest page request
     * @return 对象列表
     */
    List<Memo> queryPager(@Param("memo") Memo memo, @Param("pageRequest") PageRequest pageRequest);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param memo        memo
     * @param pageRequest page request
     * @return 对象列表
     */
    List<Memo> queryAll(@Param("memo") Memo memo, @Param("pageRequest") PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param memo 实例对象
     * @return 影响行数
     */
    int insert(Memo memo);

    /**
     * 修改数据
     *
     * @param memo 实例对象
     * @return 影响行数
     */
    int update(Memo memo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}