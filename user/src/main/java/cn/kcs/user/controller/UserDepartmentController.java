package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.UserDepartment;
import cn.kcs.user.entity.dto.DepartmentDto;
import cn.kcs.user.service.UserDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserDepartment)表控制层
 *
 * @author kcs
 * @since 2019-03-22 10:08:44
 */
@RestController
@RequestMapping("/department")
public class UserDepartmentController {
    /**
     * 服务对象
     */
    @Resource
    private UserDepartmentService userDepartmentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("{id}")
    public UserDepartment queryById(@PathVariable String id) {
        return this.userDepartmentService.queryById(id);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param department 实例对象
     * @return 对象列表
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "通过实体查询所有数据")
    @GetMapping("query")
    public List<UserDepartment> queryAll(UserDepartment department) {
        return this.userDepartmentService.queryAll(department);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param department 实例对象
     * @return 对象列表
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "通过实体查询相关的User数据")
    @GetMapping("query/user")
    public List<DepartmentDto> queryAllUserByDepartment(UserDepartment department) {
        return this.userDepartmentService.queryAllUserByDepartment(department);
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
    public UserDepartment addAccount(@RequestBody UserDepartment department) {
        return this.userDepartmentService.insert(department);
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
        return this.userDepartmentService.deleteById(id);
    }

    /**
     * 修改单条数据
     *
     * @param department
     * @return
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "修改单条数据")
    @PutMapping()
    public UserDepartment editAccount(@RequestBody UserDepartment department) {
        return this.userDepartmentService.update(department);
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
    public List<UserDepartment> query(String offset, String limit) {
        if (offset == null || "".equals(offset)) {
            offset = "0";
        }
        if (limit == null || "".equals(limit)) {
            limit = "10";
        }
        List<UserDepartment> tAccounts = this.userDepartmentService.queryAllByLimit(Integer.parseInt(offset), Integer.parseInt(limit));
        return tAccounts;
    }
}