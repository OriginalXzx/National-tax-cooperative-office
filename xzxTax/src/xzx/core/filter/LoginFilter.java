package xzx.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import xzx.core.Constant;
import xzx.core.permision.PermisionCheck;
import xzx.nsfw.user.entity.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
	    String uri = request.getRequestURI();
	    if(!uri.contains("sys/login")){
	    	//非登录 请求
	    	if(request.getSession().getAttribute(Constant.USER)!=null){
	    		//说明已经登录过了!
	    		//判断是否访问纳税服务系统
	    		if(uri.contains("/nsfw")){
	    			//需要判断是否有访问纳税服务系统的权限
	    			User user = (User) request.getSession().getAttribute(Constant.USER);
	    			//获取spring Ioc容器
	    			WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	    		    PermisionCheck pc = (PermisionCheck) applicationContext.getBean("permisionCheck");
	    		    if(pc.isAccessible(user, "nsfw")){
	    		    	//说明有权限，放行
	    		    	chain.doFilter(request, response);
	    		    }else{
	    		    	//没有权限，跳转到提示页面！
	    		    	response.sendRedirect(request.getContextPath()+"/sys/login_toNoPermissionUI.action");
	    		    }
	    		}else{
	    		  chain.doFilter(request, response);
	    		}
	    	}else{
	    		//没有登录，跳转到登录页面
	    		response.sendRedirect(request.getContextPath() + "/sys/login_toLogin.action");
	    	}
	    }else{
	    	chain.doFilter(request, response);
	    }
	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
