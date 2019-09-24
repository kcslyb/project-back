package cn.kcs.user.service.impl;

import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.UserAccountDao;
import cn.kcs.user.dao.UserDepartmentDao;
import cn.kcs.user.entity.UserAccount;
import cn.kcs.user.entity.UserDepartment;
import cn.kcs.user.entity.dto.DepartmentDto;
import cn.kcs.user.service.UserDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (UserDepartment)表服务实现类
 *
 * @author makejava
 * @since 2019-03-22 10:08:44
 */
@Service("userDepartmentService")
public class UserDepartmentServiceImpl implements UserDepartmentService {
    @Resource
    private UserDepartmentDao userDepartmentDao;

    @Resource
    private UserAccountDao userAccountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param departmentId 主键
     * @return 实例对象
     */
    @Override
    public UserDepartment queryById(String departmentId) {
        return this.userDepartmentDao.queryById(departmentId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserDepartment> queryAllByLimit(int offset, int limit) {
        return this.userDepartmentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @param userDepartment
     * @return 对象列表
     */
    @Override
    public List<UserDepartment> queryAll(UserDepartment userDepartment) {
        return this.userDepartmentDao.queryAll(userDepartment);
    }

    /**
     * 查询department user
     *
     * @param department
     * @return 对象列表
     */
    @Override
    public List<DepartmentDto> queryAllUserByDepartment(UserDepartment department) {
        List<DepartmentDto> departmentUser = new ArrayList<>();
        List<UserAccount> userAccountList = userAccountDao.queryAll(new UserAccount());
        DepartmentDto dto = new DepartmentDto();
        dto.setUserDepartment(new UserDepartment("1111", "全部", "000001", ""));
        dto.setUserAccounts(userAccountList);
        departmentUser.add(dto);
        List<UserDepartment> userDepartments = this.userDepartmentDao.queryAll(department);
        if (userDepartments.size() > 0) {
            for (UserDepartment userDepartment : userDepartments) {
                DepartmentDto departmentDto = new DepartmentDto();
                UserAccount userAccount = new UserAccount();
                userAccount.setUserDepartment(userDepartment.getDepartmentId());
                List<UserAccount> userAccounts = userAccountDao.queryAll(userAccount);
                departmentDto.setUserDepartment(userDepartment);
                departmentDto.setUserAccounts(userAccounts);
                departmentUser.add(departmentDto);
            }
            return departmentUser;
        }
        return departmentUser;
    }

    /**
     * 新增数据
     *
     * @param userDepartment 实例对象
     * @return 实例对象
     */
    @Override
    public UserDepartment insert(UserDepartment userDepartment) {
        userDepartment.setDepartmentId(ShortUUID.generate());
        this.userDepartmentDao.insert(userDepartment);
        return userDepartment;
    }

    /**
     * 修改数据
     *
     * @param userDepartment 实例对象
     * @return 实例对象
     */
    @Override
    public UserDepartment update(UserDepartment userDepartment) {
        this.userDepartmentDao.update(userDepartment);
        return this.queryById(userDepartment.getDepartmentId());
    }

    /**
     * 通过主键删除数据
     *
     * @param departmentId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String departmentId) {
        return this.userDepartmentDao.deleteById(departmentId) > 0;
    }
}