package xzx.nsfw.user.service.impl;


import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import xzx.core.service.impl.BaseServiceImpl;
import xzx.core.util.ExcelUtils;
import xzx.nsfw.role.entity.Role;
import xzx.nsfw.user.dao.UserDao;
import xzx.nsfw.user.entity.User;
import xzx.nsfw.user.entity.UserRole;
import xzx.nsfw.user.entity.UserRoleId;
import xzx.nsfw.user.service.UserService;


@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    
	
	private UserDao userDao;
	@Resource
	public void setUserDao(UserDao userDao){
		super.setBaseDao(userDao);
		this.userDao = userDao;
		
	}
	
    /**
     * 导出用户列表
     */
	@Override
	public void exportExcel(List<User> userList, ServletOutputStream outputStream) {
		ExcelUtils.exportExcel(userList, outputStream);
	}

	
	@Override
	public void importExcel(File userExcel, String userExcelName) {
		try {
			FileInputStream fileInputStream = new FileInputStream(userExcel);
			Boolean is03Excel = userExcelName.matches("^.+\\.(?i)(xls)$");
			//1.读取工作薄
			Workbook workbook = is03Excel?new HSSFWorkbook(fileInputStream):new XSSFWorkbook(fileInputStream);
			//2、读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			
			//3.读取行
			if(sheet.getPhysicalNumberOfRows()>2){
				User user = null;
				for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
					//读取单元格
					Row row = sheet.getRow(i);
				     //用户名
					user = new User();
					Cell cell0 = row.getCell(0);
					user.setName(cell0.getStringCellValue());
					Cell cell1 = row.getCell(1);
					user.setAccount(cell1.getStringCellValue());
					Cell cell2 = row.getCell(2);
					user.setDept(cell2.getStringCellValue());
					Cell cell3 = row.getCell(3);
					user.setGender(cell3.getStringCellValue().equals("男"));
					Cell cell4 = row.getCell(4);
					user.setEmail(cell4.getStringCellValue());
					//手机号
					String mobile="";
					Cell cell5 = row.getCell(5);
					
					try {
						mobile = cell5.getStringCellValue();
					} catch (Exception e) {
						double dmobile = cell5.getNumericCellValue();
					    mobile = BigDecimal.valueOf(dmobile).toString();
					}
					user.setMobile(mobile);
					
					Cell cell6 = row.getCell(6);
					//System.out.println("------"+cell6.getDateCellValue());
					/*if(cell6.getDateCellValue()!=null){
						user.setBirthday(cell6.getDateCellValue());
					}*/
					user.setPassword("123456");
					user.setState(User.USER_STATE_INVALID);
					//5.保存用户
					save(user);
				}
				
			}
			fileInputStream.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	@Override
	public List<User> findByAccountAndId(String id, String account) {
		return userDao.findByAccountAndId(id,account);
	}

	@Override
	public void saveUserAndRole(User user, String... roleIds) {
		//1.保存用户
		save(user);
		//2.保存用户的角色
		if(roleIds != null){
			for(String roleId:roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
	}

	@Override
	public void updateUserAndRole(User user, String... roleIds) {
		//1.根基用户删除该用户之前的角色
		userDao.deleteUserRoleByUserId(user.getId());
		//2.更新用户
		update(user);
		//3.保存用户对应的角色
		if(roleIds != null){
			for(String roleId:roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
	}

	@Override
	public List<UserRole> getUserRolesByUserId(String id) {
		return userDao.getUserRolesByUserId(id);
		
	}

	@Override
	public List<User> findUserByAccountAndPass(String account, String password) {
		return userDao. findUserByAccountAndPass(account,password);
	}
}
