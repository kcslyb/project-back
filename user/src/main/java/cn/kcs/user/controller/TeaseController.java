package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.Tease;
import cn.kcs.user.service.TeaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Tease)表控制层
 *
 * @author kcs
 * @since 2019-01-06 20:33:36
 */
@Api(value = "tease", description = "tease API")
@RestController
@RequestMapping("tease")
public class TeaseController {
    /**
     * 服务对象
     */
    @Resource
    private TeaseService teaseService;

    /**
     * 新增数据
     *
     * @param tease 实例对象
     * @return 实例对象
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "添加tease")
    @PostMapping("addTease")
    public Tease addTease(@RequestBody Tease tease) {
        return teaseService.insert(tease);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询tease")
    @GetMapping("selectOne")
    public Tease selectOne(String id) {
        return this.teaseService.queryById(id);
    }

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "删除tease")
    @DeleteMapping("delete/{id}")
    public boolean deleteOne(@PathVariable String id) {
        return this.teaseService.deleteById(id);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询tease")
    @PostMapping("queryTease")
    public List<Tease> queryAll() {
        Tease tease = new Tease();
        return teaseService.queryAll(tease);
    }

    /**
     * 修改数据
     *
     * @param tease 实例对象
     * @return 实例对象
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "修改tease")
    @PostMapping("update")
    public Tease update(@RequestBody Tease tease) {
        return teaseService.update(tease);
    }

}