package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.TAccount;
import cn.kcs.user.service.TAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TAccount)表控制层
 *
 * @author makejava
 * @since 2019-02-25 09:39:21
 */
@Api(value = "account", description = "account API")
@RestController
@RequestMapping("account")
public class TAccountController {
    /**
     * 服务对象
     */
    @Resource
    private TAccountService tAccountService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("select/{id}")
    public TAccount selectOne(@PathVariable String id) {
        return this.tAccountService.queryById(id);
    }

    /**
     * 添加数据
     *
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加数据")
    @PostMapping("add")
    public TAccount addAccount(@RequestBody TAccount account) {
        return this.tAccountService.insert(account);
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
    @DeleteMapping("delete/{id}")
    public boolean delete(@PathVariable String id) {
        return this.tAccountService.deleteById(id);
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
    @PostMapping("edit")
    public TAccount editAccount(@RequestBody TAccount account) {
        return this.tAccountService.update(account);
    }

    /**
     * 查询所以数据
     *
     * @return List数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询所以数据")
    @GetMapping("query")
    public List<TAccount> query(TAccount account, Integer offset, Integer limit, String startTime, String endTime) {
        List<TAccount> tAccounts = this.tAccountService.queryAllByLimit(account, offset, limit, startTime, endTime);
        return tAccounts;
    }

}