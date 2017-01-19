/**
 * 
 */
package com.hospital.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import com.hospital.model.Doctor.DoctorType;
import com.hospital.model.Doctor.UserType;
import com.hospital.model.Finance;
import com.hospital.model.Inventory;

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
import com.hospital.service.HouseKeepingService;
import com.hospital.service.LabTechnicianService;
import com.hospital.service.NurseService;
import com.hospital.service.PharmacistService;
import com.hospital.service.UserService;
import com.hospital.util.AESCrypt;
import com.hospital.util.DateUtil;

import com.hospital.util.EncryptionUtil;

import com.hospital.model.Role;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.HouseKeeping;
import com.hospital.model.Inventory;
import com.hospital.model.LabTechnician;
import com.hospital.model.Nurse;
import com.hospital.model.Pharmacist;
import com.hospital.model.Receptionist;

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
	private LabTechnicianService labTechService;
	
	@Autowired
	private PharmacistService pharmacistService;
	
	@Autowired
	private UserService userService;
	
	private String secretKey = "DA97AA70B21ADCCA89708EFC05C7E2F2";
	
    /** Constant for generic UTF. */
    public static final String CS_UTF = "utf-8";

    /** Constant for generic AES. */
    public static final String CS_AES = "AES";

	static {
		System.out.println("class StaffDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addStaff(JSONObject staff) throws Exception {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Staff appoint = om.convertValue(staff, Staff.class);
		   String encryptedPassword = AESCrypt.encrypt(staff.get("password").toString());
//        if (staff.get("password") != null) {
//            String strEncoded = Base64.getEncoder().encodeToString(secretKey.getBytes(CS_UTF));
//            byte[] decodedKey = Base64.getDecoder().decode(strEncoded);
//            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, CS_AES);
//            encryptedPassword = new String(EncryptionUtil.encrypt(staff.get("password").toString(), originalKey));
//        }
		
		
		if (staff.containsKey("Doctor")) {
			List<Object> doctorList = new ArrayList<>();
			doctorList.add(staff.get("Doctor"));
			
			for (int i = 0; i < doctorList.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) doctorList.get(i);
				Doctor doctor = new Doctor();
				doctor.setDoctorRegId(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
				doctor.setAppointmentEndTime(DateUtil.InterNationalDateTimeUtil(staff1.get("appointmentStartTime").toString()));
				doctor.setAppointmentStartTime(DateUtil.InterNationalDateTimeUtil(staff1.get("appointmentEndTime").toString()));
				
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
//				Doctor doctorDetails = doctorService.addDoctorFromStaff(doctor);
//				// Load Nurse
				appoint.setDoctor(doctor);
				appoint.setEmployeeId(doctor.getDoctorRegId());
//				nurse.setStaffId(appoint.getStaffId().toString());
				
				Users user = new Users();
				user.setProfessionType("Doctor");
				user.setPassword(encryptedPassword);
				user.setUserName(staff.get("staffName").toString());
				user.setUserEmail(staff1.get("doctorEmail").toString());
				user.setRole(roleDetails);
				user.setUserType(doctor.getUserType().toString());
				userService.addUser(user);
			}
			
			
		
		} else if (staff.containsKey("Nurse")) {
			List<Object> nurseList = new ArrayList<>();
			nurseList.add(staff.get("Nurse"));
			
			for (int i = 0; i < nurseList.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) nurseList.get(i);
				Nurse nurse = new Nurse();
				nurse.setNurseRegId(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
				nurse.setNurseDob(DateUtil.dateUtil(staff1.get("nurseDob").toString()));
				nurse.setNurseEmail(staff1.get("nurseEmail").toString());
				nurse.setNursePhoneNumber(staff1.get("nursePhoneNumber").toString());
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
				
				// Load Nurse
				appoint.setNurse(nurse);
				appoint.setEmployeeId(nurse.getNurseRegId());
//				nurse.setStaffId(appoint.getStaffId().toString());
				
				Users user = new Users();
				user.setProfessionType("Nurse");
				user.setPassword(encryptedPassword);
				user.setUserName(staff.get("staffName").toString());
				user.setUserEmail(staff1.get("nurseEmail").toString());
				user.setRole(roleDetails);
				user.setUserType(nurse.getUserType().toString());
				userService.addUser(user);
			}
			
			
			System.out.println(nurseList);
			
			
			
		}
		else if (staff.containsKey("Receptionist")) {
		List<Object> receptionistList = new ArrayList<>();
		receptionistList.add(staff.get("Receptionist"));
		
		for (int i = 0; i < receptionistList.size(); i++) {
			Map<String, Object> staff1 = (Map<String, Object>) receptionistList.get(i);
			Receptionist receptionist = new Receptionist();
			receptionist.setReceptionistRegId(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
			receptionist.setReceptionistDob(DateUtil.dateUtil(staff1.get("receptionistDob").toString()));
			receptionist.setReceptionistEmail(staff1.get("receptionistEmail").toString());
			receptionist.setReceptionistPhoneNumber(staff1.get("receptionistPhoneNumber").toString());
			
			//Load Department 
			Department departmentDetails = session.load(Department.class, (Integer) staff1.get("department_id"));
			receptionist.setDepartment(departmentDetails);
			
			//Load Role 
			Role roleDetails = session.load(Role.class, (Integer) staff1.get("role_id"));
			receptionist.setRole(roleDetails);
			
			if (staff1.get("userType").toString().equals(Receptionist.UserType.ROOT_ADMIN.toString())) {
				receptionist.setUserType(Receptionist.UserType.ROOT_ADMIN);
			}
			if (staff1.get("userType").toString().equals(Receptionist.UserType.DOMAIN_ADMIN.toString())) {
				receptionist.setUserType(Receptionist.UserType.DOMAIN_ADMIN);
			}
			if (staff1.get("userType").toString().equals(Receptionist.UserType.DOMAIN_USER.toString())) {
				receptionist.setUserType(Receptionist.UserType.DOMAIN_USER);
			}
			if (staff1.get("userType").toString().equals(Receptionist.UserType.USER.toString())) {
				receptionist.setUserType(Receptionist.UserType.USER);
			}
			
			// Load Nurse
			appoint.setReceptionist(receptionist);
			appoint.setEmployeeId(receptionist.getReceptionistRegId());
//			nurse.setStaffId(appoint.getStaffId().toString());
			
			Users user = new Users();
			user.setProfessionType("Receptionist");
			user.setPassword(encryptedPassword);
			user.setUserName(staff.get("staffName").toString());
			user.setUserEmail(staff1.get("receptionistEmail").toString());
			user.setRole(roleDetails);
			user.setUserType(receptionist.getUserType().toString());
			userService.addUser(user);
		}
		System.out.println(receptionistList);
	
			
		}
		
		
		else if (staff.containsKey("HouseKeeping")) {
			List<Object> houseKeepingList = new ArrayList<>();
			houseKeepingList.add(staff.get("HouseKeeping"));
			
			for (int i = 0; i < houseKeepingList.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) houseKeepingList.get(i);
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
				
				houseKeeping.setHouseKeeperRegId(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
				houseKeeping.setHouseKeeperPhoneNumber((Integer) staff1.get("houseKeeperPhoneNumber"));
				houseKeeping.setHouseKeeperShift(staff1.get("houseKeeperShift").toString());
				
				houseKeeping.setHouseKeeperType(staff1.get("houseKeeperType").toString());
				houseKeeping.setHouseKeeperWardNumber((Integer) staff1.get("houseKeeperWardNumber"));
				// Load Nurse
				appoint.setHouseKeeping(houseKeeping);
				appoint.setEmployeeId(houseKeeping.getHouseKeeperRegId());
//				nurse.setStaffId(appoint.getStaffId().toString());
				
				Users user = new Users();
				user.setProfessionType("HouseKeeping");
				user.setPassword(encryptedPassword);
				user.setUserName(staff.get("staffName").toString());
				user.setUserEmail(staff1.get("houseKeeperEmail").toString());
				user.setRole(roleDetails);
				user.setUserType(houseKeeping.getUserType().toString());
				userService.addUser(user);
			}
			System.out.println(houseKeepingList);
		
		} else if (staff.containsKey("Inventory")) {
			List<Object> inventoryList = new ArrayList<>();
			inventoryList.add(staff.get("Inventory"));
			
			for (int i = 0; i < inventoryList.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) inventoryList.get(i);
				Inventory inventory = new Inventory();
				inventory.setInventoryDob(DateUtil.dateUtil(staff1.get("inventoryDob").toString()));
				inventory.setInventoryEmail(staff1.get("inventoryEmail").toString());
				
				inventory.setInventoryRegId(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
				inventory.setInventoryPhoneNumber(staff1.get("inventoryPhoneNumber").toString());
				
				inventory.setInventoryType(staff1.get("inventoryType").toString());
				//Load Department 
				Department departmentDetails = session.load(Department.class, (Integer) staff1.get("department_id"));
				inventory.setDepartment(departmentDetails);
				
				//Load Role 
				Role roleDetails = session.load(Role.class, (Integer) staff1.get("role_id"));
				inventory.setRole(roleDetails);
				
				if (staff1.get("userType").toString().equals(Inventory.UserType.ROOT_ADMIN.toString())) {
					inventory.setUserType(Inventory.UserType.ROOT_ADMIN);
				}
				if (staff1.get("userType").toString().equals(Inventory.UserType.DOMAIN_ADMIN.toString())) {
					inventory.setUserType(Inventory.UserType.DOMAIN_ADMIN);
				}
				if (staff1.get("userType").toString().equals(Inventory.UserType.DOMAIN_USER.toString())) {
					inventory.setUserType(Inventory.UserType.DOMAIN_USER);
				}
				if (staff1.get("userType").toString().equals(Inventory.UserType.USER.toString())) {
					inventory.setUserType(Inventory.UserType.USER);
				}
				
				// Load inventory
				appoint.setInventory(inventory);
				appoint.setEmployeeId(inventory.getInventoryRegId().toString());
//				inventory.setStaffId(appoint.getStaffId().toString());
				
				Users user = new Users();
				user.setProfessionType("Inventory");
				user.setPassword(encryptedPassword);
				user.setUserName(staff.get("staffName").toString());
				user.setUserEmail(staff1.get("inventoryEmail").toString());
				user.setRole(roleDetails);
				user.setUserType(inventory.getUserType().toString());
				userService.addUser(user);
			}
			
			System.out.println(inventoryList);
			
		} else if (staff.containsKey("Finance")) {
			List<Object> financeList = new ArrayList<>();
			financeList.add(staff.get("Finance"));
			
			for (int i = 0; i < financeList.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) financeList.get(i);
				Finance finance = new Finance();
				finance.setFinancerDob(DateUtil.dateUtil(staff1.get("financeDob").toString()));
				finance.setFinancerEmail(staff1.get("financeEmail").toString());
				
				finance.setFinancerRegId(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
				finance.setFinancerPhoneNumber(staff1.get("financePhoneNumber").toString());

				//Load Department 
				Department departmentDetails = session.load(Department.class, (Integer) staff1.get("department_id"));
				finance.setDepartment(departmentDetails);
				
				//Load Role 
				Role roleDetails = session.load(Role.class, (Integer) staff1.get("role_id"));
				finance.setRole(roleDetails);
				
				if (staff1.get("userType").toString().equals(Finance.UserType.ROOT_ADMIN.toString())) {
					finance.setUserType(Finance.UserType.ROOT_ADMIN);
				}
				if (staff1.get("userType").toString().equals(Finance.UserType.DOMAIN_ADMIN.toString())) {
					finance.setUserType(Finance.UserType.DOMAIN_ADMIN);
				}
				if (staff1.get("userType").toString().equals(Finance.UserType.DOMAIN_USER.toString())) {
					finance.setUserType(Finance.UserType.DOMAIN_USER);
				}
				if (staff1.get("userType").toString().equals(Finance.UserType.USER.toString())) {
					finance.setUserType(Finance.UserType.USER);
				}
				
				// Load finance
				appoint.setFinance(finance);
				appoint.setEmployeeId(finance.getFinancerRegId());
//				finance.setStaffId(appoint.getStaffId().toString());
				
				Users user = new Users();
				user.setProfessionType("Finance");
				user.setPassword(encryptedPassword);
				user.setUserName(staff.get("staffName").toString());
				user.setUserEmail(staff1.get("financeEmail").toString());
				user.setRole(roleDetails);
				user.setUserType(finance.getUserType().toString());
				userService.addUser(user);
			}
			
			System.out.println(financeList);
			
		}  else if (staff.containsKey("LabTechnician")) {
			List<Object> labTechnicianList = new ArrayList<>();
			labTechnicianList.add(staff.get("LabTechnician"));
			
			for (int i = 0; i < labTechnicianList.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) labTechnicianList.get(i);
				LabTechnician labTech = new LabTechnician();
				labTech.setLabTechnicianRegId(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
				labTech.setLabTechnicianDob(DateUtil.dateUtil(staff1.get("labTechnicianDob").toString()));
				labTech.setLabTechnicianEmail(staff1.get("labTechnicianEmail").toString());
				labTech.setLabTechnicianPhoneNumber(staff1.get("labTechnicianPhoneNumber").toString());
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
				
				// Load LabTechnician
				appoint.setLabTechnician(labTech);
				appoint.setEmployeeId(labTech.getLabTechnicianRegId());
//				nurse.setStaffId(appoint.getStaffId().toString());
				
				Users user = new Users();
				user.setProfessionType("LabTechnician");
				user.setPassword(encryptedPassword);
				user.setUserName(staff.get("staffName").toString());
				user.setUserEmail(staff1.get("labTechnicianEmail").toString());
				user.setRole(roleDetails);
				user.setUserType(labTech.getUserType().toString());
				userService.addUser(user);
			}
			
			System.out.println(labTechnicianList);
			
		} else if (staff.containsKey("Pharmacist")) {
			List<Object> pharmacistList = new ArrayList<>();
			pharmacistList.add(staff.get("Pharmacist"));
			
			for (int i = 0; i < pharmacistList.size(); i++) {
				Map<String, Object> staff1 = (Map<String, Object>) pharmacistList.get(i);
				Pharmacist pharmacist = new Pharmacist();
				pharmacist.setPharmacistRegId(String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
				pharmacist.setPharmacistDob(DateUtil.dateUtil(staff1.get("pharmacistDob").toString()));
				pharmacist.setPharmacistEmail(staff1.get("pharmacistEmail").toString());
				pharmacist.setPharmacistPhoneNumber(staff1.get("pharmacistPhoneNumber").toString());
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
				
				// Load Nurse
				appoint.setPharmacist(pharmacist);
				appoint.setEmployeeId(pharmacist.getPharmacistRegId());
//				nurse.setStaffId(appoint.getStaffId().toString());
				
				Users user = new Users();
				user.setProfessionType("Pharmacist");
				user.setPassword(encryptedPassword);
				user.setUserName(staff.get("staffName").toString());
				user.setUserEmail(staff1.get("pharmacistEmail").toString());
				user.setRole(roleDetails);
				user.setUserType(pharmacist.getUserType().toString());
				userService.addUser(user);
			}
			
			System.out.println(pharmacistList);
			
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
		} finally {
			if (session.isOpen()) {
				session.close();
			}
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
		} finally {
			if (session.isOpen()) {
				session.close();
			}
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
		} finally {
			if (session.isOpen()) {
				session.close();
			}
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
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return status;
	}

}
