package xzx.nsfw.role.service.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import xzx.core.service.impl.BaseServiceImpl;
import xzx.nsfw.role.service.RoleService;
import xzx.nsfw.role.dao.RoleDao;
import xzx.nsfw.role.entity.Role;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	
	private RoleDao roleDao;
	@Resource
	public void setRoleDao(RoleDao roleDao){
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
		
	}

	@Override
	public void update(Role role) {
        roleDao.deletePrivilegeByRoleId(role.getRoleId());
		roleDao.update(role);
	}
}
