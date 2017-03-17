package com.hospital.leave.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.leave.dao.AdminDao;
import com.hospital.leave.dao.EmployeLeaveRequestDAO;
import com.hospital.leave.dao.EmployeeLeaveDao;
import com.hospital.leave.dao.RequestLeaveEmployeeDao;
import com.hospital.leave.serviceApi.IEmployeeLeaveRequest;
@Service
public class EmployeeLeaveRequestImpl implements IEmployeeLeaveRequest {
	@Autowired
	private EmployeeLeaveDao employeedao;
	@Autowired
	private AdminDao admindao;
	@Autowired
	private EmployeLeaveRequestDAO leavedao;
	@Autowired
	private RequestLeaveEmployeeDao leaverequestdao;
	@Override
	public JSONObject createLeaverequest(JSONObject leaverequest) {
		return null;
	}

	@Override
	public JSONObject getLeaveRequestHistoryById(JSONObject employee) {
			
		return employeedao.getLeaveRequestHistoryById(employee);
	}

	@Override
	public JSONObject getLeavedataList() {
		return employeedao.getLeavedataList();
	}

	@Override
	public JSONObject getEmployeeFiscalyearleavedeatils() {
		return employeedao.getEmployeeFiscalyearleavedeatils();
	}

	@Override
	public JSONObject getEmployeeleaveRequestDataById(JSONObject employedata) {
		System.out.println("Inside Employee Service");
		return employeedao.getEmployeeleaveRequestDataById(employedata);
	}

	@Override
	public JSONObject getEmployeeFiscalyearleaveByEmpId(JSONObject employeid) {
		System.out.println("Inside employee controller");
		return employeedao.getEmployeeFiscalyearleaveByEmpId(employeid);
	}

	@Override
	public JSONObject getParticularLeaveDataByEFLId(JSONObject employeleave) {
		return null;
	}

	@Override
	public JSONObject getAllDataEmployeeById(JSONObject employid) {
		return employeedao.getAllDataEmployeeById(employid);
	}

	@Override
	public JSONObject getEmployeeAllLeaveRequestByEid(JSONObject employyeid) {
		return employeedao.getEmployeeAllLeaveRequestByEid(employyeid);
	}

	@Override
	public JSONObject getEmployeeFiscalleaveDetailsByEmpId(JSONObject employee) {
		return employeedao.getEmployeeFiscalleaveDetailsByEmpId(employee);
	}

	

	

	@Override
	public JSONObject getEmployeeAllPendingdata(JSONObject emppending) {
		return leavedao.getEmployeeAllPendingdata(emppending);
	}
//Last final
	@Override
	public JSONObject requestLeaveEmployee(JSONObject emprequestLeave) {
		return leaverequestdao.requestLeaveEmployee(emprequestLeave);
	}

	@Override
	public JSONObject employeeApplyLeave(JSONObject employeerequestLeave) {
		return leaverequestdao.employeeApplyLeave(employeerequestLeave);
	}

	@Override
	public JSONObject employeeAllPARViewStatus(JSONObject leaveRequestStatus) {
		return leaverequestdao.employeeAllPARViewStatus(leaveRequestStatus);
	}

	@Override
	public JSONObject employeeViewPendingStatus(JSONObject leaveRequestpendingStatus) {
		return leaverequestdao.employeeViewPendingStatus(leaveRequestpendingStatus);
	}

	@Override
	public JSONObject employeeViewApprovedStatus(JSONObject leaveRequestApprovedStatus) {
		return leaverequestdao.employeeViewApprovedStatus(leaveRequestApprovedStatus);
	}

	@Override
	public JSONObject employeeCurrentMonthLeaveRequestStatus(JSONObject currentMonthLeaveRequestStatus) {
		return leaverequestdao.employeeCurrentMonthLeaveRequestStatus(currentMonthLeaveRequestStatus);
	}

	@Override
	public JSONObject getemployeeCurrentRangeLeaveRequest(JSONObject currentRangeLeaveRequest) {
		return leaverequestdao.getemployeeCurrentRangeLeaveRequest(currentRangeLeaveRequest);
	}

	@Override
	public JSONObject getEmployeeFiscalyearleaveBalance(JSONObject employeedata) {
		return leaverequestdao.getEmployeeFiscalyearleaveBalance(employeedata);
	}

	@Override
	public JSONObject getFiscalBalanceEmployeeId() {
		return employeedao.getFiscalBalanceEmployeeId();
	}

}
