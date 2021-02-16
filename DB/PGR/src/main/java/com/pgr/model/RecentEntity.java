package com.pgr.model;


public class RecentEntity {
	private int id;
	private String date;
	private String lteam;
	private String rteam;
	private int lscore;
	private int rscore;
	private Boolean completed;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getLteam() {
		return lteam;
	}
	public void setLteam(String lteam) {
		this.lteam = lteam;
	}
	public String getRteam() {
		return rteam;
	}
	public void setRteam(String rteam) {
		this.rteam = rteam;
	}
	public int getLscore() {
		return lscore;
	}
	public void setLscore(int lscore) {
		this.lscore = lscore;
	}
	public int getRscore() {
		return rscore;
	}
	public void setRscore(int rscore) {
		this.rscore = rscore;
	}
}
