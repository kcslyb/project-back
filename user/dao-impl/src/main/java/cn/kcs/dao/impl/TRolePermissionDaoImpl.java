
package cn.kcs.dao.impl;

import cn.kcs.dao.inter.TRolePermissionDao;
import cn.kcs.dao.inter.UUIDutil.StringIdGenerator;
import cn.kcs.dao.inter.pojo.TRolePermission;
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

import static cn.kcs.dao.inter.constant.TRolePermissionTable.T_ROLE_PERMISSION_TABLE;
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
public class TRolePermissionDaoImpl extends TinyDslDaoSupport implements TRolePermissionDao {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TRolePermission add(TRolePermission tRolePermission) {
        tRolePermission.setRolePermissionId(new StringIdGenerator().generate(new InsertContext()));
        return getDslTemplate().insertAndReturnKey(tRolePermission, new InsertGenerateCallback<TRolePermission>() {
            public Insert generate(TRolePermission t) {
                Insert insert = insertInto(T_ROLE_PERMISSION_TABLE).values(
                        T_ROLE_PERMISSION_TABLE.ROLE_PERMISSION_ID.value(t.getRolePermissionId()),
                        T_ROLE_PERMISSION_TABLE.ROLE_ID.value(t.getRoleId()),
                        T_ROLE_PERMISSION_TABLE.PERMISSION_ID.value(t.getPermissionId())

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
    public int edit(TRolePermission tRolePermission) {
        if (tRolePermission == null || StringUtils.isBlank(tRolePermission.getRolePermissionId())) {
            return 0;
        }
        return getDslTemplate().update(tRolePermission, new UpdateGenerateCallback<TRolePermission>() {
            public Update generate(TRolePermission t) {
                Update update = update(T_ROLE_PERMISSION_TABLE)
                        .set(T_ROLE_PERMISSION_TABLE.ROLE_ID.value(t.getRoleId()),
                                T_ROLE_PERMISSION_TABLE.PERMISSION_ID.value(t.getPermissionId()))
                        .where(T_ROLE_PERMISSION_TABLE.ROLE_PERMISSION_ID.eq(t.getRolePermissionId()));
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
                return delete(T_ROLE_PERMISSION_TABLE).where(T_ROLE_PERMISSION_TABLE.ROLE_PERMISSION_ID.eq(pk));
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
                return delete(T_ROLE_PERMISSION_TABLE).where(T_ROLE_PERMISSION_TABLE.ROLE_PERMISSION_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TRolePermission getByKey(String pk) {
        return getDslTemplate().getByKey(pk, TRolePermission.class, new SelectGenerateCallback<Serializable>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(T_ROLE_PERMISSION_TABLE).where(T_ROLE_PERMISSION_TABLE.ROLE_PERMISSION_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public List<TRolePermission> query(TRolePermission tRolePermission, final OrderBy... orderArgs) {
        if (tRolePermission == null) {
            tRolePermission = new TRolePermission();
        }
        return getDslTemplate().query(tRolePermission, new SelectGenerateCallback<TRolePermission>() {
            @SuppressWarnings("rawtypes")
            public Select generate(TRolePermission t) {
                Select select = selectFrom(T_ROLE_PERMISSION_TABLE)
                        .where(and(T_ROLE_PERMISSION_TABLE.ROLE_PERMISSION_ID.eq(t.getRolePermissionId()),
                                T_ROLE_PERMISSION_TABLE.ROLE_ID.eq(t.getRoleId()),
                                T_ROLE_PERMISSION_TABLE.PERMISSION_ID.eq(t.getPermissionId())

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
    public Pager<TRolePermission> queryPager(int start, int limit, TRolePermission tRolePermission,
                                             final OrderBy... orderArgs) {
        if (tRolePermission == null) {
            tRolePermission = new TRolePermission();
        }
        return getDslTemplate().queryPager(start, limit, tRolePermission, false,
                new SelectGenerateCallback<TRolePermission>() {
                    public Select generate(TRolePermission t) {
                        Select select = Select.selectFrom(T_ROLE_PERMISSION_TABLE)
                                .where(and(T_ROLE_PERMISSION_TABLE.ROLE_PERMISSION_ID.eq(t.getRolePermissionId()),
                                        T_ROLE_PERMISSION_TABLE.ROLE_ID.eq(t.getRoleId()),
                                        T_ROLE_PERMISSION_TABLE.PERMISSION_ID.eq(t.getPermissionId())

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
    public int[] batchInsert(boolean autoGeneratedKeys, List<TRolePermission> tRolePermission) {
        if (CollectionUtil.isEmpty(tRolePermission)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tRolePermission,
                new InsertGenerateCallback<TRolePermission>() {

                    public Insert generate(TRolePermission t) {
                        return insertInto(T_ROLE_PERMISSION_TABLE).values(
                                T_ROLE_PERMISSION_TABLE.ROLE_PERMISSION_ID.value(t.getRolePermissionId()),
                                T_ROLE_PERMISSION_TABLE.ROLE_ID.value(t.getRoleId()),
                                T_ROLE_PERMISSION_TABLE.PERMISSION_ID.value(t.getPermissionId())

                        );
                    }
                });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchInsert(List<TRolePermission> tRolePermissions) {
        return batchInsert(false, tRolePermissions);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchUpdate(List<TRolePermission> tRolePermissions) {
        if (CollectionUtil.isEmpty(tRolePermissions)) {
            return new int[0];
        }
        for (TRolePermission tRolePermission : tRolePermissions) {
            if (StringUtils.isBlank(tRolePermission.getRolePermissionId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tRolePermissions, new UpdateGenerateCallback<TRolePermission>() {
            public Update generate(TRolePermission t) {
                return update(T_ROLE_PERMISSION_TABLE).set(T_ROLE_PERMISSION_TABLE.ROLE_ID.value(t.getRoleId()),
                        T_ROLE_PERMISSION_TABLE.PERMISSION_ID.value(t.getPermissionId())

                ).where(T_ROLE_PERMISSION_TABLE.ROLE_PERMISSION_ID.eq(t.getRolePermissionId()));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchDelete(List<TRolePermission> tRolePermissions) {
        if (CollectionUtil.isEmpty(tRolePermissions)) {
            return new int[0];
        }
        for (TRolePermission tRolePermission : tRolePermissions) {
            if (

                    StringUtils.isBlank(tRolePermission.getRolePermissionId()) &&

                            StringUtils.isBlank(tRolePermission.getRoleId()) &&

                            StringUtils.isBlank(tRolePermission.getPermissionId())

            ) {
                return new int[0];
            }
        }
        return getDslTemplate().batchDelete(tRolePermissions, new DeleteGenerateCallback<TRolePermission>() {
            public Delete generate(TRolePermission t) {
                return delete(T_ROLE_PERMISSION_TABLE)
                        .where(and(T_ROLE_PERMISSION_TABLE.ROLE_PERMISSION_ID.eq(t.getRolePermissionId()),
                                T_ROLE_PERMISSION_TABLE.ROLE_ID.eq(t.getRoleId()),
                                T_ROLE_PERMISSION_TABLE.PERMISSION_ID.eq(t.getPermissionId())

                        ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(boolean autoGeneratedKeys, List<TRolePermission> tRolePermission) {
        if (CollectionUtil.isEmpty(tRolePermission)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tRolePermission, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(T_ROLE_PERMISSION_TABLE).values(
                        T_ROLE_PERMISSION_TABLE.ROLE_ID.value(new JdbcNamedParameter("roleId")),
                        T_ROLE_PERMISSION_TABLE.PERMISSION_ID.value(new JdbcNamedParameter("permissionId"))

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchUpdate(List<TRolePermission> tRolePermissions) {
        if (CollectionUtil.isEmpty(tRolePermissions)) {
            return new int[0];
        }

        for (TRolePermission tRolePermission : tRolePermissions) {
            if (StringUtils.isBlank(tRolePermission.getRolePermissionId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tRolePermissions, new NoParamUpdateGenerateCallback() {
            public Update generate() {
                return update(T_ROLE_PERMISSION_TABLE)
                        .set(T_ROLE_PERMISSION_TABLE.ROLE_ID.value(new JdbcNamedParameter("roleId")),
                                T_ROLE_PERMISSION_TABLE.PERMISSION_ID.value(new JdbcNamedParameter("permissionId"))

                        ).where(T_ROLE_PERMISSION_TABLE.ROLE_PERMISSION_ID
                                .eq(new JdbcNamedParameter("rolePermissionId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchDelete(List<TRolePermission> tRolePermissions) {
        if (CollectionUtil.isEmpty(tRolePermissions)) {
            return new int[0];
        }

        for (TRolePermission tRolePermission : tRolePermissions) {
            if (

                    StringUtils.isBlank(tRolePermission.getRolePermissionId()) &&

                            StringUtils.isBlank(tRolePermission.getRoleId()) &&

                            StringUtils.isBlank(tRolePermission.getPermissionId())

            ) {
                return new int[0];
            }
        }

        return getDslTemplate().batchDelete(tRolePermissions, new NoParamDeleteGenerateCallback() {
            public Delete generate() {
                return delete(T_ROLE_PERMISSION_TABLE)
                        .where(and(T_ROLE_PERMISSION_TABLE.ROLE_ID.eq(new JdbcNamedParameter("roleId")),
                                T_ROLE_PERMISSION_TABLE.PERMISSION_ID.eq(new JdbcNamedParameter("permissionId"))

                        ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(List<TRolePermission> tRolePermission) {
        return preparedBatchInsert(false, tRolePermission);
    }

    public List<TRolePermission> queryByRoleID(TRolePermission tRolePermission, final OrderBy... orderArgs) {
        if (tRolePermission == null) {
            tRolePermission = new TRolePermission();
        }
        return getDslTemplate().query(tRolePermission, new SelectGenerateCallback<TRolePermission>() {
            @SuppressWarnings("rawtypes")
            public Select generate(TRolePermission t) {
                Select select = selectFrom(T_ROLE_PERMISSION_TABLE)
                        .where(T_ROLE_PERMISSION_TABLE.ROLE_ID.eq(t.getRoleId())
                        );
                return TinyDSLUtil.addOrderByElements(select, orderArgs);
            }
        });
    }
}
