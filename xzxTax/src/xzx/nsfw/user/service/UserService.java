package xzx.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import xzx.core.exception.ServiceException;
import xzx.core.service.BaseService;
import xzx.nsfw.user.entity.User;
import xzx.nsfw.user.entity.UserRole;

public interface UserService extends BaseService<User>{
	void exportExcel(List<User> userList, ServletOutputStream outputStream);
	void importExcel(File userExcel, String userExcelName);
	List<User> findByAccountAndId(String id, String account);
	void saveUserAndRole(User user, String... roleIds);
	void updateUserAndRole(User user, String... roleIds);
	List<UserRole> getUserRolesByUserId(String id);
	List<User> findUserByAccountAndPass(String account, String password);
}
