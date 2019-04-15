
package cn.kcs.dao.impl;

import cn.kcs.dao.inter.TOrderProductDao;
import cn.kcs.dao.inter.UUIDutil.StringIdGenerator;
import cn.kcs.dao.inter.pojo.TOrderProduct;
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

import static cn.kcs.dao.inter.constant.TOrderProductTable.T_ORDER_PRODUCT_TABLE;
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
public class TOrderProductDaoImpl extends TinyDslDaoSupport implements TOrderProductDao {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TOrderProduct add(TOrderProduct tOrderProduct) {
        tOrderProduct.setOrderProductId(new StringIdGenerator().generate(new InsertContext()));
        return getDslTemplate().insertAndReturnKey(tOrderProduct, new InsertGenerateCallback<TOrderProduct>() {
            public Insert generate(TOrderProduct t) {
                Insert insert = insertInto(T_ORDER_PRODUCT_TABLE).values(
                        T_ORDER_PRODUCT_TABLE.ORDER_PRODUCT_ID.value(t.getOrderProductId()),
                        T_ORDER_PRODUCT_TABLE.ORDER_ID.value(t.getOrderId()),
                        T_ORDER_PRODUCT_TABLE.PRO_ID.value(t.getProId())

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
    public int edit(TOrderProduct tOrderProduct) {
        if (tOrderProduct == null || StringUtils.isBlank(tOrderProduct.getOrderProductId())) {
            return 0;
        }
        return getDslTemplate().update(tOrderProduct, new UpdateGenerateCallback<TOrderProduct>() {
            public Update generate(TOrderProduct t) {
                Update update = update(T_ORDER_PRODUCT_TABLE)
                        .set(T_ORDER_PRODUCT_TABLE.ORDER_ID.value(t.getOrderId()),
                                T_ORDER_PRODUCT_TABLE.PRO_ID.value(t.getProId()))
                        .where(T_ORDER_PRODUCT_TABLE.ORDER_PRODUCT_ID.eq(t.getOrderProductId()));
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
                return delete(T_ORDER_PRODUCT_TABLE).where(T_ORDER_PRODUCT_TABLE.ORDER_PRODUCT_ID.eq(pk));
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
                return delete(T_ORDER_PRODUCT_TABLE).where(T_ORDER_PRODUCT_TABLE.ORDER_PRODUCT_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TOrderProduct getByKey(String pk) {
        return getDslTemplate().getByKey(pk, TOrderProduct.class, new SelectGenerateCallback<Serializable>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(T_ORDER_PRODUCT_TABLE).where(T_ORDER_PRODUCT_TABLE.ORDER_PRODUCT_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public List<TOrderProduct> query(TOrderProduct tOrderProduct, final OrderBy... orderArgs) {
        if (tOrderProduct == null) {
            tOrderProduct = new TOrderProduct();
        }
        return getDslTemplate().query(tOrderProduct, new SelectGenerateCallback<TOrderProduct>() {
            @SuppressWarnings("rawtypes")
            public Select generate(TOrderProduct t) {
                Select select = selectFrom(T_ORDER_PRODUCT_TABLE).where(and(
                        T_ORDER_PRODUCT_TABLE.ORDER_PRODUCT_ID.eq(t.getOrderProductId()),
                        T_ORDER_PRODUCT_TABLE.ORDER_ID.eq(t.getOrderId()), T_ORDER_PRODUCT_TABLE.PRO_ID.eq(t.getProId())

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
    public Pager<TOrderProduct> queryPager(int start, int limit, TOrderProduct tOrderProduct,
                                           final OrderBy... orderArgs) {
        if (tOrderProduct == null) {
            tOrderProduct = new TOrderProduct();
        }
        return getDslTemplate().queryPager(start, limit, tOrderProduct, false,
                new SelectGenerateCallback<TOrderProduct>() {
                    public Select generate(TOrderProduct t) {
                        Select select = Select.selectFrom(T_ORDER_PRODUCT_TABLE)
                                .where(and(T_ORDER_PRODUCT_TABLE.ORDER_PRODUCT_ID.eq(t.getOrderProductId()),
                                        T_ORDER_PRODUCT_TABLE.ORDER_ID.eq(t.getOrderId()),
                                        T_ORDER_PRODUCT_TABLE.PRO_ID.eq(t.getProId())

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
    public int[] batchInsert(boolean autoGeneratedKeys, List<TOrderProduct> tOrderProduct) {
        if (CollectionUtil.isEmpty(tOrderProduct)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tOrderProduct,
                new InsertGenerateCallback<TOrderProduct>() {

                    public Insert generate(TOrderProduct t) {
                        return insertInto(T_ORDER_PRODUCT_TABLE).values(
                                T_ORDER_PRODUCT_TABLE.ORDER_PRODUCT_ID.value(t.getOrderProductId()),
                                T_ORDER_PRODUCT_TABLE.ORDER_ID.value(t.getOrderId()),
                                T_ORDER_PRODUCT_TABLE.PRO_ID.value(t.getProId())

                        );
                    }
                });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchInsert(List<TOrderProduct> tOrderProducts) {
        return batchInsert(false, tOrderProducts);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchUpdate(List<TOrderProduct> tOrderProducts) {
        if (CollectionUtil.isEmpty(tOrderProducts)) {
            return new int[0];
        }
        for (TOrderProduct tOrderProduct : tOrderProducts) {
            if (StringUtils.isBlank(tOrderProduct.getOrderProductId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tOrderProducts, new UpdateGenerateCallback<TOrderProduct>() {
            public Update generate(TOrderProduct t) {
                return update(T_ORDER_PRODUCT_TABLE).set(T_ORDER_PRODUCT_TABLE.ORDER_ID.value(t.getOrderId()),
                        T_ORDER_PRODUCT_TABLE.PRO_ID.value(t.getProId())

                ).where(T_ORDER_PRODUCT_TABLE.ORDER_PRODUCT_ID.eq(t.getOrderProductId()));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchDelete(List<TOrderProduct> tOrderProducts) {
        if (CollectionUtil.isEmpty(tOrderProducts)) {
            return new int[0];
        }
        for (TOrderProduct tOrderProduct : tOrderProducts) {
            if (

                    StringUtils.isBlank(tOrderProduct.getOrderProductId()) &&

                            StringUtils.isBlank(tOrderProduct.getOrderId()) &&

                            StringUtils.isBlank(tOrderProduct.getProId())

            ) {
                return new int[0];
            }
        }
        return getDslTemplate().batchDelete(tOrderProducts, new DeleteGenerateCallback<TOrderProduct>() {
            public Delete generate(TOrderProduct t) {
                return delete(T_ORDER_PRODUCT_TABLE).where(and(
                        T_ORDER_PRODUCT_TABLE.ORDER_PRODUCT_ID.eq(t.getOrderProductId()),
                        T_ORDER_PRODUCT_TABLE.ORDER_ID.eq(t.getOrderId()), T_ORDER_PRODUCT_TABLE.PRO_ID.eq(t.getProId())

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(boolean autoGeneratedKeys, List<TOrderProduct> tOrderProduct) {
        if (CollectionUtil.isEmpty(tOrderProduct)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tOrderProduct, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(T_ORDER_PRODUCT_TABLE).values(
                        T_ORDER_PRODUCT_TABLE.ORDER_ID.value(new JdbcNamedParameter("orderId")),
                        T_ORDER_PRODUCT_TABLE.PRO_ID.value(new JdbcNamedParameter("proId"))

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchUpdate(List<TOrderProduct> tOrderProducts) {
        if (CollectionUtil.isEmpty(tOrderProducts)) {
            return new int[0];
        }

        for (TOrderProduct tOrderProduct : tOrderProducts) {
            if (StringUtils.isBlank(tOrderProduct.getOrderProductId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tOrderProducts, new NoParamUpdateGenerateCallback() {
            public Update generate() {
                return update(T_ORDER_PRODUCT_TABLE)
                        .set(T_ORDER_PRODUCT_TABLE.ORDER_ID.value(new JdbcNamedParameter("orderId")),
                                T_ORDER_PRODUCT_TABLE.PRO_ID.value(new JdbcNamedParameter("proId"))

                        ).where(T_ORDER_PRODUCT_TABLE.ORDER_PRODUCT_ID.eq(new JdbcNamedParameter("orderProductId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchDelete(List<TOrderProduct> tOrderProducts) {
        if (CollectionUtil.isEmpty(tOrderProducts)) {
            return new int[0];
        }

        for (TOrderProduct tOrderProduct : tOrderProducts) {
            if (

                    StringUtils.isBlank(tOrderProduct.getOrderProductId()) &&

                            StringUtils.isBlank(tOrderProduct.getOrderId()) &&

                            StringUtils.isBlank(tOrderProduct.getProId())

            ) {
                return new int[0];
            }
        }

        return getDslTemplate().batchDelete(tOrderProducts, new NoParamDeleteGenerateCallback() {
            public Delete generate() {
                return delete(T_ORDER_PRODUCT_TABLE)
                        .where(and(T_ORDER_PRODUCT_TABLE.ORDER_ID.eq(new JdbcNamedParameter("orderId")),
                                T_ORDER_PRODUCT_TABLE.PRO_ID.eq(new JdbcNamedParameter("proId"))

                        ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(List<TOrderProduct> tOrderProduct) {
        return preparedBatchInsert(false, tOrderProduct);
    }

}
