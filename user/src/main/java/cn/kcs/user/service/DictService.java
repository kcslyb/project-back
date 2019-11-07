package cn.kcs.user.service;

import cn.kcs.common.util.PageRequest;
import cn.kcs.user.entity.Dict;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * (Dict)表服务接口
 *
 * @author kcs
 * @since 2019-11-01 11:37:31
 */
public interface DictService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dict queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Dict> queryAllByLimit(int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param dict    dict
     * @param pageRequest pageRequest
     * @return 对象列表
     */
    List<Dict> queryAll(Dict dict, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    ResponseEntity insert(Dict dict);

    /**
     * 修改数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    ResponseEntity update(Dict dict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}