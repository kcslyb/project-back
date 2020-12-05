package cn.kcs.schedule.controller;

import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.schedule.entity.ConvertProduct;
import cn.kcs.schedule.service.ConvertProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ConvertProduct)表控制层
 *
 * @author kcs
 * @since 2020-09-17 20:19:17
 */
@RestController
@RequestMapping("convertProduct")
public class ConvertProductController {
    /**
     * 服务对象
     */
    @Resource
    private ConvertProductService convertProductService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @GetMapping("queryById")
    public ResponseEntity<ConvertProduct> queryById(String id) {
        ConvertProduct convertProduct = this.convertProductService.queryById(id);
        return new ResponseEntity<>(convertProduct, HttpStatus.OK);
    }

    /**
     * 查询多条数据
     *
     * @param convertProduct convertProduct
     * @param pageRequest pageRequest
     * @return object list
     */
    @Decrypt
    @Encrypt
    @GetMapping("query/pager")
    public ResponseEntity queryPager(ConvertProduct convertProduct, PageRequest pageRequest) {
        pageRequest.initStart(pageRequest);
        ResponseDto responseDto = convertProductService.queryPager(convertProduct, pageRequest);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param convertProduct 实例对象
     * @return 实例对象
     */
    @Encrypt
    @Decrypt
    @PostMapping
    public ResponseEntity insert(@RequestBody ConvertProduct convertProduct) {
        boolean insert = convertProductService.insert(convertProduct);
        return new ResponseEntity<>(insert, HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param convertProduct 实例对象
     * @return 实例对象
     */
    @Encrypt
    @Decrypt
    @PutMapping
    public ResponseEntity update(@RequestBody  ConvertProduct convertProduct) {
        if (StringUtils.isBlank(convertProduct.getId())) {
            return new ResponseEntity<>("[" + convertProduct.getId() + "]为无效id", HttpStatus.BAD_REQUEST);
        }
        boolean update = convertProductService.update(convertProduct);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Decrypt
    @Encrypt
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            return new ResponseEntity<>("[" + id + "]为无效id", HttpStatus.BAD_REQUEST);
        }
        boolean delete = convertProductService.deleteById(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}