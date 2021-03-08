package com.pgr.model;

public class BetDomain extends BetEntity{
	private double w_allocation;
	private double d_allocation;
	private double l_allocation;
	private double w_percent;
	private double d_percent;
	private double l_percent;
	
	public double getW_percent() {
		return w_percent;
	}
	public void setW_percent(double w_percent) {
		this.w_percent = w_percent;
	}
	public double getD_percent() {
		return d_percent;
	}
	public void setD_percent(double d_percent) {
		this.d_percent = d_percent;
	}
	public double getL_percent() {
		return l_percent;
	}
	public void setL_percent(double l_percent) {
		this.l_percent = l_percent;
	}
	public double getW_allocation() {
		return w_allocation;
	}
	public void setW_allocation(double w_allocation) {
		this.w_allocation = w_allocation;
	}
	public double getD_allocation() {
		return d_allocation;
	}
	public void setD_allocation(double d_allocation) {
		this.d_allocation = d_allocation;
	}
	public double getL_allocation() {
		return l_allocation;
	}
	public void setL_allocation(double l_allocation) {
		this.l_allocation = l_allocation;
	}

}
