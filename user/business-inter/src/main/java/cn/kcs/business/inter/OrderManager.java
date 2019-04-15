package cn.kcs.business.inter;

import cn.kcs.dao.inter.pojo.TOrder;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-11-24 16:10
 **/
public interface OrderManager {

    TOrder add(TOrder t);

    int edit(TOrder t);

    int deleteByKey(String pk);

    TOrder getByKey(String pk);

    int deleteByKeys(String pks);

    List<TOrder> query(TOrder t, OrderBy... orderArgs);

    Pager<TOrder> queryPager(int start, int limit, TOrder t, OrderBy... orderArgs);
}
