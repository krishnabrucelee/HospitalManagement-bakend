package com.hospital.leave.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.leave.service.EmployeeLeaveRequestImpl;
import com.hospital.leave.serviceApi.IAdminService;
import com.hospital.leave.serviceApi.IEmployeeLeaveRequest;

@Controller
public class AdminController {
	@Autowired
	private IAdminService iadminservice;
	@Autowired
	private EmployeeLeaveRequestImpl iemployeeservice;
	@Autowired
	private IEmployeeLeaveRequest iempservice;
	@RequestMapping(value = "/saveLeaveData",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject saveLeaveType(@RequestBody JSONObject leaveData ){
		JSONObject obj = new JSONObject();
	
		obj.put("name","Inserted success into db");
		 return iadminservice.saveLeaveData(leaveData);		
	}
	
	@RequestMapping(value = "/listLeavetypes",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getLeavedataList( ){
			System.out.println("Inside getLeavedataList controlller");	
		 return iadminservice.getLeavedetailsByFid();
	}	
	
	@RequestMapping(value = "/deleteLeavedata",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject deleteLeavedata(@RequestBody JSONObject leaveid ){
				
		 return iadminservice.deleteLeavedetailsByFid(leaveid);
	}	
	
	
	/*@RequestMapping(value = "/employeeRequestLeave",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject employeeRequestLeave(@RequestBody JSONObject emprequestLeave){
		System.out.println("Inside  new type requestLeave controller");
		JSONObject obj = new JSONObject();
		obj.put("name","Inserted  into db  requestLeave success");	
		return iempservice.employeeRequestLeave(emprequestLeave);			
	}*/
	
	@RequestMapping(value = "/getEmployeeLeaveList",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getEmployeeLeaveList( ){
		
		JSONObject obj = new JSONObject();
		obj.put("name","Data get from  db");
		System.out.println("Value is returned");		
		 return iadminservice.getEmployeeLeaveList();
	}	
	@RequestMapping(value = "/getAllEmployeeLeaverequestByFid",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getAllEmployeeLeaverequestByFid(@RequestBody JSONObject financialyearid){
		
		JSONObject obj = new JSONObject();
		obj.put("name","Data get from  db");
			
		 return iadminservice.getAllEmployeeLeaverequestByFid(financialyearid);
	}	
	@RequestMapping(value = "/getEmployeeLeaveRequestByYearByAdmin")
	public 	@ResponseBody JSONObject getEmployeeLeaveRequestByYearByAdmin(){
	
		return iadminservice.getEmployeeLeaveRequestByYearByAdmin();
	}
	@RequestMapping(value = "/getEmployeeLeaveListByYear",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getEmployeeLeaveListByYear(){
	
		JSONObject obj = new JSONObject();
		obj.put("name","Data get from  db");
		
		 return iadminservice.getEmployeeLeaveListByYear();
	}
	//getEmployeeFiscalyearDetailByEmpId
	@RequestMapping(value = "/getEmployeeFiscalyearDetailByEmpId",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getEmployeeFiscalyearDetailByEmpId(@RequestBody JSONObject employeeid){
	
		return iadminservice.getEmployeeFiscalyearDetailByEmpId(employeeid);
	}
	//getFiscalyearDetailRowByEmpId
		@RequestMapping(value = "/getFiscalyearDetailRowByEmpId",method=RequestMethod.POST)
		public 	@ResponseBody JSONObject getFiscalyearDetailByEmpId(@RequestBody JSONObject fiscaldetail){
			
			return iadminservice.getFiscalyearDetailByEmpId(fiscaldetail);
		}
	//approveLeaverequestByAdmin getLeaverequestById
	@RequestMapping(value = "/getLeaverequestById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject approveLeaverequestByAdmin(@RequestBody JSONObject approvaldata){
		
		return iadminservice.approveLeaverequestByAdmin(approvaldata);
	}
	//**Admin Approve***//
	@RequestMapping(value = "/approveLeaverequestByAdminById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject approveLeaveRequestByAdmin(@RequestBody JSONObject getapprove)throws Exception{
		
		JSONObject obj = new JSONObject();
		obj.put("name","Data get from  db");
		
		 return iadminservice.approveEmployeeLeaveByAdmin(getapprove);
	}

	@RequestMapping(value = "/getLeaveRemainingDays",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getLeaveRemainingDays(@RequestBody JSONObject employee){
		
		 return iadminservice.getLeaveRemainingDays(employee);
	}
    //////*******NEW TYPE LEAVE APPROVE BY ADMIN*****////////
	@RequestMapping(value = "/approveLeaverequestByEmpByAdmin",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject approveLeaverequestByEmpByAdmin(@RequestBody JSONObject approveleave)throws Exception{
		
		 return iadminservice.approveLeaverequestByEmpByAdmin(approveleave);
	}
	
	////
	@RequestMapping(value = "/getEmployeerequestById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getEmployeeLeaveById(@RequestBody JSONObject getapprove)throws Exception{
		
		 return iadminservice.approveEmployeeLeaveByAdmin(getapprove);
	}
	
	@RequestMapping(value = "/getEmployeeById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getEmployeeById(@RequestBody JSONObject employeeid) {
		
		return  iadminservice.getEmployeeById(employeeid);
	}
	@RequestMapping(value = "/getLeavedetailsByFid")
	public @ResponseBody JSONObject getLeavedetailsByFid() {
		return iadminservice.getLeavedetailsByFid();
	}
	@RequestMapping(value = "/getParticularLeavedetailsByLid",method=RequestMethod.POST)
	public @ResponseBody JSONObject getParticularLeavedetailsByLid( @RequestBody JSONObject leaveconfig) {
		return iadminservice.getParticularLeavedetailsByLid(leaveconfig);
	}
	
	@RequestMapping(value = "/get")
	public 	@ResponseBody JSONObject get(@RequestBody JSONObject leaveData ){
		JSONObject obj = new JSONObject();
		obj.put("name","hot java ");
		return obj;
	}
	@RequestMapping(value = "/getAllDataMerge")
	public @ResponseBody JSONObject getAllDataMerge() {
		return iadminservice.getAllDataMerge();
	}
	//PENDING,APPROVE,REJECT[All Employee]
	@RequestMapping(value = "/getAllLeaveHistoryByAdmin")
	public @ResponseBody JSONObject getAllLeaveRequestByAdmin() {
		return iadminservice.getAllLeaveRequestByAdmin();
	}
	//PENDING[All Employee]
	@RequestMapping(value = "/getAllPendingHistoryByAdmin")
	public @ResponseBody JSONObject getAllPendingLeaveRequestByAdmin() {
	
		return iadminservice.getAllPendingLeaveRequestByAdmin();
	}
	@RequestMapping(value = "/getAllLeaveRequestByEidByAdmin",method=RequestMethod.POST)
	public @ResponseBody JSONObject getEmployeeAllLeaveRequestByEid(@RequestBody JSONObject employyeid) {
	  return iemployeeservice.getEmployeeAllLeaveRequestByEid(employyeid);
	}
	
	//pending
	@RequestMapping(value = "/getAllPendingRequestByEidByAdmin",method=RequestMethod.POST)
	public @ResponseBody JSONObject getAllPendingRequestByEidByAdmin(@RequestBody JSONObject employe) {
		return iadminservice.getAllPendingRequestByEidByAdmin(employe);
	}
	
	//ADMIN ADD FINANCIAL YEAR DETAILS//
	@RequestMapping(value = "/setFinancialData",method=RequestMethod.POST)
	public @ResponseBody JSONObject getFinancialData(@RequestBody JSONObject financialyear) {
		
		return iadminservice.getFinancialData(financialyear);
	}
	
	//ADMIN ADD HOLIDAYS  DETAILS//
	@RequestMapping(value = "/setYearlyHolidays",method=RequestMethod.POST)
	public @ResponseBody JSONObject setYearlyHolidays(@RequestBody JSONObject holidays) {
		
		return iadminservice.setYearlyHolidays(holidays);
	}
	
	@RequestMapping(value = "/getHolidaysByTypes",method=RequestMethod.POST)
	public @ResponseBody JSONObject listHolidaysByTypes(@RequestBody JSONObject holidaytypes) {
		
		return iadminservice.listHolidaysByTypes(holidaytypes);
	}
	//setWeekoffdays
	@RequestMapping(value = "/setWeekoffdays",method=RequestMethod.POST)
	public @ResponseBody JSONObject setWeekoffdays(@RequestBody JSONObject weekoff) {
		return iadminservice.setWeekoffdays(weekoff);
	}
	
	
	@RequestMapping(value = "/getFiscalLeaveDetailsByEmpIdAdmin",method=RequestMethod.POST)
	public @ResponseBody JSONObject getFiscalLeaveDetailsByEmpIdAdmin(@RequestBody JSONObject fiscaldata){	
		return  iadminservice.getFiscalLeaveDetailsByEmpIdAdmin(fiscaldata);
		
	}
	@RequestMapping(value = "/getFiscalLeaveDetailsByEmpIdYear",method=RequestMethod.POST)
	public @ResponseBody JSONObject getEmployeeFiscalleaveDetailsByEmpIdYear(@RequestBody JSONObject fiscaldata){	
		return iadminservice.getFiscalyearDetailByEmpIdYear(fiscaldata);
	}
	
	@RequestMapping(value = "/getLOPSumByEmpId",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getLOPSumByEmpId(@RequestBody JSONObject lopsumdata ){		
		 return iadminservice.getLOPSumByEmpId(lopsumdata);
	}
	@RequestMapping(value = "/deleteFiscalLeaveDetailsByEmpIdFid",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject deleteFiscalLeaveDetailsByEmpIdYear(@RequestBody JSONObject fiscaldataid ){				
		 return iadminservice.deleteFiscalyearDetailByEmpIdYear(fiscaldataid);
	}
	
	@RequestMapping(value = "/deleteEmployeeLeaveRequestById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject deleteEmployeeLeaveRequestById(@RequestBody JSONObject employeeLeaveRequestid ){		
		 return iadminservice.deleteEmployeeLeaveRequestById(employeeLeaveRequestid);
	}
	
	@RequestMapping(value = "/deleteFinancialyearById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject deleteFinancialyearById(@RequestBody JSONObject financialyearid ){				
		 return iadminservice.deleteFinancialyearById(financialyearid);
	}
	
	@RequestMapping(value = "/deleteHolidayById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject deleteHolidayById(@RequestBody JSONObject holidayid ){		
		 return iadminservice.deleteHolidayById(holidayid);
	}
	//listLeaveRequestBetweenDates
	
	@RequestMapping(value = "/listLeaveRequestBetweenDates",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject listLeaveRequestBetweenDates(@RequestBody JSONObject leaverequest ){		
		 return iadminservice.listLeaveRequestBetweenDates(leaverequest);
	}
}
