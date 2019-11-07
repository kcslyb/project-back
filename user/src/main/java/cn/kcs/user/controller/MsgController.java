package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.TMsg;
import cn.kcs.user.entity.dto.ContactDto;
import cn.kcs.user.entity.dto.MsgDto;
import cn.kcs.user.service.TMsgService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (TMsg)表控制层
 *
 * @author kcs
 * @date 2018-12-30 19:47:03
 */
@RestController
@RequestMapping("msg")
public class MsgController {
    /**
     * 服务对象
     */
    @Autowired
    private TMsgService tMsgService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询一条")
    @GetMapping
    public ResponseEntity selectOne(String id) {
        TMsg tMsg = this.tMsgService.queryById(id);
        return new ResponseEntity<>(tMsg, HttpStatus.OK);
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "添加Msg")
    @PostMapping()
    public TMsg add(@RequestBody TMsg tMsg) {
        return this.tMsgService.insert(tMsg);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询contact")
    @GetMapping("query")
    public List<ContactDto> findContact() {
        return this.tMsgService.findContact();
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "查询msg")
    @PostMapping("list")
    public MsgDto pagerSelectAll(@RequestBody MsgDto msgDto) {
        if (msgDto.getStart() == null) {
            msgDto.setStart(0);
        }
        if (msgDto.getLimit() == null) {
            msgDto.setLimit(10);
        }
        msgDto.setInfoNumber(0);
        return this.tMsgService.queryAllByLimit(msgDto);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "修改msg")
    @PostMapping("{str}/edit")
    public ResponseEntity updateNote(@PathVariable String str) {
        int update = this.tMsgService.update(str);
        return new ResponseEntity<>(update > 0, HttpStatus.OK);
    }

}