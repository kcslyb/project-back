package cn.kcs.business.inter;

import cn.kcs.dao.inter.pojo.TRole;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface RoleManager {
    TRole add(TRole t);

    int edit(TRole t);

    int deleteByKey(String pk);

    TRole getByKey(String pk);

    int deleteByKeys(String pks);

    List<TRole> query(TRole t, OrderBy... orderArgs);

    Pager<TRole> queryPager(int start, int limit, TRole t, OrderBy... orderArgs);

}
