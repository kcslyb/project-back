package cn.kcs.user.service;

import cn.kcs.user.entity.User;
import cn.kcs.user.entity.dto.UserDto;
import cn.kcs.user.entity.dto.UserGenealogyDto;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author kcs
 * @since 2019-09-30 10:27:48
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(String userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userDto 实例对象
     * @return 实例对象
     */
    boolean insert(UserDto userDto);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(String userId);

    /**
     * queryAll
     *
     * @param user
     * @return
     */
    List<User> queryAll(User user);

    /**
     * queryUserGenealogy
     *
     * @param userId userId
     * @return return
     */
    UserGenealogyDto queryUserGenealogy(String userId);
}