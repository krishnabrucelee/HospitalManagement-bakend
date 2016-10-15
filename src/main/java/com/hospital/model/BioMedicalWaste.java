/**
 * 
 */
package com.hospital.model;

import java.util.List;

import javax.persistence.CascadeType;
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

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "bioMedicalWaste")
public class BioMedicalWaste {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bio_medical_waste_id")
	private Integer bioMedicalId;
	
	@Column(name = "color_code")
	private String colorCode;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_BMEDW_FK"))
	private Department department;
	
	@OneToOne(targetEntity = BioMedicalType.class)
	@JoinColumn(name = "bio_medical_type_id", referencedColumnName = "bio_medical_type_id", foreignKey = @ForeignKey(name = "bioMedicalType_BMEDW_FK"))
	private BioMedicalType bioMedicalType;

	@OneToOne(targetEntity = BioMedicalWasteDepartment.class)
	@JoinColumn(name = "bio_medical_waste_department_id", referencedColumnName = "bio_medical_waste_department_id", foreignKey = @ForeignKey(name = "bioMedicalWasteDepartment_BMEDW_FK"))
	private BioMedicalWasteDepartment bioMedicalWasteDepartment;

	/**
	 * Get the bioMedicalId of BioMedicalWaste.
	 *
	 * @return the bioMedicalId
	 */
	public Integer getBioMedicalId() {
		return bioMedicalId;
	}

	/**
	 * Set the bioMedicalId of BioMedicalWaste.
	 *
	 * @param bioMedicalId the bioMedicalId to set
	 */
	public void setBioMedicalId(Integer bioMedicalId) {
		this.bioMedicalId = bioMedicalId;
	}

	/**
	 * Get the colorCode of BioMedicalWaste.
	 *
	 * @return the colorCode
	 */
	public String getColorCode() {
		return colorCode;
	}

	/**
	 * Set the colorCode of BioMedicalWaste.
	 *
	 * @param colorCode the colorCode to set
	 */
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	/**
	 * Get the department of BioMedicalWaste.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of BioMedicalWaste.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the bioMedicalType of BioMedicalWaste.
	 *
	 * @return the bioMedicalType
	 */
	public BioMedicalType getBioMedicalType() {
		return bioMedicalType;
	}

	/**
	 * Set the bioMedicalType of BioMedicalWaste.
	 *
	 * @param bioMedicalType the bioMedicalType to set
	 */
	public void setBioMedicalType(BioMedicalType bioMedicalType) {
		this.bioMedicalType = bioMedicalType;
	}

	/**
	 * Get the bioMedicalWasteDepartment of BioMedicalWaste.
	 *
	 * @return the bioMedicalWasteDepartment
	 */
	public BioMedicalWasteDepartment getBioMedicalWasteDepartment() {
		return bioMedicalWasteDepartment;
	}

	/**
	 * Set the bioMedicalWasteDepartment of BioMedicalWaste.
	 *
	 * @param bioMedicalWasteDepartment the bioMedicalWasteDepartment to set
	 */
	public void setBioMedicalWasteDepartment(BioMedicalWasteDepartment bioMedicalWasteDepartment) {
		this.bioMedicalWasteDepartment = bioMedicalWasteDepartment;
	}
	
}
