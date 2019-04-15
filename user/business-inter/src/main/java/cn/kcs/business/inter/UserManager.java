package cn.kcs.business.inter;

import cn.kcs.dao.inter.pojo.TUser;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface UserManager {
    TUser add(TUser user);

    int edit(TUser t);

    int deleteByKey(String pk);

    TUser getByKey(String pk);

    int deleteByKeys(String pks);

    List<TUser> query(TUser t, OrderBy... orderArgs);

    Pager<TUser> queryPager(int start, int limit, TUser t, OrderBy... orderArgs);

    TUser getUserByIdAndName(String loginName, String password);
}
