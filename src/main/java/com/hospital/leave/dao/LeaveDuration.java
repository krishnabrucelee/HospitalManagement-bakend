package com.hospital.leave.dao;

public enum LeaveDuration {
Yearly(1),Halfyearly(6),Quartarly(3),Month(1);
	public int addmonthCount;
	
	LeaveDuration(int addmonthCount) {
		this.addmonthCount = addmonthCount;
	}

}

