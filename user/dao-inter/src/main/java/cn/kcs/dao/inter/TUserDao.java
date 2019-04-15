
package cn.kcs.dao.inter;

import cn.kcs.dao.inter.pojo.TUser;
import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;

import java.util.List;

/**
 * <!-- begin-user-doc --> 如果不希望某方法或者变量被覆盖，可以删除方法或者变量的注释@modifiable <!--
 * end-user-doc -->
 */
public interface TUserDao extends BaseDao<TUser, String> {

    List<TUser> getUserByIdAndName(String loginName, String password);
}
