package cn.kcs.business.inter;

import cn.kcs.dao.inter.pojo.TLog;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * @description: log manager
 * @author: kcs
 * @create: 2018-11-06 16:02
 **/
public interface LogManager {

    TLog add(TLog tLog);

    Pager<TLog> queryPager(int start, int limit, TLog t, OrderBy... orderArgs);

    List<TLog> query(TLog t, OrderBy... orderArgs);
}
