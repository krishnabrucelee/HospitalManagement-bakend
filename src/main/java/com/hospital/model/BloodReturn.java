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
@Table(name = "bloodReturn")
public class BloodReturn {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "return_blood_id")
	private Integer returnBloodId;

	@Column(name = "returned_ward_number")
	private Integer returnedWardNumber;
	
	@Column(name = "returned_by")
	private String returnedBy;
	
	@Column(name="blood_returned_date")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bloodReturnedDate;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_BRETN_FK"))
	private Department department;
	
	@OneToOne(targetEntity = InterBloodTransfusion.class)
	@JoinColumn(name = "inter_blood_id", referencedColumnName = "inter_blood_id", foreignKey = @ForeignKey(name = "inter_blood_id_BRETN_FK"))
	private InterBloodTransfusion InterBloodTransfusion;
	
	@Column(name = "status")
	public Status staus;
	
	public enum Status {
		NOTINPROGRESS, INPROGRESS, PROGRESSED
	}

	/**
	 * Get the returnBloodId of BloodReturn.
	 *
	 * @return the returnBloodId
	 */
	public Integer getReturnBloodId() {
		return returnBloodId;
	}

	/**
	 * Set the returnBloodId of BloodReturn.
	 *
	 * @param returnBloodId the returnBloodId to set
	 */
	public void setReturnBloodId(Integer returnBloodId) {
		this.returnBloodId = returnBloodId;
	}

	/**
	 * Get the returnedWardNumber of BloodReturn.
	 *
	 * @return the returnedWardNumber
	 */
	public Integer getReturnedWardNumber() {
		return returnedWardNumber;
	}

	/**
	 * Set the returnedWardNumber of BloodReturn.
	 *
	 * @param returnedWardNumber the returnedWardNumber to set
	 */
	public void setReturnedWardNumber(Integer returnedWardNumber) {
		this.returnedWardNumber = returnedWardNumber;
	}

	/**
	 * Get the returnedBy of BloodReturn.
	 *
	 * @return the returnedBy
	 */
	public String getReturnedBy() {
		return returnedBy;
	}

	/**
	 * Set the returnedBy of BloodReturn.
	 *
	 * @param returnedBy the returnedBy to set
	 */
	public void setReturnedBy(String returnedBy) {
		this.returnedBy = returnedBy;
	}

	/**
	 * Get the bloodReturnedDate of BloodReturn.
	 *
	 * @return the bloodReturnedDate
	 */
	public Date getBloodReturnedDate() {
		return bloodReturnedDate;
	}

	/**
	 * Set the bloodReturnedDate of BloodReturn.
	 *
	 * @param bloodReturnedDate the bloodReturnedDate to set
	 */
	public void setBloodReturnedDate(Date bloodReturnedDate) {
		this.bloodReturnedDate = bloodReturnedDate;
	}

	/**
	 * Get the department of BloodReturn.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of BloodReturn.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the staus of BloodReturn.
	 *
	 * @return the staus
	 */
	public Status getStaus() {
		return staus;
	}

	/**
	 * Set the staus of BloodReturn.
	 *
	 * @param staus the staus to set
	 */
	public void setStaus(Status staus) {
		this.staus = staus;
	}

	/**
	 * Get the interBloodTransfusion of BloodReturn.
	 *
	 * @return the interBloodTransfusion
	 */
	public InterBloodTransfusion getInterBloodTransfusion() {
		return InterBloodTransfusion;
	}

	/**
	 * Set the interBloodTransfusion of BloodReturn.
	 *
	 * @param interBloodTransfusion the interBloodTransfusion to set
	 */
	public void setInterBloodTransfusion(InterBloodTransfusion interBloodTransfusion) {
		InterBloodTransfusion = interBloodTransfusion;
	}
	
}
