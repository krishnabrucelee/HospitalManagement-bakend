/**
 * 
 */
package com.hospital.model;

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
@Table(name = "bioMedicalDisposalManagement")
public class BioMedicalDisposalManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bio_medical_disposal_management_id")
	private Integer bioMedicalDisposalManagementId;
	
	@OneToOne(targetEntity = BioMedicalWasteDepartment.class)
	@JoinColumn(name = "bio_medical_waste_department_id", referencedColumnName = "bio_medical_waste_department_id", foreignKey = @ForeignKey(name = "BioMedicalWasteDepartment_BMEDM_FK"))
	private BioMedicalWasteDepartment bioMedicalWasteDepartment;	
	
	@Column(name = "bio_medical_disposal_description")
	private String bioMedicalDisposalDescription;

	/**
	 * Get the bioMedicalDisposalManagementId of BioMedicalDisposalManagement.
	 *
	 * @return the bioMedicalDisposalManagementId
	 */
	public Integer getBioMedicalDisposalManagementId() {
		return bioMedicalDisposalManagementId;
	}

	/**
	 * Set the bioMedicalDisposalManagementId of BioMedicalDisposalManagement.
	 *
	 * @param bioMedicalDisposalManagementId the bioMedicalDisposalManagementId to set
	 */
	public void setBioMedicalDisposalManagementId(Integer bioMedicalDisposalManagementId) {
		this.bioMedicalDisposalManagementId = bioMedicalDisposalManagementId;
	}

	/**
	 * Get the bioMedicalWasteDepartment of BioMedicalDisposalManagement.
	 *
	 * @return the bioMedicalWasteDepartment
	 */
	public BioMedicalWasteDepartment getBioMedicalWasteDepartment() {
		return bioMedicalWasteDepartment;
	}

	/**
	 * Set the bioMedicalWasteDepartment of BioMedicalDisposalManagement.
	 *
	 * @param bioMedicalWasteDepartment the bioMedicalWasteDepartment to set
	 */
	public void setBioMedicalWasteDepartment(BioMedicalWasteDepartment bioMedicalWasteDepartment) {
		this.bioMedicalWasteDepartment = bioMedicalWasteDepartment;
	}

	/**
	 * Get the bioMedicalDisposalDescription of BioMedicalDisposalManagement.
	 *
	 * @return the bioMedicalDisposalDescription
	 */
	public String getBioMedicalDisposalDescription() {
		return bioMedicalDisposalDescription;
	}

	/**
	 * Set the bioMedicalDisposalDescription of BioMedicalDisposalManagement.
	 *
	 * @param bioMedicalDisposalDescription the bioMedicalDisposalDescription to set
	 */
	public void setBioMedicalDisposalDescription(String bioMedicalDisposalDescription) {
		this.bioMedicalDisposalDescription = bioMedicalDisposalDescription;
	}
	
}
