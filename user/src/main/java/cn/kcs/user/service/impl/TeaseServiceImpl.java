package cn.kcs.user.service.impl;

import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.TeaseDao;
import cn.kcs.user.entity.Tease;
import cn.kcs.user.service.TeaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Tease)表服务实现类
 *
 * @author makejava
 * @since 2019-01-06 20:33:36
 */
@Service("teaseService")
public class TeaseServiceImpl implements TeaseService {
    @Resource
    private TeaseDao teaseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param teaseId 主键
     * @return 实例对象
     */
    @Override
    public Tease queryById(String teaseId) {
        return this.teaseDao.queryById(teaseId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Tease> queryAllByLimit(int offset, int limit) {
        return this.teaseDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<Tease> queryAll(Tease tease) {
        return teaseDao.queryAll(tease);
    }

    /**
     * 新增数据
     *
     * @param tease 实例对象
     * @return 实例对象
     */
    @Override
    public Tease insert(Tease tease) {
        String uuid = ShortUUID.generate();
        tease.setTeaseId(uuid);
        tease.setTeaseCreattime(CustomDateUtil.stringToDate(CustomDateUtil.currentFormatDate(CustomDateUtil.DATE_TO_STRING_DETAIL_PATTERN), CustomDateUtil.DATE_TO_STRING_DETAIL_PATTERN));
        this.teaseDao.insert(tease);
        return tease;
    }

    /**
     * 修改数据
     *
     * @param tease 实例对象
     * @return 实例对象
     */
    @Override
    public Tease update(Tease tease) {
        this.teaseDao.update(tease);
        return this.queryById(tease.getTeaseId());
    }

    /**
     * 通过主键删除数据
     *
     * @param teaseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String teaseId) {
        return this.teaseDao.deleteById(teaseId) > 0;
    }
}