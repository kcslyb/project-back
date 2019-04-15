
package cn.kcs.dao.impl;

import cn.kcs.dao.inter.TRoleDao;
import cn.kcs.dao.inter.UUIDutil.StringIdGenerator;
import cn.kcs.dao.inter.pojo.TRole;
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

import static cn.kcs.dao.inter.constant.TRoleTable.T_ROLE_TABLE;
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
public class TRoleDaoImpl extends TinyDslDaoSupport implements TRoleDao {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TRole add(TRole tRole) {
        tRole.setRoleId(new StringIdGenerator().generate(new InsertContext()));
        return getDslTemplate().insertAndReturnKey(tRole, new InsertGenerateCallback<TRole>() {
            public Insert generate(TRole t) {
                Insert insert = insertInto(T_ROLE_TABLE).values(T_ROLE_TABLE.ROLE_ID.value(t.getRoleId()),
                        T_ROLE_TABLE.ROLE_NAME.value(t.getRoleName()),
                        T_ROLE_TABLE.ROLE_DESCRIPTION.value(t.getRoleDescription())

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
    public int edit(TRole tRole) {
        if (tRole == null || StringUtils.isBlank(tRole.getRoleId())) {
            return 0;
        }
        return getDslTemplate().update(tRole, new UpdateGenerateCallback<TRole>() {
            public Update generate(TRole t) {
                Update update = update(T_ROLE_TABLE)
                        .set(T_ROLE_TABLE.ROLE_NAME.value(t.getRoleName()),
                                T_ROLE_TABLE.ROLE_DESCRIPTION.value(t.getRoleDescription()))
                        .where(T_ROLE_TABLE.ROLE_ID.eq(t.getRoleId()));
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
                return delete(T_ROLE_TABLE).where(T_ROLE_TABLE.ROLE_ID.eq(pk));
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
                return delete(T_ROLE_TABLE).where(T_ROLE_TABLE.ROLE_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TRole getByKey(String pk) {
        return getDslTemplate().getByKey(pk, TRole.class, new SelectGenerateCallback<Serializable>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(T_ROLE_TABLE).where(T_ROLE_TABLE.ROLE_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public List<TRole> query(TRole tRole, final OrderBy... orderArgs) {
        if (tRole == null) {
            tRole = new TRole();
        }
        return getDslTemplate().query(tRole, new SelectGenerateCallback<TRole>() {
            @SuppressWarnings("rawtypes")
            public Select generate(TRole t) {
                Select select = selectFrom(T_ROLE_TABLE)
                        .where(and(T_ROLE_TABLE.ROLE_ID.eq(t.getRoleId()), T_ROLE_TABLE.ROLE_NAME.eq(t.getRoleName()),
                                T_ROLE_TABLE.ROLE_DESCRIPTION.eq(t.getRoleDescription())

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
    public Pager<TRole> queryPager(int start, int limit, TRole tRole, final OrderBy... orderArgs) {
        if (tRole == null) {
            tRole = new TRole();
        }
        return getDslTemplate().queryPager(start, limit, tRole, false, new SelectGenerateCallback<TRole>() {
            public Select generate(TRole t) {
                Select select = Select.selectFrom(T_ROLE_TABLE)
                        .where(and(T_ROLE_TABLE.ROLE_ID.eq(t.getRoleId()), T_ROLE_TABLE.ROLE_NAME.eq(t.getRoleName()),
                                T_ROLE_TABLE.ROLE_DESCRIPTION.eq(t.getRoleDescription())

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
    public int[] batchInsert(boolean autoGeneratedKeys, List<TRole> tRole) {
        if (CollectionUtil.isEmpty(tRole)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tRole, new InsertGenerateCallback<TRole>() {

            public Insert generate(TRole t) {
                return insertInto(T_ROLE_TABLE).values(T_ROLE_TABLE.ROLE_ID.value(t.getRoleId()),
                        T_ROLE_TABLE.ROLE_NAME.value(t.getRoleName()),
                        T_ROLE_TABLE.ROLE_DESCRIPTION.value(t.getRoleDescription())

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchInsert(List<TRole> tRoles) {
        return batchInsert(false, tRoles);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchUpdate(List<TRole> tRoles) {
        if (CollectionUtil.isEmpty(tRoles)) {
            return new int[0];
        }
        for (TRole tRole : tRoles) {
            if (StringUtils.isBlank(tRole.getRoleId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tRoles, new UpdateGenerateCallback<TRole>() {
            public Update generate(TRole t) {
                return update(T_ROLE_TABLE).set(T_ROLE_TABLE.ROLE_NAME.value(t.getRoleName()),
                        T_ROLE_TABLE.ROLE_DESCRIPTION.value(t.getRoleDescription())

                ).where(T_ROLE_TABLE.ROLE_ID.eq(t.getRoleId()));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchDelete(List<TRole> tRoles) {
        if (CollectionUtil.isEmpty(tRoles)) {
            return new int[0];
        }
        for (TRole tRole : tRoles) {
            if (

                    StringUtils.isBlank(tRole.getRoleId()) &&

                            StringUtils.isBlank(tRole.getRoleName()) &&

                            StringUtils.isBlank(tRole.getRoleDescription())

            ) {
                return new int[0];
            }
        }
        return getDslTemplate().batchDelete(tRoles, new DeleteGenerateCallback<TRole>() {
            public Delete generate(TRole t) {
                return delete(T_ROLE_TABLE)
                        .where(and(T_ROLE_TABLE.ROLE_ID.eq(t.getRoleId()), T_ROLE_TABLE.ROLE_NAME.eq(t.getRoleName()),
                                T_ROLE_TABLE.ROLE_DESCRIPTION.eq(t.getRoleDescription())

                        ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(boolean autoGeneratedKeys, List<TRole> tRole) {
        if (CollectionUtil.isEmpty(tRole)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tRole, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(T_ROLE_TABLE).values(T_ROLE_TABLE.ROLE_NAME.value(new JdbcNamedParameter("roleName")),
                        T_ROLE_TABLE.ROLE_DESCRIPTION.value(new JdbcNamedParameter("roleDescription"))

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchUpdate(List<TRole> tRoles) {
        if (CollectionUtil.isEmpty(tRoles)) {
            return new int[0];
        }

        for (TRole tRole : tRoles) {
            if (StringUtils.isBlank(tRole.getRoleId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tRoles, new NoParamUpdateGenerateCallback() {
            public Update generate() {
                return update(T_ROLE_TABLE).set(T_ROLE_TABLE.ROLE_NAME.value(new JdbcNamedParameter("roleName")),
                        T_ROLE_TABLE.ROLE_DESCRIPTION.value(new JdbcNamedParameter("roleDescription"))

                ).where(T_ROLE_TABLE.ROLE_ID.eq(new JdbcNamedParameter("roleId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchDelete(List<TRole> tRoles) {
        if (CollectionUtil.isEmpty(tRoles)) {
            return new int[0];
        }

        for (TRole tRole : tRoles) {
            if (

                    StringUtils.isBlank(tRole.getRoleId()) &&

                            StringUtils.isBlank(tRole.getRoleName()) &&

                            StringUtils.isBlank(tRole.getRoleDescription())

            ) {
                return new int[0];
            }
        }

        return getDslTemplate().batchDelete(tRoles, new NoParamDeleteGenerateCallback() {
            public Delete generate() {
                return delete(T_ROLE_TABLE).where(and(T_ROLE_TABLE.ROLE_NAME.eq(new JdbcNamedParameter("roleName")),
                        T_ROLE_TABLE.ROLE_DESCRIPTION.eq(new JdbcNamedParameter("roleDescription"))

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(List<TRole> tRole) {
        return preparedBatchInsert(false, tRole);
    }

}
