package cn.kcs.user.dao;

import cn.kcs.user.entity.UserGenealogyRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserGenealogyRelation)表数据库访问层
 *
 * @author kcs
 * @since 2019-09-30 10:29:03
 */
public interface UserGenealogyRelationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userGenealogyRelationId 主键
     * @return 实例对象
     */
    UserGenealogyRelation queryById(@Param("userGenealogyRelationId") String userGenealogyRelationId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserGenealogyRelation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userGenealogyRelation 实例对象
     * @return 对象列表
     */
    List<UserGenealogyRelation> queryAll(UserGenealogyRelation userGenealogyRelation);

    /**
     * 新增数据
     *
     * @param userGenealogyRelation 实例对象
     * @return 影响行数
     */
    int insert(UserGenealogyRelation userGenealogyRelation);

    /**
     * 修改数据
     *
     * @param userGenealogyRelation 实例对象
     * @return 影响行数
     */
    int update(UserGenealogyRelation userGenealogyRelation);

    /**
     * 通过主键删除数据
     *
     * @param userGenealogyRelationId 主键
     * @return 影响行数
     */
    int deleteById(@Param("userGenealogyRelationId") String userGenealogyRelationId);

}