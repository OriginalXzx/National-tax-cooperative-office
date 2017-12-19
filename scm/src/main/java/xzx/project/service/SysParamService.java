package xzx.project.service;

import java.util.Map;

import xzx.project.base.service.BaseService;
import xzx.project.entity.SysParam;

public interface SysParamService extends BaseService<SysParam>{
	public Map<String,Object> selectList();
}
