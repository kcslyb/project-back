package cn.kcs.note.service.impl;

import cn.kcs.common.daoUtil.ShortUUID;
import cn.kcs.common.util.DataUtil;
import cn.kcs.note.dao.TDictionaryDao;
import cn.kcs.note.entity.TDictionary;
import cn.kcs.note.entity.dto.DictionaryDto;
import cn.kcs.note.service.TDictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (TDictionary)表服务实现类
 *
 * @author makejava
 * @since 2019-03-14 20:35:00
 */
@Service("tDictionaryService")
public class TDictionaryServiceImpl implements TDictionaryService {
    @Resource
    private TDictionaryDao tDictionaryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param dictionaryId 主键
     * @return 实例对象
     */
    @Override
    public TDictionary queryById(String dictionaryId) {
        return this.tDictionaryDao.queryById(dictionaryId);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param key
     * @return 实例对象
     */
    @Override
    public TDictionary getDictionaryByKey(String key) {
        return this.tDictionaryDao.getDictionaryByKey(key);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TDictionary> queryAllByLimit(int offset, int limit) {
        return this.tDictionaryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDictionary 实例对象
     * @return 对象列表
     */
    public List<DictionaryDto> queryAll(TDictionary tDictionary) {
        boolean groupNameIsNull = false;
        if (tDictionary.getDictionaryGroupName() == null) {
            groupNameIsNull = true;
        }
        List<DictionaryDto> dictionaryDtoList = new ArrayList<>();
        List<String> groupNames = this.tDictionaryDao.queryGroupByName();
        TDictionary t = new TDictionary();
        if (groupNames != null && groupNames.size() > 0) {
            for (String groupName : groupNames) {
                t.setDictionaryGroupName(groupName);
                if (!groupNameIsNull) {
                    t.setDictionaryGroupName(tDictionary.getDictionaryGroupName());
                }
                List<TDictionary> dictionaries = this.tDictionaryDao.queryAll(t);
                if (dictionaries != null && dictionaries.size() > 0) {
                    DictionaryDto dictionaryDto = new DictionaryDto();
                    dictionaryDto.setGroupName(groupName);
                    dictionaryDto.setDictionaries(dictionaries);
                    TDictionary dictionaryByKey = this.tDictionaryDao.getDictionaryByKey(groupName);
                    if (dictionaryByKey != null) {
                        dictionaryDto.setGroupNameValue(dictionaryByKey.getDictionaryLabel());
                    }
                    dictionaryDtoList.add(dictionaryDto);
                }
                if (!groupNameIsNull) {
                    break;
                }
            }
        }
        return dictionaryDtoList;
    }

    /**
     * 新增数据
     *
     * @param tDictionary 实例对象
     * @return 实例对象
     */
    @Override
    public TDictionary insert(TDictionary tDictionary) {
        String uuid = ShortUUID.generate();
        tDictionary.setDictionaryId(uuid);
        tDictionary.setDictionaryCreateTime(DataUtil.stringToDate(DataUtil
                .currentFormatDate(DataUtil
                        .DATE_TO_STRING_DETAIAL_PATTERN), DataUtil
                .DATE_TO_STRING_DETAIAL_PATTERN));
        List<TDictionary> dictionaries = tDictionaryDao.queryAllByLimit(0, 1);
        if (dictionaries != null && dictionaries.size() > 0) {
            tDictionary.setDictionaryIndex(String.valueOf(Integer
                    .parseInt(dictionaries.get(0)
                            .getDictionaryIndex()) + 1));
        } else {
            tDictionary.setDictionaryIndex(String.valueOf(0));
        }
        this.tDictionaryDao.insert(tDictionary);
        return tDictionary;
    }

    /**
     * 修改数据
     *
     * @param tDictionary 实例对象
     * @return 实例对象
     */
    @Override
    public TDictionary update(TDictionary tDictionary) {
        this.tDictionaryDao.update(tDictionary);
        return this.queryById(tDictionary.getDictionaryId());
    }

    /**
     * 通过主键删除数据
     *
     * @param dictionaryId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String dictionaryId) {
        return this.tDictionaryDao.deleteById(dictionaryId) > 0;
    }
}