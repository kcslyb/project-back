package cn.kcs.user.service;

import cn.kcs.user.entity.TDictionary;
import cn.kcs.user.entity.dto.DictionaryDto;

import java.util.List;

/**
 * (TDictionary)表服务接口
 *
 * @author makejava
 * @since 2019-03-14 20:34:58
 */
public interface TDictionaryService {

    /**
     * 通过ID查询单条数据
     *
     * @param dictionaryId 主键
     * @return 实例对象
     */
    TDictionary queryById(String dictionaryId);

    /**
     * 通过ID查询单条数据
     *
     * @param key
     * @return 实例对象
     */
    TDictionary getDictionaryByKey(String key);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TDictionary> queryAllByLimit(int offset, int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDictionary 实例对象
     * @return 对象列表
     */
    List<DictionaryDto> queryAll(TDictionary tDictionary);

    /**
     * 新增数据
     *
     * @param tDictionary 实例对象
     * @return 实例对象
     */
    TDictionary insert(TDictionary tDictionary);

    /**
     * 修改数据
     *
     * @param tDictionary 实例对象
     * @return 实例对象
     */
    TDictionary update(TDictionary tDictionary);

    /**
     * 通过主键删除数据
     *
     * @param dictionaryId 主键
     * @return 是否成功
     */
    boolean deleteById(String dictionaryId);

}