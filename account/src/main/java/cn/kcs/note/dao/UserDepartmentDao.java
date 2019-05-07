package cn.kcs.note.dao;

import cn.kcs.note.entity.UserDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserDepartment)表数据库访问层
 *
 * @author makejava
 * @since 2019-03-22 10:08:44
 */
public interface UserDepartmentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param departmentId 主键
     * @return 实例对象
     */
    UserDepartment queryById(@Param("departmentId") String departmentId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserDepartment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userDepartment 实例对象
     * @return 对象列表
     */
    List<UserDepartment> queryAll(UserDepartment userDepartment);

    /**
     * 新增数据
     *
     * @param userDepartment 实例对象
     * @return 影响行数
     */
    int insert(UserDepartment userDepartment);

    /**
     * 修改数据
     *
     * @param userDepartment 实例对象
     * @return 影响行数
     */
    int update(UserDepartment userDepartment);

    /**
     * 通过主键删除数据
     *
     * @param departmentId 主键
     * @return 影响行数
     */
    int deleteById(@Param("departmentId") String departmentId);

}