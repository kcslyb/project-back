package cn.kcs.note.service.impl;

import cn.kcs.common.daoUtil.ShortUUID;
import cn.kcs.common.loginInfo.LoginInfo;
import cn.kcs.common.util.DataUtil;
import cn.kcs.note.dao.TAccountDao;
import cn.kcs.note.entity.TAccount;
import cn.kcs.note.service.TAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TAccount)表服务实现类
 *
 * @author makejava
 * @since 2019-02-25 09:39:21
 */
@Service("tAccountService")
public class TAccountServiceImpl implements TAccountService {
    @Resource
    private TAccountDao tAccountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id
     * @return 实例对象
     */
    @Override
    public TAccount queryById(String id) {
        return this.tAccountDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TAccount> queryAllByLimit(TAccount tAccount, int offset, int limit, String starTime, String endTime) {
        tAccount.setAccountCreatby(LoginInfo.getUserName());
        return this.tAccountDao.queryAllByLimit(tAccount, offset, limit, starTime, endTime);
    }

    /**
     * 新增数据
     *
     * @param tAccount 实例对象
     * @return 实例对象
     */
    @Override
    public TAccount insert(TAccount tAccount) {
        String UUID = ShortUUID.generate();
        tAccount.setAccountId(UUID);
        tAccount.setAccountCreatby(LoginInfo.getUserName());
        tAccount.setAccountCreattime(DataUtil.stringToDate(DataUtil.currentFormatDate(DataUtil.DATE_TO_STRING_DETAIAL_PATTERN), DataUtil.DATE_TO_STRING_DETAIAL_PATTERN));
        tAccount.setAccountUpdatetime(tAccount.getAccountCreattime());
        this.tAccountDao.insert(tAccount);
        return tAccount;
    }

    /**
     * 修改数据
     *
     * @param tAccount 实例对象
     * @return 实例对象
     */
    @Override
    public TAccount update(TAccount tAccount) {
        tAccount.setAccountUpdatetime(DataUtil.stringToDate(DataUtil.currentFormatDate(DataUtil.DATE_TO_STRING_DETAIAL_PATTERN), DataUtil.DATE_TO_STRING_DETAIAL_PATTERN));
        this.tAccountDao.update(tAccount);
        return this.queryById(tAccount.getAccountId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tAccountDao.deleteById(id) > 0;
    }
}