package cn.kcs.business.impl;

import cn.kcs.business.inter.FileManager;
import cn.kcs.dao.inter.TFileDao;
import cn.kcs.dao.inter.pojo.TFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-25 14:47
 **/
@Component
public class FileManagerImpl implements FileManager {
    @Autowired
    private TFileDao tFileDao;

    @Override
    public TFile add(TFile tFile) {
        return tFileDao.add(tFile);
    }

    @Override
    public int edit(TFile t) {
        return tFileDao.edit(t);
    }

    @Override
    public int deleteByKey(String pk) {
        return tFileDao.deleteByKey(pk);
    }

    @Override
    public TFile getByKey(String pk) {
        return tFileDao.getByKey(pk);
    }

    @Override
    public int deleteByKeys(String pks) {
        return tFileDao.deleteByKey(pks);
    }

    @Override
    public List<TFile> query(TFile t, OrderBy... orderArgs) {
        return tFileDao.query(t, orderArgs);
    }

    @Override
    public Pager<TFile> queryPager(int start, int limit, TFile t, OrderBy... orderArgs) {
        return tFileDao.queryPager(start, limit, t, orderArgs);
    }
}
