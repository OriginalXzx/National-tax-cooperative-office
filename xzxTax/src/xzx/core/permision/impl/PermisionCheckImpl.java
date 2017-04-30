package xzx.core.permision.impl;

import java.util.List;

import javax.annotation.Resource;

import xzx.core.permision.PermisionCheck;
import xzx.nsfw.role.entity.Role;
import xzx.nsfw.role.entity.RolePrivilege;
import xzx.nsfw.user.entity.User;
import xzx.nsfw.user.entity.UserRole;
import xzx.nsfw.user.service.UserService;

public class PermisionCheckImpl implements PermisionCheck {
    
	@Resource
	private UserService userService;
	@Override
	public boolean isAccessible(User user, String code) {
		//1.获取用户所有角色
		List<UserRole> list = user.getUserRoles();
		//2.判断用户中是否具有特定权限
		//2、根据每个角色对于的所有权限进行对比
		if(list != null && list.size()>0){
			for(UserRole ur: list){
				Role role = ur.getId().getRole();
				for(RolePrivilege rp: role.getRolePrivilege()){
					//对比是否有code对应的权限
					if(code.equals(rp.getId().getCode())){
						//说明有权限，返回true
						return true;
					}
				}
			}
		}
		return false;
	}

}
