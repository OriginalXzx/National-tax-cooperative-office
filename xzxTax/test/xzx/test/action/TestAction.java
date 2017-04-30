package xzx.test.action;
import javax.annotation.Resource;

/**
 * 测试Struts与Spring
 */
import com.opensymphony.xwork2.ActionSupport;

import xzx.test.service.TestService;

public class TestAction extends ActionSupport {
     @Resource  
	private TestService testService;
     
     @Override
    public String execute() throws Exception {
    	 testService.say();
    	 return SUCCESS;
    }
}
