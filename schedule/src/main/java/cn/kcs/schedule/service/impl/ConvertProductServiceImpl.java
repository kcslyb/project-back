package cn.kcs.schedule.service.impl;

import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.schedule.dao.ConvertProductDao;
import cn.kcs.schedule.entity.ConvertProduct;
import cn.kcs.schedule.service.ConvertProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ConvertProduct)表服务实现类
 *
 * @author kcs
 * @since 2020-09-17 20:19:17
 */
@Service("convertProductService")
public class ConvertProductServiceImpl implements ConvertProductService {
    @Resource
    private ConvertProductDao convertProductDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ConvertProduct queryById(String id) {
        return this.convertProductDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ConvertProduct> queryAllByLimit(ConvertProduct convertProduct, Integer offset, Integer limit) {
        return this.convertProductDao.queryAllByLimit(convertProduct, offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @param convertProduct  查询条数
     * @return 对象列表
     */
    @Override
    public List<ConvertProduct> queryAll(ConvertProduct convertProduct) {
        return this.convertProductDao.queryAll(convertProduct);
    }

    /**
     * 新增数据
     *
     * @param convertProduct 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(ConvertProduct convertProduct) {
        convertProduct.setId(ShortUUID.generate());
        return this.convertProductDao.insert(convertProduct) > 0;
    }

    /**
     * 修改数据
     *
     * @param convertProduct 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(ConvertProduct convertProduct) {
        return this.convertProductDao.update(convertProduct) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.convertProductDao.deleteById(id) > 0;
    }

    @Override
    public ResponseDto queryPager(ConvertProduct convertProduct, PageRequest pageRequest) {
        int size = this.convertProductDao.queryAll(convertProduct).size();
        List<ConvertProduct> convertProducts = this.queryAllByLimit(convertProduct, pageRequest.getStart(), pageRequest.getSize());
        return new ResponseDto(convertProducts,size, pageRequest.getSize(), pageRequest.getStart());
    }
}