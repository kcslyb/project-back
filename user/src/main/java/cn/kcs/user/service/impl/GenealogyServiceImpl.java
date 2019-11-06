package cn.kcs.user.service.impl;

import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.GenealogyDao;
import cn.kcs.user.entity.Genealogy;
import cn.kcs.user.service.GenealogyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Genealogy)表服务实现类
 *
 * @author kcs
 * @since 2019-09-30 10:28:30
 */
@Service("genealogyService")
public class GenealogyServiceImpl implements GenealogyService {
    @Resource
    private GenealogyDao genealogyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param genealogyId 主键
     * @return 实例对象
     */
    @Override
    public Genealogy queryById(String genealogyId) {
        return this.genealogyDao.queryById(genealogyId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Genealogy> queryAllByLimit(int offset, int limit) {
        return this.genealogyDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param genealogy 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Genealogy genealogy) {
        genealogy.setGenealogyId(ShortUUID.generate());
        return this.genealogyDao.insert(genealogy) > 0;
    }

    /**
     * 修改数据
     *
     * @param genealogy 实例对象
     * @return 实例对象
     */
    @Override
    public Genealogy update(Genealogy genealogy) {
        this.genealogyDao.update(genealogy);
        return this.queryById(genealogy.getGenealogyId());
    }

    /**
     * 通过主键删除数据
     *
     * @param genealogyId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String genealogyId) {
        return this.genealogyDao.deleteById(genealogyId) > 0;
    }

    @Override
    public List<Genealogy> queryAll(Genealogy genealogy) {
        return this.genealogyDao.queryAll(genealogy);
    }
}