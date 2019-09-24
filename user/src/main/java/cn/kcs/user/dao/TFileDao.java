package cn.kcs.user.dao;

import cn.kcs.user.entity.TFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TFile)表数据库访问层
 *
 * @author makejava
 * @since 2019-04-19 15:17:07
 */
public interface TFileDao {

    /**
     * 通过ID查询单条数据
     *
     * @param fileId 主键
     * @return 实例对象
     */
    TFile queryById(@Param("fileId") String fileId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TFile> queryAllByLimit(@Param("file") TFile tFile, @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tFile 实例对象
     * @return 对象列表
     */
    List<TFile> queryAll(TFile tFile);

    /**
     * 新增数据
     *
     * @param tFile 实例对象
     * @return 影响行数
     */
    int insert(TFile tFile);

    /**
     * 修改数据
     *
     * @param tFile 实例对象
     * @return 影响行数
     */
    int update(TFile tFile);

    /**
     * 通过主键删除数据
     *
     * @param fileId 主键
     * @return 影响行数
     */
    int deleteById(@Param("fileId") String fileId);

}