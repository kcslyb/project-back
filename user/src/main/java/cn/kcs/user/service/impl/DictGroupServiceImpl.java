package cn.kcs.user.service.impl;

import cn.kcs.common.exception.CustomException;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.util.PageRequest;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.DictGroupDao;
import cn.kcs.user.entity.DictGroup;
import cn.kcs.user.service.DictGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DictGroup)表服务实现类
 *
 * @author kcs
 * @since 2019-11-01 11:37:54
 */
@Service("dictGroupService")
public class DictGroupServiceImpl implements DictGroupService {
    @Resource
    private DictGroupDao dictGroupDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DictGroup queryById(String id) {
        return this.dictGroupDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<DictGroup> queryAllByLimit(int offset, int limit) {
        return this.dictGroupDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dictGroup 实例对象
     * @return 实例对象
     */
    @Override
    public ResponseEntity insert(DictGroup dictGroup) throws CustomException {
        List<DictGroup> dictGroups = queryGroupByLabel(dictGroup.getLabel());
        boolean addFlag = !CollectionUtils.isEmpty(dictGroups);
        if (addFlag) {
            return new ResponseEntity<>("操作失败,字典组值已存在", HttpStatus.BAD_REQUEST);
        }
        dictGroup.setDeleteFlag("0");
        dictGroup.setId(ShortUUID.generate());
        dictGroup.setCreateTime(CustomDateUtil.currentFormatDate());
        boolean insert = this.dictGroupDao.insert(dictGroup) > 0;
        return new ResponseEntity<>(insert, HttpStatus.OK);
    }

    @Override
    public ResponseEntity update(DictGroup dictGroup) {
        if (StringUtils.isEmpty(dictGroup.getId())) {
            return new ResponseEntity<>("操作失败,字典组id不存在", HttpStatus.BAD_REQUEST);
        }
        List<DictGroup> dictGroups = queryGroupByLabel(dictGroup.getLabel());
        boolean updateFlag = !CollectionUtils.isEmpty(dictGroups) && !dictGroups.get(0).getId().equals(dictGroup.getId());
        if (updateFlag) {
            return new ResponseEntity<>("操作失败,字典组值已存在", HttpStatus.BAD_REQUEST);
        }
        boolean update = this.dictGroupDao.update(dictGroup) > 0;
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @Override
    public boolean deleteById(String id) {
        return this.dictGroupDao.deleteById(id) > 0;
    }

    @Override
    public List<DictGroup> queryAll(DictGroup dictGroup, PageRequest pageRequest) {
        return dictGroupDao.queryAll(dictGroup, pageRequest);
    }

    @Override
    public List<DictGroup> queryGroupByLabel(String groupLabel) {
        DictGroup dictGroup = new DictGroup();
        dictGroup.setLabel(groupLabel);
        return queryAll(dictGroup, new PageRequest(0, 10));
    }
}