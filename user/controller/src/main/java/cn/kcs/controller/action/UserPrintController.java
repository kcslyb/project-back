package cn.kcs.controller.action;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.kcs.common.fileUtil.LocalFileRepositoryImpl;
import cn.kcs.common.fileUtil.NormalException;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.service.inter.FileService;
import cn.kcs.service.inter.UserService;
import cn.kcs.service.inter.dto.UserDto;
import cn.kcs.service.inter.util.PageRequest;
import cn.kcs.service.inter.util.PageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: user print
 * @author: kcs
 * @create: 2018-10-22 11:34
 **/
@RequestMapping(value = "user")
@Api(value = "用户信息打印", description = "用户信息打印API")
@RestController
public class UserPrintController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    @Decrypt
    @Encrypt
    @ApiOperation(value = "打印分页查询后的用户信息", response = PageResponse.class)
    @GetMapping("print")
    public ResponseEntity download(HttpServletRequest request, HttpServletResponse response, UserDto userDto, Integer pageNo, Integer pageSize) {
        if (pageSize == null || pageSize < 0) {
            pageSize = 20;
        }
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
        PageResponse<UserDto> pageResponse = userService.queryPager(pageRequest.getStart(), pageSize, userDto);
        List<UserDto> list = new ArrayList<>(userService.query(new UserDto()));

        try {
            LocalFileRepositoryImpl.downLoadExcel("userDto", response, ExcelExportUtil.exportExcel(new ExportParams(), UserDto.class, list));
        } catch (NormalException e) {
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
