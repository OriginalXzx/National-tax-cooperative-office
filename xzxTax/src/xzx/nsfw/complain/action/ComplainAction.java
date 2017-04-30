package xzx.nsfw.complain.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import xzx.core.action.BaseAction;
import xzx.core.util.QueryHelper;
import xzx.nsfw.complain.entity.Complain;
import xzx.nsfw.complain.entity.ComplainReply;
import xzx.nsfw.complain.service.ComplainService;

public class ComplainAction extends BaseAction{
	
	@Resource
	private ComplainService complainService;
	private Complain complain;
	private String startTime;
	private String endTime;
	private ComplainReply reply;
	private Map staticMap;
	
	// 列表页面
		public String listUI() throws Exception {
			//加载状态集合
			ActionContext.getContext().getContextMap().put("complainStateMap", complain.COMPLAIN_STATE_MAP);
			try {
				QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
				if(StringUtils.isNotBlank(startTime)){//查询开始时间之后的投诉数据
					startTime = URLDecoder.decode(startTime, "utf-8");
					queryHelper.addCondition("c.compTime >= ?", DateUtils.parseDate(startTime+":00", "yyyy-MM-dd HH:mm:ss"));
				}
				if(StringUtils.isNotBlank(endTime)){//查询结束时间之前的投诉数据
					endTime = URLDecoder.decode(endTime, "utf-8");
					queryHelper.addCondition("c.compTime <= ?", DateUtils.parseDate(endTime+":59", "yyyy-MM-dd HH:mm:ss"));
				}
				if(complain != null){
					if(StringUtils.isNotBlank(complain.getState())){
						queryHelper.addCondition("c.state=?", complain.getState());
					}
					if(StringUtils.isNotBlank(complain.getCompTitle())){
						complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(), "utf-8"));
						queryHelper.addCondition("c.compTitle like ?", "%" + complain.getCompTitle() + "%");
					}
				}
				//安装状态升序排序
				queryHelper.addOrderByProperty("c.state", QueryHelper.ORDER_BY_ASC);
				//按照投诉时间升序排序
				queryHelper.addOrderByProperty("c.compTime", QueryHelper.ORDER_BY_ASC);
				
				pageResult = complainService.getPageResult(queryHelper, getPageNo(), getPageSize());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "listUI";
		}
		
		public String dealUI(){
			//加载状态集合
			ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
			if(complain != null){
				complain = complainService.findById(complain.getCompId());
			}
			return "dealUI";
		}
		
		public String deal(){
			if(complain != null){
				Complain tem = complainService.findById(complain.getCompId());
				//1、更新投诉的状态为 已受理
				if(!Complain.COMPLAIN_STATE_DONE.equals(tem.getState())){//更新状态为 已受理
					tem.setState(Complain.COMPLAIN_STATE_DONE);
				}
				//2、保存回复信息
				if(reply != null){
					reply.setComplain(tem);
					reply.setReplyTime(new Timestamp(new Date().getTime()));
					tem.getComplainReplies().add(reply);
				}
				complainService.update(tem);
			}
			return "list";
		}
		//统计投诉图
		public String annualStatisticChartUI(){
			return "annualStatisticChartUI";
		}
		
		public String getAnnualStatisticData(){
			//获取年份
			int year = 0;
			HttpServletRequest request = ServletActionContext.getRequest();
		    if(request.getParameter("year") != null){
		    	year = Integer.valueOf(request.getParameter("year"));
		    	
		    }else{
		    	//默认统计当前年度
		    	year = Calendar.getInstance().get(Calendar.YEAR);
		    }
		    
		    //2.根据年份获取投诉数
		    staticMap = new HashMap<String,Object>();
		    staticMap.put("msg", "success");
		    staticMap.put("chartData", complainService.getAnnualStatisticData(year));
		    return "annualStatisticData";
		}

		public Complain getComplain() {
			return complain;
		}

		public void setComplain(Complain complain) {
			this.complain = complain;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public ComplainReply getReply() {
			return reply;
		}

		public void setReply(ComplainReply reply) {
			this.reply = reply;
		}

		public Map getStaticMap() {
			return staticMap;
		}

		public void setStaticMap(Map staticMap) {
			this.staticMap = staticMap;
		}
		
		
}
