package cn.kcs.user.service;

import cn.kcs.user.entity.Role;
import cn.kcs.user.entity.dto.RoleDto;
import cn.kcs.user.entity.dto.RolePermissionRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * (Role)表服务接口
 *
 * @author kcs
 * @since 2019-10-14 10:24:42
 */
public interface RoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    RoleDto queryById(String roleId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Role> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    ResponseEntity insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    ResponseEntity update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    ResponseEntity deleteById(String roleId);

    /**
     * updateRolePermission
     *
     * @param rolePermissionRequest
     * @return
     */
    ResponseEntity updateRolePermission(RolePermissionRequest rolePermissionRequest);

    /**
     * queryAll
     *
     * @param role
     * @return
     */
    List<Role> queryAll(Role role);
}