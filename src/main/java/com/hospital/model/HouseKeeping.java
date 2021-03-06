/**
 * 
 */
package com.hospital.model;

import java.util.Date;
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
import com.hospital.model.Doctor.UserType;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "houseKeeping")
public class HouseKeeping {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "houseKeeper_id")
	private Integer houseKeeperId;

	@Column(name = "houseKeeper_reg_id")
	private String houseKeeperRegId;

	@Column(name = "staff_id")
	private String staffId;

	@Column(name = "houseKeeper_type")
	private String houseKeeperType;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "houseKeeper_dob")
	private Date houseKeeperDob;

	@Column(name = "houseKeeper_email")
	private String houseKeeperEmail;

	@Column(name = "houseKeeper_phone")
	private Integer houseKeeperPhoneNumber;

	@Column(name = "houseKeeper_address")
	private String houseKeeperAddress;

	@Column(name = "houseKeeper_ward_number")
	private Integer houseKeeperWardNumber;

	@Column(name = "houseKeeper_shift")
	private String houseKeeperShift;

	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_houseKeeping_FK"))
	private Department department;

	@OneToOne(targetEntity = RoomManagement.class)
	@JoinColumn(name = "room_management_id", referencedColumnName = "room_management_id", foreignKey = @ForeignKey(name = "room_management_houseKeeping_FK"))
	private RoomManagement roomManagement;

	/** User role. */
	@OneToOne(targetEntity = Role.class)
	@JoinColumn(name = "role_id", referencedColumnName = "role_id", foreignKey = @ForeignKey(name = "role_houseKeeping_FK"))
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
	 * Get the houseKeeperId of HouseKeeping.
	 *
	 * @return the houseKeeperId
	 */
	public Integer getHouseKeeperId() {
		return houseKeeperId;
	}

	/**
	 * Set the houseKeeperId of HouseKeeping.
	 *
	 * @param houseKeeperId
	 *            the houseKeeperId to set
	 */
	public void setHouseKeeperId(Integer houseKeeperId) {
		this.houseKeeperId = houseKeeperId;
	}

	/**
	 * Get the houseKeeperRegId of HouseKeeping.
	 *
	 * @return the houseKeeperRegId
	 */
	public String getHouseKeeperRegId() {
		return houseKeeperRegId;
	}

	/**
	 * Set the houseKeeperRegId of HouseKeeping.
	 *
	 * @param string
	 *            the houseKeeperRegId to set
	 */
	public void setHouseKeeperRegId(String string) {
		this.houseKeeperRegId = string;
	}

	/**
	 * Get the staffId of HouseKeeping.
	 *
	 * @return the staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * Set the staffId of HouseKeeping.
	 *
	 * @param staffId
	 *            the staffId to set
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * Get the houseKeeperType of HouseKeeping.
	 *
	 * @return the houseKeeperType
	 */
	public String getHouseKeeperType() {
		return houseKeeperType;
	}

	/**
	 * Set the houseKeeperType of HouseKeeping.
	 *
	 * @param houseKeeperType
	 *            the houseKeeperType to set
	 */
	public void setHouseKeeperType(String houseKeeperType) {
		this.houseKeeperType = houseKeeperType;
	}

	/**
	 * Get the houseKeeperDob of HouseKeeping.
	 *
	 * @return the houseKeeperDob
	 */
	public Date getHouseKeeperDob() {
		return houseKeeperDob;
	}

	/**
	 * Set the houseKeeperDob of HouseKeeping.
	 *
	 * @param houseKeeperDob
	 *            the houseKeeperDob to set
	 */
	public void setHouseKeeperDob(Date houseKeeperDob) {
		this.houseKeeperDob = houseKeeperDob;
	}

	/**
	 * Get the houseKeeperEmail of HouseKeeping.
	 *
	 * @return the houseKeeperEmail
	 */
	public String getHouseKeeperEmail() {
		return houseKeeperEmail;
	}

	/**
	 * Set the houseKeeperEmail of HouseKeeping.
	 *
	 * @param houseKeeperEmail
	 *            the houseKeeperEmail to set
	 */
	public void setHouseKeeperEmail(String houseKeeperEmail) {
		this.houseKeeperEmail = houseKeeperEmail;
	}

	/**
	 * Get the houseKeeperPhoneNumber of HouseKeeping.
	 *
	 * @return the houseKeeperPhoneNumber
	 */
	public Integer getHouseKeeperPhoneNumber() {
		return houseKeeperPhoneNumber;
	}

	/**
	 * Set the houseKeeperPhoneNumber of HouseKeeping.
	 *
	 * @param houseKeeperPhoneNumber
	 *            the houseKeeperPhoneNumber to set
	 */
	public void setHouseKeeperPhoneNumber(Integer houseKeeperPhoneNumber) {
		this.houseKeeperPhoneNumber = houseKeeperPhoneNumber;
	}

	/**
	 * Get the houseKeeperAddress of HouseKeeping.
	 *
	 * @return the houseKeeperAddress
	 */
	public String getHouseKeeperAddress() {
		return houseKeeperAddress;
	}

	/**
	 * Set the houseKeeperAddress of HouseKeeping.
	 *
	 * @param houseKeeperAddress
	 *            the houseKeeperAddress to set
	 */
	public void setHouseKeeperAddress(String houseKeeperAddress) {
		this.houseKeeperAddress = houseKeeperAddress;
	}

	/**
	 * Get the houseKeeperWardNumber of HouseKeeping.
	 *
	 * @return the houseKeeperWardNumber
	 */
	public Integer getHouseKeeperWardNumber() {
		return houseKeeperWardNumber;
	}

	/**
	 * Set the houseKeeperWardNumber of HouseKeeping.
	 *
	 * @param houseKeeperWardNumber
	 *            the houseKeeperWardNumber to set
	 */
	public void setHouseKeeperWardNumber(Integer houseKeeperWardNumber) {
		this.houseKeeperWardNumber = houseKeeperWardNumber;
	}

	/**
	 * Get the houseKeeperShift of HouseKeeping.
	 *
	 * @return the houseKeeperShift
	 */
	public String getHouseKeeperShift() {
		return houseKeeperShift;
	}

	/**
	 * Set the houseKeeperShift of HouseKeeping.
	 *
	 * @param houseKeeperShift
	 *            the houseKeeperShift to set
	 */
	public void setHouseKeeperShift(String houseKeeperShift) {
		this.houseKeeperShift = houseKeeperShift;
	}

	/**
	 * Get the department of HouseKeeping.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of HouseKeeping.
	 *
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the roomManagement of HouseKeeping.
	 *
	 * @return the roomManagement
	 */
	public RoomManagement getRoomManagement() {
		return roomManagement;
	}

	/**
	 * Set the roomManagement of HouseKeeping.
	 *
	 * @param roomManagement
	 *            the roomManagement to set
	 */
	public void setRoomManagement(RoomManagement roomManagement) {
		this.roomManagement = roomManagement;
	}

	/**
	 * Get the role of HouseKeeping.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Set the role of HouseKeeping.
	 *
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Get the userType of HouseKeeping.
	 *
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Set the userType of HouseKeeping.
	 *
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
