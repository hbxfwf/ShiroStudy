package com.zelin.pojo;

public class MyPage {

	private int page;
	private int pagesize;
	
	public MyPage() {
		super();
	}
	public MyPage(int page, int pagesize) {
		super();
		this.page = page;
		this.pagesize = pagesize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	
}
