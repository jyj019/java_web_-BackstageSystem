package com.jyj019.beans;

import java.util.List;

/**
 * 
 * @author jyj019
 *
 * @param <T>
 */
public class PageBean<T> {
	private int currentPage;
	private int pageSize;
	private int totalCount;
	private int totalPage;
	private List<T> list;
	private int start;
	private int end;
	
	
	
	
	public PageBean(int currentPage, int pageSize, int totalCount, List<T> list) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.list = list;
		totalPage = (int) Math.ceil(totalCount*1.0/pageSize);
		if(currentPage<=2) {
			start = 1;
			end = 3;
		}
		if(currentPage>=totalPage-1) {
			start = totalPage-2;
			end = totalPage;
			
		}
		if(currentPage>2 && currentPage<totalPage-1) {
			start = currentPage-1;
			end = currentPage+1;
		}
		
	}
	
	
	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getEnd() {
		return end;
	}


	public void setEnd(int end) {
		this.end = end;
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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		
		return (int) Math.ceil(totalCount*1.0/pageSize);
	}
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
