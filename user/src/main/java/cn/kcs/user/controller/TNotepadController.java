package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.TNotepad;
import cn.kcs.user.service.TNotepadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TNotepad)表控制层
 *
 * @author makejava
 * @since 2018-12-28 14:44:32
 */
@Api(value = "note", description = "note API")
@RestController
@RequestMapping("note")
public class TNotepadController {
    /**
     * 服务对象
     */
    @Resource
    private TNotepadService tNotepadService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "根据id查询")
    @PostMapping("selectOne")
    public TNotepad selectOne(String id) {
        if (id == null) {
            return null;
        }
        return this.tNotepadService.queryById(id);
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "添加note")
    @PostMapping("addNote")
    public TNotepad add(@RequestBody TNotepad tNotepad) {
        return this.tNotepadService.insert(tNotepad);
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "查询note")
    @PostMapping("listNote")
    public List<TNotepad> pagerSelectAll(Integer start, Integer size) {
        if (start == null) {
            start = 0;
        }
        if (size == null) {
            size = 10;
        }
        TNotepad tNotepad = new TNotepad();
        tNotepad.setNoteType("1");
        return this.tNotepadService.queryAllByLimit(tNotepad, start, size);
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "查询note")
    @PostMapping("listNoteAll")
    public List<TNotepad> queryAll() {
        TNotepad tNotepad = new TNotepad();
        return this.tNotepadService.queryAll(tNotepad);
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "修改note")
    @PostMapping("editNote/{note_id}")
    public TNotepad updateNote(@RequestBody TNotepad tNotepad, @PathVariable String note_id) {
        if (note_id == null) {
            return null;
        }
        tNotepad.setNoteId(note_id);
        return this.tNotepadService.update(tNotepad);
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "删除note")
    @PostMapping("deleteNote/{note_id}")
    public boolean deleteNote(@PathVariable String note_id) {
        if (note_id == null) {
            return false;
        }
        return this.tNotepadService.deleteById(note_id);
    }
}