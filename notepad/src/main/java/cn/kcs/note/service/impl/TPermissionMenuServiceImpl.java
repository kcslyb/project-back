package cn.kcs.note.service.impl;

import cn.kcs.common.daoUtil.ShortUUID;
import cn.kcs.common.util.ListUtil;
import cn.kcs.note.dao.TPermissionMenuDao;
import cn.kcs.note.dao.TRolePermissionDao;
import cn.kcs.note.entity.TPermissionMenu;
import cn.kcs.note.entity.TRolePermission;
import cn.kcs.note.service.TPermissionMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TPermissionMenu)表服务实现类
 *
 * @author makejava
 * @since 2019-03-08 09:55:53
 */
@Service("tPermissionMenuService")
public class TPermissionMenuServiceImpl implements TPermissionMenuService {
    @Resource
    private TPermissionMenuDao tPermissionMenuDao;

    @Resource
    private TRolePermissionDao tRolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param permid 主键
     * @return 实例对象
     */
    @Override
    public TPermissionMenu queryById(String permid) {
        return this.tPermissionMenuDao.queryById(permid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TPermissionMenu> queryAllByLimit(int offset, int limit) {
        return this.tPermissionMenuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<TPermissionMenu> queryAll(TPermissionMenu tPermissionMenu) {
        return this.tPermissionMenuDao.queryAll(tPermissionMenu);
    }

    /**
     * 新增数据
     *
     * @param tPermissionMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TPermissionMenu insert(TPermissionMenu tPermissionMenu) {
        String UUID = ShortUUID.generate();
        tPermissionMenu.setPermid(UUID);
        this.tPermissionMenuDao.insert(tPermissionMenu);
        return tPermissionMenu;
    }

    /**
     * 修改数据
     *
     * @param tPermissionMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TPermissionMenu update(TPermissionMenu tPermissionMenu) {
        this.tPermissionMenuDao.update(tPermissionMenu);
        return this.queryById(tPermissionMenu.getPermid());
    }

    /**
     * 通过主键删除数据
     *
     * @param permid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String permid) {
        TRolePermission rolePermission = new TRolePermission();
        rolePermission.setPermissionId(permid);
        List<TRolePermission> rolePermissions = tRolePermissionDao.queryAll(rolePermission);
        if (ListUtil.notNullAadEmpty(rolePermissions)) {
            for (TRolePermission permission : rolePermissions) {
                tRolePermissionDao.deleteById(permission.getRolePermissionId());
            }
        }
        return this.tPermissionMenuDao.deleteById(permid) > 0;
    }
}