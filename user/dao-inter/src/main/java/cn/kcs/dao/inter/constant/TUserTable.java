
package cn.kcs.dao.inter.constant;

import cn.kcs.dao.inter.UUIDutil.StringIdGenerator;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

import cn.kcs.dao.inter.pojo.TUser;

/**
 * <!-- begin-user-doc --> 用户表 * <!-- end-user-doc -->
 */
public class TUserTable extends Table {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public static final TUserTable T_USER_TABLE = new TUserTable();

	/**
	 * <!-- begin-user-doc --> UUID * <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public final Column UUID = new Column(this, "uuid");
	/**
	 * <!-- begin-user-doc --> 用户名 * <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public final Column USER_NAME = new Column(this, "user_name");
	/**
	 * <!-- begin-user-doc --> 用户密码 * <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public final Column USER_PASSWORD = new Column(this, "user_password");
	/**
	 * <!-- begin-user-doc --> 用户电话号码 * <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public final Column USER_PHONE = new Column(this, "user_phone");
	/**
	 * <!-- begin-user-doc --> 用户邮箱 * <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public final Column USER_EMAIL = new Column(this, "user_email");
	/**
	 * <!-- begin-user-doc --> 用户状态 * <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public final Column USER_STATUS = new Column(this, "user_status");
	/**
	 * <!-- begin-user-doc --> 创建时间 * <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public final Column CREATEDATA = new Column(this, "createdata");
	/**
	 * <!-- begin-user-doc --> 修改时间 * <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public final Column MODIFYDATA = new Column(this, "modifydata");

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public TUserTable() {
		super("t_user");
		getGeneratorMap().put(UUID.getColumnName(), new StringIdGenerator());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public TUserTable(String schemaName) {
		super(schemaName, "t_user");
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public TUserTable(String schemaName, String alias) {
		super(schemaName, "t_user", alias);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public TUserTable(String schemaName, String alias, boolean withAs) {
		super(schemaName, "t_user", alias, withAs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public Class<TUser> getPojoType() {
		return TUser.class;
	}

}
