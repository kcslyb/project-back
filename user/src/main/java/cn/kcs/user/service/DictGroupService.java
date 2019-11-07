package cn.kcs.user.service;

import cn.kcs.common.util.PageRequest;
import cn.kcs.user.entity.DictGroup;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * (DictGroup)表服务接口
 *
 * @author kcs
 * @since 2019-11-01 11:37:54
 */
public interface DictGroupService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DictGroup queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DictGroup> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param dictGroup 实例对象
     * @return 实例对象
     */
    ResponseEntity insert(DictGroup dictGroup);

    /**
     * 修改数据
     *
     * @param dictGroup 实例对象
     * @return 实例对象
     */
    ResponseEntity update(DictGroup dictGroup);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 查询多条数据
     *
     * @param dictGroup dictGroup
     * @param pageRequest pageRequest
     * @return 对象列表
     */
    List<DictGroup> queryAll(DictGroup dictGroup, PageRequest pageRequest);

    /**
     * 查询多条数据
     *
     * @param groupLabel groupLabel
     * @return 对象列表
     */
    List<DictGroup> queryGroupByLabel(String groupLabel);
}