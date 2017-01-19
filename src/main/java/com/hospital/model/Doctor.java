package com.hospital.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Krishna
 *
 */
@Entity
@Table (name = "doctor")
public class Doctor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "doctor_id")
	private Integer doctorId;
	
	@Column(name = "doctor_reg_id")
	private String doctorRegId;
	
	@Column(name = "doctor_name")
	private String doctorName;
	
	@Column(name = "doctor_email")
	private String doctorEmail;
	
	@Column(name = "doctor_description")
	private String doctorDescription;
	
	@Column(name = "doctor_age")
	private Integer doctorAge;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_doctor_FK"))
	private Department department;
	
	@Column(name = "appointment_start_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date appointmentStartTime;
	
	@Column(name = "appointment_end_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date appointmentEndTime;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "personal_details")
	private String personalDetails;

	@Column(name = "doctor_type")
	private DoctorType doctorType;
	
	public enum DoctorType {
		INHOUSE,
		SURGON,
		CONSULTANT
	}
	
	@OneToMany(mappedBy="doctor")
	private List<NewLabRequest> newLabRequest;
	
    /** User role. */
    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", foreignKey = @ForeignKey(name = "role_doctor_FK"))
    private Role role;
    
    /** User type of the user. */
    @Column(name = "user_type")
    private UserType userType;
	
    /** Define user type. */
    public enum UserType {
        /** Define type constant. */
        DOMAIN_ADMIN, ROOT_ADMIN, DOMAIN_USER, USER;
    }
    
	/**
	 * Get the doctorId of Doctor.
	 *
	 * @return the doctorId
	 */
	public Integer getDoctorId() {
		return doctorId;
	}

	/**
	 * Set the doctorId of Doctor.
	 *
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * Get the doctorRegId of Doctor.
	 *
	 * @return the doctorRegId
	 */
	public String getDoctorRegId() {
		return doctorRegId;
	}

	/**
	 * Set the doctorRegId of Doctor.
	 *
	 * @param string the doctorRegId to set
	 */
	public void setDoctorRegId(String string) {
		this.doctorRegId = string;
	}

	/**
	 * Get the doctorName of Doctor.
	 *
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * Set the doctorName of Doctor.
	 *
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * Get the doctorDescription of Doctor.
	 *
	 * @return the doctorDescription
	 */
	public String getDoctorDescription() {
		return doctorDescription;
	}

	/**
	 * Set the doctorDescription of Doctor.
	 *
	 * @param doctorDescription the doctorDescription to set
	 */
	public void setDoctorDescription(String doctorDescription) {
		this.doctorDescription = doctorDescription;
	}

	/**
	 * Get the doctorEmail of Doctor.
	 *
	 * @return the doctorEmail
	 */
	public String getDoctorEmail() {
		return doctorEmail;
	}

	/**
	 * Set the doctorEmail of Doctor.
	 *
	 * @param doctorEmail the doctorEmail to set
	 */
	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	/**
	 * Get the doctorAge of Doctor.
	 *
	 * @return the doctorAge
	 */
	public Integer getDoctorAge() {
		return doctorAge;
	}

	/**
	 * Set the doctorAge of Doctor.
	 *
	 * @param doctorAge the doctorAge to set
	 */
	public void setDoctorAge(Integer doctorAge) {
		this.doctorAge = doctorAge;
	}

	/**
	 * Get the department of Doctor.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Doctor.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the appointmentStartTime of Doctor.
	 *
	 * @return the appointmentStartTime
	 */
	public Date getAppointmentStartTime() {
		return appointmentStartTime;
	}

	/**
	 * Set the appointmentStartTime of Doctor.
	 *
	 * @param appointmentStartTime the appointmentStartTime to set
	 */
	public void setAppointmentStartTime(Date appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	/**
	 * Get the appointmentEndTime of Doctor.
	 *
	 * @return the appointmentEndTime
	 */
	public Date getAppointmentEndTime() {
		return appointmentEndTime;
	}

	/**
	 * Set the appointmentEndTime of Doctor.
	 *
	 * @param appointmentEndTime the appointmentEndTime to set
	 */
	public void setAppointmentEndTime(Date appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	/**
	 * Get the gender of Doctor.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Set the gender of Doctor.
	 *
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Get the personalDetails of Doctor.
	 *
	 * @return the personalDetails
	 */
	public String getPersonalDetails() {
		return personalDetails;
	}

	/**
	 * Set the personalDetails of Doctor.
	 *
	 * @param personalDetails the personalDetails to set
	 */
	public void setPersonalDetails(String personalDetails) {
		this.personalDetails = personalDetails;
	}

	/**
	 * Get the doctorType of Doctor.
	 *
	 * @return the doctorType
	 */
	public DoctorType getDoctorType() {
		return doctorType;
	}

	/**
	 * Set the doctorType of Doctor.
	 *
	 * @param doctorType the doctorType to set
	 */
	public void setDoctorType(DoctorType doctorType) {
		this.doctorType = doctorType;
	}

	/**
	 * Get the newLabRequest of Doctor.
	 *
	 * @return the newLabRequest
	 */
	public List<NewLabRequest> getNewLabRequest() {
		return newLabRequest;
	}

	/**
	 * Set the newLabRequest of Doctor.
	 *
	 * @param newLabRequest the newLabRequest to set
	 */
	public void setNewLabRequest(List<NewLabRequest> newLabRequest) {
		this.newLabRequest = newLabRequest;
	}

	/**
	 * Get the role of Doctor.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Set the role of Doctor.
	 *
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Get the userType of Doctor.
	 *
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Set the userType of Doctor.
	 *
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
