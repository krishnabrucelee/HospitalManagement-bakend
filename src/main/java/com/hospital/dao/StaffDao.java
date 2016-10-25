/**
 * 
 */
package com.hospital.dao;

import java.util.List;
import java.util.Map;
import com.hospital.model.Doctor.DoctorType;
import com.hospital.model.Doctor.UserType;
import com.hospital.model.Driver;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Staff;
import com.hospital.model.Users;
import com.hospital.service.DoctorService;
import com.hospital.service.DriverService;
import com.hospital.service.HouseKeepingService;
import com.hospital.service.LabTechnicianService;
import com.hospital.service.NurseService;
import com.hospital.service.PharmacistService;
import com.hospital.service.UserService;
import com.hospital.util.DateUtil;
import com.hospital.model.Role;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.HouseKeeping;
import com.hospital.model.LabTechnician;
import com.hospital.model.Nurse;
import com.hospital.model.Pharmacist;

/**
 * @author Krishna
 *
 */
@Repository
public class StaffDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	private NurseService nurseService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private HouseKeepingService houseKeepingService;
	
	@Autowired
	private DriverService driverService; 
	
	@Autowired
	private LabTechnicianService labTechService;
	
	@Autowired
	private PharmacistService pharmacistService;
	
	@Autowired
	private UserService userService;

	static {
		System.out.println("class StaffDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addStaff(JSONObject staff) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Staff appoint = om.convertValue(staff, Staff.class);
		
		if (staff.containsKey("Doctor")) {
			List<Object> jsonArray = (List<Object>) staff.get("Doctor");
			
			for (int i = 0; i < jsonArray.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) jsonArray.get(i);
				Doctor doctor = new Doctor();
				doctor.setDoctorRegId((Integer) staff1.get("doctorRegId"));
				doctor.setAppointmentEndTime(DateUtil.dateTimeUtil(staff1.get("appointmentStartTime").toString()));
				doctor.setAppointmentStartTime(DateUtil.dateTimeUtil(staff1.get("appointmentEndTime").toString()));
				
				//Load Department 
				Department departmentDetails = session.load(Department.class, (Integer) staff1.get("department_id"));
				doctor.setDepartment(departmentDetails);
				
				//Load Role 
				Role roleDetails = session.load(Role.class, (Integer) staff1.get("role_id"));
				doctor.setRole(roleDetails);
				
				if (staff1.get("userType").toString().equals(UserType.ROOT_ADMIN.toString())) {
					doctor.setUserType(UserType.ROOT_ADMIN);
				}
				if (staff1.get("userType").toString().equals(UserType.DOMAIN_ADMIN.toString())) {
					doctor.setUserType(UserType.DOMAIN_ADMIN);
				}
				if (staff1.get("userType").toString().equals(UserType.DOMAIN_USER.toString())) {
					doctor.setUserType(UserType.DOMAIN_USER);
				}
				if (staff1.get("userType").toString().equals(UserType.USER.toString())) {
					doctor.setUserType(UserType.USER);
				}
				
				doctor.setDoctorDescription(staff1.get("doctorDescription").toString());
				if (staff1.get("doctorType").toString().equals(DoctorType.INHOUSE.toString())) {
					doctor.setDoctorType(DoctorType.INHOUSE);
				}
				if (staff1.get("doctorType").toString().equals(DoctorType.SURGON.toString())) {
					doctor.setDoctorType(DoctorType.SURGON);
				}
				if (staff1.get("doctorType").toString().equals(DoctorType.CONSULTANT.toString())) {
					doctor.setDoctorType(DoctorType.CONSULTANT);
				}
				doctor.setPersonalDetails(staff1.get("personalDetails").toString());
				doctor.setDoctorEmail(staff1.get("doctorEmail").toString());
				Doctor doctorDetails = doctorService.addDoctorFromStaff(doctor);
				// Load Nurse
				appoint.setDoctor(doctorDetails);
				appoint.setEmployeeId(doctorDetails.getDoctorRegId().toString());
//				nurse.setStaffId(appoint.getStaffId().toString());
				
				Users user = new Users();
				user.setProfessionType("Doctor");
				user.setPassword(staff.get("password").toString());
				user.setUserName(staff.get("staffName").toString());
				user.setUserEmail(staff1.get("doctorEmail").toString());
				user.setRole(roleDetails);
				user.setUserType(doctorDetails.getUserType().toString());
				userService.addUser(user);
			}
			
			
			System.out.println(jsonArray);
			
			
		
		} else if (staff.containsKey("Nurse")) {
			List<Object> jsonArray = (List<Object>) staff.get("Nurse");
			
			for (int i = 0; i < jsonArray.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) jsonArray.get(i);
				Nurse nurse = new Nurse();
				nurse.setNurseRegId((Integer) staff1.get("nurseRegId"));
				nurse.setNurseDob(DateUtil.dateUtil(staff1.get("nurseDob").toString()));
				nurse.setNurseEmail(staff1.get("nurseEmail").toString());
				nurse.setNursePhoneNumber((Integer) staff1.get("nursePhoneNumber"));
				nurse.setNurseShift(staff1.get("nurseShift").toString());
				nurse.setNurseType(staff1.get("nurseType").toString());
				//Load Department 
				Department departmentDetails = session.load(Department.class, (Integer) staff1.get("department_id"));
				nurse.setDepartment(departmentDetails);
				
				//Load Role 
				Role roleDetails = session.load(Role.class, (Integer) staff1.get("role_id"));
				nurse.setRole(roleDetails);
				
				if (staff1.get("userType").toString().equals(Nurse.UserType.ROOT_ADMIN.toString())) {
					nurse.setUserType(Nurse.UserType.ROOT_ADMIN);
				}
				if (staff1.get("userType").toString().equals(Nurse.UserType.DOMAIN_ADMIN.toString())) {
					nurse.setUserType(Nurse.UserType.DOMAIN_ADMIN);
				}
				if (staff1.get("userType").toString().equals(Nurse.UserType.DOMAIN_USER.toString())) {
					nurse.setUserType(Nurse.UserType.DOMAIN_USER);
				}
				if (staff1.get("userType").toString().equals(Nurse.UserType.USER.toString())) {
					nurse.setUserType(Nurse.UserType.USER);
				}
				
				Nurse nurseDetails = nurseService.addNurseFromStaff(nurse);
				// Load Nurse
				appoint.setNurse(nurseDetails);
				appoint.setEmployeeId(nurseDetails.getNurseRegId().toString());
//				nurse.setStaffId(appoint.getStaffId().toString());
			}
			
			
			System.out.println(jsonArray);
			
			
			
		} else if (staff.containsKey("HouseKeeping")) {
			List<Object> jsonArray = (List<Object>) staff.get("HouseKeeping");
			
			for (int i = 0; i < jsonArray.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) jsonArray.get(i);
				HouseKeeping houseKeeping = new HouseKeeping();
				houseKeeping.setHouseKeeperAddress(staff1.get("houseKeeperAddress").toString());
				
				//Load Department 
				Department departmentDetails = session.load(Department.class, (Integer) staff1.get("department_id"));
				houseKeeping.setDepartment(departmentDetails);
				
				//Load Role 
				Role roleDetails = session.load(Role.class, (Integer) staff1.get("role_id"));
				houseKeeping.setRole(roleDetails);
				
				if (staff1.get("userType").toString().equals(HouseKeeping.UserType.ROOT_ADMIN.toString())) {
					houseKeeping.setUserType(HouseKeeping.UserType.ROOT_ADMIN);
				}
				if (staff1.get("userType").toString().equals(HouseKeeping.UserType.DOMAIN_ADMIN.toString())) {
					houseKeeping.setUserType(HouseKeeping.UserType.DOMAIN_ADMIN);
				}
				if (staff1.get("userType").toString().equals(HouseKeeping.UserType.DOMAIN_USER.toString())) {
					houseKeeping.setUserType(HouseKeeping.UserType.DOMAIN_USER);
				}
				if (staff1.get("userType").toString().equals(HouseKeeping.UserType.USER.toString())) {
					houseKeeping.setUserType(HouseKeeping.UserType.USER);
				}
				
				houseKeeping.setHouseKeeperDob(DateUtil.dateUtil(staff1.get("houseKeeperDob").toString()));
				houseKeeping.setHouseKeeperEmail(staff1.get("houseKeeperEmail").toString());
				
				houseKeeping.setHouseKeeperRegId((Integer) staff1.get("houseKeeperRegId"));
				houseKeeping.setHouseKeeperPhoneNumber((Integer) staff1.get("houseKeeperPhoneNumber"));
				houseKeeping.setHouseKeeperShift(staff1.get("houseKeeperShift").toString());
				
				houseKeeping.setHouseKeeperType(staff1.get("houseKeeperType").toString());
				houseKeeping.setHouseKeeperWardNumber((Integer) staff1.get("houseKeeperWardNumber"));
				HouseKeeping houseKeepingDetails = houseKeepingService.addHouseKeepingFromStaff(houseKeeping);
				// Load Nurse
				appoint.setHouseKeeping(houseKeepingDetails);
				appoint.setEmployeeId(houseKeepingDetails.getHouseKeeperRegId().toString());
//				nurse.setStaffId(appoint.getStaffId().toString());
			}
			System.out.println(jsonArray);
		
		} else if (staff.containsKey("Driver")) {
			List<Object> jsonArray = (List<Object>) staff.get("Driver");
			
			for (int i = 0; i < jsonArray.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) jsonArray.get(i);
				Driver driver = new Driver();
				driver.setDriverRegId((Integer) staff1.get("driverRegId"));
				driver.setDriverEmail(staff1.get("driverEmail").toString());
				//Load Department 
				Department departmentDetails = session.load(Department.class, (Integer) staff1.get("department_id"));
				driver.setDepartment(departmentDetails);
				
				//Load Role 
				Role roleDetails = session.load(Role.class, (Integer) staff1.get("role_id"));
				driver.setRole(roleDetails);
				
				if (staff1.get("userType").toString().equals(Driver.UserType.ROOT_ADMIN.toString())) {
					driver.setUserType(Driver.UserType.ROOT_ADMIN);
				}
				if (staff1.get("userType").toString().equals(Driver.UserType.DOMAIN_ADMIN.toString())) {
					driver.setUserType(Driver.UserType.DOMAIN_ADMIN);
				}
				if (staff1.get("userType").toString().equals(Driver.UserType.DOMAIN_USER.toString())) {
					driver.setUserType(Driver.UserType.DOMAIN_USER);
				}
				if (staff1.get("userType").toString().equals(Driver.UserType.USER.toString())) {
					driver.setUserType(Driver.UserType.USER);
				}
				
				Driver driverDetails = driverService.addDriverFromStaff(driver);
				// Load driver
				appoint.setDriver(driverDetails);
				appoint.setEmployeeId(driverDetails.getDriverRegId().toString());
//				driver.setStaffId(appoint.getStaffId().toString());
			}
			
			System.out.println(jsonArray);
		} else if (staff.containsKey("LabTechnician")) {
			List<Object> jsonArray = (List<Object>) staff.get("LabTechnician");
			
			for (int i = 0; i < jsonArray.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) jsonArray.get(i);
				LabTechnician labTech = new LabTechnician();
				labTech.setLabTechnicianRegId((Integer) staff1.get("labTechnicianRegId"));
				labTech.setLabTechnicianDob(DateUtil.dateUtil(staff1.get("labTechnicianDob").toString()));
				labTech.setLabTechnicianEmail(staff1.get("labTechnicianEmail").toString());
				labTech.setLabTechnicianPhoneNumber((Integer) staff1.get("labTechnicianPhoneNumber"));
				//Load Department 
				Department departmentDetails = session.load(Department.class, (Integer) staff1.get("department_id"));
				labTech.setDepartment(departmentDetails);
				
				//Load Role 
				Role roleDetails = session.load(Role.class, (Integer) staff1.get("role_id"));
				labTech.setRole(roleDetails);
				
				if (staff1.get("userType").toString().equals(LabTechnician.UserType.ROOT_ADMIN.toString())) {
					labTech.setUserType(LabTechnician.UserType.ROOT_ADMIN);
				}
				if (staff1.get("userType").toString().equals(LabTechnician.UserType.DOMAIN_ADMIN.toString())) {
					labTech.setUserType(LabTechnician.UserType.DOMAIN_ADMIN);
				}
				if (staff1.get("userType").toString().equals(LabTechnician.UserType.DOMAIN_USER.toString())) {
					labTech.setUserType(LabTechnician.UserType.DOMAIN_USER);
				}
				if (staff1.get("userType").toString().equals(LabTechnician.UserType.USER.toString())) {
					labTech.setUserType(LabTechnician.UserType.USER);
				}
				
				LabTechnician labTechDetails = labTechService.addLabTechnicianFromStaff(labTech);
				// Load LabTechnician
				appoint.setLabTechnician(labTechDetails);
				appoint.setEmployeeId(labTechDetails.getLabTechnicianRegId().toString());
//				nurse.setStaffId(appoint.getStaffId().toString());
			}
			
			System.out.println(jsonArray);
			
		} else if (staff.containsKey("Pharmacist")) {
			List<Object> jsonArray = (List<Object>) staff.get("Pharmacist");
			
			for (int i = 0; i < jsonArray.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) jsonArray.get(i);
				Pharmacist pharmacist = new Pharmacist();
				pharmacist.setPharmacistRegId((Integer) staff1.get("pharmacistRegId"));
				pharmacist.setPharmacistDob(DateUtil.dateUtil(staff1.get("pharmacistDob").toString()));
				pharmacist.setPharmacistEmail(staff1.get("pharmacistEmail").toString());
				pharmacist.setPharmacistPhoneNumber((Integer) staff1.get("pharmacistPhoneNumber"));
				//Load Department 
				Department departmentDetails = session.load(Department.class, (Integer) staff1.get("department_id"));
				pharmacist.setDepartment(departmentDetails);
				
				//Load Role 
				Role roleDetails = session.load(Role.class, (Integer) staff1.get("role_id"));
				pharmacist.setRole(roleDetails);
				
				if (staff1.get("userType").toString().equals(Pharmacist.UserType.ROOT_ADMIN.toString())) {
					pharmacist.setUserType(Pharmacist.UserType.ROOT_ADMIN);
				}
				if (staff1.get("userType").toString().equals(Pharmacist.UserType.DOMAIN_ADMIN.toString())) {
					pharmacist.setUserType(Pharmacist.UserType.DOMAIN_ADMIN);
				}
				if (staff1.get("userType").toString().equals(Pharmacist.UserType.DOMAIN_USER.toString())) {
					pharmacist.setUserType(Pharmacist.UserType.DOMAIN_USER);
				}
				if (staff1.get("userType").toString().equals(Pharmacist.UserType.USER.toString())) {
					pharmacist.setUserType(Pharmacist.UserType.USER);
				}
				
				Pharmacist pharmacistDetails = pharmacistService.addPharmacistFromStaff(pharmacist);
				// Load Nurse
				appoint.setPharmacist(pharmacistDetails);
				appoint.setEmployeeId(pharmacistDetails.getPharmacistRegId().toString());
//				nurse.setStaffId(appoint.getStaffId().toString());
			}
			
			System.out.println(jsonArray);
			
		}

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			transaction.commit();
			System.out.println("Save staffs");
			status.put("success", "User details saved");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

	public JSONObject listStaff() {
		System.out.println("Inside Dao1Staff");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<Staff> staffList = null;
		try {
			Query query = session.createQuery("FROM Staff");
			staffList = query.list();
			status.put("Staff", staffList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		}
		return status;
	}

	public JSONObject updateStaff(JSONObject staff) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Staff staffDetails = session.load(Staff.class,
					(Integer) staff.get("staffId"));
			session.update(staffDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public Staff getStaff(Integer staffId) {
		Staff staff = null;
		try {
			session.beginTransaction();
			session.get(Staff.class, staffId);
			staff = (Staff) session.get(Staff.class, staffId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (staff != null) {
			return staff;
		} else {
			return null;
		}
	}

	public JSONObject deleteStaff(JSONObject staffId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Staff staffDetails = session.load(Staff.class,
					(Integer) staffId.get("staffId"));
			session.delete(staffDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

}
