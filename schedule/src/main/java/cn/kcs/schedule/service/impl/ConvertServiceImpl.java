package cn.kcs.schedule.service.impl;

import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.schedule.dao.ConvertDao;
import cn.kcs.schedule.entity.Convert;
import cn.kcs.schedule.service.ConvertService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Convert)表服务实现类
 *
 * @author kcs
 * @since 2020-09-17 20:11:32
 */
@Service("convertService")
public class ConvertServiceImpl implements ConvertService {
    @Resource
    private ConvertDao convertDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Convert queryById(String id) {
        return this.convertDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Convert> queryAllByLimit(Convert convert, int offset, int limit) {
        return this.convertDao.queryAllByLimit(convert, offset, limit);
    }

    public List<Convert> queryAll(Convert convert) {
        return this.convertDao.queryAll(convert);
    }

    /**
     * 新增数据
     *
     * @param convert 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Convert convert) {
        convert.setId(ShortUUID.generate());
        convert.setCreateBy(LoginInfo.getUserId());
        convert.setCreateByName(LoginInfo.getUserName());
        convert.setCreateTime(CustomDateUtil.currentFormatDate());
        return this.convertDao.insert(convert) > 0;
    }

    /**
     * 修改数据
     *
     * @param convert 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(Convert convert) {
        convert.setUpdateBy(LoginInfo.getUserId());
        convert.setUpdateByName(LoginInfo.getUserName());
        convert.setUpdateTime(CustomDateUtil.currentFormatDate());
        return this.convertDao.update(convert) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.convertDao.deleteById(id) > 0;
    }

    @Override
    public ResponseDto<Convert> queryPager(Convert convert, PageRequest pageRequest) {
        int size = convertDao.queryAll(convert).size();
        List<Convert> productWorks = convertDao.queryAllByLimit(convert, pageRequest.getStart(), pageRequest.getSize());
        return new ResponseDto<>(productWorks, size, pageRequest.getSize(), pageRequest.getStart());
    }

    @Override
    public List<Convert> queryRelevance(String relevance) {
        Convert convert = new Convert();
        convert.setOneTypeUnit(relevance);
        List<Convert> convertList = new ArrayList<>();
        this.getConverts(convertList, relevance);
        return convertList;
    }
    public List<Convert> getConverts (List<Convert> converts, String relevance) {
        Convert convert = new Convert();
        convert.setOneTypeUnit(relevance);
        List<Convert> convertTemp = convertDao.queryAll(convert);
        if (convertTemp.size() > 0) {
            Convert temp = convertTemp.get(0);
            converts.add(temp);
            this.getConverts(converts, temp.getTwoTypeUnit());
        } else {
            return converts;
        }
        return new ArrayList<>();
    }
}