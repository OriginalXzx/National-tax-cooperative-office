package xzx.core.action;

import com.opensymphony.xwork2.ActionSupport;

import xzx.core.page.PageResult;

public class BaseAction extends ActionSupport {
	protected String[] selectedRow;
	protected PageResult pageResult;
	protected int pageNo;
	protected int pageSize;
	//默认页面大小
	public static int DEFAULT_PAGE_SIZE = 3;
	
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public PageResult getPageResult() {
		return pageResult;
	}
	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		
		if(pageSize < 1) pageSize = DEFAULT_PAGE_SIZE;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
