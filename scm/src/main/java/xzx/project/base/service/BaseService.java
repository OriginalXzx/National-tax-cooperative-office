package xzx.project.base.service;

import java.util.List;

import xzx.project.entity.Page;

public interface BaseService<T> {
	// 添加实体对象信息到表
	int insert(T entity);

	// 根据对象主键查询
	T selectByPk(T entity);

	// 根据对象主键删除
	int deleteByPk(T entity);

	// 根据对象主键修改
	int updateByPk(T entity);

	// 根据对象动态生成SQL语句
	List<T> selectUseDyc(T entity);
	
	//通过多条件分页查询
	public Page<T> selectPageUseDyc(Page<T> page);
	
	//通过id数组删除数据
	public int deleteByPks(String[] pks);
}
