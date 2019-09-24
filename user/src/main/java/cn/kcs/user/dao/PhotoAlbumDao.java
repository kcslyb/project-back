package cn.kcs.user.dao;

import cn.kcs.user.entity.PhotoAlbum;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * (PhotoAlbum)表数据库访问层
 *
 * @author makejava
 * @since 2019-05-08 11:33:55
 */
public interface PhotoAlbumDao {

    /**
     * 通过ID查询单条数据
     *
     * @param photoId 主键
     * @return 实例对象
     */
    PhotoAlbum queryById(String photoId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Cacheable(value = "photo")
    List<PhotoAlbum> queryAllByLimit(@Param("photo") PhotoAlbum photoAlbum, @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param photoAlbum 实例对象
     * @return 对象列表
     */
    @Cacheable(value = "photo")
    List<PhotoAlbum> queryAll(PhotoAlbum photoAlbum);

    /**
     * 新增数据
     *
     * @param photoAlbum 实例对象
     * @return 影响行数
     */
    @CacheEvict(value = "photo", allEntries = true)
    int insert(PhotoAlbum photoAlbum);

    /**
     * 批量新增数据
     *
     * @param photoAlbums 实例对象
     * @return 影响行数
     */
    @CacheEvict(value = "photo", allEntries = true)
    int insertBatch(@Param("list") List<PhotoAlbum> photoAlbums);

    /**
     * 修改数据
     *
     * @param photoAlbum 实例对象
     * @return 影响行数
     */
    @CachePut(value = "photo")
    int update(PhotoAlbum photoAlbum);

    /**
     * 通过主键删除数据
     *
     * @param photoId 主键
     * @return 影响行数
     */
    @CacheEvict(value = "photo", allEntries = true)
    int deleteById(String photoId);

}