package cn.kcs.user.service.impl;

import cn.kcs.common.fileutil.FileToBase64;
import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.util.PageRequest;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.BookmarksDao;
import cn.kcs.user.entity.Bookmarks;
import cn.kcs.user.service.BookmarksService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * (Bookmarks)表服务实现类
 *
 * @author makejava
 * @since 2021-05-21 11:54:44
 */
@Service("bookmarksService")
public class BookmarksServiceImpl implements BookmarksService {
    @Resource
    private BookmarksDao bookmarksDao;

    @Value("${upload_folder_path}")
    private String UPLOAD_FOLDER_PATH;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Bookmarks queryById(String id) {
        Bookmarks bookmarks = this.bookmarksDao.queryById(id);
        if (StringUtils.isEmpty(bookmarks.getId())) {
            throw new RuntimeException("主键不存在");
        }
        return bookmarks;
    }

    /**
     * 查询多条数据
     *
     * @param bookmarks   实例对象
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    @Override
    public List<Bookmarks> queryAllByLimit(Bookmarks bookmarks, PageRequest pageRequest) {
        List<Bookmarks> bookmarksList = this.bookmarksDao.queryAllByLimit(bookmarks, pageRequest);
        List<Bookmarks> temp = new ArrayList<>();
        for (int i = 0; i < bookmarksList.size() - 1; i++) {
            temp.add(this.assemblyDto(bookmarksList.get(i)));
        }
        return temp;
    }

    private Bookmarks assemblyDto(Bookmarks bookmarks) {
        if (StringUtils.isNotEmpty(bookmarks.getIconFileId())) {
            String base64 = null;
            try {
                base64 = "data:image/png;base64," + FileToBase64.encodeBase64File(bookmarks.getIconFileId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            bookmarks.setIconFileId(base64);
        }
        return bookmarks;
    }

    /**
     * 查询多条数据
     *
     * @param bookmarks   实例对象
     * @param pageRequest 查询条件
     * @return 对象列表
     */
    @Override
    public List<Bookmarks> queryAll(Bookmarks bookmarks, PageRequest pageRequest) {
        return this.bookmarksDao.queryAll(bookmarks, pageRequest);
    }

    /**
     * 新增数据
     *
     * @param bookmarks 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Bookmarks bookmarks) {
        return this.bookmarksDao.insert(this.accObj(bookmarks)) > 0;
    }

    public Bookmarks accObj(Bookmarks bookmarks) {
        bookmarks.setId(ShortUUID.generate());
        String userId = LoginInfo.getUserId();
        if (!userId.isEmpty()) {
            bookmarks.setCreateBy(userId);
            bookmarks.setCommonFlag("0");
        } else {
            bookmarks.setCommonFlag("1");
        }
        String base64 = bookmarks.getIconFileId();
        if (StringUtils.isNotEmpty(base64)) {
            File file = null;
            BufferedOutputStream bos = null;
            java.io.FileOutputStream fos = null;
            try {
                //如果没有files文件夹，则创建
                if (!Files.isWritable(Paths.get(UPLOAD_FOLDER_PATH + "/icon"))) {
                    Files.createDirectories(Paths.get(UPLOAD_FOLDER_PATH + "/icon"));
                }
                Path path = Paths.get(UPLOAD_FOLDER_PATH + "/icon/" + bookmarks.getId() + ".png");
                byte[] bytes = Base64.decodeBase64(base64.substring(base64.indexOf(",") + 1));
                file = new File(path.toString());
                fos = new java.io.FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                bos.write(bytes);
                bos.close();
                bookmarks.setIconFileId(path.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        bookmarks.setCreateTime(CustomDateUtil.currentFormatDate());
        bookmarks.setDeleteFlag("0");
        bookmarks.setSort("0");
        return bookmarks;
    }

    @Override
    public boolean insertBatch(List<Bookmarks> bookmarksList) {
        List<Bookmarks> temp = new ArrayList<>();
        for (int i = 0; i < bookmarksList.size() - 1; i++) {
            temp.add(this.accObj(bookmarksList.get(i)));
        }
        return this.bookmarksDao.insertBatch(temp) > 0;
    }

    /**
     * 修改数据
     *
     * @param bookmarks 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(Bookmarks bookmarks) {
        return this.bookmarksDao.update(bookmarks) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        Bookmarks bookmarks = this.queryById(id);
        if (bookmarks.getId().isEmpty()) {
            throw new RuntimeException("主键不存在");
        }
        bookmarks.setDeleteFlag("1");
        return update(bookmarks);
    }

    @Override
    public boolean click(String id) {
        Bookmarks bookmarks = this.queryById(id);
        if (bookmarks.getId().isEmpty()) {
            throw new RuntimeException("主键不存在");
        }
        int num = Integer.parseInt(bookmarks.getSort()) + 1;
        bookmarks.setSort(String.valueOf(num));
        return update(bookmarks);
    }
}