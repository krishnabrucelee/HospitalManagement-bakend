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
import com.hospital.model.Finance.UserType;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "finance")
public class Finance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "finance_id")
	private Integer financeId;
	
	@Column(name = "finance_reg_id")
	private String financeRegId;
	
	@Column(name = "staff_id")
	private String staffId;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "finance_dob")
	private Date financeDob;
	
	@Column(name = "finance_email")
	private String financeEmail;
	
	@Column(name = "finance_phone")
	private String financePhoneNumber;
		
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_finance_FK"))
	private Department department;

    /** User role. */
    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", foreignKey = @ForeignKey(name = "role_finance_FK"))
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
	 * Get the financeId of Financer.java.
	 *
	 * @return the financeId
	 */
	public Integer getFinancerId() {
		return financeId;
	}

	/**
	 * Set the financeId of Financer.java.
	 *
	 * @param financeId the financeId to set
	 */
	public void setFinancerId(Integer financeId) {
		this.financeId = financeId;
	}

	/**
	 * Get the financeRegId of Financer.java.
	 *
	 * @return the financeRegId
	 */
	public String getFinancerRegId() {
		return financeRegId;
	}

	/**
	 * Set the financeRegId of Financer.java.
	 *
	 * @param financeRegId the financeRegId to set
	 */
	public void setFinancerRegId(String financeRegId) {
		this.financeRegId = financeRegId;
	}

	/**
	 * Get the staffId of Financer.java.
	 *
	 * @return the staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * Set the staffId of Financer.java.
	 *
	 * @param staffId the staffId to set
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * Get the financeDob of Financer.java.
	 *
	 * @return the financeDob
	 */
	public Date getFinancerDob() {
		return financeDob;
	}

	/**
	 * Set the financeDob of Financer.java.
	 *
	 * @param financeDob the financeDob to set
	 */
	public void setFinancerDob(Date financeDob) {
		this.financeDob = financeDob;
	}

	/**
	 * Get the financeEmail of Financer.java.
	 *
	 * @return the financeEmail
	 */
	public String getFinancerEmail() {
		return financeEmail;
	}

	/**
	 * Set the financeEmail of Financer.java.
	 *
	 * @param financeEmail the financeEmail to set
	 */
	public void setFinancerEmail(String financeEmail) {
		this.financeEmail = financeEmail;
	}

	/**
	 * Get the financePhoneNumber of Financer.java.
	 *
	 * @return the financePhoneNumber
	 */
	public String getFinancerPhoneNumber() {
		return financePhoneNumber;
	}

	/**
	 * Set the financePhoneNumber of Financer.java.
	 *
	 * @param financePhoneNumber the financePhoneNumber to set
	 */
	public void setFinancerPhoneNumber(String financePhoneNumber) {
		this.financePhoneNumber = financePhoneNumber;
	}

	/**
	 * Get the department of Financer.java.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Financer.java.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the role of Financer.java.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Set the role of Financer.java.
	 *
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Get the userType of Financer.java.
	 *
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * Set the userType of Financer.java.
	 *
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
