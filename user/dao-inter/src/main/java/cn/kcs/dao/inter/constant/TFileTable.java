
package cn.kcs.dao.inter.constant;

import cn.kcs.dao.inter.pojo.TFile;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc --> T_FILE * <!-- end-user-doc -->
 */
public class TFileTable extends Table {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public static final TFileTable T_FILE_TABLE = new TFileTable();

    /**
     * <!-- begin-user-doc --> 文件id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column FILE_ID = new Column(this, "file_id");
    /**
     * <!-- begin-user-doc --> 文件名 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column FILE_NAME = new Column(this, "file_name");
    /**
     * <!-- begin-user-doc --> 文件路径 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column FILE_PATH = new Column(this, "file_path");
    /**
     * <!-- begin-user-doc --> 文件描述 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column FILE_DESCRIPTION = new Column(this, "file_description");
    /**
     * <!-- begin-user-doc --> 上传时间 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column FILE_TIME = new Column(this, "file_time");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TFileTable() {
        super("t_file");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TFileTable(String schemaName) {
        super(schemaName, "t_file");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TFileTable(String schemaName, String alias) {
        super(schemaName, "t_file", alias);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TFileTable(String schemaName, String alias, boolean withAs) {
        super(schemaName, "t_file", alias, withAs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Class<TFile> getPojoType() {
        return TFile.class;
    }

}
