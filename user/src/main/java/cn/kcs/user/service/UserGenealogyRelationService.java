package cn.kcs.user.service;

import cn.kcs.user.entity.UserGenealogyRelation;

import java.util.List;

/**
 * (UserGenealogyRelation)表服务接口
 *
 * @author kcs
 * @since 2019-09-30 10:29:03
 */
public interface UserGenealogyRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param userGenealogyRelationId 主键
     * @return 实例对象
     */
    UserGenealogyRelation queryById(String userGenealogyRelationId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserGenealogyRelation> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userGenealogyRelation 实例对象
     * @return 实例对象
     */
    UserGenealogyRelation insert(UserGenealogyRelation userGenealogyRelation);

    /**
     * 修改数据
     *
     * @param userGenealogyRelation 实例对象
     * @return 实例对象
     */
    UserGenealogyRelation update(UserGenealogyRelation userGenealogyRelation);

    /**
     * 通过主键删除数据
     *
     * @param userGenealogyRelationId 主键
     * @return 是否成功
     */
    boolean deleteById(String userGenealogyRelationId);

}