package xzx.project.action;

import java.util.Arrays;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xzx.project.base.action.BaseAction;
import xzx.project.entity.BuyOrder;
import xzx.project.entity.BuyOrderDetail;
import xzx.project.service.BuyOrderService;


@Controller
@RequestMapping("/buyOrder")
public class BuyOrderAction extends BaseAction {
	@Resource
	private BuyOrderService buyOrderService;
	
	@RequestMapping("/insertBuyOrder")
	@ResponseBody
	public Object insertBuyOrder(BuyOrder buyOrder,String rows) {
		System.out.println("buyorderaction.rows："+rows);
		
		int i=0;
        // ObjectMapper是工具类，用来把json格式内容与JAVA对象之间进行转换
		ObjectMapper objectMapper = new ObjectMapper();
		try {
           //把包含json格式的字符串转为BuyOrder类型对象
			BuyOrderDetail[] bo = objectMapper.readValue(rows, BuyOrderDetail[].class);
			System.out.println("buyOrderDetail:"+bo);
			buyOrder.setBuyOrderDetails(Arrays.asList(bo));
			i = buyOrderService.insertBuyOrder(buyOrder);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return i;		
	}
}
