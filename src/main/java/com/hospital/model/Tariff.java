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
@Table(name = "tariff")
public class Tariff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tariff_id")
	private Integer tariffId;
	
	@Column(name = "tariff_description")
	private String tariffDescription;

	/**
	 * Get the tariffId of Tariff.
	 *
	 * @return the tariffId
	 */
	public Integer getTariffId() {
		return tariffId;
	}

	/**
	 * Set the tariffId of Tariff.
	 *
	 * @param tariffId the tariffId to set
	 */
	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}

	/**
	 * Get the tariffDescription of Tariff.
	 *
	 * @return the tariffDescription
	 */
	public String getTariffDescription() {
		return tariffDescription;
	}

	/**
	 * Set the tariffDescription of Tariff.
	 *
	 * @param tariffDescription the tariffDescription to set
	 */
	public void setTariffDescription(String tariffDescription) {
		this.tariffDescription = tariffDescription;
	}

}
