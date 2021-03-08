package com.pgr.model;

public class BetDomain2 extends BetEntity {
	private String date;
	private Boolean completed;
	private int lscore;
	private int lid;
	private int rscore;
	private int rid;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	public int getLscore() {
		return lscore;
	}
	public void setLscore(int lscore) {
		this.lscore = lscore;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getRscore() {
		return rscore;
	}
	public void setRscore(int rscore) {
		this.rscore = rscore;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
}
