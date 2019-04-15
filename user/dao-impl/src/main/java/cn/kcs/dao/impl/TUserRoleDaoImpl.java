
package cn.kcs.dao.impl;

import cn.kcs.dao.inter.TUserRoleDao;
import cn.kcs.dao.inter.UUIDutil.StringIdGenerator;
import cn.kcs.dao.inter.pojo.TUserRole;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.jdbctemplatedslsession.util.TinyDSLUtil;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.InsertContext;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;

import java.io.Serializable;
import java.util.List;

import static cn.kcs.dao.inter.constant.TUserRoleTable.T_USER_ROLE_TABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

/**
 * <!-- begin-user-doc --> 如果不希望某方法或者变量被覆盖，可以删除方法或者变量的注释@modifiable <!--
 * end-user-doc -->
 */
@Component
public class TUserRoleDaoImpl extends TinyDslDaoSupport implements TUserRoleDao {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TUserRole add(TUserRole tUserRole) {
        tUserRole.setUserRoleId(new StringIdGenerator().generate(new InsertContext()));
        return getDslTemplate().insertAndReturnKey(tUserRole, new InsertGenerateCallback<TUserRole>() {
            public Insert generate(TUserRole t) {
                Insert insert = insertInto(T_USER_ROLE_TABLE).values(
                        T_USER_ROLE_TABLE.USER_ROLE_ID.value(t.getUserRoleId()),
                        T_USER_ROLE_TABLE.USER_ID.value(t.getUserId()), T_USER_ROLE_TABLE.ROLE_ID.value(t.getRoleId())

                );
                return insert;
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int edit(TUserRole tUserRole) {
        if (tUserRole == null || StringUtils.isBlank(tUserRole.getUserRoleId())) {
            return 0;
        }
        return getDslTemplate().update(tUserRole, new UpdateGenerateCallback<TUserRole>() {
            public Update generate(TUserRole t) {
                Update update = update(T_USER_ROLE_TABLE)
                        .set(T_USER_ROLE_TABLE.USER_ID.value(t.getUserId()),
                                T_USER_ROLE_TABLE.ROLE_ID.value(t.getRoleId()))
                        .where(T_USER_ROLE_TABLE.USER_ROLE_ID.eq(t.getUserRoleId()));
                return update;
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int deleteByKey(String pk) {

        if (StringUtils.isBlank(pk)) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(T_USER_ROLE_TABLE).where(T_USER_ROLE_TABLE.USER_ROLE_ID.eq(pk));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int deleteByKeys(String... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        for (String pk : pks) {
            if (StringUtils.isBlank(pk)) {
                return 0;
            }
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(T_USER_ROLE_TABLE).where(T_USER_ROLE_TABLE.USER_ROLE_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TUserRole getByKey(String pk) {
        return getDslTemplate().getByKey(pk, TUserRole.class, new SelectGenerateCallback<Serializable>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(T_USER_ROLE_TABLE).where(T_USER_ROLE_TABLE.USER_ROLE_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public List<TUserRole> query(TUserRole tUserRole, final OrderBy... orderArgs) {
        if (tUserRole == null) {
            tUserRole = new TUserRole();
        }
        return getDslTemplate().query(tUserRole, new SelectGenerateCallback<TUserRole>() {
            @SuppressWarnings("rawtypes")
            public Select generate(TUserRole t) {
                Select select = selectFrom(T_USER_ROLE_TABLE)
                        .where(and(T_USER_ROLE_TABLE.USER_ROLE_ID.eq(t.getUserRoleId()),
                                T_USER_ROLE_TABLE.USER_ID.eq(t.getUserId()), T_USER_ROLE_TABLE.ROLE_ID.eq(t.getRoleId())

                        ));
                return TinyDSLUtil.addOrderByElements(select, orderArgs);
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Pager<TUserRole> queryPager(int start, int limit, TUserRole tUserRole, final OrderBy... orderArgs) {
        if (tUserRole == null) {
            tUserRole = new TUserRole();
        }
        return getDslTemplate().queryPager(start, limit, tUserRole, false, new SelectGenerateCallback<TUserRole>() {
            public Select generate(TUserRole t) {
                Select select = Select.selectFrom(T_USER_ROLE_TABLE)
                        .where(and(T_USER_ROLE_TABLE.USER_ROLE_ID.eq(t.getUserRoleId()),
                                T_USER_ROLE_TABLE.USER_ID.eq(t.getUserId()), T_USER_ROLE_TABLE.ROLE_ID.eq(t.getRoleId())

                        ));
                return TinyDSLUtil.addOrderByElements(select, orderArgs);
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchInsert(boolean autoGeneratedKeys, List<TUserRole> tUserRole) {
        if (CollectionUtil.isEmpty(tUserRole)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tUserRole, new InsertGenerateCallback<TUserRole>() {

            public Insert generate(TUserRole t) {
                return insertInto(T_USER_ROLE_TABLE).values(T_USER_ROLE_TABLE.USER_ROLE_ID.value(t.getUserRoleId()),
                        T_USER_ROLE_TABLE.USER_ID.value(t.getUserId()), T_USER_ROLE_TABLE.ROLE_ID.value(t.getRoleId())

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchInsert(List<TUserRole> tUserRoles) {
        return batchInsert(false, tUserRoles);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchUpdate(List<TUserRole> tUserRoles) {
        if (CollectionUtil.isEmpty(tUserRoles)) {
            return new int[0];
        }
        for (TUserRole tUserRole : tUserRoles) {
            if (StringUtils.isBlank(tUserRole.getUserRoleId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tUserRoles, new UpdateGenerateCallback<TUserRole>() {
            public Update generate(TUserRole t) {
                return update(T_USER_ROLE_TABLE).set(T_USER_ROLE_TABLE.USER_ID.value(t.getUserId()),
                        T_USER_ROLE_TABLE.ROLE_ID.value(t.getRoleId())

                ).where(T_USER_ROLE_TABLE.USER_ROLE_ID.eq(t.getUserRoleId()));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchDelete(List<TUserRole> tUserRoles) {
        if (CollectionUtil.isEmpty(tUserRoles)) {
            return new int[0];
        }
        for (TUserRole tUserRole : tUserRoles) {
            if (

                    StringUtils.isBlank(tUserRole.getUserRoleId()) &&

                            StringUtils.isBlank(tUserRole.getUserId()) &&

                            StringUtils.isBlank(tUserRole.getRoleId())

            ) {
                return new int[0];
            }
        }
        return getDslTemplate().batchDelete(tUserRoles, new DeleteGenerateCallback<TUserRole>() {
            public Delete generate(TUserRole t) {
                return delete(T_USER_ROLE_TABLE).where(and(T_USER_ROLE_TABLE.USER_ROLE_ID.eq(t.getUserRoleId()),
                        T_USER_ROLE_TABLE.USER_ID.eq(t.getUserId()), T_USER_ROLE_TABLE.ROLE_ID.eq(t.getRoleId())

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(boolean autoGeneratedKeys, List<TUserRole> tUserRole) {
        if (CollectionUtil.isEmpty(tUserRole)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tUserRole, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(T_USER_ROLE_TABLE).values(
                        T_USER_ROLE_TABLE.USER_ID.value(new JdbcNamedParameter("userId")),
                        T_USER_ROLE_TABLE.ROLE_ID.value(new JdbcNamedParameter("roleId"))

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchUpdate(List<TUserRole> tUserRoles) {
        if (CollectionUtil.isEmpty(tUserRoles)) {
            return new int[0];
        }

        for (TUserRole tUserRole : tUserRoles) {
            if (StringUtils.isBlank(tUserRole.getUserRoleId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tUserRoles, new NoParamUpdateGenerateCallback() {
            public Update generate() {
                return update(T_USER_ROLE_TABLE).set(T_USER_ROLE_TABLE.USER_ID.value(new JdbcNamedParameter("userId")),
                        T_USER_ROLE_TABLE.ROLE_ID.value(new JdbcNamedParameter("roleId"))

                ).where(T_USER_ROLE_TABLE.USER_ROLE_ID.eq(new JdbcNamedParameter("userRoleId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchDelete(List<TUserRole> tUserRoles) {
        if (CollectionUtil.isEmpty(tUserRoles)) {
            return new int[0];
        }

        for (TUserRole tUserRole : tUserRoles) {
            if (

                    StringUtils.isBlank(tUserRole.getUserRoleId()) &&

                            StringUtils.isBlank(tUserRole.getUserId()) &&

                            StringUtils.isBlank(tUserRole.getRoleId())

            ) {
                return new int[0];
            }
        }

        return getDslTemplate().batchDelete(tUserRoles, new NoParamDeleteGenerateCallback() {
            public Delete generate() {
                return delete(T_USER_ROLE_TABLE)
                        .where(and(T_USER_ROLE_TABLE.USER_ID.eq(new JdbcNamedParameter("userId")),
                                T_USER_ROLE_TABLE.ROLE_ID.eq(new JdbcNamedParameter("roleId"))

                        ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(List<TUserRole> tUserRole) {
        return preparedBatchInsert(false, tUserRole);
    }

    @Override
    public TUserRole getByUserId(String id) {
        return getDslTemplate().getByKey(id, TUserRole.class, new SelectGenerateCallback<Serializable>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(T_USER_ROLE_TABLE).where(T_USER_ROLE_TABLE.USER_ID.eq(t));
            }
        });
    }
}
