package xzx.nsfw.user.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import xzx.core.dao.impl.BaseDaoImpl;
import xzx.nsfw.user.dao.UserDao;
import xzx.nsfw.user.entity.User;
import xzx.nsfw.user.entity.UserRole;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findByAccountAndId(String id, String account) {
		String hql = "FROM User WHERE account = ?";
		if (StringUtils.isNotBlank(id)) {
			hql += " AND id != ?";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter(0, account);
		if (StringUtils.isNotBlank(id)) {
			query.setParameter(1, id);
		}
		List<User> list = query.list();
		//System.out.println("list:"+list);
		return list;
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		getHibernateTemplate().save(userRole);
	}

	@Override
	public void deleteUserRoleByUserId(String id) {
		//delete from user_role where user_id = ?
		Query query = getSession().createQuery("DELETE FROM UserRole WHERE id.userId=?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<UserRole> getUserRolesByUserId(String id) {
		Query query = getSession().createQuery(" FROM UserRole WHERE id.userId=?");
		query.setParameter(0, id);
		return query.list();
	}

	@Override
	public List<User> findUserByAccountAndPass(String account, String password) {
		Query query = getSession().createQuery(" FROM User WHERE account=? AND password=?");
		query.setParameter(0, account);
		query.setParameter(1, password);
		return query.list();
	}

}
