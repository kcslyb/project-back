package cn.kcs.business.impl;

import cn.kcs.business.inter.ProManager;
import cn.kcs.dao.inter.TProductDao;
import cn.kcs.dao.inter.pojo.TProduct;
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
public class ProManagerImpl implements ProManager {
    @Autowired
    private TProductDao tProductDao;

    @Override
    public TProduct add(TProduct tProduct) {
        return tProductDao.add(tProduct);
    }

    @Override
    public int edit(TProduct t) {
        return tProductDao.edit(t);
    }

    @Override
    public int deleteByKey(String pk) {
        return tProductDao.deleteByKey(pk);
    }

    @Override
    public TProduct getByKey(String pk) {
        return tProductDao.getByKey(pk);
    }

    @Override
    public int deleteByKeys(String pks) {
        return tProductDao.deleteByKey(pks);
    }

    @Override
    public List<TProduct> query(TProduct t, OrderBy... orderArgs) {
        return tProductDao.query(t, orderArgs);
    }

    @Override
    public Pager<TProduct> queryPager(int start, int limit, TProduct t, OrderBy... orderArgs) {
        return tProductDao.queryPager(start, limit, t, orderArgs);
    }
}
