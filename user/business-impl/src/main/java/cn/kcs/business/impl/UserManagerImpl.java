package cn.kcs.business.impl;

import cn.kcs.business.inter.UserManager;
import cn.kcs.dao.inter.TUserDao;
import cn.kcs.dao.inter.pojo.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
@Component
public class UserManagerImpl implements UserManager {
    @Autowired
    private TUserDao tUserDao;
    @Override
    public TUser add(TUser user) {
        return tUserDao.add(user);
    }

    @Override
    public int edit(TUser t) {
        return tUserDao.edit(t);
    }

    @Override
    public int deleteByKey(String pk) {
        return tUserDao.deleteByKey(pk);
    }

    @Override
    public TUser getByKey(String pk) {
        return tUserDao.getByKey(pk);
    }

    @Override
    public int deleteByKeys(String pks) {
        return tUserDao.deleteByKeys(pks);
    }

    @Override
    public List<TUser> query(TUser t, OrderBy... orderArgs) {
        return tUserDao.query(t,orderArgs);
    }

    @Override
    public Pager<TUser> queryPager(int start, int limit, TUser t, OrderBy... orderArgs) {
        return tUserDao.queryPager(start,limit,t,orderArgs);
    }

    @Override
    public TUser getUserByIdAndName(String loginName, String password) {
        List<TUser> list = tUserDao.getUserByIdAndName(loginName, password);
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }
}
