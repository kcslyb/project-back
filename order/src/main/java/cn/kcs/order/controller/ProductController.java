package cn.kcs.order.controller;

import cn.kcs.common.util.CommonUtil;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.order.entity.Product;
import cn.kcs.order.entity.dto.ProductDto;
import cn.kcs.order.service.ProductService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Product)表控制层
 *
 * @author makejava
 * @since 2019-04-23 14:38:33
 */
@Api(value = "product", description = "product API")
@RestController
@RequestMapping("product")
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("")
    public ProductDto selectById(String id) {
        return this.productService.queryById(id);
    }

    /**
     * 添加数据
     *
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加数据")
    @PostMapping(value = {"", "/add"})
    public JSONObject add(@RequestBody Product product) {
        return CommonUtil.successJson(this.productService.insert(product));
    }

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "删除单条数据")
    @DeleteMapping("/{id}")
    public JSONObject delete(@PathVariable String id) {
        return CommonUtil.successJson(this.productService.deleteById(id));
    }

    /**
     * 修改单条数据
     *
     * @param product
     * @return
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "修改单条数据")
    @PutMapping()
    public JSONObject edit(@RequestBody Product product) {
        return CommonUtil.successJson(this.productService.update(product));
    }

    /**
     * 查询所以数据
     *
     * @return List数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询所以数据")
    @GetMapping("query/pager")
    public JSONObject query(Product product, String offset, String limit) {
        if (offset == null || "".equals(offset)) {
            offset = "0";
        } else {
            offset = Integer.toString((Integer.parseInt(offset) - 1) * Integer.parseInt(limit));
        }
        if (limit == null || "".equals(limit)) {
            limit = "10";
        }
        int size = this.productService.queryAll(product);
        return CommonUtil.successPage(this.productService.queryAllByLimit(product, Integer.parseInt(offset), Integer.parseInt(limit)), size);
    }
}