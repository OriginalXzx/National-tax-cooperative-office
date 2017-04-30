package xzx.nsfw.role.dao;

import xzx.core.dao.BaseDao;
import xzx.nsfw.role.entity.Role;

public interface RoleDao extends BaseDao<Role>{
    //删除该角色之前的权限
	public void deletePrivilegeByRoleId(String roleId);

}
