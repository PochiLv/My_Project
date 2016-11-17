package com.pochi.bean;

import java.util.List;

public class Page {
	private int totalPage;
	private int pageSize=5;
	
	private int totalRecord;
	private int pageNum;
	
	private int startIndex;
	
	private String url;
	
	private List<Book> list;
	
	private int startPage;
	private int endPage;
	
	
	
	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public int getTotalPage() {
		return totalPage;
	}



	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}



	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public int getTotalRecord() {
		return totalRecord;
	}



	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}



	public int getPageNum() {
		return pageNum;
	}



	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}



	public int getStartIndex() {
		return startIndex;
	}



	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}



	public List<Book> getList() {
		return list;
	}



	public void setList(List<Book> list) {
		this.list = list;
	}



	public int getStartPage() {
		return startPage;
	}



	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}



	public int getEndPage() {
		return endPage;
	}



	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}



	public Page(int pageNum,int totalRecord){
		this.pageNum=pageNum;
		this.totalRecord=totalRecord;
		
		this.totalPage=(this.totalRecord+this.pageSize-1)/this.pageSize;
		this.startIndex=(this.pageNum-1)*this.pageSize;
	
		if(this.totalPage<=this.pageSize){
			this.startPage=1;
			this.endPage=this.totalPage;
		}else{
			this.startPage=this.pageNum-this.pageSize/2;
			this.endPage=this.pageNum+(this.pageSize-this.pageSize/2-1);
			if(this.startPage<1){
				this.startPage=1;
				this.endPage=this.pageSize;
			}
			if(this.endPage>this.totalPage){
				this.endPage=this.totalPage;
				this.startPage=this.totalPage-this.pageSize+1; 
			}
		}
	}
}
