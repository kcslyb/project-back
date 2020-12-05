package cn.kcs.user.service.impl;

import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.NotepadDao;
import cn.kcs.user.entity.Notepad;
import cn.kcs.user.service.NotepadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TNotepad)表服务实现类
 *
 * @author kcs
 * @since 2018-12-28 14:44:25
 */
@Service("tNotepadService")
public class NotepadServiceImpl implements NotepadService {
    @Resource
    private NotepadDao notepadDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id
     * @return 实例对象
     */
    @Override
    public Notepad queryById(String id) {
        return this.notepadDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Notepad> queryAllByLimit(Notepad notepad, int offset, int limit) {
        return this.notepadDao.queryAllByLimit(notepad.getNoteType(), offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @param notepad
     * @return 对象列表
     */
    @Override
    public List<Notepad> queryAll(Notepad notepad) {
        return this.notepadDao.queryAll(notepad);
    }

    /**
     * 新增数据
     *
     * @param notepad 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Notepad notepad) {
        notepad.setNoteId(ShortUUID.generate());
        notepad.setNoteCreateTime(CustomDateUtil.currentFormatDate());
        notepad.setNoteModifyTime(notepad.getNoteCreateTime());
        int insert = this.notepadDao.insert(notepad);
        return insert > 0;
    }

    /**
     * 修改数据
     *
     * @param notepad 实例对象
     * @return 实例对象
     */
    @Override
    public Notepad update(Notepad notepad) {
        notepad.setNoteModifyTime(CustomDateUtil.currentFormatDate());
        this.notepadDao.update(notepad);
        return this.queryById(notepad.getNoteId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.notepadDao.deleteById(id) > 0;
    }
}