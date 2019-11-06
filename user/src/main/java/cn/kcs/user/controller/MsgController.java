package cn.kcs.user.controller;

import cn.kcs.common.util.CommonUtil;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.TMsg;
import cn.kcs.user.entity.dto.ContactDto;
import cn.kcs.user.entity.dto.MsgDto;
import cn.kcs.user.service.TMsgService;
import cn.kcs.user.service.UserAccountService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (TMsg)表控制层
 *
 * @author makejava
 * @since 2018-12-30 19:47:03
 */
@Api(value = "msg", description = "MSG API")
@RestController
@RequestMapping("msg")
public class MsgController {
    /**
     * 服务对象
     */
    @Autowired
    private TMsgService tMsgService;

    @Autowired
    private UserAccountService userAccountService;

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
    public JSONObject updateNote(@PathVariable String str) {
        if (str == null) {
            return CommonUtil.successJson(0);
        }
        return CommonUtil.successJson(this.tMsgService.update(str));
    }

//    @ApiOperation(value = "导入contact")
//    @PostMapping("import/contact")
//    public void importVCFFileContact(String filepath) {
//        if (filepath == null) {
//            filepath = "/Users/kcs/idea-workspace/myproject/notepad/src/main/resources/contact.vcf";
//        }
//        try {
//            List<Concat> contates = VcfUtil.importVCFFileContact(new FileInputStream(new File(filepath)));
//            for (Concat contate : contates) {
//                UserDto userDto = new UserDto();
//                userDto.setUserName(contate.getTrueName());
//                userDto.setUserPassword(contate.getTelePhone());
//                userDto.setUserPhone(contate.getTelePhone());
//                if ("".equals(contate.getEmail())){
//                    userDto.setUserEmail(contate.getTelePhone()+"@163.com");
//                }
//                userDto.setUserStatus("0");
//                userDto.setRoleDto(new RoleDto());
//                userService.add(userDto);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}