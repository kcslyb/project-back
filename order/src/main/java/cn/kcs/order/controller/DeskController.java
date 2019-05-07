package cn.kcs.order.controller;

import cn.kcs.common.util.CommonUtil;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.order.entity.Desk;
import cn.kcs.order.service.DeskService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Desk)表控制层
 *
 * @author makejava
 * @since 2019-04-24 14:35:14
 */
@RestController
@RequestMapping("desk")
public class DeskController {
    /**
     * 服务对象
     */
    @Resource
    private DeskService deskService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("")
    public JSONObject selectById(String id) {
        return CommonUtil.successJson(this.deskService.queryById(id));
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
    public JSONObject add(@RequestBody Desk desk) {
        return CommonUtil.successJson(this.deskService.insert(desk));
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
    public JSONObject delete(@PathVariable String id) {
        return CommonUtil.successJson(this.deskService.deleteById(id));
    }

    /**
     * 修改单条数据
     *
     * @param desk
     * @return
     */
    @Encrypt
    @Decrypt
    @ApiOperation(value = "修改单条数据")
    @PutMapping()
    public JSONObject edit(@RequestBody Desk desk) {
        return CommonUtil.successJson(this.deskService.update(desk));
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
    public JSONObject query(Desk desk, Integer offset, Integer limit) {
        if (offset == null || "".equals(offset)) {
            offset = 0;
        } else {
            offset = (offset - 1) * limit;
        }
        if (limit == null || "".equals(limit)) {
            limit = 10;
        }
        int size = this.deskService.queryAll(desk);
        return CommonUtil.successPage(this.deskService.queryAllByLimit(desk, offset, limit), size);
    }

}