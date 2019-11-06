package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.Notepad;
import cn.kcs.user.service.NotepadService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TNotepad)表控制层
 *
 * @author makejava
 * @since 2018-12-28 14:44:32
 */
@RestController
@RequestMapping("note")
public class NotepadController {
    /**
     * 服务对象
     */
    @Resource
    private NotepadService notepadService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "根据id查询")
    @GetMapping("/{id}")
    public ResponseEntity queryById(@PathVariable String id) {
        Notepad notepad = this.notepadService.queryById(id);
        return new ResponseEntity<>(notepad, HttpStatus.OK);
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "添加note")
    @PostMapping()
    public ResponseEntity add(@RequestBody Notepad notepad) {
        boolean addResult = this.notepadService.insert(notepad);
        return new ResponseEntity<>(addResult, HttpStatus.OK);
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "查询note")
    @GetMapping("/query/pager")
    public ResponseEntity queryPager(Integer start, Integer size) {
        if (start == null) {
            start = 0;
        }
        if (size == null) {
            size = 10;
        }
        Notepad notepad = new Notepad();
        notepad.setNoteType("1");
        List<Notepad> notepads = this.notepadService.queryAllByLimit(notepad, start, size);
        return new ResponseEntity<>(notepads, HttpStatus.OK);
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "查询note")
    @GetMapping("query")
    public ResponseEntity queryAll() {
        Notepad notepad = new Notepad();
        List<Notepad> notepads = this.notepadService.queryAll(notepad);
        return new ResponseEntity<>(notepads, HttpStatus.OK);
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "修改note")
    @PutMapping
    public ResponseEntity updateNote(@RequestBody Notepad notepad) {
        if (StringUtils.isBlank(notepad.getNoteId())) {
            return null;
        }
        Notepad tNotepad = this.notepadService.update(notepad);
        return new ResponseEntity<>(tNotepad, HttpStatus.OK);
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "删除note")
    @DeleteMapping("/{noteId}")
    public ResponseEntity deleteNote(@PathVariable String noteId) {
        boolean delete = this.notepadService.deleteById(noteId);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}