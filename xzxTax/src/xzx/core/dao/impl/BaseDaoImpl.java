package xzx.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import xzx.core.dao.BaseDao;
import xzx.core.page.PageResult;
import xzx.core.util.QueryHelper;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<T> clazz;
	public BaseDaoImpl(){
		//通过反射得到T的真是类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型

		
	}
	
	
	
	@Override
	public void save(T entity) {
        getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);

	}

	@Override
	public void delete(Serializable id) {
		getHibernateTemplate().delete(findById(id));
	}

	@Override
	public T findById(Serializable id) {
		return getHibernateTemplate().get(clazz,id);
	}

	@Override
	public List<T> findObjects() {
		Query query = getSession().createQuery("FROM "+clazz.getSimpleName());
		return query.list();
	}



	@Override
	public List<T> findObjects(String hql, List<Object> parameters) {
		Query query = getSession().createQuery(hql);
		if(parameters!=null){
			for(int i=0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}



	@Override
	public List<T> findObjects(QueryHelper qh) {
		Query query = getSession().createQuery(qh.getListQueryHql());
		List<Object> parameters = qh.getParameters();
		if(parameters!=null){
			for(int i=0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}



	@Override
	public PageResult getPageResult(QueryHelper qh, int pageNo, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery(qh.getListQueryHql());
		List<Object> parameters = qh.getParameters();
		if(parameters!=null){
			for(int i=0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		if(pageNo==0){
			pageNo = 1;
		}
		//设置起始行索引号
		query.setFirstResult((pageNo-1)*pageSize);
		//设置本次查询取得记录数大小
		query.setMaxResults(pageSize);
		List items = query.list();
		//System.out.println(items.toString());
		//查询总记录数
		Query totalQuery = session.createQuery(qh.getCountHql());
		if(parameters!=null){
			for(int i=0;i<parameters.size();i++){
				totalQuery.setParameter(i, parameters.get(i));
			}
		}
		long totalCount = (Long) totalQuery.uniqueResult();
		//构造返回的分页对象
		return new PageResult(totalCount,pageNo,pageSize,items);
	
	}

}
