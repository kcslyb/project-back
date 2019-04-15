package cn.kcs.service.inter;

import cn.kcs.service.inter.dto.RoleDto;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface RoleService {
    RoleDto add(RoleDto t);

    int edit(RoleDto t);

    int deleteByKey(String pk);

    RoleDto getByKey(String pk);

    int deleteByKeys(String pks);

    List<RoleDto> query(RoleDto t, OrderBy... orderArgs);

    Pager<RoleDto> queryPager(int start, int limit, RoleDto t, OrderBy... orderArgs);

    RoleDto addPerm(RoleDto roleDto);

    void editRolePerm(String roleId, List<String> permIds);

    void deleteRolePerm(String roleId, List<String> list);
}
