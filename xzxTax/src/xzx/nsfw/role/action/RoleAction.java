package xzx.nsfw.role.action;

import java.net.URLDecoder;
import java.util.HashSet;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import xzx.core.Constant;
import xzx.core.action.BaseAction;
import xzx.core.util.QueryHelper;
import xzx.nsfw.role.entity.Role;
import xzx.nsfw.role.entity.RolePrivilege;
import xzx.nsfw.role.entity.RolePrivilegeId;
import xzx.nsfw.role.service.RoleService;

public class RoleAction extends BaseAction {
	@Resource
	private RoleService roleService;
	private Role role;
	private String[] privilegeIds;

	// 列表页面
	public String listUI() throws Exception {
		// 创建一个查询助手对象
		QueryHelper qh = new QueryHelper(Role.class, "r");
		if (role != null) {
			if (StringUtils.isNotBlank(role.getName())) {
				role.setName(URLDecoder.decode(role.getName(), "utf-8"));
				qh.addCondition("r.name like ?", "%" + role.getName() + "%");// 控制查询条件回显，与中文乱码问题
			}
			// 根据创建时间降序排序
		}
		pageResult = roleService.getPageResult(qh, getPageNo(), getPageSize());

		// 加载权限集合
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		// 加载权限集合
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);

		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (role != null) {
				// 处理权限保存
				if (privilegeIds != null) {
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for (int i = 0; i < privilegeIds.length; i++) {
						set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
					}
					role.setRolePrivilege(set);
				}
				roleService.save(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		// 加载权限集合
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		if (role != null && role.getRoleId() != null) {
			// System.out.println("role不为空哦");
			role = roleService.findById(role.getRoleId());
			// 处理权限回显
			if (role.getRolePrivilege() != null) {
				privilegeIds = new String[role.getRolePrivilege().size()];
				int i = 0;
				for (RolePrivilege rp : role.getRolePrivilege()) {
					privilegeIds[i++] = rp.getId().getCode();
				}
			}
		}
		return "editUI";
	}

	// 保存编辑
	public String edit() {
		try {
			if (role != null) {
				// 处理权限保存
				if (privilegeIds != null) {
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for (int i = 0; i < privilegeIds.length; i++) {
						set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
					}
					role.setRolePrivilege(set);
				}
				roleService.update(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (role != null && role.getRoleId() != null) {
			roleService.delete(role.getRoleId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				roleService.delete(id);
			}
		}
		return "list";
	}

	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
}
