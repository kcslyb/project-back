
package cn.kcs.dao.impl;

import cn.kcs.dao.inter.TLogDao;
import cn.kcs.dao.inter.pojo.TLog;
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

import static cn.kcs.dao.inter.constant.TLogTable.T_LOG_TABLE;
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
public class TLogDaoImpl extends TinyDslDaoSupport implements TLogDao {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TLog add(TLog tLog) {
        return getDslTemplate().insertAndReturnKey(tLog, new InsertGenerateCallback<TLog>() {
            public Insert generate(TLog t) {
                Insert insert = insertInto(T_LOG_TABLE).values(T_LOG_TABLE.LOG_ID.value(t.getLogId()),
                        T_LOG_TABLE.LOG_TIME.value(t.getLogTime()), T_LOG_TABLE.LOG_USED.value(t.getLogUsed()),
                        T_LOG_TABLE.LOG_OPERATE.value(t.getLogOperate()),
                        T_LOG_TABLE.LOG_ACCESSURL.value(t.getLogAccessurl())

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
    public int edit(TLog tLog) {
        if (tLog == null || StringUtils.isBlank(tLog.getLogId())) {
            return 0;
        }
        return getDslTemplate().update(tLog, new UpdateGenerateCallback<TLog>() {
            public Update generate(TLog t) {
                Update update = update(T_LOG_TABLE)
                        .set(T_LOG_TABLE.LOG_TIME.value(t.getLogTime()), T_LOG_TABLE.LOG_USED.value(t.getLogUsed()),
                                T_LOG_TABLE.LOG_OPERATE.value(t.getLogOperate()),
                                T_LOG_TABLE.LOG_ACCESSURL.value(t.getLogAccessurl()))
                        .where(T_LOG_TABLE.LOG_ID.eq(t.getLogId()));
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
                return delete(T_LOG_TABLE).where(T_LOG_TABLE.LOG_ID.eq(pk));
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
                return delete(T_LOG_TABLE).where(T_LOG_TABLE.LOG_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TLog getByKey(String pk) {
        return getDslTemplate().getByKey(pk, TLog.class, new SelectGenerateCallback<Serializable>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(T_LOG_TABLE).where(T_LOG_TABLE.LOG_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public List<TLog> query(TLog tLog, final OrderBy... orderArgs) {
        if (tLog == null) {
            tLog = new TLog();
        }
        return getDslTemplate().query(tLog, new SelectGenerateCallback<TLog>() {
            @SuppressWarnings("rawtypes")
            public Select generate(TLog t) {
                Select select = selectFrom(T_LOG_TABLE).where(and(T_LOG_TABLE.LOG_ID.eq(t.getLogId()),
                        T_LOG_TABLE.LOG_TIME.eq(t.getLogTime()), T_LOG_TABLE.LOG_USED.eq(t.getLogUsed()),
                        T_LOG_TABLE.LOG_OPERATE.eq(t.getLogOperate()), T_LOG_TABLE.LOG_ACCESSURL.eq(t.getLogAccessurl())

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
    public Pager<TLog> queryPager(int start, int limit, TLog tLog, final OrderBy... orderArgs) {
        if (tLog == null) {
            tLog = new TLog();
        }
        return getDslTemplate().queryPager(start, limit, tLog, false, new SelectGenerateCallback<TLog>() {
            public Select generate(TLog t) {
                Select select = Select.selectFrom(T_LOG_TABLE)
                        .where(and(T_LOG_TABLE.LOG_ID.eq(t.getLogId()), T_LOG_TABLE.LOG_TIME.eq(t.getLogTime()),
                                T_LOG_TABLE.LOG_USED.eq(t.getLogUsed()), T_LOG_TABLE.LOG_OPERATE.eq(t.getLogOperate()),
                                T_LOG_TABLE.LOG_ACCESSURL.eq(t.getLogAccessurl())

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
    public int[] batchInsert(boolean autoGeneratedKeys, List<TLog> tLog) {
        if (CollectionUtil.isEmpty(tLog)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tLog, new InsertGenerateCallback<TLog>() {

            public Insert generate(TLog t) {
                return insertInto(T_LOG_TABLE).values(T_LOG_TABLE.LOG_ID.value(t.getLogId()),
                        T_LOG_TABLE.LOG_TIME.value(t.getLogTime()), T_LOG_TABLE.LOG_USED.value(t.getLogUsed()),
                        T_LOG_TABLE.LOG_OPERATE.value(t.getLogOperate()),
                        T_LOG_TABLE.LOG_ACCESSURL.value(t.getLogAccessurl())

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchInsert(List<TLog> tLogs) {
        return batchInsert(false, tLogs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchUpdate(List<TLog> tLogs) {
        if (CollectionUtil.isEmpty(tLogs)) {
            return new int[0];
        }
        for (TLog tLog : tLogs) {
            if (StringUtils.isBlank(tLog.getLogId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tLogs, new UpdateGenerateCallback<TLog>() {
            public Update generate(TLog t) {
                return update(T_LOG_TABLE).set(T_LOG_TABLE.LOG_TIME.value(t.getLogTime()),
                        T_LOG_TABLE.LOG_USED.value(t.getLogUsed()), T_LOG_TABLE.LOG_OPERATE.value(t.getLogOperate()),
                        T_LOG_TABLE.LOG_ACCESSURL.value(t.getLogAccessurl())

                ).where(T_LOG_TABLE.LOG_ID.eq(t.getLogId()));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchDelete(List<TLog> tLogs) {
        if (CollectionUtil.isEmpty(tLogs)) {
            return new int[0];
        }
        for (TLog tLog : tLogs) {
            if (

                    StringUtils.isBlank(tLog.getLogId()) &&

                            StringUtils.isBlank(String.valueOf(tLog.getLogTime())) &&

                            StringUtils.isBlank(tLog.getLogUsed()) &&

                            StringUtils.isBlank(tLog.getLogOperate()) &&

                            StringUtils.isBlank(tLog.getLogAccessurl())

            ) {
                return new int[0];
            }
        }
        return getDslTemplate().batchDelete(tLogs, new DeleteGenerateCallback<TLog>() {
            public Delete generate(TLog t) {
                return delete(T_LOG_TABLE).where(and(T_LOG_TABLE.LOG_ID.eq(t.getLogId()),
                        T_LOG_TABLE.LOG_TIME.eq(t.getLogTime()), T_LOG_TABLE.LOG_USED.eq(t.getLogUsed()),
                        T_LOG_TABLE.LOG_OPERATE.eq(t.getLogOperate()), T_LOG_TABLE.LOG_ACCESSURL.eq(t.getLogAccessurl())

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(boolean autoGeneratedKeys, List<TLog> tLog) {
        if (CollectionUtil.isEmpty(tLog)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tLog, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(T_LOG_TABLE).values(T_LOG_TABLE.LOG_TIME.value(new JdbcNamedParameter("logTime")),
                        T_LOG_TABLE.LOG_USED.value(new JdbcNamedParameter("logUsed")),
                        T_LOG_TABLE.LOG_OPERATE.value(new JdbcNamedParameter("logOperate")),
                        T_LOG_TABLE.LOG_ACCESSURL.value(new JdbcNamedParameter("logAccessurl"))

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchUpdate(List<TLog> tLogs) {
        if (CollectionUtil.isEmpty(tLogs)) {
            return new int[0];
        }

        for (TLog tLog : tLogs) {
            if (StringUtils.isBlank(tLog.getLogId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tLogs, new NoParamUpdateGenerateCallback() {
            public Update generate() {
                return update(T_LOG_TABLE).set(T_LOG_TABLE.LOG_TIME.value(new JdbcNamedParameter("logTime")),
                        T_LOG_TABLE.LOG_USED.value(new JdbcNamedParameter("logUsed")),
                        T_LOG_TABLE.LOG_OPERATE.value(new JdbcNamedParameter("logOperate")),
                        T_LOG_TABLE.LOG_ACCESSURL.value(new JdbcNamedParameter("logAccessurl"))

                ).where(T_LOG_TABLE.LOG_ID.eq(new JdbcNamedParameter("logId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchDelete(List<TLog> tLogs) {
        if (CollectionUtil.isEmpty(tLogs)) {
            return new int[0];
        }

        for (TLog tLog : tLogs) {
            if (

                    StringUtils.isBlank(tLog.getLogId()) &&

                            StringUtils.isBlank(String.valueOf(tLog.getLogTime())) &&

                            StringUtils.isBlank(tLog.getLogUsed()) &&

                            StringUtils.isBlank(tLog.getLogOperate()) &&

                            StringUtils.isBlank(tLog.getLogAccessurl())

            ) {
                return new int[0];
            }
        }

        return getDslTemplate().batchDelete(tLogs, new NoParamDeleteGenerateCallback() {
            public Delete generate() {
                return delete(T_LOG_TABLE).where(and(T_LOG_TABLE.LOG_TIME.eq(new JdbcNamedParameter("logTime")),
                        T_LOG_TABLE.LOG_USED.eq(new JdbcNamedParameter("logUsed")),
                        T_LOG_TABLE.LOG_OPERATE.eq(new JdbcNamedParameter("logOperate")),
                        T_LOG_TABLE.LOG_ACCESSURL.eq(new JdbcNamedParameter("logAccessurl"))

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(List<TLog> tLog) {
        return preparedBatchInsert(false, tLog);
    }

}
