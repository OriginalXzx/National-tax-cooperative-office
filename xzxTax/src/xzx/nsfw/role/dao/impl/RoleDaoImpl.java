package xzx.nsfw.role.dao.impl;

import org.hibernate.Query;

import xzx.core.dao.impl.BaseDaoImpl;
import xzx.nsfw.role.dao.RoleDao;
import xzx.nsfw.role.entity.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public void deletePrivilegeByRoleId(String roleId) {
		Query query = getSession().createQuery("DELETE FROM RolePrivilege WHERE id.role.roleId=?");//"id.role.roleId是联合主键的写法"
        query.setParameter(0, roleId);
        query.executeUpdate();
	}

}
