package cn.kcs.service.inter;


import cn.kcs.service.inter.dto.DeskDto;
import cn.kcs.service.inter.util.PageResponse;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;

import java.util.List;

public interface DeskService {

    DeskDto add(DeskDto t);

    int edit(DeskDto t);

    int deleteByKey(String pk);

    DeskDto getByKey(String pk);

    int deleteByKeys(String pks);

    List<DeskDto> query(DeskDto t, OrderBy... orderArgs);

    PageResponse<DeskDto> queryPager(int start, int limit, DeskDto t, OrderBy... orderArgs);
}
