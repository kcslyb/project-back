package cn.kcs.note.service.impl;

import cn.kcs.common.daoUtil.ShortUUID;
import cn.kcs.common.util.DataUtil;
import cn.kcs.note.dao.TNotepadDao;
import cn.kcs.note.entity.TNotepad;
import cn.kcs.note.service.TNotepadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TNotepad)表服务实现类
 *
 * @author makejava
 * @since 2018-12-28 14:44:25
 */
@Service("tNotepadService")
public class TNotepadServiceImpl implements TNotepadService {
    @Resource
    private TNotepadDao tNotepadDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id
     * @return 实例对象
     */
    @Override
    public TNotepad queryById(String id) {
        return this.tNotepadDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TNotepad> queryAllByLimit(TNotepad tNotepad, int offset, int limit) {
        return this.tNotepadDao.queryAllByLimit(tNotepad.getNoteType(), offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @param tNotepad
     * @return 对象列表
     */
    @Override
    public List<TNotepad> queryAll(TNotepad tNotepad) {
        return this.tNotepadDao.queryAll(tNotepad);
    }
    /**
     * 新增数据
     *
     * @param tNotepad 实例对象
     * @return 实例对象
     */
    @Override
    public TNotepad insert(TNotepad tNotepad) {
        String UUID = ShortUUID.generate();
        tNotepad.setNoteId(UUID);
        tNotepad.setNoteCreatetime(DataUtil.stringToDate(DataUtil.currentFormatDate(DataUtil.DATE_TO_STRING_DETAIAL_PATTERN), DataUtil.DATE_TO_STRING_DETAIAL_PATTERN));
        tNotepad.setNoteModifytime(tNotepad.getNoteCreatetime());
        this.tNotepadDao.insert(tNotepad);
        return tNotepad;
    }

    /**
     * 修改数据
     *
     * @param tNotepad 实例对象
     * @return 实例对象
     */
    @Override
    public TNotepad update(TNotepad tNotepad) {
        tNotepad.setNoteModifytime(DataUtil.stringToDate(DataUtil.currentFormatDate(DataUtil.DATE_TO_STRING_DETAIAL_PATTERN), DataUtil.DATE_TO_STRING_DETAIAL_PATTERN));
        this.tNotepadDao.update(tNotepad);
        return this.queryById(tNotepad.getNoteId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tNotepadDao.deleteById(id) > 0;
    }
}