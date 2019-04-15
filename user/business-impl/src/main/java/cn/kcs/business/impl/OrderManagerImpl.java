package cn.kcs.business.impl;

import cn.kcs.business.inter.OrderManager;
import cn.kcs.dao.inter.TOrderDao;
import cn.kcs.dao.inter.pojo.TOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-11-24 16:12
 **/
@Component
public class OrderManagerImpl implements OrderManager {
    @Autowired
    private TOrderDao tOrderDao;

    @Override
    public TOrder add(TOrder tProduct) {
        return tOrderDao.add(tProduct);
    }

    @Override
    public int edit(TOrder t) {
        return tOrderDao.edit(t);
    }

    @Override
    public int deleteByKey(String pk) {
        return tOrderDao.deleteByKey(pk);
    }

    @Override
    public TOrder getByKey(String pk) {
        return tOrderDao.getByKey(pk);
    }

    @Override
    public int deleteByKeys(String pks) {
        return tOrderDao.deleteByKey(pks);
    }

    @Override
    public List<TOrder> query(TOrder t, OrderBy... orderArgs) {
        return tOrderDao.query(t, orderArgs);
    }

    @Override
    public Pager<TOrder> queryPager(int start, int limit, TOrder t, OrderBy... orderArgs) {
        return tOrderDao.queryPager(start, limit, t, orderArgs);
    }
}
