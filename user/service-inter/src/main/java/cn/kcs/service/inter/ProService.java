package cn.kcs.service.inter;


import cn.kcs.service.inter.dto.ProDto;
import cn.kcs.service.inter.util.PageResponse;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;

import java.util.List;

public interface ProService {

    ProDto add(ProDto proDto);

    int edit(ProDto t);

    int deleteByKey(String pk);

    ProDto getByKey(String pk);

    int deleteByKeys(String pks);

    List<ProDto> query(ProDto t, OrderBy... orderArgs);

    PageResponse<ProDto> queryPager(int start, int limit, ProDto t, OrderBy... orderArgs);

    ProDto getByUserName(String username);

    ProDto getByUserId(String key);

    ProDto getUserByIdAndName(String loginName, String password);

    void editUserRolePerm(String roleId, String userId);
}
