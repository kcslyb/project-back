package cn.kcs.controller.action;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.service.inter.LogService;
import cn.kcs.service.inter.dto.LogDto;
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

import java.util.List;

/**
 * @description: log info
 * @author: kcs
 * @create: 2018-11-06 15:55
 **/
@RequestMapping(value = "log")
@Api(value = "日志", description = "日志 API")
@RestController
public class LogController {
    @Autowired
    private LogService logService;

    @Decrypt
    @Encrypt
    @ApiOperation(value = "分页查询所有log信息", response = PageResponse.class)
    @GetMapping("list")
    public ResponseEntity queryPager(LogDto logDto, Integer pageNo, Integer pageSize) {
        if (pageSize == null || pageSize < 0) {
            pageSize = 10;
        }
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
        PageResponse pageResponse = logService.queryPager(pageRequest.getStart(), pageSize, logDto);
        return new ResponseEntity(pageResponse, HttpStatus.OK);
    }

    @Decrypt
    @Encrypt
    @ApiOperation(value = "分页查询个人日志信息")
    @GetMapping("query")
    public ResponseEntity queryPagerByUser() {
        List<LogDto> query = logService.query();
        return new ResponseEntity(query, HttpStatus.OK);
    }
}
