package cn.kcs.user.service;

import cn.kcs.user.entity.Genealogy;

import java.util.List;

/**
 * (Genealogy)表服务接口
 *
 * @author kcs
 * @since 2019-09-30 10:28:30
 */
public interface GenealogyService {

    /**
     * 通过ID查询单条数据
     *
     * @param genealogyId 主键
     * @return 实例对象
     */
    Genealogy queryById(String genealogyId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Genealogy> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param genealogy 实例对象
     * @return 实例对象
     */
    boolean insert(Genealogy genealogy);

    /**
     * 修改数据
     *
     * @param genealogy 实例对象
     * @return 实例对象
     */
    Genealogy update(Genealogy genealogy);

    /**
     * 通过主键删除数据
     *
     * @param genealogyId 主键
     * @return 是否成功
     */
    boolean deleteById(String genealogyId);

    /**
     * queryAll
     *
     * @param genealogy genealogy
     * @return
     */
    List<Genealogy> queryAll(Genealogy genealogy);
}