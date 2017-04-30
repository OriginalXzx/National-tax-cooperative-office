package xzx.core.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * 内容包括了：当前页号，总页数，数据列表，总记录数，每页大小。
 *这个类主要目的是：在数据库层查询数据后，应该将查询到的列表封装给页面对象，并且根据从action中传递的页面大小和当前页，
 *计算出在页面上所需要的属性（当前页、总页数、数据列表），然后再将这个页面对象返回给jsp页面使用。
 * 
 * @author xzx
 *
 */
public class PageResult {
    //总记录数
	private long totalCount;
	//当前页号
	private int pageNo;
	//总页数
	private int totalPageCount;
	//页大小
	private int pageSize;
	//列表记录
	private List items;
	
	//构造分页对象
	public PageResult(long totalCount,int pageNo,int pageSize,List items){
		this.totalCount = totalCount;
		this.items = items==null?new ArrayList():items;
		if(totalCount>0){
			this.pageNo = pageNo;
			int tem = (int) (totalCount/pageSize);
			totalPageCount = totalCount/pageSize==0?tem:(tem+1);
		}else{
			//没有记录，页号，页数设置为0
			this.pageNo = 0;
			this.totalPageCount = 0;
		}
		this.pageSize = pageSize;
	}
	

	public PageResult() {
		
	}


	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}
	
}
