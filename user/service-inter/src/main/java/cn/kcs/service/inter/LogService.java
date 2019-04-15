package cn.kcs.service.inter;

import cn.kcs.service.inter.dto.LogDto;
import cn.kcs.service.inter.util.PageResponse;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;

import java.util.List;

/**
 * @description: log service
 * @author: kcs
 * @create: 2018-11-06 16:13
 **/
public interface LogService {

    LogDto add(LogDto logDto);

    PageResponse<LogDto> queryPager(int start, int limit, LogDto t, OrderBy... orderArgs);

    List<LogDto> query();
}
