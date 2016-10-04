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
	private Integer houseKeeperRegId;
	
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
	
	@OneToOne(targetEntity = Ward.class)
	@JoinColumn(name = "ward_id", referencedColumnName = "ward_id", foreignKey = @ForeignKey(name = "ward_houseKeeping_FK"))
	private Ward ward;

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
	 * @param houseKeeperId the houseKeeperId to set
	 */
	public void setHouseKeeperId(Integer houseKeeperId) {
		this.houseKeeperId = houseKeeperId;
	}

	/**
	 * Get the houseKeeperRegId of HouseKeeping.
	 *
	 * @return the houseKeeperRegId
	 */
	public Integer getHouseKeeperRegId() {
		return houseKeeperRegId;
	}

	/**
	 * Set the houseKeeperRegId of HouseKeeping.
	 *
	 * @param houseKeeperRegId the houseKeeperRegId to set
	 */
	public void setHouseKeeperRegId(Integer houseKeeperRegId) {
		this.houseKeeperRegId = houseKeeperRegId;
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
	 * @param staffId the staffId to set
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
	 * @param houseKeeperType the houseKeeperType to set
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
	 * @param houseKeeperDob the houseKeeperDob to set
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
	 * @param houseKeeperEmail the houseKeeperEmail to set
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
	 * @param houseKeeperPhoneNumber the houseKeeperPhoneNumber to set
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
	 * @param houseKeeperAddress the houseKeeperAddress to set
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
	 * @param houseKeeperWardNumber the houseKeeperWardNumber to set
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
	 * @param houseKeeperShift the houseKeeperShift to set
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
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the ward of HouseKeeping.
	 *
	 * @return the ward
	 */
	public Ward getWard() {
		return ward;
	}

	/**
	 * Set the ward of HouseKeeping.
	 *
	 * @param ward the ward to set
	 */
	public void setWard(Ward ward) {
		this.ward = ward;
	}
	
}
