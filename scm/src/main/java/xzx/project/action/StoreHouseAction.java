package xzx.project.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xzx.project.base.action.BaseAction;
import xzx.project.entity.Page;
import xzx.project.entity.StoreHouse;
import xzx.project.entity.Supplier;
import xzx.project.service.StoreHouseService;

@Controller
@RequestMapping("/storeHouse")

public class StoreHouseAction extends BaseAction {
     
	@Resource
	private StoreHouseService storeHouseService;
	
	
	//通过关键字分页查询
 	@RequestMapping("/selectPageUseDyc")
 	@ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
 	public Object selectPageUseDyc(Page<StoreHouse> page,StoreHouse storeHouse){
 		
 		page.setParamEntity(storeHouse);
 		System.out.println("----page:"+page);

 		Page p = storeHouseService.selectPageUseDyc(page);
 		System.out.println("map:"+p.getPageMap());
 		//supplier.setSupName("supName1");
 		/*Map<String, Object> map =new HashMap<String, Object>();
 		map.put("total",p.getTotalRecord());
 		map.put("rows",p.getList());*/
 		return p.getPageMap();
 	}
 	
 	/**
 	 * 删除数据
 	 * @param pks
 	 * @return
 	 */
 	@RequestMapping("/deleteByPks")
 	@ResponseBody
 	public Object deleteByPks(String[] pks){
 		System.out.println("---deleteByPks.pks:" + pks);
		int i = 0;
		try {
			i = storeHouseService.deleteByPks(pks);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return i;
 	}
 	
 	/**
 	 * 新增功能的实现
 	 */
 	@RequestMapping("/insert")
 	@ResponseBody
 	public Object insert(StoreHouse storeHouse){
 		System.out.println(storeHouse);
 		int i = 0;
 		try {
			i = storeHouseService.insert(storeHouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
 		System.out.println("仓库添加成功了吗？"+i);
 		return i;
 	}
 	/**
 	 * 修改功能的实现
 	 */
 	@RequestMapping("/update")
 	@ResponseBody
 	public Object update(StoreHouse storeHouse){
 		System.out.println(storeHouse);
 		int i = 0;
 		try {
			i = storeHouseService.updateByPk(storeHouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
 		System.out.println("修改成功了吗？"+i);
 		return i;
 	}
}
