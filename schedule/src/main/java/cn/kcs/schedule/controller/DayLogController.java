package cn.kcs.schedule.controller;

import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.schedule.entity.DayLog;
import cn.kcs.schedule.entity.DayLogCount;
import cn.kcs.schedule.service.DayLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DayLog)表控制层
 *
 * @author makejava
 * @since 2020-12-07 15:42:31
 */
@RestController
@RequestMapping("dayLog")
public class DayLogController {
    /**
     * 服务对象
     */
    @Resource
    private DayLogService dayLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Decrypt
    @Encrypt
    @GetMapping("queryById")
    public ResponseEntity<DayLog> queryById(String id) {
        DayLog dayLog = this.dayLogService.queryById(id);
        return new ResponseEntity<>(dayLog, HttpStatus.OK);
    }

    /**
     * 查询多条数据
     *
     * @param dayLog dayLog
     * @param pageRequest pageRequest
     * @return object list
     */
    @Decrypt
    @Encrypt
    @GetMapping("query/pager")
    public ResponseEntity queryPager(DayLog dayLog, PageRequest pageRequest) {
        pageRequest.initStart(pageRequest);
        ResponseDto responseDto = dayLogService.queryPager(dayLog, pageRequest);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    /**
     * 查询統計
     *
     * @param dayLog dayLog
     * @return object list
     */
    @Decrypt
    @Encrypt
    @GetMapping("query/count")
    public ResponseEntity queryCount(DayLog dayLog) {
        List<DayLogCount> dayLogCounts = dayLogService.queryCount(dayLog);
        return new ResponseEntity<>(dayLogCounts, HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param dayLog 实例对象
     * @return 实例对象
     */
    @Encrypt
    @Decrypt
    @PostMapping
    public ResponseEntity insert(@RequestBody DayLog dayLog) {
        boolean insert = dayLogService.insert(dayLog);
        return new ResponseEntity<>(insert, HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param dayLog 实例对象
     * @return 实例对象
     */
    @Encrypt
    @Decrypt
    @PutMapping
    public ResponseEntity update(@RequestBody DayLog dayLog) {
        if (StringUtils.isBlank(dayLog.getId())) {
            return new ResponseEntity<>("[" + dayLog.getId() + "]为无效id", HttpStatus.BAD_REQUEST);
        }
        boolean update = dayLogService.update(dayLog);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Decrypt
    @Encrypt
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            return new ResponseEntity<>("[" + id + "]为无效id", HttpStatus.BAD_REQUEST);
        }
        boolean delete = dayLogService.deleteById(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

}