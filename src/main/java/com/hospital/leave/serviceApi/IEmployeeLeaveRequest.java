package com.hospital.leave.serviceApi;

import org.json.simple.JSONObject;

public interface IEmployeeLeaveRequest {
	
	public JSONObject createLeaverequest(JSONObject leaverequest);
	public JSONObject getLeaveRequestHistoryById(JSONObject value);
	public JSONObject getLeavedataList();
	public JSONObject getEmployeeFiscalyearleaveByEmpId(JSONObject employeid);
	public JSONObject getEmployeeFiscalyearleavedeatils();
	
	
	public JSONObject getEmployeeleaveRequestDataById(JSONObject employedata);
	
	public JSONObject getParticularLeaveDataByEFLId(JSONObject employeleave );
	
	public JSONObject getAllDataEmployeeById(JSONObject employid); 
	
	public JSONObject getEmployeeAllLeaveRequestByEid(JSONObject employyeid);
	public JSONObject getEmployeeFiscalleaveDetailsByEmpId(JSONObject employee);

	public JSONObject getEmployeeAllPendingdata(JSONObject emppending);
	
	public JSONObject requestLeaveEmployee(JSONObject emprequestLeave);
	//particular leave balance
	public JSONObject getEmployeeFiscalyearleaveBalance(JSONObject employeedata);
	
	//employeeApplyLeave
	public JSONObject employeeApplyLeave(JSONObject employeerequestLeave);
	
	public JSONObject employeeAllPARViewStatus(JSONObject leaveRequestStatus);
	
	public JSONObject employeeViewPendingStatus(JSONObject leaveRequestpendingStatus);
	
	public JSONObject employeeViewApprovedStatus(JSONObject leaveRequestApprovedStatus);
	//within month//
	public JSONObject employeeCurrentMonthLeaveRequestStatus(JSONObject currentMonthLeaveRequestStatus);
	
	public JSONObject getemployeeCurrentRangeLeaveRequest(JSONObject currentRangeLeaveRequest);
	
}
