
package cn.kcs.dao.impl;

import cn.kcs.dao.inter.TOrderDao;
import cn.kcs.dao.inter.UUIDutil.StringIdGenerator;
import cn.kcs.dao.inter.pojo.TOrder;
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

import static cn.kcs.dao.inter.constant.TOrderTable.T_ORDER_TABLE;
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
public class TOrderDaoImpl extends TinyDslDaoSupport implements TOrderDao {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TOrder add(TOrder tOrder) {
        tOrder.setOrderId(new StringIdGenerator().generate(new InsertContext()));
        return getDslTemplate().insertAndReturnKey(tOrder, new InsertGenerateCallback<TOrder>() {
            public Insert generate(TOrder t) {
                Insert insert = insertInto(T_ORDER_TABLE).values(T_ORDER_TABLE.ORDER_ID.value(t.getOrderId()),
                        T_ORDER_TABLE.ORDER_INDEX.value(t.getOrderIndex()),
                        T_ORDER_TABLE.ORDER_CREATED.value(t.getOrderCreated()),
                        T_ORDER_TABLE.ORDER_DESK.value(t.getOrderDesk()),
                        T_ORDER_TABLE.ORDER_CREATETIME.value(t.getOrderCreatetime())

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
    public int edit(TOrder tOrder) {
        if (tOrder == null || StringUtils.isBlank(tOrder.getOrderId())) {
            return 0;
        }
        return getDslTemplate().update(tOrder, new UpdateGenerateCallback<TOrder>() {
            public Update generate(TOrder t) {
                Update update = update(T_ORDER_TABLE)
                        .set(T_ORDER_TABLE.ORDER_INDEX.value(t.getOrderIndex()),
                                T_ORDER_TABLE.ORDER_CREATED.value(t.getOrderCreated()),
                                T_ORDER_TABLE.ORDER_DESK.value(t.getOrderDesk()),
                                T_ORDER_TABLE.ORDER_CREATETIME.value(t.getOrderCreatetime()))
                        .where(T_ORDER_TABLE.ORDER_ID.eq(t.getOrderId()));
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
                return delete(T_ORDER_TABLE).where(T_ORDER_TABLE.ORDER_ID.eq(pk));
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
                return delete(T_ORDER_TABLE).where(T_ORDER_TABLE.ORDER_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TOrder getByKey(String pk) {
        return getDslTemplate().getByKey(pk, TOrder.class, new SelectGenerateCallback<Serializable>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(T_ORDER_TABLE).where(T_ORDER_TABLE.ORDER_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public List<TOrder> query(TOrder tOrder, final OrderBy... orderArgs) {
        if (tOrder == null) {
            tOrder = new TOrder();
        }
        return getDslTemplate().query(tOrder, new SelectGenerateCallback<TOrder>() {
            @SuppressWarnings("rawtypes")
            public Select generate(TOrder t) {
                Select select = selectFrom(T_ORDER_TABLE).where(
                        and(T_ORDER_TABLE.ORDER_ID.eq(t.getOrderId()), T_ORDER_TABLE.ORDER_INDEX.eq(t.getOrderIndex()),
                                T_ORDER_TABLE.ORDER_CREATED.eq(t.getOrderCreated()),
                                T_ORDER_TABLE.ORDER_DESK.eq(t.getOrderDesk()),
                                T_ORDER_TABLE.ORDER_CREATETIME.eq(t.getOrderCreatetime())

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
    public Pager<TOrder> queryPager(int start, int limit, TOrder tOrder, final OrderBy... orderArgs) {
        if (tOrder == null) {
            tOrder = new TOrder();
        }
        return getDslTemplate().queryPager(start, limit, tOrder, false, new SelectGenerateCallback<TOrder>() {
            public Select generate(TOrder t) {
                Select select = Select.selectFrom(T_ORDER_TABLE)
                        .where(and(T_ORDER_TABLE.ORDER_ID.eq(t.getOrderId()),
                                T_ORDER_TABLE.ORDER_INDEX.eq(t.getOrderIndex()),
                                T_ORDER_TABLE.ORDER_CREATED.eq(t.getOrderCreated()),
                                T_ORDER_TABLE.ORDER_DESK.eq(t.getOrderDesk()),
                                T_ORDER_TABLE.ORDER_CREATETIME.eq(t.getOrderCreatetime())

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
    public int[] batchInsert(boolean autoGeneratedKeys, List<TOrder> tOrder) {
        if (CollectionUtil.isEmpty(tOrder)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tOrder, new InsertGenerateCallback<TOrder>() {

            public Insert generate(TOrder t) {
                return insertInto(T_ORDER_TABLE).values(T_ORDER_TABLE.ORDER_ID.value(t.getOrderId()),
                        T_ORDER_TABLE.ORDER_INDEX.value(t.getOrderIndex()),
                        T_ORDER_TABLE.ORDER_CREATED.value(t.getOrderCreated()),
                        T_ORDER_TABLE.ORDER_DESK.value(t.getOrderDesk()),
                        T_ORDER_TABLE.ORDER_CREATETIME.value(t.getOrderCreatetime())

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchInsert(List<TOrder> tOrders) {
        return batchInsert(false, tOrders);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchUpdate(List<TOrder> tOrders) {
        if (CollectionUtil.isEmpty(tOrders)) {
            return new int[0];
        }
        for (TOrder tOrder : tOrders) {
            if (StringUtils.isBlank(tOrder.getOrderId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tOrders, new UpdateGenerateCallback<TOrder>() {
            public Update generate(TOrder t) {
                return update(T_ORDER_TABLE).set(T_ORDER_TABLE.ORDER_INDEX.value(t.getOrderIndex()),
                        T_ORDER_TABLE.ORDER_CREATED.value(t.getOrderCreated()),
                        T_ORDER_TABLE.ORDER_DESK.value(t.getOrderDesk()),
                        T_ORDER_TABLE.ORDER_CREATETIME.value(t.getOrderCreatetime())

                ).where(T_ORDER_TABLE.ORDER_ID.eq(t.getOrderId()));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchDelete(List<TOrder> tOrders) {
        if (CollectionUtil.isEmpty(tOrders)) {
            return new int[0];
        }
        for (TOrder tOrder : tOrders) {
            if (

                    StringUtils.isBlank(tOrder.getOrderId()) &&

                            StringUtils.isBlank(tOrder.getOrderIndex()) &&

                            StringUtils.isBlank(tOrder.getOrderCreated()) &&

                            StringUtils.isBlank(tOrder.getOrderDesk()) &&

                            StringUtils.isBlank(String.valueOf(tOrder.getOrderCreatetime()))

            ) {
                return new int[0];
            }
        }
        return getDslTemplate().batchDelete(tOrders, new DeleteGenerateCallback<TOrder>() {
            public Delete generate(TOrder t) {
                return delete(T_ORDER_TABLE).where(
                        and(T_ORDER_TABLE.ORDER_ID.eq(t.getOrderId()), T_ORDER_TABLE.ORDER_INDEX.eq(t.getOrderIndex()),
                                T_ORDER_TABLE.ORDER_CREATED.eq(t.getOrderCreated()),
                                T_ORDER_TABLE.ORDER_DESK.eq(t.getOrderDesk()),
                                T_ORDER_TABLE.ORDER_CREATETIME.eq(t.getOrderCreatetime())

                        ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(boolean autoGeneratedKeys, List<TOrder> tOrder) {
        if (CollectionUtil.isEmpty(tOrder)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tOrder, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(T_ORDER_TABLE).values(
                        T_ORDER_TABLE.ORDER_INDEX.value(new JdbcNamedParameter("orderIndex")),
                        T_ORDER_TABLE.ORDER_CREATED.value(new JdbcNamedParameter("orderCreated")),
                        T_ORDER_TABLE.ORDER_DESK.value(new JdbcNamedParameter("orderDesk")),
                        T_ORDER_TABLE.ORDER_CREATETIME.value(new JdbcNamedParameter("orderCreatetime"))

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchUpdate(List<TOrder> tOrders) {
        if (CollectionUtil.isEmpty(tOrders)) {
            return new int[0];
        }

        for (TOrder tOrder : tOrders) {
            if (StringUtils.isBlank(tOrder.getOrderId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tOrders, new NoParamUpdateGenerateCallback() {
            public Update generate() {
                return update(T_ORDER_TABLE).set(T_ORDER_TABLE.ORDER_INDEX.value(new JdbcNamedParameter("orderIndex")),
                        T_ORDER_TABLE.ORDER_CREATED.value(new JdbcNamedParameter("orderCreated")),
                        T_ORDER_TABLE.ORDER_DESK.value(new JdbcNamedParameter("orderDesk")),
                        T_ORDER_TABLE.ORDER_CREATETIME.value(new JdbcNamedParameter("orderCreatetime"))

                ).where(T_ORDER_TABLE.ORDER_ID.eq(new JdbcNamedParameter("orderId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchDelete(List<TOrder> tOrders) {
        if (CollectionUtil.isEmpty(tOrders)) {
            return new int[0];
        }

        for (TOrder tOrder : tOrders) {
            if (

                    StringUtils.isBlank(tOrder.getOrderId()) &&

                            StringUtils.isBlank(tOrder.getOrderIndex()) &&

                            StringUtils.isBlank(tOrder.getOrderCreated()) &&

                            StringUtils.isBlank(tOrder.getOrderDesk()) &&

                            StringUtils.isBlank(String.valueOf(tOrder.getOrderCreatetime()))

            ) {
                return new int[0];
            }
        }

        return getDslTemplate().batchDelete(tOrders, new NoParamDeleteGenerateCallback() {
            public Delete generate() {
                return delete(T_ORDER_TABLE)
                        .where(and(T_ORDER_TABLE.ORDER_INDEX.eq(new JdbcNamedParameter("orderIndex")),
                                T_ORDER_TABLE.ORDER_CREATED.eq(new JdbcNamedParameter("orderCreated")),
                                T_ORDER_TABLE.ORDER_DESK.eq(new JdbcNamedParameter("orderDesk")),
                                T_ORDER_TABLE.ORDER_CREATETIME.eq(new JdbcNamedParameter("orderCreatetime"))

                        ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(List<TOrder> tOrder) {
        return preparedBatchInsert(false, tOrder);
    }

}
