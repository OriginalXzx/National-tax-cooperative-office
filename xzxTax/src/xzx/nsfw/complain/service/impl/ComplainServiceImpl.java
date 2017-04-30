package xzx.nsfw.complain.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xzx.core.service.impl.BaseServiceImpl;
import xzx.core.util.QueryHelper;
import xzx.nsfw.complain.dao.ComplainDao;
import xzx.nsfw.complain.entity.Complain;
import xzx.nsfw.complain.service.ComplainService;

@Service("complainService")
public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements ComplainService {
      
	private ComplainDao complainDao;
	@Resource
	public void setComplainDao(ComplainDao complainDao){
		super.setBaseDao(complainDao);
		this.complainDao = complainDao;
		
	}
	@Override
	public void autoDeal() {
		 //查询上个月未受理投诉
		QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
	    queryHelper.addCondition("c.state=?", Complain.COMPLAIN_STATE_UNDONE);
	    //查找本月开始之前未受理的投诉信息
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);//本月1号
	    cal.set(Calendar.HOUR_OF_DAY, 0);//24小时制。0时
	    cal.set(Calendar.MINUTE, 0);//0分
	    cal.set(Calendar.SECOND, 0);//0秒
	    queryHelper.addCondition("c.compTime < ?", cal.getTime());
	    List<Complain> list = findObjects(queryHelper);
	    
	    //2，更改投诉的状态为已失效
	    if(list != null){
	    	for (Complain complain : list) {
				complain.setState(Complain.COMPLAIN_STATE_INVALID);
				update(complain);
			}
	    }
	
	}
	@Override
	public List getAnnualStatisticData(int year) {
		List<Object[]> list = complainDao.getAnnualStatisticData(year);
		List retList = new ArrayList();
		if(list != null){
			//是否是当前年度
			Calendar cal = Calendar.getInstance();
			boolean isCurrentYear = (year == cal.get(Calendar.YEAR));
			int curMonth = cal.get(Calendar.MONTH)+1;//获取当前月份
			int month = 0;
			for (Object[] obj : list) {
				
				HashMap<String, Object> map = new HashMap<String,Object>();
			    month = Integer.valueOf(obj[0]+"");
			    map.put("laber", month+"月");
			    if(isCurrentYear){
			    	if(month > curMonth){
			    		//如果是当前年度，则当前月份之后是不能投票的
			    		map.put("value", "");
			    	}else{
			    		if(obj[1] != null){
			    			map.put("value", String.valueOf(obj[1]));
			    			
			    		}else{
			    			map.put("value", "0");
			    		}
			    	}
			    }else{
			    	//如果非当前年度，则所有为空的计数应默认为0；
			    	if(obj[1] != null){
			    		map.put("value", String.valueOf(obj[1]));
			    	}else{
			    		map.put("value","0");
			    	}
			    }
			    retList.add(map);
			}
			return retList;
		}
		return null;
	}
}
