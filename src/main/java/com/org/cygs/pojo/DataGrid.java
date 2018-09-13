package com.org.cygs.pojo;

import java.io.Serializable;
import java.util.List;

public class DataGrid<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private int total;
	private List<T> rows;
	
	
	public DataGrid(int total,List<T> rows){
		this.total = total;
		this.rows = rows;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	

}
