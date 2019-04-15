package cn.kcs.service.inter;

import cn.kcs.service.inter.dto.UserDto;
import cn.kcs.service.inter.util.PageResponse;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;

import java.util.List;

public interface UserService {

    UserDto add(UserDto user);

    int edit(UserDto t);

    int deleteByKey(String pk);

    UserDto getByKey(String pk);

    int deleteByKeys(String pks);

    List<UserDto> query(UserDto t, OrderBy... orderArgs);

    PageResponse<UserDto> queryPager(int start, int limit, UserDto t, OrderBy... orderArgs);

    UserDto getByUserName(String username);

    UserDto getByUserId(String key);

    UserDto getUserByIdAndName(String loginName, String password);

    void editUserRolePerm(String roleId, String userId);
}
