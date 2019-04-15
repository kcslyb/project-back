
package cn.kcs.dao.impl;

import cn.kcs.dao.inter.TUserDao;
import cn.kcs.dao.inter.pojo.TUser;
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
import java.util.ArrayList;
import java.util.List;

import static cn.kcs.dao.inter.constant.TUserTable.T_USER_TABLE;
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
public class TUserDaoImpl extends TinyDslDaoSupport implements TUserDao {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public TUser add(TUser tUser) {
		return getDslTemplate().insertAndReturnKey(tUser, new InsertGenerateCallback<TUser>() {
			public Insert generate(TUser t) {
				Insert insert = insertInto(T_USER_TABLE).values(T_USER_TABLE.UUID.value(t.getUuid()),
						T_USER_TABLE.USER_NAME.value(t.getUserName()),
						T_USER_TABLE.USER_PASSWORD.value(t.getUserPassword()),
						T_USER_TABLE.USER_PHONE.value(t.getUserPhone()),
						T_USER_TABLE.USER_EMAIL.value(t.getUserEmail()),
						T_USER_TABLE.USER_STATUS.value(t.getUserStatus()),
						T_USER_TABLE.CREATEDATA.value(t.getCreatedata()),
						T_USER_TABLE.MODIFYDATA.value(t.getModifydata())

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
	public int edit(TUser tUser) {
		if (tUser == null || StringUtils.isBlank(tUser.getUuid())) {
			return 0;
		}
		return getDslTemplate().update(tUser, new UpdateGenerateCallback<TUser>() {
			public Update generate(TUser t) {
				Update update = update(T_USER_TABLE)
						.set(T_USER_TABLE.USER_NAME.value(t.getUserName()),
								T_USER_TABLE.USER_PASSWORD.value(t.getUserPassword()),
								T_USER_TABLE.USER_PHONE.value(t.getUserPhone()),
								T_USER_TABLE.USER_EMAIL.value(t.getUserEmail()),
								T_USER_TABLE.USER_STATUS.value(t.getUserStatus()),
								T_USER_TABLE.CREATEDATA.value(t.getCreatedata()),
								T_USER_TABLE.MODIFYDATA.value(t.getModifydata()))
						.where(T_USER_TABLE.UUID.eq(t.getUuid()));
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
				return delete(T_USER_TABLE).where(T_USER_TABLE.UUID.eq(pk));
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
				return delete(T_USER_TABLE).where(T_USER_TABLE.UUID.in(t));
			}
		}, pks);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public TUser getByKey(String pk) {
		return getDslTemplate().getByKey(pk, TUser.class, new SelectGenerateCallback<Serializable>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Serializable t) {
                return selectFrom(T_USER_TABLE).where(T_USER_TABLE.UUID.eq(t).or(T_USER_TABLE.USER_NAME.eq(t)));
			}
		});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public List<TUser> query(TUser tUser, final OrderBy... orderArgs) {
		if (tUser == null) {
			tUser = new TUser();
		}
		return getDslTemplate().query(tUser, new SelectGenerateCallback<TUser>() {
			@SuppressWarnings("rawtypes")
			public Select generate(TUser t) {
				Select select = selectFrom(T_USER_TABLE).where(and(T_USER_TABLE.UUID.eq(t.getUuid()),
						T_USER_TABLE.USER_NAME.eq(t.getUserName()), T_USER_TABLE.USER_PASSWORD.eq(t.getUserPassword()),
						T_USER_TABLE.USER_PHONE.eq(t.getUserPhone()), T_USER_TABLE.USER_EMAIL.eq(t.getUserEmail()),
						T_USER_TABLE.USER_STATUS.eq(t.getUserStatus()), T_USER_TABLE.CREATEDATA.eq(t.getCreatedata()),
						T_USER_TABLE.MODIFYDATA.eq(t.getModifydata())

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
	public Pager<TUser> queryPager(int start, int limit, TUser tUser, final OrderBy... orderArgs) {
		if (tUser == null) {
			tUser = new TUser();
		}
		return getDslTemplate().queryPager(start, limit, tUser, false, new SelectGenerateCallback<TUser>() {
			public Select generate(TUser t) {
				Select select = Select.selectFrom(T_USER_TABLE).where(and(T_USER_TABLE.UUID.eq(t.getUuid()),
						T_USER_TABLE.USER_NAME.eq(t.getUserName()), T_USER_TABLE.USER_PASSWORD.eq(t.getUserPassword()),
						T_USER_TABLE.USER_PHONE.eq(t.getUserPhone()), T_USER_TABLE.USER_EMAIL.eq(t.getUserEmail()),
						T_USER_TABLE.USER_STATUS.eq(t.getUserStatus()), T_USER_TABLE.CREATEDATA.eq(t.getCreatedata()),
						T_USER_TABLE.MODIFYDATA.eq(t.getModifydata())

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
	public int[] batchInsert(boolean autoGeneratedKeys, List<TUser> tUser) {
		if (CollectionUtil.isEmpty(tUser)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, tUser, new InsertGenerateCallback<TUser>() {

			public Insert generate(TUser t) {
				return insertInto(T_USER_TABLE).values(T_USER_TABLE.UUID.value(t.getUuid()),
						T_USER_TABLE.USER_NAME.value(t.getUserName()),
						T_USER_TABLE.USER_PASSWORD.value(t.getUserPassword()),
						T_USER_TABLE.USER_PHONE.value(t.getUserPhone()),
						T_USER_TABLE.USER_EMAIL.value(t.getUserEmail()),
						T_USER_TABLE.USER_STATUS.value(t.getUserStatus()),
						T_USER_TABLE.CREATEDATA.value(t.getCreatedata()),
						T_USER_TABLE.MODIFYDATA.value(t.getModifydata())

				);
			}
		});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public int[] batchInsert(List<TUser> tUsers) {
		return batchInsert(false, tUsers);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public int[] batchUpdate(List<TUser> tUsers) {
		if (CollectionUtil.isEmpty(tUsers)) {
			return new int[0];
		}
		for (TUser tUser : tUsers) {
			if (StringUtils.isBlank(tUser.getUuid())) {
				return new int[0];
			}
		}
		return getDslTemplate().batchUpdate(tUsers, new UpdateGenerateCallback<TUser>() {
			public Update generate(TUser t) {
				return update(T_USER_TABLE).set(T_USER_TABLE.USER_NAME.value(t.getUserName()),
						T_USER_TABLE.USER_PASSWORD.value(t.getUserPassword()),
						T_USER_TABLE.USER_PHONE.value(t.getUserPhone()),
						T_USER_TABLE.USER_EMAIL.value(t.getUserEmail()),
						T_USER_TABLE.USER_STATUS.value(t.getUserStatus()),
						T_USER_TABLE.CREATEDATA.value(t.getCreatedata()),
						T_USER_TABLE.MODIFYDATA.value(t.getModifydata())

				).where(T_USER_TABLE.UUID.eq(t.getUuid()));
			}
		});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public int[] batchDelete(List<TUser> tUsers) {
		if (CollectionUtil.isEmpty(tUsers)) {
			return new int[0];
		}
		for (TUser tUser : tUsers) {
			if (

			StringUtils.isBlank(tUser.getUuid()) &&

					StringUtils.isBlank(tUser.getUserName()) &&

					StringUtils.isBlank(tUser.getUserPassword()) &&

					StringUtils.isBlank(tUser.getUserPhone()) &&

					StringUtils.isBlank(tUser.getUserEmail()) &&

					StringUtils.isBlank(tUser.getUserStatus()) &&

					StringUtils.isBlank(String.valueOf(tUser.getCreatedata())) &&

					StringUtils.isBlank(String.valueOf(tUser.getModifydata()))

			) {
				return new int[0];
			}
		}
		return getDslTemplate().batchDelete(tUsers, new DeleteGenerateCallback<TUser>() {
			public Delete generate(TUser t) {
				return delete(T_USER_TABLE).where(and(T_USER_TABLE.UUID.eq(t.getUuid()),
						T_USER_TABLE.USER_NAME.eq(t.getUserName()), T_USER_TABLE.USER_PASSWORD.eq(t.getUserPassword()),
						T_USER_TABLE.USER_PHONE.eq(t.getUserPhone()), T_USER_TABLE.USER_EMAIL.eq(t.getUserEmail()),
						T_USER_TABLE.USER_STATUS.eq(t.getUserStatus()), T_USER_TABLE.CREATEDATA.eq(t.getCreatedata()),
						T_USER_TABLE.MODIFYDATA.eq(t.getModifydata())

				));
			}
		});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public int[] preparedBatchInsert(boolean autoGeneratedKeys, List<TUser> tUser) {
		if (CollectionUtil.isEmpty(tUser)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, tUser, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(T_USER_TABLE).values(T_USER_TABLE.USER_NAME.value(new JdbcNamedParameter("userName")),
						T_USER_TABLE.USER_PASSWORD.value(new JdbcNamedParameter("userPassword")),
						T_USER_TABLE.USER_PHONE.value(new JdbcNamedParameter("userPhone")),
						T_USER_TABLE.USER_EMAIL.value(new JdbcNamedParameter("userEmail")),
						T_USER_TABLE.USER_STATUS.value(new JdbcNamedParameter("userStatus")),
						T_USER_TABLE.CREATEDATA.value(new JdbcNamedParameter("createdata")),
						T_USER_TABLE.MODIFYDATA.value(new JdbcNamedParameter("modifydata"))

				);
			}
		});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public int[] preparedBatchUpdate(List<TUser> tUsers) {
		if (CollectionUtil.isEmpty(tUsers)) {
			return new int[0];
		}

		for (TUser tUser : tUsers) {
			if (StringUtils.isBlank(tUser.getUuid())) {
				return new int[0];
			}
		}
		return getDslTemplate().batchUpdate(tUsers, new NoParamUpdateGenerateCallback() {
			public Update generate() {
				return update(T_USER_TABLE).set(T_USER_TABLE.USER_NAME.value(new JdbcNamedParameter("userName")),
						T_USER_TABLE.USER_PASSWORD.value(new JdbcNamedParameter("userPassword")),
						T_USER_TABLE.USER_PHONE.value(new JdbcNamedParameter("userPhone")),
						T_USER_TABLE.USER_EMAIL.value(new JdbcNamedParameter("userEmail")),
						T_USER_TABLE.USER_STATUS.value(new JdbcNamedParameter("userStatus")),
						T_USER_TABLE.CREATEDATA.value(new JdbcNamedParameter("createdata")),
						T_USER_TABLE.MODIFYDATA.value(new JdbcNamedParameter("modifydata"))

				).where(T_USER_TABLE.UUID.eq(new JdbcNamedParameter("uuid")));
			}
		});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public int[] preparedBatchDelete(List<TUser> tUsers) {
		if (CollectionUtil.isEmpty(tUsers)) {
			return new int[0];
		}

		for (TUser tUser : tUsers) {
			if (

			StringUtils.isBlank(tUser.getUuid()) &&

					StringUtils.isBlank(tUser.getUserName()) &&

					StringUtils.isBlank(tUser.getUserPassword()) &&

					StringUtils.isBlank(tUser.getUserPhone()) &&

					StringUtils.isBlank(tUser.getUserEmail()) &&

					StringUtils.isBlank(tUser.getUserStatus()) &&

					StringUtils.isBlank(String.valueOf(tUser.getCreatedata())) &&

					StringUtils.isBlank(String.valueOf(tUser.getModifydata()))

			) {
				return new int[0];
			}
		}

		return getDslTemplate().batchDelete(tUsers, new NoParamDeleteGenerateCallback() {
			public Delete generate() {
				return delete(T_USER_TABLE).where(and(T_USER_TABLE.USER_NAME.eq(new JdbcNamedParameter("userName")),
						T_USER_TABLE.USER_PASSWORD.eq(new JdbcNamedParameter("userPassword")),
						T_USER_TABLE.USER_PHONE.eq(new JdbcNamedParameter("userPhone")),
						T_USER_TABLE.USER_EMAIL.eq(new JdbcNamedParameter("userEmail")),
						T_USER_TABLE.USER_STATUS.eq(new JdbcNamedParameter("userStatus")),
						T_USER_TABLE.CREATEDATA.eq(new JdbcNamedParameter("createdata")),
						T_USER_TABLE.MODIFYDATA.eq(new JdbcNamedParameter("modifydata"))

				));
			}
		});
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @modifiable
	 */
	public int[] preparedBatchInsert(List<TUser> tUser) {
		return preparedBatchInsert(false, tUser);
	}

	@Override
	public List<TUser> getUserByIdAndName(String loginName, String password) {
		TUser tUser = new TUser();
		tUser.setUserName(loginName);
		tUser.setUserPassword(password);
		List<TUser> List = new ArrayList<>();
		return getDslTemplate().query(tUser, new SelectGenerateCallback<TUser>() {
			@SuppressWarnings("rawtypes")
			public Select generate(TUser t) {
				return selectFrom(T_USER_TABLE).where(and(
						T_USER_TABLE.USER_NAME.eq(tUser.getUserName()),
						T_USER_TABLE.USER_PASSWORD.eq(tUser.getUserPassword())
				));
			}
		});
	}
}
