package xzx.core.service;

import java.io.Serializable;
import java.util.List;

import xzx.core.page.PageResult;
import xzx.core.util.QueryHelper;

public interface BaseService<T> {
	void save(T entity);
    void update(T entity);
    void delete(Serializable id);
    T findById(Serializable id);
    List<T> findObjects() ;
    List<T> findObjects(String hql, List<Object> parameters);
    public List<T> findObjects(QueryHelper qh);
    PageResult getPageResult(QueryHelper qh, int pageNo, int pageSize);
}
