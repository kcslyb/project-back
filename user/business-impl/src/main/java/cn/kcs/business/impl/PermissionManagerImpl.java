package cn.kcs.business.impl;

import cn.kcs.business.inter.PermissionManager;
import cn.kcs.dao.inter.TPermissionDao;
import cn.kcs.dao.inter.pojo.TPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-26 12:42
 **/
@Component
public class PermissionManagerImpl implements PermissionManager {
    @Autowired
    private TPermissionDao tPermissionDao;

    @Override
    public TPermission add(TPermission t) {
        return tPermissionDao.add(t);
    }

    @Override
    public int edit(TPermission t) {
        return tPermissionDao.edit(t);
    }

    @Override
    public int deleteByKey(String pk) {
        return tPermissionDao.deleteByKey(pk);
    }

    @Override
    public TPermission getByKey(String pk) {
        return tPermissionDao.getByKey(pk);
    }

    @Override
    public int deleteByKeys(String pks) {
        return tPermissionDao.deleteByKeys(pks);
    }

    @Override
    public List<TPermission> query(TPermission t, OrderBy... orderArgs) {
        return tPermissionDao.query(t, orderArgs);
    }

    @Override
    public Pager<TPermission> queryPager(int start, int limit, TPermission t, OrderBy... orderArgs) {
        return tPermissionDao.queryPager(start, limit, t, orderArgs);
    }
}
