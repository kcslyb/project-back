package cn.kcs.schedule.controller;

import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.schedule.dto.ProductWorkDto;
import cn.kcs.schedule.entity.ProductWork;
import cn.kcs.schedule.service.ProductWorkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ProductWork)表控制层
 *
 * @author kcs
 * @since 2020-09-17 20:16:25
 */
@RestController
@RequestMapping("productWork")
public class ProductWorkController {
    /**
     * 服务对象
     */
    @Resource
    private ProductWorkService productWorkService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @GetMapping("queryById")
    public ResponseEntity<ProductWorkDto> queryById(String id) {
        ProductWorkDto productWork = this.productWorkService.queryById(id);
        return new ResponseEntity<>(productWork, HttpStatus.OK);
    }

    /**
     * 查询多条数据
     *
     * @param productWork productWork
     * @param pageRequest    pageRequest
     * @return object list
     */
    @Decrypt
    @Encrypt
    @GetMapping("query/pager")
    public ResponseEntity<ResponseDto<ProductWorkDto>> queryPager(ProductWork productWork, PageRequest pageRequest) {
        pageRequest.initStart(pageRequest);
        ResponseDto<ProductWorkDto> responseDto = productWorkService.queryPager(productWork, pageRequest);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param productWorkDto 实例对象
     * @return 实例对象
     */
    @Encrypt
    @Decrypt
    @PostMapping
    public ResponseEntity<Boolean> insert(@RequestBody ProductWorkDto productWorkDto) {
        boolean insert = productWorkService.insert(productWorkDto);
        return new ResponseEntity<>(insert, HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param productWorkDto 实例对象
     * @return 实例对象
     */
    @Encrypt
    @Decrypt
    @PutMapping
    public ResponseEntity update(@RequestBody ProductWorkDto productWorkDto) {
        if (StringUtils.isBlank(productWorkDto.getId())) {
            return new ResponseEntity<>("[" + productWorkDto.getId() + "]为无效id", HttpStatus.BAD_REQUEST);
        }
        boolean update = productWorkService.update(productWorkDto);
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
    public ResponseEntity<Boolean> deleteById(@PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            return new ResponseEntity("[" + id + "]为无效id", HttpStatus.BAD_REQUEST);
        }
        boolean delete = productWorkService.deleteById(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}