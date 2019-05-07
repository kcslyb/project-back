package cn.kcs.note.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.note.entity.ContactDto;
import cn.kcs.note.entity.MsgDto;
import cn.kcs.note.entity.TMsg;
import cn.kcs.note.service.TMsgService;
import cn.kcs.note.service.UserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (TMsg)表控制层
 *
 * @author makejava
 * @since 2018-12-30 19:47:03
 */
@Api(value = "msg", description = "msg API")
@RestController
@RequestMapping("tMsg")
public class TMsgController {
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
    @GetMapping("selectOne")
    public TMsg selectOne(String id) {
        return this.tMsgService.queryById(id);
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "添加Msg")
    @PostMapping("addMsg")
    public TMsg add(@RequestBody TMsg tMsgt) {
        return this.tMsgService.insert(tMsgt);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询contact")
    @PostMapping("contact")
    public List<ContactDto> findContact() {
        return this.tMsgService.findContact();
    }

    @Encrypt
    @Decrypt
    @ApiOperation(value = "查询msg")
    @PostMapping("listMsg")
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
    @PostMapping("{str}/editMsg")
    public String updateNote(@PathVariable String str) {
        if (str == null) {
            return null;
        }
        return this.tMsgService.update(str);
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