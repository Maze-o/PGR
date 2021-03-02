package com.pgr.model;

public class MatchRecordEntity {
	private int id;
	private int lfoulsCommitted;
	private int lwonCorners;
	private double lpossessionPct;
	private int lshotsOnTarget;
	private int ltotalShots;
	private int rfoulsCommitted;
	private int rwonCorners;
	private double rpossessionPct;
	private int rshotsOnTarget;
	private int rtotalShots;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLfoulsCommitted() {
		return lfoulsCommitted;
	}
	public void setLfoulsCommitted(int lfoulsCommitted) {
		this.lfoulsCommitted = lfoulsCommitted;
	}
	public int getLwonCorners() {
		return lwonCorners;
	}
	public void setLwonCorners(int lwonCorners) {
		this.lwonCorners = lwonCorners;
	}
	public double getLpossessionPct() {
		return lpossessionPct;
	}
	public void setLpossessionPct(double lpossessionPct) {
		this.lpossessionPct = lpossessionPct;
	}
	public int getLshotsOnTarget() {
		return lshotsOnTarget;
	}
	public void setLshotsOnTarget(int lshotsOnTarget) {
		this.lshotsOnTarget = lshotsOnTarget;
	}
	public int getLtotalShots() {
		return ltotalShots;
	}
	public void setLtotalShots(int ltotalShots) {
		this.ltotalShots = ltotalShots;
	}
	public int getRfoulsCommitted() {
		return rfoulsCommitted;
	}
	public void setRfoulsCommitted(int rfoulsCommitted) {
		this.rfoulsCommitted = rfoulsCommitted;
	}
	public int getRwonCorners() {
		return rwonCorners;
	}
	public void setRwonCorners(int rwonCorners) {
		this.rwonCorners = rwonCorners;
	}
	public double getRpossessionPct() {
		return rpossessionPct;
	}
	public void setRpossessionPct(double rpossessionPct) {
		this.rpossessionPct = rpossessionPct;
	}
	public int getRshotsOnTarget() {
		return rshotsOnTarget;
	}
	public void setRshotsOnTarget(int rshotsOnTarget) {
		this.rshotsOnTarget = rshotsOnTarget;
	}
	public int getRtotalShots() {
		return rtotalShots;
	}
	public void setRtotalShots(int rtotalShots) {
		this.rtotalShots = rtotalShots;
	}
}
