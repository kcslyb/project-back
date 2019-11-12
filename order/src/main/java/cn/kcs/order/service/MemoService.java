package cn.kcs.order.service;

import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.order.entity.Memo;

import java.util.List;

/**
 * (Memo)表服务接口
 *
 * @author kcs
 * @since 2019-11-08 18:23:52
 */
public interface MemoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Memo queryById(String id);

    /**
     * 查询多条数据
     *
     * @param memo        memo
     * @param pageRequest page request
     * @return 对象列表
     */
    ResponseDto queryPager(Memo memo, PageRequest pageRequest);

    /**
     * 查询多条数据
     *
     * @param memo        memo
     * @param pageRequest page request
     * @return 对象列表
     */
    List<Memo> queryAll(Memo memo, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param memo 实例对象
     * @return 实例对象
     */
    boolean insert(Memo memo);

    /**
     * 修改数据
     *
     * @param memo 实例对象
     * @return 实例对象
     */
    boolean update(Memo memo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);
}