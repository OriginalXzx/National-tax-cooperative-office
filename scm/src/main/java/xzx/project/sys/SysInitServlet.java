package xzx.project.sys;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import xzx.project.dao.SysParamMapper;
import xzx.project.entity.SysParam;

public class SysInitServlet extends HttpServlet {
    
	private SysParamMapper sysParamMapper;
	public SysParamMapper getSysParamMapper() {
		return sysParamMapper;
	}
	public void setSysParamMapper(SysParamMapper sysParamMapper) {
		this.sysParamMapper = sysParamMapper;
	}

	public void init() throws ServletException {
		System.out.println("=========数据字典初始化开始==========");
		ServletContext application =this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		sysParamMapper = (SysParamMapper) ctx.getBean("sysParamMapper");
		System.out.println(sysParamMapper);
		List<SysParam> list = sysParamMapper.selectList(sysParamMapper.toString());
		System.out.println("系统参数个数"+list.size());
		Map<String,Object> sysParam = new HashMap<String,Object>();
		Map<String,String> supType = new HashMap<String,String>();
		Map<String,String> goodsColor = new HashMap<String,String>();
		Iterator<SysParam> iterator = list.iterator();
        while(iterator.hasNext()){
        	SysParam param = iterator.next();
        	System.out.println(param);
        	if("supType".equalsIgnoreCase(param.getSysParamField())){
        		supType.put(param.getSysParamValue(),param.getSysParamText());
        	}else{
        		goodsColor.put(param.getSysParamValue(),param.getSysParamText());
        	}
        }
        sysParam.put("goodsColor", goodsColor);
    	sysParam.put("supType", supType);
    	application.setAttribute("sysParam",sysParam);
		System.out.println("=======初始化完成，已加载数据字典数据=======");
	}

}


