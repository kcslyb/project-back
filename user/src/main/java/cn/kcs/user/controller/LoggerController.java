package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.LoggerDto;
import cn.kcs.user.service.LoggerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Logger)表控制层
 *
 * @author kcs
 * @date 2019-09-06 13:05:53
 */
@RestController
@RequestMapping("logger")
public class LoggerController {
    /**
     * 服务对象
     */
    @Resource
    private LoggerService loggerService;

    /**
     * 添加单条数据
     *
     * @param loggerDto 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @PostMapping()
    public ResponseEntity<Boolean> add(@RequestBody LoggerDto loggerDto) {
        return new ResponseEntity<>(this.loggerService.insert(loggerDto), HttpStatus.OK);
    }

    /**
     * 条件分页查询所以数据
     *
     * @return List数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询所以数据")
    @GetMapping("query")
    public ResponseEntity query(LoggerDto loggerDto, Integer offset, Integer limit) {
        if (limit == null || limit < 0) {
            limit = 10;
        }
        if (offset == null || offset < 0) {
            offset = 0;
        } else {
            offset = (offset - 1) * limit;
        }
        int size = this.loggerService.queryAll(loggerDto).size();
        List<LoggerDto> loggers = this.loggerService.queryAllByLimit(loggerDto, offset, limit);
        Map<String, Object> map = new HashMap<>(2);
        map.put("data", loggers);
        map.put("count", size);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}