package cn.kcs.business.impl;

import cn.kcs.business.inter.DeskManager;
import cn.kcs.dao.inter.TDeskDao;
import cn.kcs.dao.inter.pojo.TDesk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-11-27 16:02
 **/
@Component
public class DeskManagerImpl implements DeskManager {
    @Autowired
    private TDeskDao tOrderDao;

    @Override
    public TDesk add(TDesk t) {
        return tOrderDao.add(t);
    }

    @Override
    public int edit(TDesk t) {
        return tOrderDao.edit(t);
    }

    @Override
    public int deleteByKey(String pk) {
        return tOrderDao.deleteByKey(pk);
    }

    @Override
    public TDesk getByKey(String pk) {
        return tOrderDao.getByKey(pk);
    }

    @Override
    public int deleteByKeys(String pks) {
        return tOrderDao.deleteByKeys(pks);
    }

    @Override
    public List<TDesk> query(TDesk t, OrderBy... orderArgs) {
        return tOrderDao.query(t, orderArgs);
    }

    @Override
    public Pager<TDesk> queryPager(int start, int limit, TDesk t, OrderBy... orderArgs) {
        return tOrderDao.queryPager(start, limit, t, orderArgs);
    }
}
