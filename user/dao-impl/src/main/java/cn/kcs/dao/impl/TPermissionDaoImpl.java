
package cn.kcs.dao.impl;

import cn.kcs.dao.inter.TPermissionDao;
import cn.kcs.dao.inter.UUIDutil.StringIdGenerator;
import cn.kcs.dao.inter.pojo.TPermission;
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

import static cn.kcs.dao.inter.constant.TPermissionTable.T_PERMISSION_TABLE;
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
public class TPermissionDaoImpl extends TinyDslDaoSupport implements TPermissionDao {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TPermission add(TPermission tPermission) {
        tPermission.setPermissionId(new StringIdGenerator().generate(new InsertContext()));
        return getDslTemplate().insertAndReturnKey(tPermission, new InsertGenerateCallback<TPermission>() {
            public Insert generate(TPermission t) {
                Insert insert = insertInto(T_PERMISSION_TABLE).values(
                        T_PERMISSION_TABLE.PERMISSION_ID.value(t.getPermissionId()),
                        T_PERMISSION_TABLE.PERMISSION_NAME.value(t.getPermissionName()),
                        T_PERMISSION_TABLE.PERMISSION_URL.value(t.getPermissionUrl()),
                        T_PERMISSION_TABLE.PERMISSION_METHOD.value(t.getPermissionMethod()),
                        T_PERMISSION_TABLE.PERMISSION_DESCRIPTION.value(t.getPermissionDescription())

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
    public int edit(TPermission tPermission) {
        if (tPermission == null || StringUtils.isBlank(tPermission.getPermissionId())) {
            return 0;
        }
        return getDslTemplate().update(tPermission, new UpdateGenerateCallback<TPermission>() {
            public Update generate(TPermission t) {
                Update update = update(T_PERMISSION_TABLE)
                        .set(T_PERMISSION_TABLE.PERMISSION_NAME.value(t.getPermissionName()),
                                T_PERMISSION_TABLE.PERMISSION_URL.value(t.getPermissionUrl()),
                                T_PERMISSION_TABLE.PERMISSION_METHOD.value(t.getPermissionMethod()),
                                T_PERMISSION_TABLE.PERMISSION_DESCRIPTION.value(t.getPermissionDescription()))
                        .where(T_PERMISSION_TABLE.PERMISSION_ID.eq(t.getPermissionId()));
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
                return delete(T_PERMISSION_TABLE).where(T_PERMISSION_TABLE.PERMISSION_ID.eq(pk));
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
                return delete(T_PERMISSION_TABLE).where(T_PERMISSION_TABLE.PERMISSION_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TPermission getByKey(String pk) {
        return getDslTemplate().getByKey(pk, TPermission.class, new SelectGenerateCallback<Serializable>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(T_PERMISSION_TABLE).where(T_PERMISSION_TABLE.PERMISSION_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public List<TPermission> query(TPermission tPermission, final OrderBy... orderArgs) {
        if (tPermission == null) {
            tPermission = new TPermission();
        }
        return getDslTemplate().query(tPermission, new SelectGenerateCallback<TPermission>() {
            @SuppressWarnings("rawtypes")
            public Select generate(TPermission t) {
                Select select = selectFrom(T_PERMISSION_TABLE)
                        .where(and(T_PERMISSION_TABLE.PERMISSION_ID.eq(t.getPermissionId()),
                                T_PERMISSION_TABLE.PERMISSION_NAME.eq(t.getPermissionName()),
                                T_PERMISSION_TABLE.PERMISSION_URL.eq(t.getPermissionUrl()),
                                T_PERMISSION_TABLE.PERMISSION_METHOD.eq(t.getPermissionMethod()),
                                T_PERMISSION_TABLE.PERMISSION_DESCRIPTION.eq(t.getPermissionDescription())

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
    public Pager<TPermission> queryPager(int start, int limit, TPermission tPermission, final OrderBy... orderArgs) {
        if (tPermission == null) {
            tPermission = new TPermission();
        }
        return getDslTemplate().queryPager(start, limit, tPermission, false, new SelectGenerateCallback<TPermission>() {
            public Select generate(TPermission t) {
                Select select = Select.selectFrom(T_PERMISSION_TABLE)
                        .where(and(T_PERMISSION_TABLE.PERMISSION_ID.eq(t.getPermissionId()),
                                T_PERMISSION_TABLE.PERMISSION_NAME.eq(t.getPermissionName()),
                                T_PERMISSION_TABLE.PERMISSION_URL.eq(t.getPermissionUrl()),
                                T_PERMISSION_TABLE.PERMISSION_METHOD.eq(t.getPermissionMethod()),
                                T_PERMISSION_TABLE.PERMISSION_DESCRIPTION.eq(t.getPermissionDescription())

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
    public int[] batchInsert(boolean autoGeneratedKeys, List<TPermission> tPermission) {
        if (CollectionUtil.isEmpty(tPermission)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tPermission, new InsertGenerateCallback<TPermission>() {

            public Insert generate(TPermission t) {
                return insertInto(T_PERMISSION_TABLE).values(
                        T_PERMISSION_TABLE.PERMISSION_ID.value(t.getPermissionId()),
                        T_PERMISSION_TABLE.PERMISSION_NAME.value(t.getPermissionName()),
                        T_PERMISSION_TABLE.PERMISSION_URL.value(t.getPermissionUrl()),
                        T_PERMISSION_TABLE.PERMISSION_METHOD.value(t.getPermissionMethod()),
                        T_PERMISSION_TABLE.PERMISSION_DESCRIPTION.value(t.getPermissionDescription())

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchInsert(List<TPermission> tPermissions) {
        return batchInsert(false, tPermissions);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchUpdate(List<TPermission> tPermissions) {
        if (CollectionUtil.isEmpty(tPermissions)) {
            return new int[0];
        }
        for (TPermission tPermission : tPermissions) {
            if (StringUtils.isBlank(tPermission.getPermissionId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tPermissions, new UpdateGenerateCallback<TPermission>() {
            public Update generate(TPermission t) {
                return update(T_PERMISSION_TABLE).set(T_PERMISSION_TABLE.PERMISSION_NAME.value(t.getPermissionName()),
                        T_PERMISSION_TABLE.PERMISSION_URL.value(t.getPermissionUrl()),
                        T_PERMISSION_TABLE.PERMISSION_METHOD.value(t.getPermissionMethod()),
                        T_PERMISSION_TABLE.PERMISSION_DESCRIPTION.value(t.getPermissionDescription())

                ).where(T_PERMISSION_TABLE.PERMISSION_ID.eq(t.getPermissionId()));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchDelete(List<TPermission> tPermissions) {
        if (CollectionUtil.isEmpty(tPermissions)) {
            return new int[0];
        }
        for (TPermission tPermission : tPermissions) {
            if (

                    StringUtils.isBlank(tPermission.getPermissionId()) &&

                            StringUtils.isBlank(tPermission.getPermissionName()) &&

                            StringUtils.isBlank(tPermission.getPermissionUrl()) &&

                            StringUtils.isBlank(tPermission.getPermissionMethod()) &&

                            StringUtils.isBlank(tPermission.getPermissionDescription())

            ) {
                return new int[0];
            }
        }
        return getDslTemplate().batchDelete(tPermissions, new DeleteGenerateCallback<TPermission>() {
            public Delete generate(TPermission t) {
                return delete(T_PERMISSION_TABLE).where(and(T_PERMISSION_TABLE.PERMISSION_ID.eq(t.getPermissionId()),
                        T_PERMISSION_TABLE.PERMISSION_NAME.eq(t.getPermissionName()),
                        T_PERMISSION_TABLE.PERMISSION_URL.eq(t.getPermissionUrl()),
                        T_PERMISSION_TABLE.PERMISSION_METHOD.eq(t.getPermissionMethod()),
                        T_PERMISSION_TABLE.PERMISSION_DESCRIPTION.eq(t.getPermissionDescription())

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(boolean autoGeneratedKeys, List<TPermission> tPermission) {
        if (CollectionUtil.isEmpty(tPermission)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tPermission, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(T_PERMISSION_TABLE).values(
                        T_PERMISSION_TABLE.PERMISSION_NAME.value(new JdbcNamedParameter("permissionName")),
                        T_PERMISSION_TABLE.PERMISSION_URL.value(new JdbcNamedParameter("permissionUrl")),
                        T_PERMISSION_TABLE.PERMISSION_METHOD.value(new JdbcNamedParameter("permissionMethod")),
                        T_PERMISSION_TABLE.PERMISSION_DESCRIPTION.value(new JdbcNamedParameter("permissionDescription"))

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchUpdate(List<TPermission> tPermissions) {
        if (CollectionUtil.isEmpty(tPermissions)) {
            return new int[0];
        }

        for (TPermission tPermission : tPermissions) {
            if (StringUtils.isBlank(tPermission.getPermissionId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tPermissions, new NoParamUpdateGenerateCallback() {
            public Update generate() {
                return update(T_PERMISSION_TABLE).set(
                        T_PERMISSION_TABLE.PERMISSION_NAME.value(new JdbcNamedParameter("permissionName")),
                        T_PERMISSION_TABLE.PERMISSION_URL.value(new JdbcNamedParameter("permissionUrl")),
                        T_PERMISSION_TABLE.PERMISSION_METHOD.value(new JdbcNamedParameter("permissionMethod")),
                        T_PERMISSION_TABLE.PERMISSION_DESCRIPTION.value(new JdbcNamedParameter("permissionDescription"))

                ).where(T_PERMISSION_TABLE.PERMISSION_ID.eq(new JdbcNamedParameter("permissionId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchDelete(List<TPermission> tPermissions) {
        if (CollectionUtil.isEmpty(tPermissions)) {
            return new int[0];
        }

        for (TPermission tPermission : tPermissions) {
            if (

                    StringUtils.isBlank(tPermission.getPermissionId()) &&

                            StringUtils.isBlank(tPermission.getPermissionName()) &&

                            StringUtils.isBlank(tPermission.getPermissionUrl()) &&

                            StringUtils.isBlank(tPermission.getPermissionMethod()) &&

                            StringUtils.isBlank(tPermission.getPermissionDescription())

            ) {
                return new int[0];
            }
        }

        return getDslTemplate().batchDelete(tPermissions, new NoParamDeleteGenerateCallback() {
            public Delete generate() {
                return delete(T_PERMISSION_TABLE).where(and(
                        T_PERMISSION_TABLE.PERMISSION_NAME.eq(new JdbcNamedParameter("permissionName")),
                        T_PERMISSION_TABLE.PERMISSION_URL.eq(new JdbcNamedParameter("permissionUrl")),
                        T_PERMISSION_TABLE.PERMISSION_METHOD.eq(new JdbcNamedParameter("permissionMethod")),
                        T_PERMISSION_TABLE.PERMISSION_DESCRIPTION.eq(new JdbcNamedParameter("permissionDescription"))

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(List<TPermission> tPermission) {
        return preparedBatchInsert(false, tPermission);
    }

}
