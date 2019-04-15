package cn.kcs.service.inter;


import cn.kcs.service.inter.dto.OrderDto;
import cn.kcs.service.inter.util.PageResponse;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;

import java.util.List;

public interface OrderService {

    OrderDto add(OrderDto t);

    int edit(OrderDto t);

    int deleteByKey(String pk);

    OrderDto getByKey(String pk);

    int deleteByKeys(String pks);

    List<OrderDto> query(OrderDto t, OrderBy... orderArgs);

    PageResponse<OrderDto> queryPager(int start, int limit, OrderDto t, OrderBy... orderArgs);

    void account(String key);
}
