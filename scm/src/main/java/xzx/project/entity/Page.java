package xzx.project.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 6621708557409749822L;

	private Integer page = 1;// 表示当前页，与datagrid请求的参数名一致，便于查询时赋值，默认第一页

	private Integer rows = 10;// 表页的条数，与datagrid请求的参数名一致，便于查询时赋值，默认10条

	private Integer start;// 从第几条开始，记录的下标从0开始计算
	
	private T paramEntity;//多查询条件时使用
	
	private String keyWord;//单个关键字查询

	private Integer total;// 总条数

	private List<T> lists;// 页面数据集
	//datagrid会自动计算页数，所以不用定义总页数,总条数和页面数据集可以在这里定义Map进行存放，返回页面时直接给map对象
	
	private Map<String, Object> pageMap = new HashMap<String, Object>() ;
	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getStart() {
		return (page-1)*rows;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public T getParamEntity() {
		return paramEntity;
	}

	public void setParamEntity(T paramEntity) {
		this.paramEntity = paramEntity;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		pageMap.put("total", total);
		this.total = total;
	}

	public List<T> getLists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		pageMap.put("rows", lists);
		this.lists = lists;
	}

	@Override
	public String toString() {
		return "Page [page=" + page + ", rows=" + rows + ", start=" + start + ", paramEntity=" + paramEntity
				+ ", keyWord=" + keyWord + ", total=" + total + ", lists=" + lists + "]";
	}
	
}
