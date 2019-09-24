package cn.kcs.user.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.UserAccount;
import cn.kcs.user.service.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (UserAccount)表控制层
 *
 * @author kcs
 * @date 2019-03-21 14:46:59
 */
@Api(value = "user account", description = "用户账号 API")
@RestController
@RequestMapping("/user/account")
public class UserAccountController {
    /**
     * 服务对象
     */
    @Resource
    private UserAccountService userAccountService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping()
    public UserAccount selectOne(String id) {
        return this.userAccountService.queryById(id);
    }

    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("user")
    public ResponseEntity selectById(String id) {
        return new ResponseEntity<>(this.userAccountService.queryById(id), HttpStatus.OK);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param userAccount 实例对象
     * @return 对象列表
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "通过实体查询所有数据")
    @GetMapping("query")
    public List<UserAccount> queryAll(UserAccount userAccount) {
        return this.userAccountService.queryAll(userAccount);
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
    public UserAccount addAccount(@RequestBody UserAccount account) {
        return this.userAccountService.insert(account);
    }

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @RequiresRoles("SuperAdmin")
    @ApiOperation(value = "删除单条数据")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return this.userAccountService.deleteById(id);
    }

    /**
     * 修改单条数据
     *
     * @param account
     * @return
     */
    @Encrypt
    @Decrypt
    @RequiresRoles("SuperAdmin")
    @ApiOperation(value = "修改单条数据")
    @PutMapping()
    public UserAccount editAccount(@RequestBody UserAccount account) {
        return this.userAccountService.update(account);
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
    public List<UserAccount> query(Integer offset, Integer limit) {
        if (limit == null || limit < 0) {
            limit = 10;
        }
        if (offset == null || offset < 0) {
            offset = 0;
        } else {
            offset = (offset - 1) * limit;
        }
        List<UserAccount> tAccounts = this.userAccountService.queryAllByLimit(offset, limit);
        return tAccounts;
    }

    /**
     * 查询所以数据
     *
     * @return List数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "导出user数据")
    @GetMapping("query/export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        Map map = new HashMap();
        ExportParams params = new ExportParams("userAccount", "user", ExcelType.XSSF);
        params.setFreezeCol(2);
        map.put(NormalExcelConstants.DATA_LIST, userAccountService.queryAll(new UserAccount()));
        map.put(NormalExcelConstants.CLASS, UserAccount.class);
        map.put(NormalExcelConstants.PARAMS, params);
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

}