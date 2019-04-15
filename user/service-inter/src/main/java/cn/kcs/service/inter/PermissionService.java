package cn.kcs.service.inter;

import cn.kcs.service.inter.dto.PermissionDto;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface PermissionService {
    PermissionDto add(PermissionDto t);

    int edit(PermissionDto t);

    int deleteByKey(String pk);

    PermissionDto getByKey(String pk);

    int deleteByKeys(String pks);

    List<PermissionDto> query(PermissionDto t, OrderBy... orderArgs);

    Pager<PermissionDto> queryPager(int start, int limit, PermissionDto t, OrderBy... orderArgs);

}
