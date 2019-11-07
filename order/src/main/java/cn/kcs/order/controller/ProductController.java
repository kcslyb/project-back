package cn.kcs.order.controller;

import cn.kcs.common.util.ResponseDto;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.order.entity.Product;
import cn.kcs.order.entity.dto.ProductDto;
import cn.kcs.order.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Product)表控制层
 *
 * @author makejava
 * @since 2019-04-23 14:38:33
 */
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
    public ResponseEntity add(@RequestBody Product product) {
        ProductDto insert = this.productService.insert(product);
        return new ResponseEntity<>(insert, HttpStatus.OK);
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
    public ResponseEntity delete(@PathVariable String id) {
        boolean delete = this.productService.deleteById(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
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
    public ResponseEntity edit(@RequestBody Product product) {
        ProductDto update = this.productService.update(product);
        return new ResponseEntity<>(update, HttpStatus.OK);
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
    public ResponseEntity query(Product product, String offset, String limit) {
        if (offset == null || "".equals(offset)) {
            offset = "0";
        } else {
            offset = Integer.toString((Integer.parseInt(offset) - 1) * Integer.parseInt(limit));
        }
        if (limit == null || "".equals(limit)) {
            limit = "10";
        }
        int size = this.productService.queryAll(product);
        List<ProductDto> productDtoList = this.productService.queryAllByLimit(product,
                Integer.parseInt(offset), Integer.parseInt(limit));
        ResponseDto<ProductDto> productDtoResponseDto = new ResponseDto<>(productDtoList,
                size, Integer.parseInt(limit), Integer.parseInt(offset));
        return new ResponseEntity<>(productDtoResponseDto, HttpStatus.OK);
    }
}