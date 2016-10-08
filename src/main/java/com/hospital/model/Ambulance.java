/**
 * 
 */
package com.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "ambulance")
public class Ambulance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ambulance_id")
	private Integer ambulanceId;
	
	@Column(name = "ambulance_number")
	private Integer ambulanceNumber;
	
	@Column(name = "facilities")
	private String facilities;
	
	@Column(name = "communication_facilities")
	private String communicationFacilities;

	/**
	 * Get the ambulanceId of Ambulance.
	 *
	 * @return the ambulanceId
	 */
	public Integer getAmbulanceId() {
		return ambulanceId;
	}

	/**
	 * Set the ambulanceId of Ambulance.
	 *
	 * @param ambulanceId the ambulanceId to set
	 */
	public void setAmbulanceId(Integer ambulanceId) {
		this.ambulanceId = ambulanceId;
	}

	/**
	 * Get the ambulanceNumber of Ambulance.
	 *
	 * @return the ambulanceNumber
	 */
	public Integer getAmbulanceNumber() {
		return ambulanceNumber;
	}

	/**
	 * Set the ambulanceNumber of Ambulance.
	 *
	 * @param ambulanceNumber the ambulanceNumber to set
	 */
	public void setAmbulanceNumber(Integer ambulanceNumber) {
		this.ambulanceNumber = ambulanceNumber;
	}

	/**
	 * Get the facilities of Ambulance.
	 *
	 * @return the facilities
	 */
	public String getFacilities() {
		return facilities;
	}

	/**
	 * Set the facilities of Ambulance.
	 *
	 * @param facilities the facilities to set
	 */
	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	/**
	 * Get the communicationFacilities of Ambulance.
	 *
	 * @return the communicationFacilities
	 */
	public String getCommunicationFacilities() {
		return communicationFacilities;
	}

	/**
	 * Set the communicationFacilities of Ambulance.
	 *
	 * @param communicationFacilities the communicationFacilities to set
	 */
	public void setCommunicationFacilities(String communicationFacilities) {
		this.communicationFacilities = communicationFacilities;
	}
	
}
