package com.zelin.pojo;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable {

	private int total;				//代表总记录数
	private List<Student> rows;		//代表每一页集合
	private int totalpages;
	public PageBean() {
		super();
	}
	public PageBean(int total, List<Student> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public PageBean(int total, List<Student> rows, int totalpages) {
		this.total = total;
		this.rows = rows;
		this.totalpages = totalpages;
	}

	public int getTotalpages() {
		return totalpages;
	}

	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
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
