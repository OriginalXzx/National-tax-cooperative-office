package xzx.home.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import xzx.core.Constant;
import xzx.core.util.QueryHelper;
import xzx.nsfw.complain.entity.Complain;
import xzx.nsfw.complain.service.ComplainService;
import xzx.nsfw.info.entity.Info;
import xzx.nsfw.info.service.InfoService;
import xzx.nsfw.user.entity.User;
import xzx.nsfw.user.service.UserService;

public class HomeAction extends ActionSupport {
    
	@Resource
	private UserService userService;
	
	@Resource
	private ComplainService complainService;
	
	@Resource
	private InfoService infoService;
	
	private Map<String, Object> return_map;
	
	private Complain comp;
	
	private Info info;
	
	//跳转到首页
	@Override
	public String execute() throws Exception {
		//加载信息列表
		//加载分类集合
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper1 = new QueryHelper(Info.class, "i");
		queryHelper1.addOrderByProperty("i.createTime", QueryHelper.ORDER_BY_DESC);
		List<Info> infoList = infoService.getPageResult(queryHelper1, 1, 5).getItems();
		ActionContext.getContext().getContextMap().put("infoList", infoList);
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.USER);
		//加载投诉信息列表
		//加载状态集合
		ActionContext.getContext().getContextMap().put("complainStateMAp", Complain.COMPLAIN_STATE_MAP);
		QueryHelper queryHelper2 = new QueryHelper(Complain.class, "c");
		queryHelper2.addCondition("c.compName = ?", user.getName());
		//根据投诉时间升序排序
		queryHelper2.addOrderByProperty("c.state", QueryHelper.ORDER_BY_DESC);
		queryHelper2.addOrderByProperty("c.compTime", QueryHelper.ORDER_BY_DESC);
		List<Info> complainList = infoService.getPageResult(queryHelper2, 1, 6).getItems();
		ActionContext.getContext().getContextMap().put("complainList", complainList);
		
		return "home";
	}
	
	public String complainAddUI(){
		return "complainAddUI";
	}
	public void getUserJson(){
		try {
			//1、获取部门
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if(StringUtils.isNotBlank(dept)){
				QueryHelper queryHelper = new QueryHelper(User.class, "u");
				queryHelper.addCondition("u.dept like ?", "%" + dept);
				//2、根据部门查询用户列表
				List<User> userList = userService.findObjects(queryHelper);
				//创建Json对象
				JSONObject jso = new JSONObject();
				jso.put("msg", "success");
				jso.accumulate("userList", userList);
				
				//3、输出用户列表以json格式字符串形式输出
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(jso.toString().getBytes("utf-8"));
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getUserJson2(){
		try {
			//1、获取部门
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if(StringUtils.isNotBlank(dept)){
				QueryHelper queryHelper = new QueryHelper(User.class, "u");
				queryHelper.addCondition("u.dept like ?", "%" +dept);
				//2、根据部门查询用户列表
				return_map = new HashMap<String, Object>();
				return_map.put("msg", "success");
				return_map.put("userList", userService.findObjects(queryHelper));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void complainAdd(){
		try{
			if(comp != null){
				//设置状态为待受理
				comp.setState(Complain.COMPLAIN_STATE_UNDONE);
				comp.setCompTime(new Timestamp(new Date().getTime()));
			    complainService.save(comp);
			    
			    //输出投诉结果
			    HttpServletResponse response = ServletActionContext.getResponse();
			    response.setContentType("text/html");
			    ServletOutputStream outputStream = response.getOutputStream();
			    outputStream.write("success".getBytes("utf-8"));
			    outputStream.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
	//查看信息
	public String  infoViewUI(){
		if(info != null){
			info = infoService.findById(info.getInfoId());
		}
		return "infoViewUI";
	}
	
	public String  complainViewUI(){
		//加载状态集合
		ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
		if(comp!=null){
			comp = complainService.findById(comp.getCompId());
		}
		return "complainViewUI";
	}
	public Map<String, Object> getReturn_map() {
		return return_map;
	}

	public void setReturn_map(Map<String, Object> return_map) {
		this.return_map = return_map;
	}

	public Complain getComp() {
		return comp;
	}

	public void setComp(Complain comp) {
		this.comp = comp;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
	
	
	
}
