package cn.kcs.schedule.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.schedule.entity.Schedule;
import cn.kcs.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Schedule)表控制层
 *
 * @author kcs
 * @date 2019-09-05 12:50:38
 */
@RestController
@RequestMapping("schedule")
public class ScheduleController {
    /**
     * 服务对象
     */
    @Resource
    private ScheduleService scheduleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @GetMapping("{id}")
    public ResponseEntity<Schedule> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.scheduleService.queryById(id), HttpStatus.OK);
    }

    /**
     * 添加单条数据
     *
     * @param schedule 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @PostMapping()
    public ResponseEntity<Schedule> add(@RequestBody Schedule schedule) {
        return new ResponseEntity<>(this.scheduleService.insert(schedule), HttpStatus.OK);
    }

    /**
     * 通过时间段查询数据
     *
     * @param startTime startTime
     * @param endTime   endTime
     * @return 数据
     */
    @Encrypt
    @Decrypt
    @GetMapping("period")
    public ResponseEntity<List<Schedule>> queryPeriodOfTime(String startTime, String endTime) {
        return new ResponseEntity<>(this.scheduleService.queryPeriodOfTime(startTime, endTime), HttpStatus.OK);
    }

    /**
     * 通过时间查询数据
     *
     * @param time time
     * @return 数据
     */
    @Encrypt
    @Decrypt
    @GetMapping("query")
    public ResponseEntity<List<Schedule>> queryPeriodOfTime(String time) {
        return new ResponseEntity<>(this.scheduleService.queryOfTime(time), HttpStatus.OK);
    }

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.scheduleService.deleteById(id), HttpStatus.OK);
    }


}