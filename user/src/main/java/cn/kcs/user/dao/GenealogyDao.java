package cn.kcs.user.dao;

import cn.kcs.user.entity.Genealogy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Genealogy)表数据库访问层
 *
 * @author kcs
 * @since 2019-09-30 10:28:30
 */
public interface GenealogyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param genealogyId 主键
     * @return 实例对象
     */
    Genealogy queryById(@Param("genealogyId") String genealogyId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Genealogy> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param genealogy 实例对象
     * @return 对象列表
     */
    List<Genealogy> queryAll(Genealogy genealogy);

    /**
     * 新增数据
     *
     * @param genealogy 实例对象
     * @return 影响行数
     */
    int insert(Genealogy genealogy);

    /**
     * 修改数据
     *
     * @param genealogy 实例对象
     * @return 影响行数
     */
    int update(Genealogy genealogy);

    /**
     * 通过主键删除数据
     *
     * @param genealogyId 主键
     * @return 影响行数
     */
    int deleteById(@Param("genealogyId") String genealogyId);

}