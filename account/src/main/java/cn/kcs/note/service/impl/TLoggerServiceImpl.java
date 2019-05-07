package cn.kcs.note.service.impl;

import cn.kcs.common.daoUtil.ShortUUID;
import cn.kcs.common.util.DataUtil;
import cn.kcs.note.dao.TLoggerDao;
import cn.kcs.note.entity.TLogger;
import cn.kcs.note.service.TLoggerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TLogger)表服务实现类
 *
 * @author makejava
 * @since 2019-04-17 11:46:47
 */
@Service("tLoggerService")
public class TLoggerServiceImpl implements TLoggerService {
    @Resource
    private TLoggerDao tLoggerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    @Override
    public TLogger queryById(String logId) {
        return this.tLoggerDao.queryById(logId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TLogger> queryAllByLimit(TLogger tLogger, int offset, int limit) {
        return this.tLoggerDao.queryAllByLimit(tLogger, offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<TLogger> queryAll(TLogger tLogger) {
        return this.tLoggerDao.queryAll(tLogger);
    }

    /**
     * 新增数据
     *
     * @param tLogger 实例对象
     * @return 实例对象
     */
    @Override
    public TLogger insert(TLogger tLogger) {
        tLogger.setLogId(ShortUUID.generate());
        tLogger.setLogAccessTime(DataUtil.currentFormatDate());
        this.tLoggerDao.insert(tLogger);
        return tLogger;
    }

    /**
     * 修改数据
     *
     * @param tLogger 实例对象
     * @return 实例对象
     */
    @Override
    public TLogger update(TLogger tLogger) {
        this.tLoggerDao.update(tLogger);
        return this.queryById(tLogger.getLogId());
    }

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String logId) {
        return this.tLoggerDao.deleteById(logId) > 0;
    }
}