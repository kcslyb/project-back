package cn.kcs.user.controller;

import cn.kcs.common.util.PageRequest;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.DictGroup;
import cn.kcs.user.service.DictGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DictGroup)表控制层
 *
 * @author kcs
 * @since 2019-11-01 11:37:54
 */
@RestController
@RequestMapping("dictGroup")
public class DictGroupController {
    @Resource
    private DictGroupService dictGroupService;

    @Decrypt
    @Encrypt
    @GetMapping("{id}")
    public ResponseEntity queryById(@PathVariable String id) {
        DictGroup dictGroup = this.dictGroupService.queryById(id);
        return new ResponseEntity<>(dictGroup, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @GetMapping("query")
    public ResponseEntity query(DictGroup dictGroup, PageRequest pageRequest) {
        pageRequest.initStart(pageRequest);
        List<DictGroup> dictGroupList = dictGroupService.queryAll(dictGroup, pageRequest);
        return new ResponseEntity<>(dictGroupList, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @PutMapping()
    public ResponseEntity update(@RequestBody DictGroup dictGroup) {
        return dictGroupService.update(dictGroup);
    }

    @Decrypt
    @Encrypt
    @PostMapping()
    public ResponseEntity insert(@RequestBody DictGroup dictGroup) {
        return dictGroupService.insert(dictGroup);
    }

    @Decrypt
    @Encrypt
    @DeleteMapping("{dictGroupId}")
    public ResponseEntity delete(@PathVariable String dictGroupId) {
        boolean delete = dictGroupService.deleteById(dictGroupId);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }
}