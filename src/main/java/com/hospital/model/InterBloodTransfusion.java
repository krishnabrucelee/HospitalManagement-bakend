/**
 * 
 */
package com.hospital.model;

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

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "interBloodTransfusion")
public class InterBloodTransfusion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "inter_blood_id")
	private Integer interBloodId;
	
	@Column(name = "blood_bag_number")
	private Integer bloodBagNumber;
	
	@Column(name = "blood_group")
	private String bloodGroup;
	
	@Column(name = "blood_unit")
	private String bloodUnit;
	
	@OneToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(name = "patient_INTB_FK"))
	private Patient patient;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_INTB_FK"))
	private Department department;

	/**
	 * Get the interBloodId of InterBloodTransfusion.
	 *
	 * @return the interBloodId
	 */
	public Integer getInterBloodId() {
		return interBloodId;
	}

	/**
	 * Set the interBloodId of InterBloodTransfusion.
	 *
	 * @param interBloodId the interBloodId to set
	 */
	public void setInterBloodId(Integer interBloodId) {
		this.interBloodId = interBloodId;
	}

	/**
	 * Get the bloodBagNumber of InterBloodTransfusion.
	 *
	 * @return the bloodBagNumber
	 */
	public Integer getBloodBagNumber() {
		return bloodBagNumber;
	}

	/**
	 * Set the bloodBagNumber of InterBloodTransfusion.
	 *
	 * @param bloodBagNumber the bloodBagNumber to set
	 */
	public void setBloodBagNumber(Integer bloodBagNumber) {
		this.bloodBagNumber = bloodBagNumber;
	}

	/**
	 * Get the bloodGroup of InterBloodTransfusion.
	 *
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * Set the bloodGroup of InterBloodTransfusion.
	 *
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/**
	 * Get the bloodUnit of InterBloodTransfusion.
	 *
	 * @return the bloodUnit
	 */
	public String getBloodUnit() {
		return bloodUnit;
	}

	/**
	 * Set the bloodUnit of InterBloodTransfusion.
	 *
	 * @param bloodUnit the bloodUnit to set
	 */
	public void setBloodUnit(String bloodUnit) {
		this.bloodUnit = bloodUnit;
	}

	/**
	 * Get the patient of InterBloodTransfusion.
	 *
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Set the patient of InterBloodTransfusion.
	 *
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Get the department of InterBloodTransfusion.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of InterBloodTransfusion.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
