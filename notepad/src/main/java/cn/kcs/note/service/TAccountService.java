package cn.kcs.note.service;

import cn.kcs.note.entity.TAccount;

import java.util.List;

/**
 * (TAccount)表服务接口
 *
 * @author makejava
 * @since 2019-02-25 09:39:21
 */
public interface TAccountService {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     * @return 实例对象
     */
    TAccount queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TAccount> queryAllByLimit(TAccount tAccount, int offset, int limit, String starTime, String endTime);

    /**
     * 新增数据
     *
     * @param tAccount 实例对象
     * @return 实例对象
     */
    TAccount insert(TAccount tAccount);

    /**
     * 修改数据
     *
     * @param tAccount 实例对象
     * @return 实例对象
     */
    TAccount update(TAccount tAccount);

    /**
     * 通过主键删除数据
     *
     * @param id
     * @return 是否成功
     */
    boolean deleteById(String id);

}