package cn.kcs.business.impl;

import cn.kcs.business.inter.RoleManager;
import cn.kcs.dao.inter.TRoleDao;
import cn.kcs.dao.inter.pojo.TRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-26 12:37
 **/
@Component
public class RoleManagerImpl implements RoleManager {
    @Autowired
    private TRoleDao tRoleDao;

    @Override
    public TRole add(TRole t) {
        return tRoleDao.add(t);
    }

    @Override
    public int edit(TRole t) {
        return tRoleDao.edit(t);
    }

    @Override
    public int deleteByKey(String pk) {
        return tRoleDao.deleteByKey(pk);
    }

    @Override
    public TRole getByKey(String pk) {
        return tRoleDao.getByKey(pk);
    }

    @Override
    public int deleteByKeys(String pks) {
        return tRoleDao.deleteByKeys(pks);
    }

    @Override
    public List<TRole> query(TRole t, OrderBy... orderArgs) {
        return tRoleDao.query(t, orderArgs);
    }

    @Override
    public Pager<TRole> queryPager(int start, int limit, TRole t, OrderBy... orderArgs) {
        return tRoleDao.queryPager(start, limit, t, orderArgs);
    }
}
