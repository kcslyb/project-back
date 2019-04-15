package cn.kcs.service.impl;

import cn.kcs.business.inter.PermissionManager;
import cn.kcs.dao.inter.TRolePermissionDao;
import cn.kcs.dao.inter.pojo.TPermission;
import cn.kcs.dao.inter.pojo.TRolePermission;
import cn.kcs.service.inter.PermissionService;
import cn.kcs.service.inter.dto.PermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-26 13:05
 **/
@Component
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionManager permissionManager;
    @Autowired
    private TRolePermissionDao tRolePermissionDao;
    @Override
    public PermissionDto add(PermissionDto t) {
        return assemblyDto(permissionManager.add(assembly(t)));
    }

    private TPermission assembly(PermissionDto t) {
        TPermission tPermission = new TPermission();
        tPermission.setPermissionId(t.getPermissionId());
        tPermission.setPermissionName(t.getPermissionName());
        tPermission.setPermissionUrl(t.getPermissionUrl());
        tPermission.setPermissionMethod(t.getPermissionMethod());
        tPermission.setPermissionDescription(t.getPermissionDescription());
        return tPermission;
    }

    private PermissionDto assemblyDto(TPermission t) {
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setPermissionId(t.getPermissionId());
        permissionDto.setPermissionName(t.getPermissionName());
        permissionDto.setPermissionUrl(t.getPermissionUrl());
        permissionDto.setPermissionMethod(t.getPermissionMethod());
        permissionDto.setPermissionDescription(t.getPermissionDescription());
        return permissionDto;
    }

    @Override
    public int edit(PermissionDto t) {
        return permissionManager.edit(assembly(t));
    }

    @Override
    public int deleteByKey(String pk) {
        TRolePermission tRolePermission = new TRolePermission();
        tRolePermission.setRolePermissionId(pk);
        List<TRolePermission> tRolePermissions = tRolePermissionDao.query(tRolePermission);
        tRolePermissionDao.batchDelete(tRolePermissions);
        return permissionManager.deleteByKey(pk);
    }

    @Override
    public PermissionDto getByKey(String pk) {
        return assemblyDto(permissionManager.getByKey(pk));
    }

    @Override
    public int deleteByKeys(String pks) {
        return permissionManager.deleteByKeys(pks);
    }

    @Override
    public List<PermissionDto> query(PermissionDto t, OrderBy... orderArgs) {
        List<TPermission> list = permissionManager.query(assembly(t), orderArgs);
        List<PermissionDto> listDto = new ArrayList<>();
        for (TPermission tPermission : list) {
            listDto.add(assemblyDto(tPermission));
        }
        return listDto;
    }

    @Override
    public Pager<PermissionDto> queryPager(int start, int limit, PermissionDto t, OrderBy... orderArgs) {
        return null;
    }
}
