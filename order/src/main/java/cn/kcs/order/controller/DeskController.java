package cn.kcs.order.controller;

import cn.kcs.common.util.ResponseDto;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.order.entity.Desk;
import cn.kcs.order.service.DeskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public ResponseEntity selectById(String id) {
        Desk desk = this.deskService.queryById(id);
        return new ResponseEntity<>(desk, HttpStatus.OK);
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
    public ResponseEntity add(@RequestBody Desk desk) {
        Desk insert = this.deskService.insert(desk);
        return new ResponseEntity<>(insert, HttpStatus.OK);
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
    public ResponseEntity delete(@PathVariable String id) {
        boolean delete = this.deskService.deleteById(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
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
    public ResponseEntity edit(@RequestBody Desk desk) {
        Desk update = this.deskService.update(desk);
        return new ResponseEntity<>(update, HttpStatus.OK);
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
    public ResponseEntity query(Desk desk, Integer offset, Integer limit) {
        if (offset == null || "".equals(offset)) {
            offset = 0;
        } else {
            offset = (offset - 1) * limit;
        }
        if (limit == null || "".equals(limit)) {
            limit = 10;
        }
        int size = this.deskService.queryAll(desk);
        List<Desk> desks = this.deskService.queryAllByLimit(desk, offset, limit);
        ResponseDto<Desk> orderDtoResponseDto = new ResponseDto<>(desks, size, limit, offset);
        return new ResponseEntity<>(orderDtoResponseDto, HttpStatus.OK);
    }

}