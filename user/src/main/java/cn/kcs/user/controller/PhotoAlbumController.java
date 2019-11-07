package cn.kcs.user.controller;

import cn.kcs.common.util.ResponseDto;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.PhotoAlbum;
import cn.kcs.user.service.PhotoAlbumService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (PhotoAlbum)表控制层
 *
 * @author kcs
 * @date 2019-05-08 11:33:55
 */
@RestController
@RequestMapping("photo")
public class PhotoAlbumController {
    /**
     * 服务对象
     */
    @Resource
    private PhotoAlbumService photoAlbumService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @GetMapping("")
    public PhotoAlbum selectOne(String id) {
        return this.photoAlbumService.queryById(id);
    }

    /**
     * 添加数据
     *
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "批量添加数据")
    @PostMapping()
    public boolean addAccount(@RequestBody List<PhotoAlbum> photoAlbums) {
        return this.photoAlbumService.insertBatch(photoAlbums);
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
    public ResponseEntity query(PhotoAlbum photoAlbum, Integer offset, Integer limit) {
        if (limit == null || limit < 0) {
            limit = 10;
        }
        if (offset == null || offset < 0) {
            offset = 0;
        } else {
            offset = (offset - 1) * limit;
        }
        int size = this.photoAlbumService.queryAll(photoAlbum);
        List<PhotoAlbum> photoAlbums = this.photoAlbumService.queryAllByLimit(photoAlbum, offset, limit);
        ResponseDto<PhotoAlbum> orderDtoResponseDto = new ResponseDto<>(photoAlbums, size, limit, offset);
        return new ResponseEntity<>(orderDtoResponseDto, HttpStatus.OK);
    }


}