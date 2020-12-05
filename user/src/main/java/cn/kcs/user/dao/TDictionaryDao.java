package cn.kcs.user.dao;

import cn.kcs.user.entity.TDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TDictionary)表数据库访问层
 *
 * @author kcs
 * @since 2019-03-14 20:34:57
 */
public interface TDictionaryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param dictionaryId 主键
     * @return 实例对象
     */
    TDictionary queryById(@Param("dictionaryId") String dictionaryId);

    /**
     * 通过ID查询单条数据
     *
     * @param key 主键
     * @return 实例对象
     */
    TDictionary getDictionaryByKey(@Param("key") String key);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TDictionary> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDictionary 实例对象
     * @return 对象列表
     */
    List<TDictionary> queryAll(TDictionary tDictionary);

    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<String> queryGroupByName();

    /**
     * 新增数据
     *
     * @param tDictionary 实例对象
     * @return 影响行数
     */
    int insert(TDictionary tDictionary);

    /**
     * 修改数据
     *
     * @param tDictionary 实例对象
     * @return 影响行数
     */
    int update(TDictionary tDictionary);

    /**
     * 通过主键删除数据
     *
     * @param dictionaryId 主键
     * @return 影响行数
     */
    int deleteById(@Param("dictionaryId") String dictionaryId);

}