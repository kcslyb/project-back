
package cn.kcs.dao.impl;

import cn.kcs.dao.inter.TScheduleDao;
import cn.kcs.dao.inter.pojo.TSchedule;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.jdbctemplatedslsession.util.TinyDSLUtil;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;

import java.io.Serializable;
import java.util.List;

import static cn.kcs.dao.inter.constant.TScheduleTable.T_SCHEDULE_TABLE;
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
public class TScheduleDaoImpl extends TinyDslDaoSupport implements TScheduleDao {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TSchedule add(TSchedule tSchedule) {
        return getDslTemplate().insertAndReturnKey(tSchedule, new InsertGenerateCallback<TSchedule>() {
            public Insert generate(TSchedule t) {
                Insert insert = insertInto(T_SCHEDULE_TABLE).values(
                        T_SCHEDULE_TABLE.SCHEDULE_ID.value(t.getScheduleId()),
                        T_SCHEDULE_TABLE.SCHEDULE_STATUS.value(t.getScheduleStatus()),
                        T_SCHEDULE_TABLE.SCHEDULE_CONFIRMED.value(t.getScheduleConfirmed())

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
    public int edit(TSchedule tSchedule) {
        if (tSchedule == null || StringUtils.isBlank(tSchedule.getScheduleId())) {
            return 0;
        }
        return getDslTemplate().update(tSchedule, new UpdateGenerateCallback<TSchedule>() {
            public Update generate(TSchedule t) {
                Update update = update(T_SCHEDULE_TABLE)
                        .set(T_SCHEDULE_TABLE.SCHEDULE_STATUS.value(t.getScheduleStatus()),
                                T_SCHEDULE_TABLE.SCHEDULE_CONFIRMED.value(t.getScheduleConfirmed()))
                        .where(T_SCHEDULE_TABLE.SCHEDULE_ID.eq(t.getScheduleId()));
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
                return delete(T_SCHEDULE_TABLE).where(T_SCHEDULE_TABLE.SCHEDULE_ID.eq(pk));
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
                return delete(T_SCHEDULE_TABLE).where(T_SCHEDULE_TABLE.SCHEDULE_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TSchedule getByKey(String pk) {
        return getDslTemplate().getByKey(pk, TSchedule.class, new SelectGenerateCallback<Serializable>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(T_SCHEDULE_TABLE).where(T_SCHEDULE_TABLE.SCHEDULE_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public List<TSchedule> query(TSchedule tSchedule, final OrderBy... orderArgs) {
        if (tSchedule == null) {
            tSchedule = new TSchedule();
        }
        return getDslTemplate().query(tSchedule, new SelectGenerateCallback<TSchedule>() {
            @SuppressWarnings("rawtypes")
            public Select generate(TSchedule t) {
                Select select = selectFrom(T_SCHEDULE_TABLE)
                        .where(and(T_SCHEDULE_TABLE.SCHEDULE_ID.eq(t.getScheduleId()),
                                T_SCHEDULE_TABLE.SCHEDULE_STATUS.eq(t.getScheduleStatus()),
                                T_SCHEDULE_TABLE.SCHEDULE_CONFIRMED.eq(t.getScheduleConfirmed())

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
    public Pager<TSchedule> queryPager(int start, int limit, TSchedule tSchedule, final OrderBy... orderArgs) {
        if (tSchedule == null) {
            tSchedule = new TSchedule();
        }
        return getDslTemplate().queryPager(start, limit, tSchedule, false, new SelectGenerateCallback<TSchedule>() {
            public Select generate(TSchedule t) {
                Select select = Select.selectFrom(T_SCHEDULE_TABLE)
                        .where(and(T_SCHEDULE_TABLE.SCHEDULE_ID.eq(t.getScheduleId()),
                                T_SCHEDULE_TABLE.SCHEDULE_STATUS.eq(t.getScheduleStatus()),
                                T_SCHEDULE_TABLE.SCHEDULE_CONFIRMED.eq(t.getScheduleConfirmed())

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
    public int[] batchInsert(boolean autoGeneratedKeys, List<TSchedule> tSchedule) {
        if (CollectionUtil.isEmpty(tSchedule)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tSchedule, new InsertGenerateCallback<TSchedule>() {

            public Insert generate(TSchedule t) {
                return insertInto(T_SCHEDULE_TABLE).values(T_SCHEDULE_TABLE.SCHEDULE_ID.value(t.getScheduleId()),
                        T_SCHEDULE_TABLE.SCHEDULE_STATUS.value(t.getScheduleStatus()),
                        T_SCHEDULE_TABLE.SCHEDULE_CONFIRMED.value(t.getScheduleConfirmed())

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchInsert(List<TSchedule> tSchedules) {
        return batchInsert(false, tSchedules);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchUpdate(List<TSchedule> tSchedules) {
        if (CollectionUtil.isEmpty(tSchedules)) {
            return new int[0];
        }
        for (TSchedule tSchedule : tSchedules) {
            if (StringUtils.isBlank(tSchedule.getScheduleId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tSchedules, new UpdateGenerateCallback<TSchedule>() {
            public Update generate(TSchedule t) {
                return update(T_SCHEDULE_TABLE).set(T_SCHEDULE_TABLE.SCHEDULE_STATUS.value(t.getScheduleStatus()),
                        T_SCHEDULE_TABLE.SCHEDULE_CONFIRMED.value(t.getScheduleConfirmed())

                ).where(T_SCHEDULE_TABLE.SCHEDULE_ID.eq(t.getScheduleId()));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchDelete(List<TSchedule> tSchedules) {
        if (CollectionUtil.isEmpty(tSchedules)) {
            return new int[0];
        }
        for (TSchedule tSchedule : tSchedules) {
            if (

                    StringUtils.isBlank(tSchedule.getScheduleId()) &&

                            StringUtils.isBlank(tSchedule.getScheduleStatus()) &&

                            StringUtils.isBlank(tSchedule.getScheduleConfirmed())

            ) {
                return new int[0];
            }
        }
        return getDslTemplate().batchDelete(tSchedules, new DeleteGenerateCallback<TSchedule>() {
            public Delete generate(TSchedule t) {
                return delete(T_SCHEDULE_TABLE).where(and(T_SCHEDULE_TABLE.SCHEDULE_ID.eq(t.getScheduleId()),
                        T_SCHEDULE_TABLE.SCHEDULE_STATUS.eq(t.getScheduleStatus()),
                        T_SCHEDULE_TABLE.SCHEDULE_CONFIRMED.eq(t.getScheduleConfirmed())

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(boolean autoGeneratedKeys, List<TSchedule> tSchedule) {
        if (CollectionUtil.isEmpty(tSchedule)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tSchedule, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(T_SCHEDULE_TABLE).values(
                        T_SCHEDULE_TABLE.SCHEDULE_STATUS.value(new JdbcNamedParameter("scheduleStatus")),
                        T_SCHEDULE_TABLE.SCHEDULE_CONFIRMED.value(new JdbcNamedParameter("scheduleConfirmed"))

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchUpdate(List<TSchedule> tSchedules) {
        if (CollectionUtil.isEmpty(tSchedules)) {
            return new int[0];
        }

        for (TSchedule tSchedule : tSchedules) {
            if (StringUtils.isBlank(tSchedule.getScheduleId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tSchedules, new NoParamUpdateGenerateCallback() {
            public Update generate() {
                return update(T_SCHEDULE_TABLE)
                        .set(T_SCHEDULE_TABLE.SCHEDULE_STATUS.value(new JdbcNamedParameter("scheduleStatus")),
                                T_SCHEDULE_TABLE.SCHEDULE_CONFIRMED.value(new JdbcNamedParameter("scheduleConfirmed"))

                        ).where(T_SCHEDULE_TABLE.SCHEDULE_ID.eq(new JdbcNamedParameter("scheduleId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchDelete(List<TSchedule> tSchedules) {
        if (CollectionUtil.isEmpty(tSchedules)) {
            return new int[0];
        }

        for (TSchedule tSchedule : tSchedules) {
            if (

                    StringUtils.isBlank(tSchedule.getScheduleId()) &&

                            StringUtils.isBlank(tSchedule.getScheduleStatus()) &&

                            StringUtils.isBlank(tSchedule.getScheduleConfirmed())

            ) {
                return new int[0];
            }
        }

        return getDslTemplate().batchDelete(tSchedules, new NoParamDeleteGenerateCallback() {
            public Delete generate() {
                return delete(T_SCHEDULE_TABLE)
                        .where(and(T_SCHEDULE_TABLE.SCHEDULE_STATUS.eq(new JdbcNamedParameter("scheduleStatus")),
                                T_SCHEDULE_TABLE.SCHEDULE_CONFIRMED.eq(new JdbcNamedParameter("scheduleConfirmed"))

                        ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(List<TSchedule> tSchedule) {
        return preparedBatchInsert(false, tSchedule);
    }

}
