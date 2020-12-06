package cn.kcs.schedule.service.impl;
import java.util.Date;

import cn.kcs.schedule.dao.ConvertProductDao;
import cn.kcs.schedule.entity.ConvertProduct;
import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.schedule.dao.ProductWorkDao;
import cn.kcs.schedule.dto.ProductWorkDto;
import cn.kcs.schedule.entity.ProductWork;
import cn.kcs.schedule.service.ProductWorkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (ProductWork)表服务实现类
 *
 * @author kcs
 * @since 2020-09-17 20:16:25
 */
@Service("productWorkService")
public class ProductWorkServiceImpl implements ProductWorkService {
    @Resource
    private ProductWorkDao productWorkDao;

    @Resource
    private ConvertProductDao convertProductDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProductWorkDto queryById(String id) {
        ProductWork productWork = this.productWorkDao.queryById(id);
        return convertDto(productWork);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ProductWork> queryAllByLimit(ProductWork productWork, Integer offset, Integer limit) {
        return this.productWorkDao.queryAllByLimit(productWork, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param productWorkDto 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(ProductWorkDto productWorkDto) {
        productWorkDto.setId(ShortUUID.generate());
        productWorkDto.setCreateBy(LoginInfo.getUserId());
        productWorkDto.setCreateByName(LoginInfo.getUserName());
        productWorkDto.setCreateTime(CustomDateUtil.currentFormatDate());
        productWorkDto.setDeleteFlag(false);
        if (productWorkDto.getConvertProducts().size() > 0) {
            for (ConvertProduct convertProduct : productWorkDto.getConvertProducts()) {
                convertProduct.setId(ShortUUID.generate());
                convertProduct.setProductWorkId(productWorkDto.getId());
                convertProductDao.insert(convertProduct);
            }
        }
        return this.productWorkDao.insert(dtoConvert(productWorkDto)) > 0;
    }

    private ProductWork dtoConvert(ProductWorkDto productWorkDto) {
        ProductWork productWork = new ProductWork();
        productWork.setId(productWorkDto.getId());
        productWork.setCreateBy(productWorkDto.getCreateBy());
        productWork.setCreateByName(productWorkDto.getCreateByName());
        productWork.setCreateTime(productWorkDto.getCreateTime());
        productWork.setHappenTime(productWorkDto.getHappenTime());
        productWork.setUpdateBy(productWorkDto.getUpdateBy());
        productWork.setUpdateByName(productWorkDto.getUpdateByName());
        productWork.setUpdateTime(productWorkDto.getUpdateTime());
        productWork.setResultNumber(productWorkDto.getResultNumber());
        productWork.setResultUnit(productWorkDto.getResultUnit());
        productWork.setResultUnitName(productWorkDto.getResultUnitName());
        productWork.setMinNumber(productWorkDto.getMinNumber());
        productWork.setMinUnit(productWorkDto.getMinUnit());
        productWork.setMinUnitName(productWorkDto.getMinUnitName());
        productWork.setDeleteFlag(productWorkDto.getDeleteFlag());
        productWork.setRemark(productWorkDto.getRemark());
        productWork.setParticipants(productWorkDto.getParticipants());
        productWork.setParticipantsNumber(productWorkDto.getParticipantsNumber());
        return  productWork;
    }

    /**
     * 修改数据
     *
     * @param productWorkDto 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(ProductWorkDto productWorkDto) {
        productWorkDto.setUpdateBy(LoginInfo.getUserId());
        productWorkDto.setUpdateByName(LoginInfo.getUserName());
        productWorkDto.setUpdateTime(CustomDateUtil.currentFormatDate());
        if (productWorkDto.getConvertProducts().size() == 0) {
            for (ConvertProduct convertProduct : productWorkDto.getConvertProducts()) {
                convertProduct.setId(ShortUUID.generate());
                convertProduct.setProductWorkId(productWorkDto.getId());
                convertProductDao.insert(convertProduct);
            }
        } else {
            ConvertProduct convertProduct = new ConvertProduct();
            convertProduct.setProductWorkId(productWorkDto.getId());
            List<ConvertProduct> convertProducts = convertProductDao.queryAll(convertProduct);
            if (convertProducts.size() > 0) {
                for (ConvertProduct item : convertProducts) {
                    convertProductDao.deleteById(item.getId());
                }
            }
            for (ConvertProduct addItem : productWorkDto.getConvertProducts()) {
                addItem.setId(ShortUUID.generate());
                addItem.setProductWorkId(productWorkDto.getId());
                convertProductDao.insert(addItem);
            }
        }
        return this.productWorkDao.update(dtoConvert(productWorkDto)) > 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        ProductWork productWork = this.productWorkDao.queryById(id);
        productWork.setDeleteFlag(true);
        return this.productWorkDao.update(productWork) > 0;
    }

    @Override
    public ResponseDto<ProductWorkDto> queryPager(ProductWork productWork, PageRequest pageRequest) {
        productWork.setDeleteFlag(false);
        int size = productWorkDao.queryAll(productWork).size();
        List<ProductWorkDto> productWorkDtoList = new ArrayList<>();
        List<ProductWork> productWorks = productWorkDao.queryAllByLimit(productWork, pageRequest.getStart(), pageRequest.getSize());
        for (ProductWork item : productWorks) {
            ProductWorkDto productWorkDto = convertDto(item);
            productWorkDtoList.add(productWorkDto);
        }
        return new ResponseDto<>(productWorkDtoList, size, pageRequest.getSize(), pageRequest.getStart());
    }

    private ProductWorkDto convertDto(ProductWork productWork) {
        ProductWorkDto productWorkDto = new ProductWorkDto();
        productWorkDto.setId(productWork.getId());
        productWorkDto.setCreateBy(productWork.getCreateBy());
        productWorkDto.setCreateByName(productWork.getCreateByName());
        productWorkDto.setCreateTime(productWork.getCreateTime());
        productWorkDto.setHappenTime(productWork.getHappenTime());
        productWorkDto.setUpdateBy(productWork.getUpdateBy());
        productWorkDto.setUpdateByName(productWork.getUpdateByName());
        productWorkDto.setUpdateTime(productWork.getUpdateTime());
        productWorkDto.setResultNumber(productWork.getResultNumber());
        productWorkDto.setResultUnit(productWork.getResultUnit());
        productWorkDto.setResultUnitName(productWork.getResultUnitName());
        productWorkDto.setMinNumber(productWork.getMinNumber());
        productWorkDto.setMinUnit(productWork.getMinUnit());
        productWorkDto.setMinUnitName(productWork.getMinUnitName());
        productWorkDto.setDeleteFlag(productWork.getDeleteFlag());
        productWorkDto.setRemark(productWork.getRemark());
        productWorkDto.setParticipants(productWork.getParticipants());
        productWorkDto.setParticipantsNumber(productWork.getParticipantsNumber());
        productWorkDto.setAverage(productWork.getResultNumber() / productWork.getParticipantsNumber());
        ConvertProduct convertProduct = new ConvertProduct();
        convertProduct.setProductWorkId(productWork.getId());
        List<ConvertProduct> convertProducts = convertProductDao.queryAll(convertProduct);
        productWorkDto.setConvertProducts(convertProducts);
        return productWorkDto;
    }

}