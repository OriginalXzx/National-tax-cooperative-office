package xzx.project.base.dao;

import java.util.List;

import xzx.project.entity.Page;

public interface BaseMapper<T> {
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
	
	//通过关键字分页查询数据列表
	public List<T> selectPageListUseDyc(Page<T> page);
			
	//通过关键字分页查询，返回总记录数
	public Integer selectPageCountUseDyc(Page<T> page);
	
	//通过id数组删除数据
	public int deleteByPks(String[] pks);

}
