package cn.kcs.business.impl;

import cn.kcs.business.inter.LogManager;
import cn.kcs.dao.inter.TLogDao;
import cn.kcs.dao.inter.UUIDutil.StringIdGenerator;
import cn.kcs.dao.inter.pojo.TLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.InsertContext;

import java.util.List;

/**
 * @description: log manager impl
 * @author: kcs
 * @create: 2018-11-06 16:04
 **/
@Component
public class LogManagerImpl implements LogManager {

    @Autowired
    private TLogDao tLogDao;

    @Override
    public TLog add(TLog tLog) {
        tLog.setLogId(new StringIdGenerator().generate(new InsertContext()));
        return tLogDao.add(tLog);
    }

    @Override
    public Pager<TLog> queryPager(int start, int limit, TLog t, OrderBy... orderArgs) {
        return tLogDao.queryPager(start, limit, t, orderArgs);
    }

    @Override
    public List<TLog> query(TLog t, OrderBy... orderArgs) {
        return tLogDao.query(t, orderArgs);
    }
}
