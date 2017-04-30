package xzx.core.service.impl;

import java.io.Serializable;
import java.util.List;

import xzx.core.dao.BaseDao;
import xzx.core.page.PageResult;
import xzx.core.service.BaseService;
import xzx.core.util.QueryHelper;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> dao;
	//该方法即是向service中注入相关的dao对象
	public void setBaseDao(BaseDao<T> baseDao){
		this.dao = baseDao;
	}
	@Override
	public void save(T entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Serializable id) {
          dao.delete(id);
	}

	@Override
	public T findById(Serializable id) {
		return dao.findById(id);
	}

	@Override
	public List<T> findObjects()  {
			return dao.findObjects();
	}

	@Override
	public void update(T entity) {
		dao.update(entity);
	} 
	@Override
	public List<T> findObjects(String hql, List<Object> parameters){
		return dao.findObjects(hql, parameters);
	}
	
	@Override
	public List<T> findObjects(QueryHelper qh){
		return dao.findObjects(qh);
	}
	public PageResult getPageResult(QueryHelper qh, int pageNo, int pageSize){
		return dao.getPageResult(qh,pageNo,pageSize);
	}
	
}
