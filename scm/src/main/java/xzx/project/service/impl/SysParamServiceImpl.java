package xzx.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import xzx.project.base.service.impl.BaseServiceImpl;
import xzx.project.entity.SysParam;
import xzx.project.service.SysParamService;



@Service("sysParamService")
public class SysParamServiceImpl extends BaseServiceImpl<SysParam> implements SysParamService {

	public Map<String, Object> selectList() {
		List<SysParam> sysParams = sysParamMapper.selectList("");
		//系统参数map，用于存放所有字段相关信息
		Map<String,Object> sysParamMap = new HashMap<String,Object>();
		//用于存放属性字段，及值
		Map<String,Object> fieldMap = null;
		for(SysParam sysParam:sysParams){
			if("1".equals(sysParam.getSysParamType())){
				//需要执行sql，查询其他表
				String sql = sysParam.getSysParamValue();
				System.out.println(sql);
				List<SysParam> otherList = sysParamMapper.selectOthreTable(sql);
				fieldMap = new HashMap<String,Object>();
				//遍历，把数据存入到字段Map中
				for (SysParam other : otherList) {
					fieldMap.put(other.getSysParamValue(), other.getSysParamText());
				}
				sysParamMap.put(sysParam.getSysParamValue(), fieldMap);
			}else{
				//从存放系统参数的map获取字段的map
				if(sysParamMap.get(sysParam.getSysParamField())==null){
					fieldMap = new HashMap<String, Object>();
					fieldMap.put(sysParam.getSysParamValue(), sysParam.getSysParamText());
					sysParamMap.put(sysParam.getSysParamField(), fieldMap);				
				}else{
					fieldMap = (Map<String, Object>) sysParamMap.get(sysParam.getSysParamField());
					fieldMap.put(sysParam.getSysParamValue(), sysParam.getSysParamText());					
				}
			}
		}
		
		return sysParamMap;
	}

}
