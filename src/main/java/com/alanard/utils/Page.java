package com.alanard.utils;

import com.alanard.pojo.User;

public class Page {
	private int currentPage;
	private int pageSize;
	private int resultSize;
	private int pageNum;
	private int userId;
	private String category;
	private String module;
	
	public Page(int currentPage, int pageSize,int resultSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.resultSize = resultSize;
		int basePageNum = resultSize/pageSize;
		pageNum = resultSize%pageSize == 0?basePageNum:(basePageNum+1);
	}
	
	public Page(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public Page() {

	}

	public int getBegin() {
		return (currentPage-1)*pageSize;
	}
	
	public int getEnd() {
		return pageSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public boolean hasNextPage(){
		return currentPage==pageNum?false:true;
	}
	
	public boolean hasPrevPage(){
		return currentPage==1?false:true;
	}

	public int getResultSize() {
		return resultSize;
	}

	public void setResultSize(int resultSize) {
		this.resultSize = resultSize;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	

}
