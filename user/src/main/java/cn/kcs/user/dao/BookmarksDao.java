package cn.kcs.user.dao;

import cn.kcs.common.util.PageRequest;
import cn.kcs.user.entity.Bookmarks;
import cn.kcs.user.entity.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Bookmarks)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-21 11:54:44
 */
public interface BookmarksDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Bookmarks queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param bookmarks  实例对象
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    List<Bookmarks> queryAllByLimit(@Param("bookmarks") Bookmarks bookmarks, @Param("pageRequest") PageRequest pageRequest);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param bookmarks 实例对象
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    List<Bookmarks> queryAll(@Param("bookmarks") Bookmarks bookmarks, @Param("pageRequest") PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param bookmarks 实例对象
     * @return 影响行数
     */
    int insert(Bookmarks bookmarks);

    /**
     * 批量新增数据
     *
     * @param bookmarks 实例对象
     * @return 影响行数
     */
    int insertBatch(List<Bookmarks> bookmarks);


    /**
     * 修改数据
     *
     * @param bookmarks 实例对象
     * @return 影响行数
     */
    int update(Bookmarks bookmarks);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}