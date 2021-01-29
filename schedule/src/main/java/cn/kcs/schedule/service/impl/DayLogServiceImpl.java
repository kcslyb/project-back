package cn.kcs.schedule.service.impl;

import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.schedule.dao.DayLogDao;
import cn.kcs.schedule.entity.DayLog;
import cn.kcs.schedule.service.DayLogService;
import cn.kcs.user.entity.Dict;
import cn.kcs.user.service.DictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (DayLog)表服务实现类
 *
 * @author makejava
 * @since 2020-12-07 15:42:31
 */
@Service("dayLogService")
public class DayLogServiceImpl implements DayLogService {
    @Resource
    private DayLogDao dayLogDao;

    @Resource
    private DictService dictService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DayLog queryById(String id) {
        return this.dayLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<DayLog> queryAllByLimit(DayLog dayLog, int offset, int limit) {
        return this.dayLogDao.queryAllByLimit(dayLog, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dayLog 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(DayLog dayLog) {
        dayLog.setDeleteFlag("0");
        dayLog.setId(ShortUUID.generate());
        dayLog.setCreateById(LoginInfo.getUserId());
        dayLog.setCreateByName(LoginInfo.getUserName());
        dayLog.setCreateTime(CustomDateUtil.currentFormatDate());
        return this.dayLogDao.insert(dayLog) > 0;
    }

    /**
     * 修改数据
     *
     * @param dayLog 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(DayLog dayLog) {
        dayLog.setUpdateById(LoginInfo.getUserId());
        dayLog.setUpdateByName(LoginInfo.getUserName());
        dayLog.setUpdateTime(CustomDateUtil.currentFormatDate());
        return this.dayLogDao.update(dayLog) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        DayLog dayLog = dayLogDao.queryById(id);
        dayLog.setDeleteFlag("1");
        return dayLogDao.update(dayLog) > 0;
    }

    @Override
    public ResponseDto queryPager(DayLog dayLog, PageRequest pageRequest) {
        dayLog.setDeleteFlag("0");
        dayLog.setCreateById(LoginInfo.getUserId());
        int size = this.dayLogDao.queryAll(dayLog).size();
        List<DayLog> dayLogs = this.queryAllByLimit(dayLog, pageRequest.getStart(), pageRequest.getSize());
        List<Dict> dictList = dictService.queryByDictGroupLabel("eventType");
        Map<String, String> collect = dictList.stream().collect(Collectors.toMap(Dict::getKey, Dict::getLabel));
        for (int i = 0; i < dayLogs.size(); i++) {
            String key = dayLogs.get(i).getReservedKeyOne();
            if (StringUtils.isNotEmpty(key)) {
                dayLogs.get(i).setReservedKeyTwo(collect.get(key));
            }
        }
        return new ResponseDto(dayLogs, size, pageRequest.getSize(), pageRequest.getStart());
    }
}