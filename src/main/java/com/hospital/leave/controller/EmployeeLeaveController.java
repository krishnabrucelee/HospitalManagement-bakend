package com.hospital.leave.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.leave.serviceApi.IEmployeeLeaveRequest;

@Controller
public class EmployeeLeaveController {
	
	@Autowired
	private IEmployeeLeaveRequest iemployee;
	
	//getEmployeeAllPending  emppending
	@RequestMapping(value = "/getEmployeeAllPendingdata",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getEmployeeAllPendingdata(@RequestBody JSONObject  emppending){
		System.out.println("Inside employee controller");
	
		 return iemployee.getEmployeeAllPendingdata(emppending);
	}
	@RequestMapping(value = "/requestLeaveEmployee",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject requestLeaveEmployee(@RequestBody JSONObject  emprequestLeave){
		System.out.println("Inside employee requestLeaveEmployee controller");
		return iemployee.requestLeaveEmployee(emprequestLeave);
		
	}
	
	
	
	@RequestMapping(value = "/employeeApplyLeave",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject employeeApplyLeave(@RequestBody JSONObject  employeerequestLeave){
		System.out.println("Inside employeeRequestLeave controller new type with two range");
		return iemployee.employeeApplyLeave(employeerequestLeave);		
	}
	//////******Get EmployeeLeaveTrans ByEmpId******//////
	
	@RequestMapping(value = "/getEmployeeLeavedetailsById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getEmployeeLeavedetailsById(@RequestBody JSONObject  employee){
		System.out.println("Inside employee controller");
		
		
		System.out.println("Value is returned");
		
		 return iemployee.getLeaveRequestHistoryById(employee);
	}
	@RequestMapping(value = "/getLeavedataList")
	public 	@ResponseBody JSONObject getLeavedataList(){
		System.out.println("Inside employee controller");
		
		return iemployee.getLeavedataList();
	}
	@RequestMapping(value = "/getEmployeeFiscalyearleavedeatils")
	public 	@ResponseBody JSONObject getEmployeeFiscalyearleavedeatils(){
		System.out.println("Inside employee controller");
		
		return iemployee.getEmployeeFiscalyearleavedeatils();
	}
	@RequestMapping(value = "/getEmployeeFiscalyearleaveByEmpId",method=RequestMethod.POST)
	public @ResponseBody JSONObject getEmployeeFiscalyearleaveByEmpId(@RequestBody JSONObject employeid){
		System.out.println("Inside employee controller");
		return iemployee.getEmployeeFiscalyearleaveByEmpId(employeid);
	}
	@RequestMapping(value = "/getEmployeeleaveRequestDataById",method=RequestMethod.POST)
	public @ResponseBody JSONObject getEmployeeleaveRequestDataById(@RequestBody JSONObject employedata){
		System.out.println("Inside employee  getEmployeeleaveRequestDataById controller");
		Integer empid = (Integer)employedata.get("employee_id");
		System.out.println("Employee id="+empid);
		return iemployee.getEmployeeleaveRequestDataById(employedata);
	}
	@RequestMapping(value = "/getAllDataEmployeeById",method=RequestMethod.POST)
	public @ResponseBody  JSONObject getAllDataEmployeeById(@RequestBody JSONObject employid) {
		System.out.println("Inside employee  getAllDataEmployeeById controller");
		return iemployee.getAllDataEmployeeById(employid);
	}
	//All leave details and All type remaining days
	@RequestMapping(value = "/getFiscalLeaveDetailsByEmpId",method=RequestMethod.POST)
	public @ResponseBody JSONObject getEmployeeFiscalleaveDetailsByEmpId(@RequestBody JSONObject employee){
		return iemployee.getEmployeeFiscalleaveDetailsByEmpId(employee);
	}
	
	@RequestMapping(value = "/employeeLeaveRequestAllPAR",method=RequestMethod.POST)
	public @ResponseBody JSONObject employeeAllPARViewStatusByEmpId(@RequestBody JSONObject leaveRequestStatus){
		return iemployee.employeeAllPARViewStatus(leaveRequestStatus);
	}
}
