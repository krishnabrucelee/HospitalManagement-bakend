package com.hospital.leave.controller;

import java.util.function.Consumer;

import com.hospital.leave.dao.LeaveDuration;

public class Sample {

	public static void main(String[] args) {
		
		
		
		LeaveDuration[] values = LeaveDuration.values();
		String leaveType ="Halfyearly1";
		
		boolean matched = false;
		
		LeaveDuration matchedEnum = null;
		
		for (LeaveDuration leaveDuration : values) {
			if(leaveType.equals(leaveDuration.toString()))
			{
				matched = true;
				matchedEnum = leaveDuration;
				System.out.println("matched");
				break;
			}
			System.out.println(leaveDuration);
		}
		
		StringBuffer ss = new StringBuffer();
		for (LeaveDuration leaveDuration : values) {
			ss.append(leaveDuration+" ");
		}
		
		if(!matched)
		{
			System.out.println("Allowed values are : ["+ss.toString()  +"] but found "+leaveType);
		}
		
	}

}
