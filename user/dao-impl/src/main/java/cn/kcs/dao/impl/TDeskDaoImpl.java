
package cn.kcs.dao.impl;

import cn.kcs.dao.inter.TDeskDao;
import cn.kcs.dao.inter.UUIDutil.StringIdGenerator;
import cn.kcs.dao.inter.pojo.TDesk;
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

import static cn.kcs.dao.inter.constant.TDeskTable.T_DESK_TABLE;
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
public class TDeskDaoImpl extends TinyDslDaoSupport implements TDeskDao {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TDesk add(TDesk tDesk) {
        tDesk.setDeskId(new StringIdGenerator().generate(new InsertContext()));
        return getDslTemplate().insertAndReturnKey(tDesk, new InsertGenerateCallback<TDesk>() {
            public Insert generate(TDesk t) {
                Insert insert = insertInto(T_DESK_TABLE).values(T_DESK_TABLE.DESK_ID.value(t.getDeskId()),
                        T_DESK_TABLE.DESK_NUMBER.value(t.getDeskNumber()),
                        T_DESK_TABLE.DESK_STATUS.value(t.getDeskStatus()), T_DESK_TABLE.DESK_SIZE.value(t.getDeskSize())

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
    public int edit(TDesk tDesk) {
        if (tDesk == null || StringUtils.isBlank(tDesk.getDeskId())) {
            return 0;
        }
        return getDslTemplate().update(tDesk, new UpdateGenerateCallback<TDesk>() {
            public Update generate(TDesk t) {
                Update update = update(T_DESK_TABLE)
                        .set(T_DESK_TABLE.DESK_NUMBER.value(t.getDeskNumber()),
                                T_DESK_TABLE.DESK_STATUS.value(t.getDeskStatus()),
                                T_DESK_TABLE.DESK_SIZE.value(t.getDeskSize()))
                        .where(T_DESK_TABLE.DESK_ID.eq(t.getDeskId()));
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
                return delete(T_DESK_TABLE).where(T_DESK_TABLE.DESK_ID.eq(pk));
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
                return delete(T_DESK_TABLE).where(T_DESK_TABLE.DESK_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TDesk getByKey(String pk) {
        return getDslTemplate().getByKey(pk, TDesk.class, new SelectGenerateCallback<Serializable>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(T_DESK_TABLE).where(T_DESK_TABLE.DESK_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public List<TDesk> query(TDesk tDesk, final OrderBy... orderArgs) {
        if (tDesk == null) {
            tDesk = new TDesk();
        }
        return getDslTemplate().query(tDesk, new SelectGenerateCallback<TDesk>() {
            @SuppressWarnings("rawtypes")
            public Select generate(TDesk t) {
                Select select = selectFrom(T_DESK_TABLE).where(and(T_DESK_TABLE.DESK_ID.eq(t.getDeskId()),
                        T_DESK_TABLE.DESK_NUMBER.eq(t.getDeskNumber()), T_DESK_TABLE.DESK_STATUS.eq(t.getDeskStatus()),
                        T_DESK_TABLE.DESK_SIZE.eq(t.getDeskSize())

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
    public Pager<TDesk> queryPager(int start, int limit, TDesk tDesk, final OrderBy... orderArgs) {
        if (tDesk == null) {
            tDesk = new TDesk();
        }
        return getDslTemplate().queryPager(start, limit, tDesk, false, new SelectGenerateCallback<TDesk>() {
            public Select generate(TDesk t) {
                Select select = Select.selectFrom(T_DESK_TABLE).where(and(T_DESK_TABLE.DESK_ID.eq(t.getDeskId()),
                        T_DESK_TABLE.DESK_NUMBER.eq(t.getDeskNumber()), T_DESK_TABLE.DESK_STATUS.eq(t.getDeskStatus()),
                        T_DESK_TABLE.DESK_SIZE.eq(t.getDeskSize())

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
    public int[] batchInsert(boolean autoGeneratedKeys, List<TDesk> tDesk) {
        if (CollectionUtil.isEmpty(tDesk)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tDesk, new InsertGenerateCallback<TDesk>() {

            public Insert generate(TDesk t) {
                return insertInto(T_DESK_TABLE).values(T_DESK_TABLE.DESK_ID.value(t.getDeskId()),
                        T_DESK_TABLE.DESK_NUMBER.value(t.getDeskNumber()),
                        T_DESK_TABLE.DESK_STATUS.value(t.getDeskStatus()), T_DESK_TABLE.DESK_SIZE.value(t.getDeskSize())

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchInsert(List<TDesk> tDesks) {
        return batchInsert(false, tDesks);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchUpdate(List<TDesk> tDesks) {
        if (CollectionUtil.isEmpty(tDesks)) {
            return new int[0];
        }
        for (TDesk tDesk : tDesks) {
            if (StringUtils.isBlank(tDesk.getDeskId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tDesks, new UpdateGenerateCallback<TDesk>() {
            public Update generate(TDesk t) {
                return update(T_DESK_TABLE).set(T_DESK_TABLE.DESK_NUMBER.value(t.getDeskNumber()),
                        T_DESK_TABLE.DESK_STATUS.value(t.getDeskStatus()), T_DESK_TABLE.DESK_SIZE.value(t.getDeskSize())

                ).where(T_DESK_TABLE.DESK_ID.eq(t.getDeskId()));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchDelete(List<TDesk> tDesks) {
        if (CollectionUtil.isEmpty(tDesks)) {
            return new int[0];
        }
        for (TDesk tDesk : tDesks) {
            if (

                    StringUtils.isBlank(tDesk.getDeskId()) &&

                            StringUtils.isBlank(tDesk.getDeskNumber()) &&

                            StringUtils.isBlank(tDesk.getDeskStatus()) &&

                            StringUtils.isBlank(tDesk.getDeskSize())

            ) {
                return new int[0];
            }
        }
        return getDslTemplate().batchDelete(tDesks, new DeleteGenerateCallback<TDesk>() {
            public Delete generate(TDesk t) {
                return delete(T_DESK_TABLE).where(and(T_DESK_TABLE.DESK_ID.eq(t.getDeskId()),
                        T_DESK_TABLE.DESK_NUMBER.eq(t.getDeskNumber()), T_DESK_TABLE.DESK_STATUS.eq(t.getDeskStatus()),
                        T_DESK_TABLE.DESK_SIZE.eq(t.getDeskSize())

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(boolean autoGeneratedKeys, List<TDesk> tDesk) {
        if (CollectionUtil.isEmpty(tDesk)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tDesk, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(T_DESK_TABLE).values(
                        T_DESK_TABLE.DESK_NUMBER.value(new JdbcNamedParameter("deskNumber")),
                        T_DESK_TABLE.DESK_STATUS.value(new JdbcNamedParameter("deskStatus")),
                        T_DESK_TABLE.DESK_SIZE.value(new JdbcNamedParameter("deskSize"))

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchUpdate(List<TDesk> tDesks) {
        if (CollectionUtil.isEmpty(tDesks)) {
            return new int[0];
        }

        for (TDesk tDesk : tDesks) {
            if (StringUtils.isBlank(tDesk.getDeskId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tDesks, new NoParamUpdateGenerateCallback() {
            public Update generate() {
                return update(T_DESK_TABLE).set(T_DESK_TABLE.DESK_NUMBER.value(new JdbcNamedParameter("deskNumber")),
                        T_DESK_TABLE.DESK_STATUS.value(new JdbcNamedParameter("deskStatus")),
                        T_DESK_TABLE.DESK_SIZE.value(new JdbcNamedParameter("deskSize"))

                ).where(T_DESK_TABLE.DESK_ID.eq(new JdbcNamedParameter("deskId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchDelete(List<TDesk> tDesks) {
        if (CollectionUtil.isEmpty(tDesks)) {
            return new int[0];
        }

        for (TDesk tDesk : tDesks) {
            if (

                    StringUtils.isBlank(tDesk.getDeskId()) &&

                            StringUtils.isBlank(tDesk.getDeskNumber()) &&

                            StringUtils.isBlank(tDesk.getDeskStatus()) &&

                            StringUtils.isBlank(tDesk.getDeskSize())

            ) {
                return new int[0];
            }
        }

        return getDslTemplate().batchDelete(tDesks, new NoParamDeleteGenerateCallback() {
            public Delete generate() {
                return delete(T_DESK_TABLE).where(and(T_DESK_TABLE.DESK_NUMBER.eq(new JdbcNamedParameter("deskNumber")),
                        T_DESK_TABLE.DESK_STATUS.eq(new JdbcNamedParameter("deskStatus")),
                        T_DESK_TABLE.DESK_SIZE.eq(new JdbcNamedParameter("deskSize"))

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(List<TDesk> tDesk) {
        return preparedBatchInsert(false, tDesk);
    }

}
