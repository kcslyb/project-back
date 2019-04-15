package cn.kcs.business.inter;

import cn.kcs.dao.inter.pojo.TFile;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface FileManager {
    TFile add(TFile t);

    int edit(TFile t);

    int deleteByKey(String pk);

    TFile getByKey(String pk);

    int deleteByKeys(String pks);

    List<TFile> query(TFile t, OrderBy... orderArgs);

    Pager<TFile> queryPager(int start, int limit, TFile t, OrderBy... orderArgs);

}
