package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.Dict;
import cn.kcs.user.service.DictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Dict)表控制层
 *
 * @author kcs
 * @since 2019-11-01 11:37:31
 */
@RestController
@RequestMapping("dict")
public class DictController {
    /**
     * 服务对象
     */
    @Resource
    private DictService dictService;

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
        Dict dict = this.dictService.queryById(id);
        return new ResponseEntity<>(dict, HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @return 多条数据
     */
    @Decrypt
    @Encrypt
    @GetMapping("query")
    public ResponseEntity query(Dict dict, String keyWord) {
        List<Dict> dictList = this.dictService.queryAll(dict, keyWord);
        return new ResponseEntity<>(dictList, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @PutMapping()
    public ResponseEntity update(@RequestBody Dict dict) {
        return dictService.update(dict);
    }

    @Decrypt
    @Encrypt
    @PostMapping()
    public ResponseEntity insert(@RequestBody Dict dict) {
        return dictService.insert(dict);
    }

    @Decrypt
    @Encrypt
    @DeleteMapping("{dictId}")
    public ResponseEntity delete(@PathVariable String dictId) {
        boolean delete = dictService.deleteById(dictId);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}