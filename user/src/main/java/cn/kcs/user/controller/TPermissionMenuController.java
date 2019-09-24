package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.TPermissionMenu;
import cn.kcs.user.service.TPermissionMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TPermissionMenu)表控制层
 *
 * @author makejava
 * @since 2019-03-08 09:55:53
 */
@Api(value = "PermissionMenu", description = "PermissionMenu API")
@RestController
@RequestMapping("/permission/menu")
public class TPermissionMenuController {
    /**
     * 服务对象
     */
    @Resource
    private TPermissionMenuService tPermissionMenuService;

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
    public TPermissionMenu selectOne(@PathVariable String id) {
        return this.tPermissionMenuService.queryById(id);
    }

    /**
     * 添加数据
     *
     * @param tPermissionMenu
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "添加数据")
    @PostMapping()
    public TPermissionMenu add(@RequestBody TPermissionMenu tPermissionMenu) {
        return this.tPermissionMenuService.insert(tPermissionMenu);
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
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable String id) {
        return this.tPermissionMenuService.deleteById(id);
    }

    /**
     * 修改单条数据
     *
     * @param tPermissionMenu
     * @return
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "修改单条数据")
    @PutMapping()
    public TPermissionMenu edit(@RequestBody TPermissionMenu tPermissionMenu) {
        return this.tPermissionMenuService.update(tPermissionMenu);
    }

    /**
     * 查询所以数据
     *
     * @param tPermissionMenu
     * @return List数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询所以数据")
    @GetMapping("query")
    public List<TPermissionMenu> query(TPermissionMenu tPermissionMenu) {
        List<TPermissionMenu> list = this.tPermissionMenuService.queryAll(tPermissionMenu);
        return list;
    }
}