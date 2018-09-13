package com.org.cygs.pojo;

import java.util.List;
import java.io.Serializable;

public class Page<T> implements Serializable{
	
   private static final long serialVersionUID = 1L;
   
    private List<T> list; //结果集
    private int totalRecord; //总记录数
    private int pageNum; //第几页
    private int pageSize; //每页记录数
    private int totalPage; // 总页数
    private int size; //当前页的数量<=pageSize
    
    
    
    /* public Page(List<T> list){
    this.pageNum = Page.getPageNum();
    this.pageSize = page.getPageSize();
    this.total = page.getTotal();
    this.pages = page.getPages();
    this.list = page;
    this.size = page.size();

}*/
    public Page(List<T> list, int totalRecord,int pageNum,int pageSize,int size){
    	this.list = list;
    	this.totalRecord = totalRecord;
    	this.pageNum = pageNum;
    	this.pageSize = pageSize;
    	this.size = size;
    	setTotalPage();
    }
    
    public Page(){
    	
    	this.pageNum = 1;
    	this.pageSize = 15;

    }
    
    
    
    private void setTotalPage(){  
        if(totalRecord%pageSize==0){  
            totalPage = totalRecord/pageSize;  
        }else{  
            totalPage = totalRecord/pageSize+1;  
        }  
    }
    
    
    //sqlserver 2012 提供 “Offset 800 Row Fetch Next 200 Rows Only”
    public int getOffset(){  
        return (pageNum-1)*pageSize;  
    }  
    
    
	public long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		 if(totalRecord%pageSize==0){  
	            totalPage = totalRecord/pageSize;  
	        }else{  
	            totalPage = totalRecord/pageSize+1;  
	        }  
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

   
}
