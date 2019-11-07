package cn.kcs.user.dao;

import cn.kcs.common.util.PageRequest;
import cn.kcs.user.entity.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Dict)表数据库访问层
 *
 * @author kcs
 * @since 2019-11-01 11:37:31
 */
public interface DictDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dict queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Dict> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dict    实例对象
     * @param pageRequest pageRequest
     * @return 对象列表
     */
    List<Dict> queryAll(@Param("dict") Dict dict, @Param("pageRequest") PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param dict 实例对象
     * @return 影响行数
     */
    int insert(Dict dict);

    /**
     * 修改数据
     *
     * @param dict 实例对象
     * @return 影响行数
     */
    int update(Dict dict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}