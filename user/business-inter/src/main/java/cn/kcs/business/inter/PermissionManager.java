package cn.kcs.business.inter;

import cn.kcs.dao.inter.pojo.TPermission;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface PermissionManager {
    TPermission add(TPermission t);

    int edit(TPermission t);

    int deleteByKey(String pk);

    TPermission getByKey(String pk);

    int deleteByKeys(String pks);

    List<TPermission> query(TPermission t, OrderBy... orderArgs);

    Pager<TPermission> queryPager(int start, int limit, TPermission t, OrderBy... orderArgs);

}
