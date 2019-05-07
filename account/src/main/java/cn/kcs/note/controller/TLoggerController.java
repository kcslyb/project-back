package cn.kcs.note.controller;

import cn.kcs.common.util.CommonUtil;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.note.entity.TLogger;
import cn.kcs.note.service.TLoggerService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TLogger)表控制层
 *
 * @author makejava
 * @since 2019-04-17 11:46:47
 */
@Api(value = "logger", description = "logger API")
@RestController
@RequestMapping("/logger")
public class TLoggerController {
    /**
     * 服务对象
     */
    @Resource
    private TLoggerService tLoggerService;

    /**
     * 条件分页查询所以数据
     *
     * @return List数据
     */
    @Decrypt
    @Encrypt
    @ApiOperation(value = "查询所以数据")
    @GetMapping("query")
    public JSONObject query(TLogger tLogger, Integer offset, Integer limit) {
        if (limit == null || limit < 0) {
            limit = 10;
        }
        if (offset == null || offset < 0) {
            offset = 0;
        } else {
            offset = (offset - 1) * limit;
        }
        int size = this.tLoggerService.queryAll(tLogger).size();
        List<TLogger> tLoggers = this.tLoggerService.queryAllByLimit(tLogger, offset, limit);
        return CommonUtil.successPage(tLoggers, size);
    }

}