package cn.kcs.note.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.note.entity.TDictionary;
import cn.kcs.note.entity.dto.DictionaryDto;
import cn.kcs.note.service.TDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TDictionary)表控制层
 *
 * @author makejava
 * @since 2019-03-14 20:35:01
 */
@Api(value = "dict", description = "数据字典 API")
@RestController
@RequestMapping("dict")
public class TDictionaryController {
    /**
     * 服务对象
     */
    @Resource
    private TDictionaryService tDictionaryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping()
    public TDictionary selectOne(String id) {
        return this.tDictionaryService.queryById(id);
    }

    /**
     * 通过key查询单条数据
     *
     * @param key
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "通过key查询单条数据")
    @GetMapping("key")
    public TDictionary getDictionaryByKey(String key) {
        return this.tDictionaryService.getDictionaryByKey(key);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDictionary 实例对象
     * @return 对象列表
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "通过实体查询所有数据")
    @GetMapping("query")
    @Cacheable(value = "file-query-all")
    public List<DictionaryDto> queryAll(TDictionary tDictionary) {
        return this.tDictionaryService.queryAll(tDictionary);
    }

    /**
     * 添加数据
     *
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加数据")
    @PostMapping()
    public TDictionary addAccount(@RequestBody TDictionary account) {
        return this.tDictionaryService.insert(account);
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
    public boolean delete(@PathVariable String id) {
        return this.tDictionaryService.deleteById(id);
    }

    /**
     * 修改单条数据
     *
     * @param account
     * @return
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "修改单条数据")
    @PutMapping()
    public TDictionary editAccount(@RequestBody TDictionary account) {
        return this.tDictionaryService.update(account);
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
    @Cacheable(value = "dictionary-query-pager")
    public List<TDictionary> query(Integer offset, Integer limit) {
        List<TDictionary> tAccounts = this.tDictionaryService.queryAllByLimit(offset, limit);
        return tAccounts;
    }

}