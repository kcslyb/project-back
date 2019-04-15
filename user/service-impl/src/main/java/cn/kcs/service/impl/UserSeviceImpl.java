package cn.kcs.service.impl;

import cn.kcs.business.inter.UserManager;
import cn.kcs.common.daoUtil.ShortUUID;
import cn.kcs.common.util.DataUtil;
import cn.kcs.common.util.MD5Utils;
import cn.kcs.dao.inter.TRolePermissionDao;
import cn.kcs.dao.inter.TUserRoleDao;
import cn.kcs.dao.inter.pojo.TRolePermission;
import cn.kcs.dao.inter.pojo.TUser;
import cn.kcs.dao.inter.pojo.TUserRole;
import cn.kcs.service.inter.PermissionService;
import cn.kcs.service.inter.RoleService;
import cn.kcs.service.inter.UserService;
import cn.kcs.service.inter.dto.PermissionDto;
import cn.kcs.service.inter.dto.RoleDto;
import cn.kcs.service.inter.dto.UserDto;
import cn.kcs.service.inter.util.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSeviceImpl implements UserService {
    @Autowired
    private UserManager userManager;
    @Autowired
    private TUserRoleDao tUserRoleDao;
    @Autowired
    private TRolePermissionDao tRolePermissionDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDto add(UserDto userDto) {
        if (userDto.getUuid() == null) {
            userDto.setUuid(ShortUUID.generate());
        }
        userDto.setCreatedata(DataUtil.stringToDate(DataUtil.currentFormatDate(DataUtil.DATE_TO_STRING_DETAIAL_PATTERN), DataUtil.DATE_TO_STRING_DETAIAL_PATTERN));
        userDto.setModifydata(userDto.getCreatedata());
        if (userDto.getUserPassword() == null || "".equals(userDto.getUserPassword())) {
            userDto.setUserPassword(MD5Utils.GetMD5Code(MD5Utils.GetMD5Code("123456")));
        } else {
            userDto.setUserPassword(MD5Utils.GetMD5Code(MD5Utils.GetMD5Code(userDto.getUserPassword())));
        }
        if (userDto.getRoleDto() != null && userDto.getRoleDto().getRoleId() != null) {
            this.editUserRolePerm(userDto.getRoleDto().getRoleId(), userDto.getUuid());
        } else {
            this.editUserRolePerm("Qrjb1hZ8VUe2dARo9Kr", userDto.getUuid());
        }
        return assemblyDto(userManager.add(assembly(userDto)));
    }

    private UserDto assemblyDto(TUser tUser) {
        UserDto userDto = new UserDto();
        userDto.setUuid(tUser.getUuid() == null ? "" : tUser.getUuid());
        userDto.setUserName(tUser.getUserName());
        userDto.setUserPassword(tUser.getUserPassword());
        userDto.setUserPhone(tUser.getUserPhone());
        userDto.setUserEmail(tUser.getUserEmail());
        userDto.setUserStatus(tUser.getUserStatus());
        userDto.setCreatedata(tUser.getCreatedata());
        userDto.setModifydata(tUser.getModifydata());
        return userDto;
    }

    private TUser assembly(UserDto userDto) {
        TUser tUser = new TUser();
        tUser.setUuid(userDto.getUuid());
        tUser.setUserName(userDto.getUserName());
        tUser.setUserPassword(userDto.getUserPassword());
        tUser.setUserPhone(userDto.getUserPhone());
        tUser.setUserEmail(userDto.getUserEmail());
        tUser.setUserStatus(userDto.getUserStatus());
        tUser.setCreatedata(userDto.getCreatedata());
        tUser.setModifydata(userDto.getModifydata());
        return tUser;
    }

    @Override
    public int edit(UserDto t) {
        t.setModifydata(DataUtil.stringToDate(DataUtil.currentFormatDate(DataUtil.DATE_TO_STRING_DETAIAL_PATTERN), DataUtil.DATE_TO_STRING_DETAIAL_PATTERN));
        return userManager.edit(assembly(t));
    }

    @Override
    public int deleteByKey(String pk) {
        TUserRole tUserRole = new TUserRole();
        tUserRole.setUserId(pk);
        List<TUserRole> tUserRoles = tUserRoleDao.query(tUserRole);
        if (CollectionUtil.isNotEmpty(tUserRoles)) {
            tUserRoleDao.deleteByKey(tUserRoles.get(0).getRoleId());
        }
        return userManager.deleteByKey(pk);
    }

    @Override
    public UserDto getByKey(String pk) {
        UserDto userDto = assemblyDto(userManager.getByKey(pk));
        TUserRole tUserRole = tUserRoleDao.getByUserId(userDto.getUuid());
        RoleDto roleDto = roleService.getByKey(tUserRole.getRoleId());
        userDto.setRoleDto(roleDto);
        TRolePermission tRolePermission = new TRolePermission();
        tRolePermission.setRoleId(roleDto.getRoleId());
        List<TRolePermission> tRolePermissions = tRolePermissionDao.query(tRolePermission);
        List<PermissionDto> permissionDtos = new ArrayList<>();
        if (tRolePermissions.size() > 0 && tRolePermissions != null) {
            for (TRolePermission trm : tRolePermissions) {
                permissionDtos.add(permissionService.getByKey(trm.getPermissionId()));
            }
        }
        roleDto.setPermissionDtos(permissionDtos);
        return userDto;
    }

    @Override
    public int deleteByKeys(String pks) {
        return userManager.deleteByKeys(pks);
    }

    @Override
    public List<UserDto> query(UserDto t, OrderBy... orderArgs) {
        List<TUser> list = userManager.query(assembly(t), orderArgs);
        List<UserDto> listdto = new ArrayList<>();
        for (TUser tUser : list) {
            listdto.add(assemblyDto(tUser));
        }
        return listdto;
    }

    @Override
    public PageResponse<UserDto> queryPager(int start, int limit, UserDto t, OrderBy... orderArgs) {
        OrderBy orderBy = new OrderBy("createdata", false);
        Pager<TUser> list = userManager.queryPager(start, limit, assembly(t), orderBy);
        List<UserDto> listdto = new ArrayList<>();
        for (TUser user : list.getRecords()) {
            TUserRole tUserRole = new TUserRole();
            tUserRole.setUserId(user.getUuid());
            List<TUserRole> roles = tUserRoleDao.query(tUserRole);
            if (roles.size() > 0) {
                RoleDto roleDto = roleService.getByKey(roles.get(0).getRoleId());
                UserDto userDto = assemblyDto(user);
                userDto.setRoleDto(roleDto);
                listdto.add(userDto);
            } else {
                UserDto userDto = assemblyDto(user);
                RoleDto roleDto = new RoleDto();
                roleDto.setRoleName("无");
                userDto.setRoleDto(roleDto);
                listdto.add(userDto);
            }

        }

        PageResponse<UserDto> pageResponse = new PageResponse(list.getCurrentPage(), list.getLimit(), list.getTotalCount(), listdto);
        return pageResponse;
    }

    @Override
    public UserDto getByUserName(String username) {
        return getByKey(username);
    }

    @Override
    public UserDto getByUserId(String key) {
        return assemblyDto(userManager.getByKey(key));
    }

    @Override
    public UserDto getUserByIdAndName(String loginName, String password) {
        return assemblyDto(userManager.getUserByIdAndName(loginName, password));
    }

    @Override
    public void editUserRolePerm(String roleId, String userId) {
        TUserRole tUserRole = new TUserRole();
        tUserRole.setUserId(userId);
        List<TUserRole> tUserRoles = tUserRoleDao.query(tUserRole);
        if (tUserRoles.size() == 0) {
            tUserRole.setRoleId(roleId);
            tUserRoleDao.add(tUserRole);
        } else if (tUserRoles.size() == 1) {
            tUserRole.setUserRoleId(tUserRoles.get(0).getUserRoleId());
            tUserRole.setRoleId(roleId);
            tUserRoleDao.edit(tUserRole);
        }
    }
}
