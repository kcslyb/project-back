package cn.kcs.business.inter;

import cn.kcs.dao.inter.pojo.TDesk;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-11-24 16:10
 **/
public interface DeskManager {

    TDesk add(TDesk t);

    int edit(TDesk t);

    int deleteByKey(String pk);

    TDesk getByKey(String pk);

    int deleteByKeys(String pks);

    List<TDesk> query(TDesk t, OrderBy... orderArgs);

    Pager<TDesk> queryPager(int start, int limit, TDesk t, OrderBy... orderArgs);
}
