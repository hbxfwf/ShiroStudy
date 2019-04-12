package com.zelin.pojo;

import java.util.List;

public class PageBean {
	//jqueryeasyui分页注意二：下面的两个变量必须叫下面的名字
	private int total;				//代表总记录数
	private List<Student> rows;		//代表每一页集合
	public PageBean() {
		super();
	}
	public PageBean(int total, List<Student> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Student> getRows() {
		return rows;
	}
	public void setRows(List<Student> rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "PageBean [total=" + total + ", rows=" + rows + "]";
	}
	
}
