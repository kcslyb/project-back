package cn.kcs.note.service.impl;

import cn.kcs.common.daoUtil.ShortUUID;
import cn.kcs.common.loginInfo.LoginInfo;
import cn.kcs.common.util.DataUtil;
import cn.kcs.note.dao.PhotoAlbumDao;
import cn.kcs.note.entity.PhotoAlbum;
import cn.kcs.note.service.PhotoAlbumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (PhotoAlbum)表服务实现类
 *
 * @author makejava
 * @since 2019-05-08 11:33:55
 */
@Service("photoAlbumService")
public class PhotoAlbumServiceImpl implements PhotoAlbumService {
    @Resource
    private PhotoAlbumDao photoAlbumDao;

    /**
     * 通过ID查询单条数据
     *
     * @param photoId 主键
     * @return 实例对象
     */
    @Override
    public PhotoAlbum queryById(String photoId) {
        return this.photoAlbumDao.queryById(photoId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<PhotoAlbum> queryAllByLimit(PhotoAlbum photoAlbum, int offset, int limit) {
        List<PhotoAlbum> photoAlbums = this.photoAlbumDao.queryAllByLimit(photoAlbum, offset, limit);
        return photoAlbums;
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public int queryAll(PhotoAlbum photoAlbum) {
        List<PhotoAlbum> photoAlbums = this.photoAlbumDao.queryAll(photoAlbum);
        return photoAlbums.size();
    }

    /**
     * 新增数据
     *
     * @param photoAlbums 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insertBatch(List<PhotoAlbum> photoAlbums) {
        for (PhotoAlbum photoAlbum : photoAlbums) {
            photoAlbum.setPhotoId(ShortUUID.generate());
            photoAlbum.setPhotoUploadTime(DataUtil.currentFormatDate());
            photoAlbum.setPhotoUploadBy(LoginInfo.getUserId());
        }
        return this.photoAlbumDao.insertBatch(photoAlbums) > 0;
    }

    /**
     * 新增数据
     *
     * @param photoAlbum 实例对象
     * @return 实例对象
     */
    @Override
    public PhotoAlbum insert(PhotoAlbum photoAlbum) {
        photoAlbum.setPhotoId(ShortUUID.generate());
        photoAlbum.setPhotoUploadTime(DataUtil.currentFormatDate());
        photoAlbum.setPhotoUploadBy(LoginInfo.getUserId());
        this.photoAlbumDao.insert(photoAlbum);
        return photoAlbum;
    }

    /**
     * 修改数据
     *
     * @param photoAlbum 实例对象
     * @return 实例对象
     */
    @Override
    public PhotoAlbum update(PhotoAlbum photoAlbum) {
        this.photoAlbumDao.update(photoAlbum);
        return this.queryById(photoAlbum.getPhotoId());
    }

    /**
     * 通过主键删除数据
     *
     * @param photoId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String photoId) {
        return this.photoAlbumDao.deleteById(photoId) > 0;
    }
}