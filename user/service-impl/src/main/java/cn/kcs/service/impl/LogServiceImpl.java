package cn.kcs.service.impl;

import cn.kcs.business.inter.LogManager;
import cn.kcs.common.util.constants.Constants;
import cn.kcs.dao.inter.pojo.TLog;
import cn.kcs.service.inter.LogService;
import cn.kcs.service.inter.dto.LogDto;
import cn.kcs.service.inter.util.PageResponse;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: log service impl
 * @author: kcs
 * @create: 2018-11-06 16:15
 **/
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogManager logManager;

    @Override
    public LogDto add(LogDto logDto) {
        return assemblyDto(logManager.add(assembly(logDto)));
    }

    private LogDto assemblyDto(TLog tLog) {
        LogDto logDto = new LogDto();
        logDto.setLogId(tLog.getLogId());
        logDto.setLogTime(tLog.getLogTime());
        logDto.setLogUsed(tLog.getLogUsed());
        logDto.setLogOperate(tLog.getLogOperate());
        logDto.setLogAccessurl(tLog.getLogAccessurl());
        return logDto;
    }

    private TLog assembly(LogDto logDto) {
        TLog tUser = new TLog();
        tUser.setLogId(logDto.getLogId());
        tUser.setLogTime(logDto.getLogTime());
        tUser.setLogUsed(logDto.getLogUsed());
        tUser.setLogOperate(logDto.getLogOperate());
        tUser.setLogAccessurl(logDto.getLogAccessurl());
        return tUser;
    }

    @Override
    @Cacheable(value = "sys-log")
    public PageResponse<LogDto> queryPager(int start, int limit, LogDto t, OrderBy... orderArgs) {
        OrderBy orderBy = new OrderBy("log_time", false);

        Pager<TLog> list = logManager.queryPager(start, limit, assembly(t), orderBy);
        List<LogDto> listdto = new ArrayList<>();
        for (TLog tLog : list.getRecords()) {
            listdto.add(assemblyDto(tLog));
        }
        PageResponse<LogDto> pageResponse = new PageResponse(list.getCurrentPage(), list.getLimit(), list.getTotalCount(), listdto);
        return pageResponse;
    }

    @Override
    @Cacheable(value = "user-log")
    public List<LogDto> query() {
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject jsonUser = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String userName = jsonUser.getString("userName");
        TLog tLog = new TLog();
        tLog.setLogUsed(userName);
        List<LogDto> logDtos = new ArrayList<>();
        List<TLog> tLogs = logManager.query(tLog);
        for (TLog t : tLogs) {
            logDtos.add(assemblyDto(t));
        }
        return logDtos;
    }
}
