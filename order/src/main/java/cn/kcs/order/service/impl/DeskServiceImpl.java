package cn.kcs.order.service.impl;

import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.order.dao.DeskDao;
import cn.kcs.order.entity.Desk;
import cn.kcs.order.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Desk)表服务实现类
 *
 * @author kcs
 * @since 2019-04-24 14:35:14
 */
@Service("deskService")
public class DeskServiceImpl implements DeskService {
    @Autowired
    private DeskDao deskDao;

    /**
     * 通过ID查询单条数据
     *
     * @param deskId 主键
     * @return 实例对象
     */
    @Override
    public Desk queryById(String deskId) {
        return this.deskDao.queryById(deskId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Desk> queryAllByLimit(Desk desk, int offset, int limit) {
        return this.deskDao.queryAllByLimit(desk, offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public int queryAll(Desk desk) {
        return this.deskDao.queryAll(desk).size();
    }

    /**
     * 新增数据
     *
     * @param desk 实例对象
     * @return 实例对象
     */
    @Override
    public Desk insert(Desk desk) {
        desk.setDeskId(ShortUUID.generate());
        desk.setDeskCreateTime(CustomDateUtil.currentFormatDate());
        this.deskDao.insert(desk);
        return desk;
    }

    /**
     * 修改数据
     *
     * @param desk 实例对象
     * @return 实例对象
     */
    @Override
    public Desk update(Desk desk) {
        this.deskDao.update(desk);
        return this.queryById(desk.getDeskId());
    }

    /**
     * 通过主键删除数据
     *
     * @param deskId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String deskId) {
        Desk desk = deskDao.queryById(deskId);
        if ("2".equals(desk.getDeskStatus())) {
            desk.setDeskStatus("0");
        } else {
            desk.setDeskStatus("2");
        }
        return this.deskDao.update(desk) > 0;
    }
}