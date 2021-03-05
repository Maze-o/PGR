package com.pgr.model;

public class BetDomain extends BetEntity{
	private double w_allocation;
	private double d_allocation;
	private double l_allocation;
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
