package cn.kcs.schedule.controller;

import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.schedule.entity.Convert;
import cn.kcs.schedule.service.ConvertService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Convert)表控制层
 *
 * @author kcs
 * @since 2020-09-17 20:11:33
 */
@RestController
@RequestMapping("convert")
public class ConvertController {
    /**
     * 服务对象
     */
    @Resource
    private ConvertService convertService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @GetMapping("queryById")
    public ResponseEntity<Convert> queryById(String id) {
        Convert convert = this.convertService.queryById(id);
        return new ResponseEntity<>(convert, HttpStatus.OK);
    }

    /**
     * 查询多条数据
     *
     * @param convert convert
     * @param pageRequest    pageRequest
     * @return object list
     */
    @Decrypt
    @Encrypt
    @GetMapping("query/pager")
    public ResponseEntity<ResponseDto<Convert>> queryPager(Convert convert, PageRequest pageRequest) {
        pageRequest.initStart(pageRequest);
        ResponseDto<Convert> responseDto = convertService.queryPager(convert, pageRequest);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 查询关联关系
     *
     * @param relevance relevance
     * @return object list
     */
    @Decrypt
    @Encrypt
    @GetMapping("query/relevance")
    public ResponseEntity<List<Convert>> queryRelevance(String relevance) {
        List<Convert> responseDto = convertService.queryRelevance(relevance);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param convert 实例对象
     * @return 实例对象
     */
    @Encrypt
    @Decrypt
    @PostMapping
    public ResponseEntity<Boolean> insert(@RequestBody Convert convert) {
        boolean insert = convertService.insert(convert);
        return new ResponseEntity<>(insert, HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param convert 实例对象
     * @return 实例对象
     */
    @Encrypt
    @Decrypt
    @PutMapping
    public ResponseEntity update(@RequestBody Convert convert) {
        if (StringUtils.isBlank(convert.getId())) {
            return new ResponseEntity<>("[" + convert.getId() + "]为无效id", HttpStatus.BAD_REQUEST);
        }
        boolean update = convertService.update(convert);
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
        boolean delete = convertService.deleteById(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}