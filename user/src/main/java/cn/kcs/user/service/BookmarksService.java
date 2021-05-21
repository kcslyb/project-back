package cn.kcs.user.service;

import cn.kcs.common.util.PageRequest;
import cn.kcs.user.entity.Bookmarks;
import cn.kcs.user.entity.PhotoAlbum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Bookmarks)表服务接口
 *
 * @author makejava
 * @since 2021-05-21 11:54:44
 */
public interface BookmarksService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Bookmarks queryById(String id);

    /**
     * 查询多条数据
     *
     * @param bookmarks 实例对象
     * @param pageRequest  查询条件
     * @return 对象列表
     */
    List<Bookmarks> queryAllByLimit(Bookmarks bookmarks, PageRequest pageRequest);

    /**
     * 查询多条数据
     *
     * @param bookmarks 实例对象
     * @param pageRequest  查询条件
     * @return 对象列表
     */
    List<Bookmarks> queryAll(Bookmarks bookmarks, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param bookmarks 实例对象
     * @return 实例对象
     */
    boolean insert(Bookmarks bookmarks);

    /**
     * 批量新增数据
     *
     * @param bookmarks 实例对象
     * @return 是否成功
     */
    boolean insertBatch(List<Bookmarks> bookmarks);

    /**
     * 修改数据
     *
     * @param bookmarks 实例对象
     * @return 实例对象
     */
    boolean update(Bookmarks bookmarks);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    boolean click(String id);
}