package cn.kcs.user.service.impl;

import cn.kcs.user.dao.UserGenealogyRelationDao;
import cn.kcs.user.entity.UserGenealogyRelation;
import cn.kcs.user.service.UserGenealogyRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserGenealogyRelation)表服务实现类
 *
 * @author kcs
 * @since 2019-09-30 10:29:03
 */
@Service("userGenealogyRelationService")
public class UserGenealogyRelationServiceImpl implements UserGenealogyRelationService {
    @Resource
    private UserGenealogyRelationDao userGenealogyRelationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userGenealogyRelationId 主键
     * @return 实例对象
     */
    @Override
    public UserGenealogyRelation queryById(String userGenealogyRelationId) {
        return this.userGenealogyRelationDao.queryById(userGenealogyRelationId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserGenealogyRelation> queryAllByLimit(int offset, int limit) {
        return this.userGenealogyRelationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userGenealogyRelation 实例对象
     * @return 实例对象
     */
    @Override
    public UserGenealogyRelation insert(UserGenealogyRelation userGenealogyRelation) {
        this.userGenealogyRelationDao.insert(userGenealogyRelation);
        return userGenealogyRelation;
    }

    /**
     * 修改数据
     *
     * @param userGenealogyRelation 实例对象
     * @return 实例对象
     */
    @Override
    public UserGenealogyRelation update(UserGenealogyRelation userGenealogyRelation) {
        this.userGenealogyRelationDao.update(userGenealogyRelation);
        return this.queryById(userGenealogyRelation.getUserGenealogyRelationId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userGenealogyRelationId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userGenealogyRelationId) {
        return this.userGenealogyRelationDao.deleteById(userGenealogyRelationId) > 0;
    }
}