package cn.kcs.user.controller;

import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.Bookmarks;
import cn.kcs.user.entity.Dict;
import cn.kcs.user.service.BookmarksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Bookmarks)表控制层
 *
 * @author makejava
 * @since 2021-05-21 11:54:44
 */
@RestController
@RequestMapping("bookmarks")
public class BookmarksController {
    /**
     * 服务对象
     */
    @Resource
    private BookmarksService bookmarksService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @GetMapping("{id}")
    public ResponseEntity<Bookmarks> queryById(@PathVariable String id) {
        Bookmarks bookmarks = this.bookmarksService.queryById(id);
        return new ResponseEntity<>(bookmarks, HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @return 多条数据
     */
    @Decrypt
    @Encrypt
    @GetMapping("query")
    public ResponseEntity<ResponseDto<Bookmarks>> query(Bookmarks bookmarks, PageRequest pageRequest) {
        pageRequest = pageRequest.initStart(pageRequest);
        List<Bookmarks> bookmarksList = this.bookmarksService.queryAll(bookmarks, pageRequest);
        List<Bookmarks> bookmarksListPage = this.bookmarksService.queryAllByLimit(bookmarks, pageRequest);
        ResponseDto<Bookmarks> responseDto = new ResponseDto<>(bookmarksListPage,
                bookmarksList.size(), pageRequest.getSize(), pageRequest.getStart());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @PutMapping()
    public ResponseEntity update(@RequestBody Bookmarks bookmarks) {
        boolean flag = bookmarksService.update(bookmarks);
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @PostMapping()
    public ResponseEntity insert(@RequestBody Bookmarks bookmarks) {
        boolean flag = bookmarksService.insert(bookmarks);
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @PostMapping("batch")
    public ResponseEntity insertBatch(@RequestBody List<Bookmarks> bookmarks) {
        boolean flag = bookmarksService.insertBatch(bookmarks);
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id) {
        boolean delete = bookmarksService.deleteById(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @PostMapping("/click/{id}")
    public ResponseEntity click(@PathVariable String id) {
        boolean delete = bookmarksService.click(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}