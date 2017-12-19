package xzx.project.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xzx.project.base.action.BaseAction;
import xzx.project.entity.Page;
import xzx.project.entity.Supplier;
import xzx.project.service.SupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierAction extends BaseAction {
     @Resource
	private SupplierService supplierService;
     
   //通过关键字分页查询
 	@RequestMapping("/selectPageUseDyc")
 	@ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
 	public Object selectPageUseDyc(Page<Supplier> page,Supplier supplier){
 		
 		page.setParamEntity(supplier);
 		System.out.println("----page:"+page);

 		Page p = supplierService.selectPageUseDyc(page);
 		System.out.println("map:"+p.getPageMap());
 		//supplier.setSupName("supName1");
 		/*Map<String, Object> map =new HashMap<String, Object>();
 		map.put("total",p.getTotalRecord());
 		map.put("rows",p.getList());*/
 		return p.getPageMap();
 	}
 	
 	//通过关键字分页查询
 	/*	@RequestMapping("/selectPage")
 		@ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
 		public Object selectPage(Page<Supplier> page,HttpServletRequest request){
 			String pageIndex=request.getParameter("page");
 			String pageSize =  request.getParameter("rows");
 			String keyWord =  request.getParameter("keyWord");
 			System.out.println("---doAjax.page||rows||keyWord:"+pageIndex+","+pageSize+","+keyWord);

 			Page<Supplier> page = new Page<Supplier>();
 			page.setPageIndex(Integer.parseInt(pageIndex));
 			page.setPageSize(Integer.parseInt(pageSize));
 			page.setKeyWord(keyWord);
 			Page p = supplierService.selectPage(page);
 			System.out.println("----page:"+page);
 			//supplier.setSupName("supName1");
 			Map<String, Object> map =new HashMap<String, Object>();
 			map.put("total",page.getTotalRecord());
 			map.put("rows",page.getList());
 			return map;
 		}*/
 	/**
 	 * 删除
 	 * @param pks
 	 * @return
 	 */
 	@RequestMapping("/deleteByPks")
 	@ResponseBody
 	public Object deleteByPks(String[] pks){
 		System.out.println("---deleteByPks.pks:" + pks);
		int i = 0;
		try {
			i = supplierService.deleteByPks(pks);
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
 	public Object insert(Supplier supplier){
 		System.out.println(supplier);
 		int i = 0;
 		try {
			i = supplierService.insert(supplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
 		System.out.println("成功了吗？"+i);
 		return i;
 	}
 	
 	/**
 	 * 修改功能的实现
 	 */
 	@RequestMapping("/update")
 	@ResponseBody
 	public Object update(Supplier supplier){
 		System.out.println(supplier);
 		int i = 0;
 		try {
			i = supplierService.updateByPk(supplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
 		System.out.println("修改成功了吗？"+i);
 		return i;
 	}
 	
}










