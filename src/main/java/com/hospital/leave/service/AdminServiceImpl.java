package com.hospital.leave.service;

import java.text.ParseException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.leave.dao.AdminDao;
import com.hospital.leave.dao.EmployeeLeaveDao;
import com.hospital.leave.serviceApi.IAdminService;
@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	private AdminDao admindao;
	@Autowired 
	private EmployeeLeaveDao employeedao;
	@Override
	public JSONObject saveLeaveData(JSONObject leaveData) {
	
		return admindao.saveLeaveData(leaveData);
	}
	
	@Override
	public JSONObject getEmployeeLeaveList() {
		return admindao.getEmployeeLeaveList();
	}
	@Override
	public JSONObject approveEmployeeLeaveByAdmin(JSONObject getapprove) throws ParseException {
		return admindao.approveEmployeeLeaveByAdmin(getapprove);
	}
	@Override
	public JSONObject getEmployeeLeaveListByYear() {
		return admindao.getEmployeeLeaveListByYear();
	}
	@Override
	public JSONObject getLeaverequestById(JSONObject leavebyid) {
		return null;
	}
	@Override
	public JSONObject getAllEmployeeLeaverequestByFid(JSONObject financialyearid) {
		return admindao.getAllEmployeeLeaverequestByFid(financialyearid);
	}
	@Override
	public JSONObject approveLeaverequestByAdmin(JSONObject approvaldata) {
		return admindao.approveLeaverequestByAdmin(approvaldata);
	}
	@Override
	public JSONObject getEmployeeFiscalyearDetailByEmpId(JSONObject employeeid) {
		return admindao.getEmployeeFiscalyearDetailByEmpId(employeeid);
	}
	@Override
	public JSONObject getEmployeeLeaveRequestByYearByAdmin() {
	
		return admindao.getEmployeeLeaveRequestByYearByAdmin();
	}
	@Override
	public JSONObject getEmployeeById(JSONObject employeeid) {
		return admindao.getEmployeeById(employeeid);
	}
	@Override
	public JSONObject getLeavedetailsByFid() {
		return admindao.getLeavedetailsByFid();
	}
	@Override
	public JSONObject deleteLeavedetailsByFid(JSONObject leaveid) {
	
		return admindao.deleteLeavedetailsByFid(leaveid);
	}
	@Override
	public JSONObject getParticularLeavedetailsByLid(JSONObject leaveconfig) {
		return admindao.getParticularLeavedetailsByLid(leaveconfig);
	}
	@Override
	public JSONObject getAllDataMerge() {
		return employeedao.getAllDataMerge();
	}
	@Override
	public JSONObject getAllLeaveRequestByAdmin() {
		return admindao.getAllLeaveRequestByAdmin();
	}
	@Override
	public JSONObject getAllPendingRequestByEidByAdmin(JSONObject employe) {
		return admindao.getAllPendingRequestByEidByAdmin(employe);
	}
	@Override
	public JSONObject addEmployee(JSONObject employedata) {
		return null;
	}
	@Override //ADMIN APPROVE//
	public JSONObject approveLeaverequestByEmpByAdmin(JSONObject approveleave) {
		System.out.println("Approval service");	
		return admindao.approveLeaverequestByEmpByAdmin(approveleave);
	}
	@Override
	public JSONObject getAllPendingLeaveRequestByAdmin() {		
		return admindao.getAllPendingLeaveRequestByAdmin();
	}
	@Override
	public JSONObject getFinancialData(JSONObject financialyear) {
		return admindao.getFinancialData(financialyear);
	}
	@Override
	public JSONObject setYearlyHolidays(JSONObject holidays) {
		return admindao.setYearlyHolidays(holidays);
	}
	@Override
	public JSONObject getFiscalyearDetailByEmpId(JSONObject fiscaldetail) {
		return admindao.getFiscalyearDetailByEmpId(fiscaldetail);
	}
	@Override
	public JSONObject getLeaveRemainingDays(JSONObject employee) {
		return admindao.getLeaveRemainingDays(employee);
	}
	@Override
	public JSONObject setWeekoffdays(JSONObject weekoff) {
		return admindao.setWeekoffdays(weekoff);
	}
	@Override
	public JSONObject getFiscalyearDetailByEmpIdYear(JSONObject fiscaldata) {
		return admindao.getFiscalyearDetailByEmpIdYear(fiscaldata);
	}
	@Override
	public JSONObject getFiscalLeaveDetailsByEmpIdAdmin(JSONObject fiscaldata) {
	
		return admindao.getFiscalLeaveDetailsByEmpIdAdmin(fiscaldata);
	}
	@Override
	public JSONObject deleteFiscalyearDetailByEmpIdYear(JSONObject fiscaldataid) {
		return admindao.deleteFiscalyearDetailByEmpIdYear(fiscaldataid);
	}
	@Override
	public JSONObject deleteEmployeeLeaveRequestById(JSONObject employeeLeaveRequestid) {
		return admindao.deleteEmployeeLeaveRequestById(employeeLeaveRequestid);
	}
	@Override
	public JSONObject deleteFinancialyearById(JSONObject financialyearid) {
		return admindao.deleteFinancialyearById(financialyearid);
	}
	@Override
	public JSONObject deleteHolidayById(JSONObject holidayid) {
		return admindao.deleteHolidayById(holidayid);
	}
	
	
}
