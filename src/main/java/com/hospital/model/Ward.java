package com.hospital.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ward")
public class Ward {
	
	@Column(name = "ward_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer wardId;
	
	@Column(name = "ward_bed_number")
	private Integer wardBedNumber;
	
	@Column(name = "ward_number")
	private Integer wardNumber;
	
	@Column(name = "ward_type")
	private String wardType;
	
	@Column(name = "price")  
	private Long price;
	
	@Column(name = "bed_details")
	private String bedDetails;

	/**
	 * Get the wardId of Ward.
	 *
	 * @return the wardId
	 */
	public Integer getWardId() {
		return wardId;
	}

	/**
	 * Set the wardId of Ward.
	 *
	 * @param wardId the wardId to set
	 */
	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}

	/**
	 * Get the wardBedNumber of Ward.
	 *
	 * @return the wardBedNumber
	 */
	public Integer getWardBedNumber() {
		return wardBedNumber;
	}

	/**
	 * Set the wardBedNumber of Ward.
	 *
	 * @param wardBedNumber the wardBedNumber to set
	 */
	public void setWardBedNumber(Integer wardBedNumber) {
		this.wardBedNumber = wardBedNumber;
	}

	/**
	 * Get the wardNumber of Ward.
	 *
	 * @return the wardNumber
	 */
	public Integer getWardNumber() {
		return wardNumber;
	}

	/**
	 * Set the wardNumber of Ward.
	 *
	 * @param wardNumber the wardNumber to set
	 */
	public void setWardNumber(Integer wardNumber) {
		this.wardNumber = wardNumber;
	}

	/**
	 * Get the wardType of Ward.
	 *
	 * @return the wardType
	 */
	public String getWardType() {
		return wardType;
	}

	/**
	 * Set the wardType of Ward.
	 *
	 * @param wardType the wardType to set
	 */
	public void setWardType(String wardType) {
		this.wardType = wardType;
	}

	/**
	 * Get the price of Ward.
	 *
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * Set the price of Ward.
	 *
	 * @param price the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}

	/**
	 * Get the bedDetails of Ward.
	 *
	 * @return the bedDetails
	 */
	public String getBedDetails() {
		return bedDetails;
	}

	/**
	 * Set the bedDetails of Ward.
	 *
	 * @param bedDetails the bedDetails to set
	 */
	public void setBedDetails(String bedDetails) {
		this.bedDetails = bedDetails;
	}
	
}
