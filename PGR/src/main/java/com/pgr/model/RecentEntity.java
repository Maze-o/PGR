package com.pgr.model;


public class RecentEntity {
	private int id;
	private String date;
	private String lteam;
	private String rteam;
	private int lscore;
	private int lid;
	private int rscore;
	private int rid;
	private Boolean completed;
	private String venue;
	
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
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
