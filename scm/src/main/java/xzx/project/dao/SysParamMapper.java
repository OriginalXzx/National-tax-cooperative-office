package xzx.project.dao;

import java.util.List;

import xzx.project.base.dao.BaseMapper;
import xzx.project.entity.SysParam;

public interface SysParamMapper extends BaseMapper<SysParam>{

	public List<SysParam> selectList(String type);
	public List<SysParam> selectOthreTable(String sql);
	//public List<SysParam> selectList();
}