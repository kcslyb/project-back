package cn.kcs.user.service;

import cn.kcs.user.entity.PhotoAlbum;

import java.util.List;

/**
 * (PhotoAlbum)表服务接口
 *
 * @author kcs
 * @since 2019-05-08 11:33:55
 */
public interface PhotoAlbumService {

    /**
     * 通过ID查询单条数据
     *
     * @param photoId 主键
     * @return 实例对象
     */
    PhotoAlbum queryById(String photoId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<PhotoAlbum> queryAllByLimit(PhotoAlbum photoAlbum, int offset, int limit);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    int queryAll(PhotoAlbum photoAlbum);

    /**
     * 新增数据
     *
     * @param photoAlbum 实例对象
     * @return 实例对象
     */
    PhotoAlbum insert(PhotoAlbum photoAlbum);

    /**
     * 新增数据
     *
     * @param photoAlbums 实例对象
     * @return 实例对象
     */
    boolean insertBatch(List<PhotoAlbum> photoAlbums);

    /**
     * 修改数据
     *
     * @param photoAlbum 实例对象
     * @return 实例对象
     */
    PhotoAlbum update(PhotoAlbum photoAlbum);

    /**
     * 通过主键删除数据
     *
     * @param photoId 主键
     * @return 是否成功
     */
    boolean deleteById(String photoId);

}