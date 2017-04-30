package xzx.nsfw.user.dao;

import java.util.List;

import xzx.core.dao.BaseDao;
import xzx.nsfw.user.entity.User;
import xzx.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {

	List<User> findByAccountAndId(String id, String account);

	public void saveUserRole(UserRole userRole);
	

	public void deleteUserRoleByUserId(String id);

	public List<UserRole> getUserRolesByUserId(String id);

	public List<User> findUserByAccountAndPass(String account, String password);


}
