/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "staff")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "staff_id")
	private Integer staffId;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "staff_name")
	private String staffName;
	
	@Column(name = "staff_age")
	private String staffAge;

	@Column(name = "staff_gender")
	private String staffGender;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "staff_doj")
	private Date staffDoj;
	
	@Column(name = "staff_address")
	private String staffAddress;
	
	@Column(name = "staff_ward_number")
	private Integer staffWardNumber;
	
	@Column(name = "staff_work")
	private String staffWork;
	
	@Column(name = "staff_role")
	private String staffRole;
	
	@OneToOne(targetEntity = Nurse.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "nurse_id", referencedColumnName = "nurse_id", foreignKey = @ForeignKey(name = "nurse_STF_FK"))
	private Nurse nurse;

	@OneToOne(targetEntity = Doctor.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", foreignKey = @ForeignKey(name = "doctor_STF_FK"))
	private Doctor doctor;
	
	@OneToOne(targetEntity = HouseKeeping.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "houseKeeper_id", referencedColumnName = "houseKeeper_id", foreignKey = @ForeignKey(name = "house_keeping_STF_FK"))
	private HouseKeeping houseKeeping;

	/**
	 * Get the staffId of Staff.
	 *
	 * @return the staffId
	 */
	public Integer getStaffId() {
		return staffId;
	}

	/**
	 * Set the staffId of Staff.
	 *
	 * @param staffId the staffId to set
	 */
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * Get the employeeId of Staff.
	 *
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * Set the employeeId of Staff.
	 *
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * Get the staffName of Staff.
	 *
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * Set the staffName of Staff.
	 *
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * Get the staffAge of Staff.
	 *
	 * @return the staffAge
	 */
	public String getStaffAge() {
		return staffAge;
	}

	/**
	 * Set the staffAge of Staff.
	 *
	 * @param staffAge the staffAge to set
	 */
	public void setStaffAge(String staffAge) {
		this.staffAge = staffAge;
	}

	/**
	 * Get the staffGender of Staff.
	 *
	 * @return the staffGender
	 */
	public String getStaffGender() {
		return staffGender;
	}

	/**
	 * Set the staffGender of Staff.
	 *
	 * @param staffGender the staffGender to set
	 */
	public void setStaffGender(String staffGender) {
		this.staffGender = staffGender;
	}

	/**
	 * Get the staffDoj of Staff.
	 *
	 * @return the staffDoj
	 */
	public Date getStaffDoj() {
		return staffDoj;
	}

	/**
	 * Set the staffDoj of Staff.
	 *
	 * @param staffDoj the staffDoj to set
	 */
	public void setStaffDoj(Date staffDoj) {
		this.staffDoj = staffDoj;
	}

	/**
	 * Get the staffAddress of Staff.
	 *
	 * @return the staffAddress
	 */
	public String getStaffAddress() {
		return staffAddress;
	}

	/**
	 * Set the staffAddress of Staff.
	 *
	 * @param staffAddress the staffAddress to set
	 */
	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}

	/**
	 * Get the staffWardNumber of Staff.
	 *
	 * @return the staffWardNumber
	 */
	public Integer getStaffWardNumber() {
		return staffWardNumber;
	}

	/**
	 * Set the staffWardNumber of Staff.
	 *
	 * @param staffWardNumber the staffWardNumber to set
	 */
	public void setStaffWardNumber(Integer staffWardNumber) {
		this.staffWardNumber = staffWardNumber;
	}

	/**
	 * Get the staffWork of Staff.
	 *
	 * @return the staffWork
	 */
	public String getStaffWork() {
		return staffWork;
	}

	/**
	 * Set the staffWork of Staff.
	 *
	 * @param staffWork the staffWork to set
	 */
	public void setStaffWork(String staffWork) {
		this.staffWork = staffWork;
	}

	/**
	 * Get the staffRole of Staff.
	 *
	 * @return the staffRole
	 */
	public String getStaffRole() {
		return staffRole;
	}

	/**
	 * Set the staffRole of Staff.
	 *
	 * @param staffRole the staffRole to set
	 */
	public void setStaffRole(String staffRole) {
		this.staffRole = staffRole;
	}

	/**
	 * Get the nurse of Staff.
	 *
	 * @return the nurse
	 */
	public Nurse getNurse() {
		return nurse;
	}

	/**
	 * Set the nurse of Staff.
	 *
	 * @param nurse the nurse to set
	 */
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	/**
	 * Get the doctor of Staff.
	 *
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * Set the doctor of Staff.
	 *
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * Get the houseKeeping of Staff.
	 *
	 * @return the houseKeeping
	 */
	public HouseKeeping getHouseKeeping() {
		return houseKeeping;
	}

	/**
	 * Set the houseKeeping of Staff.
	 *
	 * @param houseKeeping the houseKeeping to set
	 */
	public void setHouseKeeping(HouseKeeping houseKeeping) {
		this.houseKeeping = houseKeeping;
	}
	
	
}
