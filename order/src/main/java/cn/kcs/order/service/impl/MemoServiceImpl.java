package cn.kcs.order.service.impl;

import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CustomCollection;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.order.dao.MemoDao;
import cn.kcs.order.entity.Memo;
import cn.kcs.order.service.MemoService;
import cn.kcs.user.entity.Dict;
import cn.kcs.user.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Memo)表服务实现类
 *
 * @author kcs
 * @since 2019-11-08 18:23:54
 */
@Service("memoService")
public class MemoServiceImpl implements MemoService {
    @Resource
    private MemoDao memoDao;

    @Autowired
    private DictService dictService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Memo queryById(String id) {
        return this.memoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param memo        memo
     * @param pageRequest page request
     * @return 对象列表
     */
    @Override
    public ResponseDto queryPager(Memo memo, PageRequest pageRequest) {
        List<Memo> memos = this.memoDao.queryPager(memo, pageRequest);
        List<Dict> memoType = dictService.queryByDictGroupLabel("memoType");
        if (!CollectionUtils.isEmpty(memoType)) {
            Map<String, Dict> memoTypeDictMap = CustomCollection.extractIndexToMap(memoType, Dict::getKey);
            memos.forEach(value -> {
                value.setType(memoTypeDictMap.get(value.getType()).getLabel());
            });
        }
        int total = this.memoDao.queryAll(memo, pageRequest).size();
        return new ResponseDto<>(memos, total, pageRequest.getSize(), pageRequest.getStart());
    }

    /**
     * 查询多条数据
     *
     * @param memo        memo
     * @param pageRequest page request
     * @return 对象列表
     */
    @Override
    public List<Memo> queryAll(Memo memo, PageRequest pageRequest) {
        return memoDao.queryAll(memo, pageRequest);
    }

    /**
     * 新增数据
     *
     * @param memo 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Memo memo) {
        memo.setId(ShortUUID.generate());
        memo.setCreateBy(LoginInfo.getUserId());
        memo.setDeleteFlag(false);
        if (memo.getCreateTime() == null) {
            memo.setCreateTime(CustomDateUtil.currentFormatDate());
        }
        int insert = this.memoDao.insert(memo);
        return insert > 0;
    }

    /**
     * 修改数据
     *
     * @param memo 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(Memo memo) {
        int update = this.memoDao.update(memo);
        return update > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        Memo memo = queryById(id);
        if (memo == null) {
            return false;
        }
        memo.setDeleteFlag(true);
        return update(memo);
    }
}