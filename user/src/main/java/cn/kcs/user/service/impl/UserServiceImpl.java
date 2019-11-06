package cn.kcs.user.service.impl;

import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.UserAccountDao;
import cn.kcs.user.dao.UserDao;
import cn.kcs.user.dao.UserGenealogyRelationDao;
import cn.kcs.user.entity.User;
import cn.kcs.user.entity.UserAccount;
import cn.kcs.user.entity.UserGenealogyRelation;
import cn.kcs.user.entity.dto.UserDto;
import cn.kcs.user.entity.dto.UserGenealogyDto;
import cn.kcs.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * (User)表服务实现类
 *
 * @author kcs
 * @since 2019-09-30 10:27:48
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private UserGenealogyRelationDao userGenealogyRelationDao;

    @Resource
    private UserAccountDao userAccountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String userId) {
        return this.userDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    @Override
    public boolean insert(UserDto userDto) {
        UserAccount userAccount = userAccountDao.queryById(userDto.getUserAccountId());
        User user = new User();
        user.setUserId(ShortUUID.generate());
        user.setUserName(userAccount.getUserName());
        user.setUserAlias(userDto.getUserAlias());
        user.setUserWord(userDto.getUserWord());
        user.setUserAccountId(userDto.getUserAccountId());
        user.setUserStatus(userAccount.getUserStatus());
        user.setUserMaritalStatus(userDto.getUserMaritalStatus());
        user.setUserBirthTime(userDto.getUserBirthTime());
        UserGenealogyRelation userGenealogyRelation = new UserGenealogyRelation();
        userGenealogyRelation.setUserGenealogyRelationId(ShortUUID.generate());
        userGenealogyRelation.setUserId(userDto.getUserAccountId());
        userGenealogyRelation.setUserGenealogyId(userDto.getUserGenealogyId());
        userGenealogyRelation.setUserParentId(userDto.getUserParentId());
        userGenealogyRelation.setUserParentSpouseId(userDto.getUserParentSpouseId());
        userGenealogyRelation.setUserSpouseId(userDto.getUserSpouseId());
        int insert1 = userGenealogyRelationDao.insert(userGenealogyRelation);
        int insert2 = this.userDao.insert(user);
        return insert1 > 0 && insert2 > 0;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userId) {
        return this.userDao.deleteById(userId) > 0;
    }

    @Override
    public List<User> queryAll(User user) {
        return userDao.queryAll(user);
    }

    @Override
    public UserGenealogyDto queryUserGenealogy(String userId) {
        UserGenealogyDto userGenealogyDto = new UserGenealogyDto();
        User user = userDao.queryById(userId);
        UserGenealogyRelation userRelation = getUserRelation(userId);
        userGenealogyDto.setSelf(user);
        List<User> userParents = getUserParent(userId);
        if (!CollectionUtils.isEmpty(userParents)) {
            userGenealogyDto.setSelfParents(userParents);
        }
        List<User> brotherList = getBrotherList(userId, userRelation.getUserParentId(), userRelation.getUserParentSpouseId());
        userGenealogyDto.setBrotherList(brotherList);
        String maritalStatus = "1";
        if (maritalStatus.equals(user.getUserMaritalStatus())) {
            userGenealogyDto.setSpouse(userDao.queryById(userRelation.getUserSpouseId()));
            List<User> spouseParent = getUserParent(userRelation.getUserSpouseId());
            if (!CollectionUtils.isEmpty(spouseParent)) {
                userGenealogyDto.setSpouseParents(spouseParent);
            }
            UserGenealogyRelation spouseRelation = getUserRelation(userRelation.getUserSpouseId());
            if (spouseRelation == null) {
                userGenealogyDto.setSpouseBrotherList(new ArrayList<>());
            } else {
                List<User> spouseBrotherList = getBrotherList(userId, spouseRelation.getUserParentId(), spouseRelation.getUserParentSpouseId());
                userGenealogyDto.setSpouseBrotherList(spouseBrotherList);
            }
        }
        List<User> childList = getChildList(userId, userRelation.getUserSpouseId());
        userGenealogyDto.setChildList(childList);
        return userGenealogyDto;
    }

    private List<User> getChildList(String userId, String spouseId) {
        return getBrotherList(userId, userId, spouseId);
    }

    private List<User> getUserParent(String userId) {
        List<User> userList = new ArrayList<>();
        UserGenealogyRelation userRelation = getUserRelation(userId);
        if (userRelation == null) {
            return null;
        }
        userList.add(userDao.queryById(userRelation.getUserParentId()));
        userList.add(userDao.queryById(userRelation.getUserParentSpouseId()));
        return userList;
    }

    private UserGenealogyRelation getUserRelation(String userId) {
        UserGenealogyRelation userGenealogyRelation = new UserGenealogyRelation();
        userGenealogyRelation.setUserId(userId);
        List<UserGenealogyRelation> userGenealogyRelations = userGenealogyRelationDao.queryAll(userGenealogyRelation);
        if (CollectionUtils.isEmpty(userGenealogyRelations)) {
            return null;
        }
        return userGenealogyRelations.get(0);
    }

    private List<User> getBrotherList(String userId, String parentId, String parentSpouseId) {
        UserGenealogyRelation userGenealogyRelation1 = new UserGenealogyRelation();
        userGenealogyRelation1.setUserParentId(parentId);
        List<UserGenealogyRelation> userGenealogyRelations1 = userGenealogyRelationDao.queryAll(userGenealogyRelation1);
        UserGenealogyRelation userGenealogyRelation2 = new UserGenealogyRelation();
        userGenealogyRelation2.setUserParentSpouseId(parentSpouseId);
        List<UserGenealogyRelation> userGenealogyRelations2 = userGenealogyRelationDao.queryAll(userGenealogyRelation2);
        Set<UserGenealogyRelation> userGenealogyRelationSet = new HashSet<>();
        userGenealogyRelations1.forEach(value -> {
            if (!userId.equals(value.getUserId())) {
                userGenealogyRelationSet.add(value);
            }
        });
        userGenealogyRelations2.forEach(value -> {
            if (!userId.equals(value.getUserId())) {
                userGenealogyRelationSet.add(value);
            }
        });
        List<UserGenealogyRelation> userGenealogyRelations = new ArrayList<>(userGenealogyRelationSet);
        List<User> userList = new ArrayList<>();
        userGenealogyRelations.forEach(value -> {
            User user = userDao.queryById(value.getUserId());
            userList.add(user);
        });
        return userList;
    }
}