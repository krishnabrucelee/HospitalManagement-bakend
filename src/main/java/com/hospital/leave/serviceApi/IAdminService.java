package com.hospital.leave.serviceApi;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public interface IAdminService {	
	public JSONObject addEmployee(JSONObject employedata);
	public JSONObject saveLeaveData(JSONObject leaveData);
	
	public JSONObject getLeavedetailsByFid();
	public JSONObject deleteLeavedetailsByFid(JSONObject leaveid);
	public JSONObject getParticularLeavedetailsByLid(JSONObject leaveconfig);
//getEmployeeLeaveList
	public JSONObject getEmployeeLeaveList();
	public JSONObject approveEmployeeLeaveByAdmin(JSONObject getapprove)throws Exception;
	public JSONObject getEmployeeLeaveRequestByYearByAdmin();
	public JSONObject getEmployeeLeaveListByYear();
	public JSONObject getLeaverequestById(JSONObject leavebyid);
	public JSONObject getAllEmployeeLeaverequestByFid(JSONObject financialyearid);
	public JSONObject  getEmployeeFiscalyearDetailByEmpId(JSONObject employeeid);
	public JSONObject approveLeaverequestByAdmin(JSONObject approvaldata);
	//NEW TYPE
	public JSONObject approveLeaverequestByEmpByAdmin(JSONObject approveleave);
	public JSONObject getEmployeeById(JSONObject employeeid);
	//
	public JSONObject getAllDataMerge();
	//
	public JSONObject getAllLeaveRequestByAdmin();
	//
	public JSONObject getAllPendingLeaveRequestByAdmin();
	public JSONObject getAllPendingRequestByEidByAdmin(JSONObject employe);
	//Dynamic set financial from year and toyear
	public JSONObject getFinancialData(JSONObject financialyear);
	//Set holidays configuration
	public JSONObject setYearlyHolidays(JSONObject holidays);
	public JSONObject setWeekoffdays(JSONObject weekoff);
	//
	public JSONObject getFiscalyearDetailByEmpId( JSONObject employeeid);
	//Get leave remaining days for leave id with employee id
	public JSONObject getLeaveRemainingDays(JSONObject employee);
	
	
	public JSONObject getFiscalLeaveDetailsByEmpIdAdmin(JSONObject fiscaldata);
	public JSONObject getFiscalyearDetailByEmpIdYear(JSONObject fiscaldata);
	
	public JSONObject deleteFiscalyearDetailByEmpIdYear(JSONObject fiscaldataid);
	
	public JSONObject deleteEmployeeLeaveRequestById(JSONObject employeeLeaveRequestid);
	
    public JSONObject deleteFinancialyearById(JSONObject financialyearid);
    public JSONObject listHolidaysByTypes(JSONObject holidaytypes); 
    
  
    
	public JSONObject deleteHolidayById(JSONObject holidayid);
	//GetLOP SUM//
	
	public JSONObject getLOPSumByEmpId(JSONObject lopsumdata);
	
	public JSONObject listLeaveRequestBetweenDates(JSONObject leaverequest);
	
	
}
