package xzx.project.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xzx.project.base.action.BaseAction;
import xzx.project.entity.Account;
import xzx.project.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountAction extends BaseAction {
	
	@Resource
	private AccountService accountService;
	
	@RequestMapping("/login")
	public String login(Account account,HttpServletRequest request,HttpSession session){
    System.out.println("action.account:"+account);
		try {
			List<Account> accounts=accountService.login(account);
			if(accounts.size()>0){
				System.out.println("table.accout:"+accounts.get(0));
				session.setAttribute("account", accounts.get(0));
				return "forward:/jsp/main.jsp";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("msg", "账号或密码有错，请重新输入！");
		return "forward:/jsp/login.jsp";

	}
	
}
