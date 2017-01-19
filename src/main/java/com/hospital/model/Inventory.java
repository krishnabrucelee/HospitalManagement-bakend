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
import com.hospital.model.Inventory.UserType;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "inventory_id")
	private Integer inventoryId;
	
	@Column(name = "inventory_reg_id")
	private String inventoryRegId;
	
	@Column(name = "staff_id")
	private String staffId;
	
	@Column(name = "inventory_type")
	private String inventoryType;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "inventory_dob")
	private Date inventoryDob;
	
	@Column(name = "inventory_email")
	private String inventoryEmail;
	
	@Column(name = "inventory_phone")
	private String inventoryPhoneNumber;
	
	@Column(name = "inventory_address")
	private String inventoryAddress;
		
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_inventory_FK"))
	private Department department;

    /** User role. */
    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", foreignKey = @ForeignKey(name = "role_inventory_FK"))
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
	 * Get the inventoryId of Inventory.
	 *
	 * @return the inventoryId
	 */
	public Integer getInventoryId() {
		return inventoryId;
	}

	/**
	 * Set the inventoryId of Inventory.
	 *
	 * @param inventoryId the inventoryId to set
	 */
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * Get the inventoryRegId of Inventory.
	 *
	 * @return the inventoryRegId
	 */
	public String getInventoryRegId() {
		return inventoryRegId;
	}

	/**
	 * Set the inventoryRegId of Inventory.
	 *
	 * @param inventoryRegId the inventoryRegId to set
	 */
	public void setInventoryRegId(String inventoryRegId) {
		this.inventoryRegId = inventoryRegId;
	}

	/**
	 * Get the staffId of Inventory.
	 *
	 * @return the staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * Set the staffId of Inventory.
	 *
	 * @param staffId the staffId to set
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * Get the inventoryType of Inventory.
	 *
	 * @return the inventoryType
	 */
	public String getInventoryType() {
		return inventoryType;
	}

	/**
	 * Set the inventoryType of Inventory.
	 *
	 * @param inventoryType the inventoryType to set
	 */
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	/**
	 * Get the inventoryDob of Inventory.
	 *
	 * @return the inventoryDob
	 */
	public Date getInventoryDob() {
		return inventoryDob;
	}

	/**
	 * Set the inventoryDob of Inventory.
	 *
	 * @param inventoryDob the inventoryDob to set
	 */
	public void setInventoryDob(Date inventoryDob) {
		this.inventoryDob = inventoryDob;
	}

	/**
	 * Get the inventoryEmail of Inventory.
	 *
	 * @return the inventoryEmail
	 */
	public String getInventoryEmail() {
		return inventoryEmail;
	}

	/**
	 * Set the inventoryEmail of Inventory.
	 *
	 * @param inventoryEmail the inventoryEmail to set
	 */
	public void setInventoryEmail(String inventoryEmail) {
		this.inventoryEmail = inventoryEmail;
	}

	/**
	 * Get the inventoryPhoneNumber of Inventory.
	 *
	 * @return the inventoryPhoneNumber
	 */
	public String getInventoryPhoneNumber() {
		return inventoryPhoneNumber;
	}

	/**
	 * Set the inventoryPhoneNumber of Inventory.
	 *
	 * @param inventoryPhoneNumber the inventoryPhoneNumber to set
	 */
	public void setInventoryPhoneNumber(String inventoryPhoneNumber) {
		this.inventoryPhoneNumber = inventoryPhoneNumber;
	}

	/**
	 * Get the inventoryAddress of Inventory.
	 *
	 * @return the inventoryAddress
	 */
	public String getInventoryAddress() {
		return inventoryAddress;
	}

	/**
	 * Set the inventoryAddress of Inventory.
	 *
	 * @param inventoryAddress the inventoryAddress to set
	 */
	public void setInventoryAddress(String inventoryAddress) {
		this.inventoryAddress = inventoryAddress;
	}

	/**
	 * Get the department of Inventory.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Inventory.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the role of Inventory.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Set the role of Inventory.
	 *
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Get the userType of Inventory.
	 *
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Set the userType of Inventory.
	 *
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
    
}
