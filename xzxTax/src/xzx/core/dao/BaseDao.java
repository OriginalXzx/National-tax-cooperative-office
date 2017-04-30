package xzx.core.dao;

import java.io.Serializable;
import java.util.List;

import xzx.core.page.PageResult;
import xzx.core.util.QueryHelper;

/**
 * 公共dao类，增删改查
 * @author xzx
 *
 * @param <T>
 */
public interface BaseDao<T> {
      void save(T entity);
      void update(T entity);
      void delete(Serializable id);
      T findById(Serializable id);
      List<T> findObjects();
      List<T> findObjects(String hql, List<Object> parameters);
      List<T> findObjects(QueryHelper qh);
	PageResult getPageResult(QueryHelper qh, int pageNo, int pageSize);
      
}
