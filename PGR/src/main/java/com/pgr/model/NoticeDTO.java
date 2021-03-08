package com.pgr.model;

public class NoticeDTO {
	private int noticePk;
	private int page;
	private int sIdx;
	private int rowCnt;
	public int getNoticePk() {
		return noticePk;
	}
	public void setNoticePk(int noticePk) {
		this.noticePk = noticePk;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getsIdx() {
		return sIdx;
	}
	public void setsIdx(int sIdx) {
		this.sIdx = sIdx;
	}
	public int getRowCnt() {
		return rowCnt;
	}
	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	private String searchText;
}
