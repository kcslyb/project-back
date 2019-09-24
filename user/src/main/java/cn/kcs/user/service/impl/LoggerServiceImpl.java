package cn.kcs.user.service.impl;

import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.LoggerDao;
import cn.kcs.user.entity.LoggerDto;
import cn.kcs.user.service.LoggerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Logger)表服务实现类
 *
 * @author kcs
 * @date 2019-09-06 13:05:53
 */
@Service("loggerService")
public class LoggerServiceImpl implements LoggerService {
    @Resource
    private LoggerDao loggerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    @Override
    public LoggerDto queryById(String logId) {
        return this.loggerDao.queryById(logId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<LoggerDto> queryAllByLimit(LoggerDto loggerDto, int offset, int limit) {
        return this.loggerDao.queryAllByLimit(loggerDto, offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @param loggerDto logger
     * @return 对象列表
     */
    @Override
    public List<LoggerDto> queryAll(LoggerDto loggerDto) {
        return this.loggerDao.queryAll(loggerDto);
    }

    /**
     * 新增数据
     *
     * @param loggerDto 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(LoggerDto loggerDto) {
        loggerDto.setLogId(ShortUUID.generate());
        loggerDto.setLogUserId(LoginInfo.getUserId());
        loggerDto.setLogRequestTime(CustomDateUtil.currentFormatDate());
        return this.loggerDao.insert(loggerDto) > 0;
    }

    /**
     * 修改数据
     *
     * @param loggerDto 实例对象
     * @return 实例对象
     */
    @Override
    public LoggerDto update(LoggerDto loggerDto) {
        this.loggerDao.update(loggerDto);
        return this.queryById(loggerDto.getLogId());
    }

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String logId) {
        return this.loggerDao.deleteById(logId) > 0;
    }
}