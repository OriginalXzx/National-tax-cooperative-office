package xzx.login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import xzx.core.Constant;
import xzx.nsfw.user.entity.User;
import xzx.nsfw.user.service.UserService;

public class LoginAction extends ActionSupport {
    @Resource
	private UserService userService;
    
    private User user;
    private String loginResult;
	
	public String toLogin(){
      return "loginUI";
    }
	
	//登录
	public String login(){
		if(user!=null){
			if(StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNotBlank(user.getPassword())){
				//根据用户账号和密码查询用户列表
				List<User> list = userService.findUserByAccountAndPass(user.getAccount(),user.getPassword());
			    if(list!=null&&list.size()>0){
			    	//登录成功
			    	User user = list.get(0);
			    	//1.根据ID查询该用户所有角色
			    	user.setUserRoles(userService.getUserRolesByUserId(user.getId()));
			    	//2.将用户信息保存到session中
			    	ServletActionContext.getRequest().getSession().setAttribute(Constant.USER,user);
			    	//3.记录到日志文件中
			    	Log log2 = LogFactory.getLog(getClass());
			    	log2.info("用户名称为：" + user.getName()+"的用户登录了系统！");
			    	return "home";
			    }else{
			    	loginResult = "账号或者密码错误！";
			    }
			}else{
				loginResult = "账号或者密码不能为空！";
			}
		}else{
			loginResult = "请输入账号和密码！";
		}
		return toLogin();
	}
	
	public String logout(){
		
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
		return toLogin();
	}
    
	public String toNoPermissionUI(){
		return "noPermissionUI";
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	
	
}
