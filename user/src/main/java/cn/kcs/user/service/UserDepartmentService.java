package cn.kcs.user.service;

import cn.kcs.user.entity.UserDepartment;
import cn.kcs.user.entity.dto.DepartmentDto;

import java.util.List;

/**
 * (UserDepartment)表服务接口
 *
 * @author makejava
 * @since 2019-03-22 10:08:44
 */
public interface UserDepartmentService {

    /**
     * 通过ID查询单条数据
     *
     * @param departmentId 主键
     * @return 实例对象
     */
    UserDepartment queryById(String departmentId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserDepartment> queryAllByLimit(int offset, int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param userDepartment 实例对象
     * @return 对象列表
     */
    List<UserDepartment> queryAll(UserDepartment userDepartment);

    /**
     * 通过实体作为筛选条件查询User
     *
     * @param department
     * @return
     */
    List<DepartmentDto> queryAllUserByDepartment(UserDepartment department);

    /**
     * 新增数据
     *
     * @param userDepartment 实例对象
     * @return 实例对象
     */
    UserDepartment insert(UserDepartment userDepartment);

    /**
     * 修改数据
     *
     * @param userDepartment 实例对象
     * @return 实例对象
     */
    UserDepartment update(UserDepartment userDepartment);

    /**
     * 通过主键删除数据
     *
     * @param departmentId 主键
     * @return 是否成功
     */
    boolean deleteById(String departmentId);

}