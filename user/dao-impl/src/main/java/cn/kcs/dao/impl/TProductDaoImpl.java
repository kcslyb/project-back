
package cn.kcs.dao.impl;

import cn.kcs.dao.inter.TProductDao;
import cn.kcs.dao.inter.UUIDutil.StringIdGenerator;
import cn.kcs.dao.inter.pojo.TProduct;
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

import static cn.kcs.dao.inter.constant.TProductTable.T_PRODUCT_TABLE;
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
public class TProductDaoImpl extends TinyDslDaoSupport implements TProductDao {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TProduct add(TProduct tProduct) {
        tProduct.setProId(new StringIdGenerator().generate(new InsertContext()));
        return getDslTemplate().insertAndReturnKey(tProduct, new InsertGenerateCallback<TProduct>() {
            public Insert generate(TProduct t) {
                Insert insert = insertInto(T_PRODUCT_TABLE).values(T_PRODUCT_TABLE.PRO_ID.value(t.getProId()),
                        T_PRODUCT_TABLE.PRO_LABLE.value(t.getProLable()),
                        T_PRODUCT_TABLE.PRO_PRISE.value(t.getProPrise()),
                        T_PRODUCT_TABLE.PRO_PATH.value(t.getProPath()), T_PRODUCT_TABLE.PRO_SRC.value(t.getProSrc()),
                        T_PRODUCT_TABLE.PRO_TYPE.value(t.getProType()),
                        T_PRODUCT_TABLE.PRO_CREATTIME.value(t.getProCreattime()),
                        T_PRODUCT_TABLE.PRO_MODIFYTIME.value(t.getProModifytime())

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
    public int edit(TProduct tProduct) {
        if (tProduct == null || StringUtils.isBlank(tProduct.getProId())) {
            return 0;
        }
        return getDslTemplate().update(tProduct, new UpdateGenerateCallback<TProduct>() {
            public Update generate(TProduct t) {
                Update update = update(T_PRODUCT_TABLE).set(T_PRODUCT_TABLE.PRO_LABLE.value(t.getProLable()),
                        T_PRODUCT_TABLE.PRO_PRISE.value(t.getProPrise()),
                        T_PRODUCT_TABLE.PRO_PATH.value(t.getProPath()), T_PRODUCT_TABLE.PRO_SRC.value(t.getProSrc()),
                        T_PRODUCT_TABLE.PRO_TYPE.value(t.getProType()),
                        T_PRODUCT_TABLE.PRO_CREATTIME.value(t.getProCreattime()),
                        T_PRODUCT_TABLE.PRO_MODIFYTIME.value(t.getProModifytime()))
                        .where(T_PRODUCT_TABLE.PRO_ID.eq(t.getProId()));
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
                return delete(T_PRODUCT_TABLE).where(T_PRODUCT_TABLE.PRO_ID.eq(pk));
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
                return delete(T_PRODUCT_TABLE).where(T_PRODUCT_TABLE.PRO_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TProduct getByKey(String pk) {
        return getDslTemplate().getByKey(pk, TProduct.class, new SelectGenerateCallback<Serializable>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(T_PRODUCT_TABLE).where(T_PRODUCT_TABLE.PRO_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public List<TProduct> query(TProduct tProduct, final OrderBy... orderArgs) {
        if (tProduct == null) {
            tProduct = new TProduct();
        }
        return getDslTemplate().query(tProduct, new SelectGenerateCallback<TProduct>() {
            @SuppressWarnings("rawtypes")
            public Select generate(TProduct t) {
                Select select = selectFrom(T_PRODUCT_TABLE).where(and(T_PRODUCT_TABLE.PRO_ID.eq(t.getProId()),
                        T_PRODUCT_TABLE.PRO_LABLE.eq(t.getProLable()), T_PRODUCT_TABLE.PRO_PRISE.eq(t.getProPrise()),
                        T_PRODUCT_TABLE.PRO_PATH.eq(t.getProPath()), T_PRODUCT_TABLE.PRO_SRC.eq(t.getProSrc()),
                        T_PRODUCT_TABLE.PRO_TYPE.eq(t.getProType()),
                        T_PRODUCT_TABLE.PRO_CREATTIME.eq(t.getProCreattime()),
                        T_PRODUCT_TABLE.PRO_MODIFYTIME.eq(t.getProModifytime())

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
    public Pager<TProduct> queryPager(int start, int limit, TProduct tProduct, final OrderBy... orderArgs) {
        if (tProduct == null) {
            tProduct = new TProduct();
        }
        return getDslTemplate().queryPager(start, limit, tProduct, false, new SelectGenerateCallback<TProduct>() {
            public Select generate(TProduct t) {
                Select select = Select.selectFrom(T_PRODUCT_TABLE).where(and(T_PRODUCT_TABLE.PRO_ID.eq(t.getProId()),
                        T_PRODUCT_TABLE.PRO_LABLE.eq(t.getProLable()), T_PRODUCT_TABLE.PRO_PRISE.eq(t.getProPrise()),
                        T_PRODUCT_TABLE.PRO_PATH.eq(t.getProPath()), T_PRODUCT_TABLE.PRO_SRC.eq(t.getProSrc()),
                        T_PRODUCT_TABLE.PRO_TYPE.eq(t.getProType()),
                        T_PRODUCT_TABLE.PRO_CREATTIME.eq(t.getProCreattime()),
                        T_PRODUCT_TABLE.PRO_MODIFYTIME.eq(t.getProModifytime())

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
    public int[] batchInsert(boolean autoGeneratedKeys, List<TProduct> tProduct) {
        if (CollectionUtil.isEmpty(tProduct)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tProduct, new InsertGenerateCallback<TProduct>() {

            public Insert generate(TProduct t) {
                return insertInto(T_PRODUCT_TABLE).values(T_PRODUCT_TABLE.PRO_ID.value(t.getProId()),
                        T_PRODUCT_TABLE.PRO_LABLE.value(t.getProLable()),
                        T_PRODUCT_TABLE.PRO_PRISE.value(t.getProPrise()),
                        T_PRODUCT_TABLE.PRO_PATH.value(t.getProPath()), T_PRODUCT_TABLE.PRO_SRC.value(t.getProSrc()),
                        T_PRODUCT_TABLE.PRO_TYPE.value(t.getProType()),
                        T_PRODUCT_TABLE.PRO_CREATTIME.value(t.getProCreattime()),
                        T_PRODUCT_TABLE.PRO_MODIFYTIME.value(t.getProModifytime())

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchInsert(List<TProduct> tProducts) {
        return batchInsert(false, tProducts);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchUpdate(List<TProduct> tProducts) {
        if (CollectionUtil.isEmpty(tProducts)) {
            return new int[0];
        }
        for (TProduct tProduct : tProducts) {
            if (StringUtils.isBlank(tProduct.getProId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tProducts, new UpdateGenerateCallback<TProduct>() {
            public Update generate(TProduct t) {
                return update(T_PRODUCT_TABLE).set(T_PRODUCT_TABLE.PRO_LABLE.value(t.getProLable()),
                        T_PRODUCT_TABLE.PRO_PRISE.value(t.getProPrise()),
                        T_PRODUCT_TABLE.PRO_PATH.value(t.getProPath()), T_PRODUCT_TABLE.PRO_SRC.value(t.getProSrc()),
                        T_PRODUCT_TABLE.PRO_TYPE.value(t.getProType()),
                        T_PRODUCT_TABLE.PRO_CREATTIME.value(t.getProCreattime()),
                        T_PRODUCT_TABLE.PRO_MODIFYTIME.value(t.getProModifytime())

                ).where(T_PRODUCT_TABLE.PRO_ID.eq(t.getProId()));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchDelete(List<TProduct> tProducts) {
        if (CollectionUtil.isEmpty(tProducts)) {
            return new int[0];
        }
        for (TProduct tProduct : tProducts) {
            if (

                    StringUtils.isBlank(tProduct.getProId()) &&

                            StringUtils.isBlank(tProduct.getProLable()) &&

                            StringUtils.isBlank(String.valueOf(tProduct.getProPrise())) &&

                            StringUtils.isBlank(tProduct.getProPath()) &&

                            StringUtils.isBlank(tProduct.getProSrc()) &&

                            StringUtils.isBlank(tProduct.getProType()) &&

                            StringUtils.isBlank(String.valueOf(tProduct.getProCreattime())) &&

                            StringUtils.isBlank(String.valueOf(tProduct.getProModifytime()))

            ) {
                return new int[0];
            }
        }
        return getDslTemplate().batchDelete(tProducts, new DeleteGenerateCallback<TProduct>() {
            public Delete generate(TProduct t) {
                return delete(T_PRODUCT_TABLE).where(and(T_PRODUCT_TABLE.PRO_ID.eq(t.getProId()),
                        T_PRODUCT_TABLE.PRO_LABLE.eq(t.getProLable()), T_PRODUCT_TABLE.PRO_PRISE.eq(t.getProPrise()),
                        T_PRODUCT_TABLE.PRO_PATH.eq(t.getProPath()), T_PRODUCT_TABLE.PRO_SRC.eq(t.getProSrc()),
                        T_PRODUCT_TABLE.PRO_TYPE.eq(t.getProType()),
                        T_PRODUCT_TABLE.PRO_CREATTIME.eq(t.getProCreattime()),
                        T_PRODUCT_TABLE.PRO_MODIFYTIME.eq(t.getProModifytime())

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(boolean autoGeneratedKeys, List<TProduct> tProduct) {
        if (CollectionUtil.isEmpty(tProduct)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tProduct, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(T_PRODUCT_TABLE).values(
                        T_PRODUCT_TABLE.PRO_LABLE.value(new JdbcNamedParameter("proLable")),
                        T_PRODUCT_TABLE.PRO_PRISE.value(new JdbcNamedParameter("proPrise")),
                        T_PRODUCT_TABLE.PRO_PATH.value(new JdbcNamedParameter("proPath")),
                        T_PRODUCT_TABLE.PRO_SRC.value(new JdbcNamedParameter("proSrc")),
                        T_PRODUCT_TABLE.PRO_TYPE.value(new JdbcNamedParameter("proType")),
                        T_PRODUCT_TABLE.PRO_CREATTIME.value(new JdbcNamedParameter("proCreattime")),
                        T_PRODUCT_TABLE.PRO_MODIFYTIME.value(new JdbcNamedParameter("proModifytime"))

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchUpdate(List<TProduct> tProducts) {
        if (CollectionUtil.isEmpty(tProducts)) {
            return new int[0];
        }

        for (TProduct tProduct : tProducts) {
            if (StringUtils.isBlank(tProduct.getProId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tProducts, new NoParamUpdateGenerateCallback() {
            public Update generate() {
                return update(T_PRODUCT_TABLE).set(T_PRODUCT_TABLE.PRO_LABLE.value(new JdbcNamedParameter("proLable")),
                        T_PRODUCT_TABLE.PRO_PRISE.value(new JdbcNamedParameter("proPrise")),
                        T_PRODUCT_TABLE.PRO_PATH.value(new JdbcNamedParameter("proPath")),
                        T_PRODUCT_TABLE.PRO_SRC.value(new JdbcNamedParameter("proSrc")),
                        T_PRODUCT_TABLE.PRO_TYPE.value(new JdbcNamedParameter("proType")),
                        T_PRODUCT_TABLE.PRO_CREATTIME.value(new JdbcNamedParameter("proCreattime")),
                        T_PRODUCT_TABLE.PRO_MODIFYTIME.value(new JdbcNamedParameter("proModifytime"))

                ).where(T_PRODUCT_TABLE.PRO_ID.eq(new JdbcNamedParameter("proId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchDelete(List<TProduct> tProducts) {
        if (CollectionUtil.isEmpty(tProducts)) {
            return new int[0];
        }

        for (TProduct tProduct : tProducts) {
            if (

                    StringUtils.isBlank(tProduct.getProId()) &&

                            StringUtils.isBlank(tProduct.getProLable()) &&

                            StringUtils.isBlank(String.valueOf(tProduct.getProPrise())) &&

                            StringUtils.isBlank(tProduct.getProPath()) &&

                            StringUtils.isBlank(tProduct.getProSrc()) &&

                            StringUtils.isBlank(tProduct.getProType()) &&

                            StringUtils.isBlank(String.valueOf(tProduct.getProCreattime())) &&

                            StringUtils.isBlank(String.valueOf(tProduct.getProModifytime()))

            ) {
                return new int[0];
            }
        }

        return getDslTemplate().batchDelete(tProducts, new NoParamDeleteGenerateCallback() {
            public Delete generate() {
                return delete(T_PRODUCT_TABLE)
                        .where(and(T_PRODUCT_TABLE.PRO_LABLE.eq(new JdbcNamedParameter("proLable")),
                                T_PRODUCT_TABLE.PRO_PRISE.eq(new JdbcNamedParameter("proPrise")),
                                T_PRODUCT_TABLE.PRO_PATH.eq(new JdbcNamedParameter("proPath")),
                                T_PRODUCT_TABLE.PRO_SRC.eq(new JdbcNamedParameter("proSrc")),
                                T_PRODUCT_TABLE.PRO_TYPE.eq(new JdbcNamedParameter("proType")),
                                T_PRODUCT_TABLE.PRO_CREATTIME.eq(new JdbcNamedParameter("proCreattime")),
                                T_PRODUCT_TABLE.PRO_MODIFYTIME.eq(new JdbcNamedParameter("proModifytime"))

                        ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(List<TProduct> tProduct) {
        return preparedBatchInsert(false, tProduct);
    }

}
