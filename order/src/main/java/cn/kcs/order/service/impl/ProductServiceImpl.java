package cn.kcs.order.service.impl;

import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.order.dao.ProductDao;
import cn.kcs.order.dao.TOrderProductDao;
import cn.kcs.order.entity.Product;
import cn.kcs.order.entity.dto.ProductDto;
import cn.kcs.order.service.ProductService;
import cn.kcs.user.dao.TFileDao;
import cn.kcs.user.dao.UserAccountDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * (Product)表服务实现类
 *
 * @author kcs
 * @since 2019-04-23 14:38:33
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private TFileDao tFileDao;

    @Autowired
    private TOrderProductDao tOrderProductDao;

    @Autowired
    private UserAccountDao userAccountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param productId 主键
     * @return 实例对象
     */
    @Override
    public ProductDto queryById(String productId) {
        return toDto(this.productDao.queryById(productId));
    }

    /**
     * 查询多条数据
     *
     * @param product
     * @param offset  查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    @Override
    public List<ProductDto> queryAllByLimit(Product product, int offset, int limit) {
        List<Product> products = this.productDao.queryAllByLimit(product, offset, limit);
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p : products) {
            productDtos.add(toDto(p));
        }
        return productDtos;
    }

    /**
     * 查询多条数据
     *
     * @param product
     * @return 对象列表
     */
    @Override
    public int queryAll(Product product) {
        List<Product> products = this.productDao.queryAll(product);
        return products.size();
    }

    private ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductCreateTime(product.getProductCreateTime());
        productDto.setProductUpdateTime(product.getProductUpdateTime());
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setProductPrise(product.getProductPrise());
        productDto.setProductStatus(product.getProductStatus());
        productDto.setProductSalesNumber(product.getProductSalesNumber());
        productDto.setProductType(product.getProductType());
        productDto.setProductDescription(product.getProductDescription());
        if (!StringUtils.isEmpty(product.getProductFileInfo())) {
            productDto.setProductFileId(product.getProductFileInfo());
            productDto.setProductFilePath(tFileDao.queryById(product.getProductFileInfo()).getFilePath());
        }
        if (!StringUtils.isEmpty(product.getProductCreateBy())) {
            productDto.setProductCreateBy(product.getProductCreateBy());
            productDto.setProductCreateByName(userAccountDao.queryById(product.getProductCreateBy()).getUserName());
        }
        if (!StringUtils.isEmpty(product.getProductUpdateBy())) {
            productDto.setProductUpdateBy(product.getProductUpdateBy());
            productDto.setProductUpdateByName(userAccountDao.queryById(product.getProductUpdateBy()).getUserName());
        }
        return productDto;
    }

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public ProductDto insert(Product product) {
        product.setProductId(ShortUUID.generate());
        product.setProductCreateBy(LoginInfo.getUserId());
        product.setProductCreateTime(CustomDateUtil.currentFormatDate());
        product.setProductSalesNumber("0");
        product.setProductStatus("0");
        this.productDao.insert(product);
        return toDto(productDao.queryById(product.getProductId()));
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public ProductDto update(Product product) {
        product.setProductUpdateBy(LoginInfo.getUserId());
        product.setProductUpdateTime(CustomDateUtil.currentFormatDate());
        this.productDao.update(product);
        return this.queryById(product.getProductId());
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public ProductDto updateSales(Product product) {
        this.productDao.update(product);
        return this.queryById(product.getProductId());
    }

    /**
     * 通过主键删除数据
     *
     * @param productId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String productId) {
        Product product = productDao.queryById(productId);
        if ("1".equals(product.getProductStatus())) {
            product.setProductStatus("0");
        } else {
            product.setProductStatus("1");
        }
        return this.productDao.update(product) > 0;
    }
}