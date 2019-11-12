package cn.kcs.order.controller;

import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.order.entity.Memo;
import cn.kcs.order.service.MemoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Memo)表控制层
 *
 * @author kcs
 * @date 2019-11-08 18:23:56
 */
@RestController
@RequestMapping("memo")
public class MemoController {
    /**
     * 服务对象
     */
    @Resource
    private MemoService memoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @GetMapping("{id}")
    public ResponseEntity queryById(@PathVariable String id) {
        Memo memo = this.memoService.queryById(id);
        return new ResponseEntity<>(memo, HttpStatus.OK);
    }

    /**
     * 查询多条数据
     *
     * @param memo        memo
     * @param pageRequest page request
     * @return object list
     */
    @Decrypt
    @Encrypt
    @GetMapping("query/pager")
    public ResponseEntity queryPager(Memo memo, PageRequest pageRequest) {
        pageRequest.initStart(pageRequest);
        ResponseDto responseDto = memoService.queryPager(memo, pageRequest);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param memo 实例对象
     * @return 实例对象
     */
    @Encrypt
    @Decrypt
    @PostMapping
    public ResponseEntity insert(@RequestBody Memo memo) {
        boolean insert = memoService.insert(memo);
        return new ResponseEntity<>(insert, HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param memo 实例对象
     * @return 实例对象
     */
    @Encrypt
    @Decrypt
    @PutMapping
    public ResponseEntity update(@RequestBody Memo memo) {
        if (StringUtils.isBlank(memo.getId())) {
            return new ResponseEntity<>("[" + memo.getId() + "]为无效id", HttpStatus.BAD_REQUEST);
        }
        boolean update = memoService.update(memo);
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
        Memo memo = memoService.queryById(id);
        if (StringUtils.isBlank(memo.getId())) {
            return new ResponseEntity<>("[" + id + "]为无效id", HttpStatus.BAD_REQUEST);
        }
        boolean delete = memoService.deleteById(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}