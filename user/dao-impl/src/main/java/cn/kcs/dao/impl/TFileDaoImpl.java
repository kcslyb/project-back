
package cn.kcs.dao.impl;

import cn.kcs.dao.inter.TFileDao;
import cn.kcs.dao.inter.UUIDutil.StringIdGenerator;
import cn.kcs.dao.inter.pojo.TFile;
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

import static cn.kcs.dao.inter.constant.TFileTable.T_FILE_TABLE;
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
public class TFileDaoImpl extends TinyDslDaoSupport implements TFileDao {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TFile add(TFile tFile) {
        tFile.setFileId(new StringIdGenerator().generate(new InsertContext()));
        return getDslTemplate().insertAndReturnKey(tFile, new InsertGenerateCallback<TFile>() {
            public Insert generate(TFile t) {
                Insert insert = insertInto(T_FILE_TABLE).values(T_FILE_TABLE.FILE_ID.value(t.getFileId()),
                        T_FILE_TABLE.FILE_NAME.value(t.getFileName()), T_FILE_TABLE.FILE_PATH.value(t.getFilePath()),
                        T_FILE_TABLE.FILE_DESCRIPTION.value(t.getFileDescription()),
                        T_FILE_TABLE.FILE_TIME.value(t.getFileTime())

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
    public int edit(TFile tFile) {
        if (tFile == null || StringUtils.isBlank(tFile.getFileId())) {
            return 0;
        }
        return getDslTemplate().update(tFile, new UpdateGenerateCallback<TFile>() {
            public Update generate(TFile t) {
                Update update = update(T_FILE_TABLE)
                        .set(T_FILE_TABLE.FILE_NAME.value(t.getFileName()),
                                T_FILE_TABLE.FILE_PATH.value(t.getFilePath()),
                                T_FILE_TABLE.FILE_DESCRIPTION.value(t.getFileDescription()),
                                T_FILE_TABLE.FILE_TIME.value(t.getFileTime()))
                        .where(T_FILE_TABLE.FILE_ID.eq(t.getFileId()));
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
                return delete(T_FILE_TABLE).where(T_FILE_TABLE.FILE_ID.eq(pk));
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
                return delete(T_FILE_TABLE).where(T_FILE_TABLE.FILE_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TFile getByKey(String pk) {
        return getDslTemplate().getByKey(pk, TFile.class, new SelectGenerateCallback<Serializable>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(T_FILE_TABLE).where(T_FILE_TABLE.FILE_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public List<TFile> query(TFile tFile, final OrderBy... orderArgs) {
        if (tFile == null) {
            tFile = new TFile();
        }
        return getDslTemplate().query(tFile, new SelectGenerateCallback<TFile>() {
            @SuppressWarnings("rawtypes")
            public Select generate(TFile t) {
                Select select = selectFrom(T_FILE_TABLE).where(and(T_FILE_TABLE.FILE_ID.eq(t.getFileId()),
                        T_FILE_TABLE.FILE_NAME.eq(t.getFileName()), T_FILE_TABLE.FILE_PATH.eq(t.getFilePath()),
                        T_FILE_TABLE.FILE_DESCRIPTION.eq(t.getFileDescription()),
                        T_FILE_TABLE.FILE_TIME.eq(t.getFileTime())

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
    public Pager<TFile> queryPager(int start, int limit, TFile tFile, final OrderBy... orderArgs) {
        if (tFile == null) {
            tFile = new TFile();
        }
        return getDslTemplate().queryPager(start, limit, tFile, false, new SelectGenerateCallback<TFile>() {
            public Select generate(TFile t) {
                Select select = Select.selectFrom(T_FILE_TABLE)
                        .where(and(T_FILE_TABLE.FILE_ID.eq(t.getFileId()), T_FILE_TABLE.FILE_NAME.eq(t.getFileName()),
                                T_FILE_TABLE.FILE_PATH.eq(t.getFilePath()),
                                T_FILE_TABLE.FILE_DESCRIPTION.eq(t.getFileDescription()),
                                T_FILE_TABLE.FILE_TIME.eq(t.getFileTime())

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
    public int[] batchInsert(boolean autoGeneratedKeys, List<TFile> tFile) {
        if (CollectionUtil.isEmpty(tFile)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tFile, new InsertGenerateCallback<TFile>() {

            public Insert generate(TFile t) {
                return insertInto(T_FILE_TABLE).values(T_FILE_TABLE.FILE_ID.value(t.getFileId()),
                        T_FILE_TABLE.FILE_NAME.value(t.getFileName()), T_FILE_TABLE.FILE_PATH.value(t.getFilePath()),
                        T_FILE_TABLE.FILE_DESCRIPTION.value(t.getFileDescription()),
                        T_FILE_TABLE.FILE_TIME.value(t.getFileTime())

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchInsert(List<TFile> tFiles) {
        return batchInsert(false, tFiles);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchUpdate(List<TFile> tFiles) {
        if (CollectionUtil.isEmpty(tFiles)) {
            return new int[0];
        }
        for (TFile tFile : tFiles) {
            if (StringUtils.isBlank(tFile.getFileId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tFiles, new UpdateGenerateCallback<TFile>() {
            public Update generate(TFile t) {
                return update(T_FILE_TABLE).set(T_FILE_TABLE.FILE_NAME.value(t.getFileName()),
                        T_FILE_TABLE.FILE_PATH.value(t.getFilePath()),
                        T_FILE_TABLE.FILE_DESCRIPTION.value(t.getFileDescription()),
                        T_FILE_TABLE.FILE_TIME.value(t.getFileTime())

                ).where(T_FILE_TABLE.FILE_ID.eq(t.getFileId()));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] batchDelete(List<TFile> tFiles) {
        if (CollectionUtil.isEmpty(tFiles)) {
            return new int[0];
        }
        for (TFile tFile : tFiles) {
            if (

                    StringUtils.isBlank(tFile.getFileId()) &&

                            StringUtils.isBlank(tFile.getFileName()) &&

                            StringUtils.isBlank(tFile.getFilePath()) &&

                            StringUtils.isBlank(tFile.getFileDescription()) &&

                            StringUtils.isBlank(String.valueOf(tFile.getFileTime()))

            ) {
                return new int[0];
            }
        }
        return getDslTemplate().batchDelete(tFiles, new DeleteGenerateCallback<TFile>() {
            public Delete generate(TFile t) {
                return delete(T_FILE_TABLE).where(and(T_FILE_TABLE.FILE_ID.eq(t.getFileId()),
                        T_FILE_TABLE.FILE_NAME.eq(t.getFileName()), T_FILE_TABLE.FILE_PATH.eq(t.getFilePath()),
                        T_FILE_TABLE.FILE_DESCRIPTION.eq(t.getFileDescription()),
                        T_FILE_TABLE.FILE_TIME.eq(t.getFileTime())

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(boolean autoGeneratedKeys, List<TFile> tFile) {
        if (CollectionUtil.isEmpty(tFile)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, tFile, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(T_FILE_TABLE).values(T_FILE_TABLE.FILE_NAME.value(new JdbcNamedParameter("fileName")),
                        T_FILE_TABLE.FILE_PATH.value(new JdbcNamedParameter("filePath")),
                        T_FILE_TABLE.FILE_DESCRIPTION.value(new JdbcNamedParameter("fileDescription")),
                        T_FILE_TABLE.FILE_TIME.value(new JdbcNamedParameter("fileTime"))

                );
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchUpdate(List<TFile> tFiles) {
        if (CollectionUtil.isEmpty(tFiles)) {
            return new int[0];
        }

        for (TFile tFile : tFiles) {
            if (StringUtils.isBlank(tFile.getFileId())) {
                return new int[0];
            }
        }
        return getDslTemplate().batchUpdate(tFiles, new NoParamUpdateGenerateCallback() {
            public Update generate() {
                return update(T_FILE_TABLE).set(T_FILE_TABLE.FILE_NAME.value(new JdbcNamedParameter("fileName")),
                        T_FILE_TABLE.FILE_PATH.value(new JdbcNamedParameter("filePath")),
                        T_FILE_TABLE.FILE_DESCRIPTION.value(new JdbcNamedParameter("fileDescription")),
                        T_FILE_TABLE.FILE_TIME.value(new JdbcNamedParameter("fileTime"))

                ).where(T_FILE_TABLE.FILE_ID.eq(new JdbcNamedParameter("fileId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchDelete(List<TFile> tFiles) {
        if (CollectionUtil.isEmpty(tFiles)) {
            return new int[0];
        }

        for (TFile tFile : tFiles) {
            if (

                    StringUtils.isBlank(tFile.getFileId()) &&

                            StringUtils.isBlank(tFile.getFileName()) &&

                            StringUtils.isBlank(tFile.getFilePath()) &&

                            StringUtils.isBlank(tFile.getFileDescription()) &&

                            StringUtils.isBlank(String.valueOf(tFile.getFileTime()))

            ) {
                return new int[0];
            }
        }

        return getDslTemplate().batchDelete(tFiles, new NoParamDeleteGenerateCallback() {
            public Delete generate() {
                return delete(T_FILE_TABLE).where(and(T_FILE_TABLE.FILE_NAME.eq(new JdbcNamedParameter("fileName")),
                        T_FILE_TABLE.FILE_PATH.eq(new JdbcNamedParameter("filePath")),
                        T_FILE_TABLE.FILE_DESCRIPTION.eq(new JdbcNamedParameter("fileDescription")),
                        T_FILE_TABLE.FILE_TIME.eq(new JdbcNamedParameter("fileTime"))

                ));
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public int[] preparedBatchInsert(List<TFile> tFile) {
        return preparedBatchInsert(false, tFile);
    }

}
