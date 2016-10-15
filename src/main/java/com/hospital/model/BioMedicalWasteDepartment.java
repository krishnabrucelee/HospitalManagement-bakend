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
@Table(name = "bioMedicalWasteDepartment")
public class BioMedicalWasteDepartment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bio_medical_waste_department_id")
	private Integer BioMedicalWasteDepartmentId;
	
	@OneToOne(targetEntity = BioMedicalType.class)
	@JoinColumn(name = "bio_medical_type_id", referencedColumnName = "bio_medical_type_id", foreignKey = @ForeignKey(name = "bioMedicalType_BMEDWD_FK"))
	private BioMedicalType bioMedicalType;	
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_BMEDWD_FK"))
	private Department department;
	
	@Column(name = "recieved_date")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date recievedDate;
	
	@Column(name = "disposal_date")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date disposalDate;
	
	@Column(name = "status")
	private Status status;
	
	public enum Status {
		RECIEVED,
		DISPOSED
	}

	/**
	 * Get the BioMedicalWasteDepartmentId of BioMedicalWasteDepartment.
	 *
	 * @return the BioMedicalWasteDepartmentId
	 */
	public Integer getBioMedicalWasteDepartmentId() {
		return BioMedicalWasteDepartmentId;
	}

	/**
	 * Set the BioMedicalWasteDepartmentId of BioMedicalWasteDepartment.
	 *
	 * @param BioMedicalWasteDepartmentId the BioMedicalWasteDepartmentId to set
	 */
	public void setBioMedicalWasteDepartmentId(Integer BioMedicalWasteDepartmentId) {
		this.BioMedicalWasteDepartmentId = BioMedicalWasteDepartmentId;
	}

	/**
	 * Get the bioMedicalType of BioMedicalWasteDepartment.
	 *
	 * @return the bioMedicalType
	 */
	public BioMedicalType getBioMedicalType() {
		return bioMedicalType;
	}

	/**
	 * Set the bioMedicalType of BioMedicalWasteDepartment.
	 *
	 * @param bioMedicalType the bioMedicalType to set
	 */
	public void setBioMedicalType(BioMedicalType bioMedicalType) {
		this.bioMedicalType = bioMedicalType;
	}

	/**
	 * Get the department of BioMedicalWasteDepartment.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of BioMedicalWasteDepartment.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the recievedDate of BioMedicalWasteDepartment.
	 *
	 * @return the recievedDate
	 */
	public Date getRecievedDate() {
		return recievedDate;
	}

	/**
	 * Set the recievedDate of BioMedicalWasteDepartment.
	 *
	 * @param recievedDate the recievedDate to set
	 */
	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}

	/**
	 * Get the disposalDate of BioMedicalWasteDepartment.
	 *
	 * @return the disposalDate
	 */
	public Date getDisposalDate() {
		return disposalDate;
	}

	/**
	 * Set the disposalDate of BioMedicalWasteDepartment.
	 *
	 * @param disposalDate the disposalDate to set
	 */
	public void setDisposalDate(Date disposalDate) {
		this.disposalDate = disposalDate;
	}

	/**
	 * Get the status of BioMedicalWasteDepartment.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Set the status of BioMedicalWasteDepartment.
	 *
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
