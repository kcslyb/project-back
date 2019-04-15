package cn.kcs.business.inter;

import cn.kcs.dao.inter.pojo.TProduct;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-11-24 16:10
 **/
public interface ProManager {

    TProduct add(TProduct tProduct);

    int edit(TProduct t);

    int deleteByKey(String pk);

    TProduct getByKey(String pk);

    int deleteByKeys(String pks);

    List<TProduct> query(TProduct t, OrderBy... orderArgs);

    Pager<TProduct> queryPager(int start, int limit, TProduct t, OrderBy... orderArgs);
}
