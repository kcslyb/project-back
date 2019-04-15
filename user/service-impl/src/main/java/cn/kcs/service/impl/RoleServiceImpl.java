package cn.kcs.service.impl;

import cn.kcs.business.inter.RoleManager;
import cn.kcs.dao.inter.TRolePermissionDao;
import cn.kcs.dao.inter.pojo.TRole;
import cn.kcs.dao.inter.pojo.TRolePermission;
import cn.kcs.service.inter.PermissionService;
import cn.kcs.service.inter.RoleService;
import cn.kcs.service.inter.dto.PermissionDto;
import cn.kcs.service.inter.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-26 12:53
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private TRolePermissionDao tRolePermissionDao;

    @Override
    public RoleDto add(RoleDto t) {
        RoleDto roleDto = assemblyDto(roleManager.add(assembly(t)));
        for (PermissionDto permissionDto : t.getPermissionDtos()) {
            PermissionDto perm = permissionService.add(permissionDto);
            TRolePermission tRolePermission = new TRolePermission();
            tRolePermission.setRoleId(roleDto.getRoleId());
            tRolePermission.setPermissionId(perm.getPermissionId());
            tRolePermissionDao.add(tRolePermission);
        }
        return roleDto;
    }

    private TRole assembly(RoleDto t) {
        TRole tRole = new TRole();
        tRole.setRoleId(t.getRoleId());
        tRole.setRoleName(t.getRoleName());
        tRole.setRoleDescription(t.getRoleDescription());
        return tRole;
    }

    private RoleDto assemblyDto(TRole t) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(t.getRoleId());
        roleDto.setRoleName(t.getRoleName());
        roleDto.setRoleDescription(t.getRoleDescription());
        return roleDto;
    }

    @Override
    public int edit(RoleDto t) {
        return roleManager.edit(assembly(t));
    }

    @Override
    public int deleteByKey(String pk) {
        return roleManager.deleteByKey(pk);
    }

    @Override
    public RoleDto getByKey(String pk) {
        return assemblyDto(roleManager.getByKey(pk));
    }

    @Override
    public int deleteByKeys(String pks) {
        return roleManager.deleteByKeys(pks);
    }

    @Override
    public List<RoleDto> query(RoleDto t, OrderBy... orderArgs) {
        List<TRole> list = roleManager.query(assembly(t), orderArgs);
        List<RoleDto> listDto = new ArrayList<>();
        for (TRole tRole : list) {
            TRolePermission tRolePermission = new TRolePermission();
            tRolePermission.setRoleId(tRole.getRoleId());
            List<TRolePermission> tRolePermissions = tRolePermissionDao.query(tRolePermission);
            List<PermissionDto> permissionDtos = new ArrayList<>();
            if (tRolePermissions.size() > 0) {
                for (TRolePermission trm : tRolePermissions) {
                    permissionDtos.add(permissionService.getByKey(trm.getPermissionId()));
                }
            }
            RoleDto roleDto = assemblyDto(tRole);
            roleDto.setPermissionDtos(permissionDtos);
            listDto.add(roleDto);
        }
        return listDto;
    }

    @Override
    public Pager<RoleDto> queryPager(int start, int limit, RoleDto t, OrderBy... orderArgs) {
        return null;
    }

    @Override
    public RoleDto addPerm(RoleDto roleDto) {
        List<TRolePermission> tRolePermissions = new ArrayList<>();
        List<PermissionDto> permissionDtos = new ArrayList<>();
        TRolePermission tRolePermission = new TRolePermission();
        tRolePermission.setRoleId(roleDto.getRoleId());
        PermissionDto perm = new PermissionDto();
        for (PermissionDto permissionDto : roleDto.getPermissionDtos()) {
            perm = permissionService.add(permissionDto);
            permissionDtos.add(perm);
            tRolePermission.setPermissionId(perm.getPermissionId());
            tRolePermissions.add(tRolePermissionDao.add(tRolePermission));
        }
        roleDto.setPermissionDtos(permissionDtos);
        return roleDto;
    }

    @Override
    public void editRolePerm(String roleId, List<String> permIds) {
        TRolePermission tRolePerm = new TRolePermission();
        tRolePerm.setRoleId(roleId);
        List<TRolePermission> tRolePermissions = tRolePermissionDao.query(tRolePerm);
        for (TRolePermission trp : tRolePermissions) {
            tRolePermissionDao.deleteByKey(trp.getRolePermissionId());
        }
        for (String string : permIds) {
            if ("".equals(string) || string == null) {
                break;
            }
            tRolePerm.setPermissionId(string);
            tRolePermissionDao.add(tRolePerm);
        }
    }

    @Override
    public void deleteRolePerm(String roleId, List<String> list) {
        for (String string : list) {
            TRolePermission tRolePerm = new TRolePermission();
            tRolePerm.setRoleId(roleId);
            tRolePerm.setPermissionId(string);
            List<TRolePermission> tRolePerms = tRolePermissionDao.query(tRolePerm);
            if (tRolePerms.size() > 0) {
                tRolePermissionDao.deleteByKey(tRolePerms.get(0).getRolePermissionId());
            }
        }
    }
}
