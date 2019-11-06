package cn.kcs.user.service.impl;

import cn.kcs.common.exception.CustomException;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.DictDao;
import cn.kcs.user.dao.DictGroupDao;
import cn.kcs.user.entity.Dict;
import cn.kcs.user.entity.DictGroup;
import cn.kcs.user.service.DictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Dict)表服务实现类
 *
 * @author kcs
 * @since 2019-11-01 11:37:31
 */
@Service("dictService")
public class DictServiceImpl implements DictService {
    @Resource
    private DictDao dictDao;

    @Autowired
    private DictGroupDao dictGroupDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dict queryById(String id) {
        return this.dictDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Dict> queryAllByLimit(int offset, int limit) {
        return this.dictDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @param dict    dict
     * @param keyWord keyWord
     * @return 对象列表
     */
    @Override
    public List<Dict> queryAll(Dict dict, String keyWord) {
        return this.dictDao.queryAll(dict, keyWord);
    }

    /**
     * 新增数据
     *
     * @param dict 实例对象
     * @return 实例对象
     */
    @Override
    public ResponseEntity insert(Dict dict) throws CustomException {
        DictGroup dictGroup = dictGroupDao.queryById(dict.getGroupId());
        if (dictGroup == null) {
            return new ResponseEntity<>("操作失败,不存在ID为[" + dict.getGroupId() + "]字典组", HttpStatus.BAD_REQUEST);
        }
        Dict d = new Dict();
        d.setKey(dict.getKey());
        d.setGroupId(dictGroup.getId());
        List<Dict> dictList = dictDao.queryAll(d, null);
        if (!CollectionUtils.isEmpty(dictList)) {
            return new ResponseEntity<>("操作失败,字典组" + dictGroup.getLabel() + "已存在key为[" + d.getKey() + "]的字典", HttpStatus.BAD_REQUEST);
        }
        dict.setId(ShortUUID.generate());
        dict.setDeleteFlag("0");
        boolean insert = this.dictDao.insert(dict) > 0;
        return new ResponseEntity<>(insert, HttpStatus.OK);
    }

    @Override
    public ResponseEntity update(Dict dict) {
        if (StringUtils.isBlank(dict.getGroupId())) {
            return new ResponseEntity<>("操作失败,字典id不存在", HttpStatus.BAD_REQUEST);
        }
        DictGroup dictGroup = dictGroupDao.queryById(dict.getGroupId());
        Dict d = new Dict();
        d.setGroupId(dictGroup.getId());
        d.setKey(dict.getKey());
        List<Dict> dictList = dictDao.queryAll(d, null);
        if (!CollectionUtils.isEmpty(dictList) && !dictList.get(0).getId().equals(dict.getId())) {
            return new ResponseEntity<>("操作失败,字典组" + dictGroup.getLabel() + "已存在key为[" + d.getKey() + "]的字典", HttpStatus.BAD_REQUEST);
        }
        boolean update = this.dictDao.update(dict) > 0;
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @Override
    public boolean deleteById(String id) {
        return this.dictDao.deleteById(id) > 0;
    }
}