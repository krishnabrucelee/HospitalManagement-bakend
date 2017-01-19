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
@Table(name = "roomManagement")
public class RoomManagement {

	@Column(name = "room_management_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer roomManagementId;
	
	@Column(name = "custom_ward_number")
	private String customWardNumber;
	
	@Column(name = "ward_number")
	private String wardNumber;
	
	@Column(name = "number_of_beds")
	private Integer numberOfBeds;
	
	@Column(name = "number_of_floors")
	private Integer numberOfFloors;
	
	@Column(name = "floor_number")
	private Integer floorNumber;
	
	@Column(name = "class_type")
	private String classType;
	
	@Column(name = "price")  
	private Double price;
	
	@Column(name = "beds_for_class_type")
	private Integer bedsForClassType;
	
	@Column(name = "is_available")
	private Boolean isAvailable;

	/**
	 * Get the roomManagementId of RoomManagement.
	 *
	 * @return the roomManagementId
	 */
	public Integer getRoomManagementId() {
		return roomManagementId;
	}

	/**
	 * Set the roomManagementId of RoomManagement.
	 *
	 * @param roomManagementId the roomManagementId to set
	 */
	public void setRoomManagementId(Integer roomManagementId) {
		this.roomManagementId = roomManagementId;
	}

	/**
	 * Get the wardNumber of RoomManagement.
	 *
	 * @return the wardNumber
	 */
	public String getWardNumber() {
		return wardNumber;
	}

	/**
	 * Set the wardNumber of RoomManagement.
	 *
	 * @param wardNumber the wardNumber to set
	 */
	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

	/**
	 * Get the numberOfBeds of RoomManagement.
	 *
	 * @return the numberOfBeds
	 */
	public Integer getNumberOfBeds() {
		return numberOfBeds;
	}

	/**
	 * Set the numberOfBeds of RoomManagement.
	 *
	 * @param numberOfBeds the numberOfBeds to set
	 */
	public void setNumberOfBeds(Integer numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	/**
	 * Get the classType of RoomManagement.
	 *
	 * @return the classType
	 */
	public String getClassType() {
		return classType;
	}

	/**
	 * Set the classType of RoomManagement.
	 *
	 * @param classType the classType to set
	 */
	public void setClassType(String classType) {
		this.classType = classType;
	}

	/**
	 * Get the price of RoomManagement.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Set the price of RoomManagement.
	 *
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Get the bedsForClassType of RoomManagement.
	 *
	 * @return the bedsForClassType
	 */
	public Integer getBedsForClassType() {
		return bedsForClassType;
	}

	/**
	 * Set the bedsForClassType of RoomManagement.
	 *
	 * @param bedsForClassType the bedsForClassType to set
	 */
	public void setBedsForClassType(Integer bedsForClassType) {
		this.bedsForClassType = bedsForClassType;
	}

	/**
	 * Get the isAvailable of RoomManagement.
	 *
	 * @return the isAvailable
	 */
	public Boolean getIsAvailable() {
		return isAvailable;
	}

	/**
	 * Set the isAvailable of RoomManagement.
	 *
	 * @param isAvailable the isAvailable to set
	 */
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * Get the numberOfFloors of RoomManagement.
	 *
	 * @return the numberOfFloors
	 */
	public Integer getNumberOfFloors() {
		return numberOfFloors;
	}

	/**
	 * Set the numberOfFloors of RoomManagement.
	 *
	 * @param numberOfFloors the numberOfFloors to set
	 */
	public void setNumberOfFloors(Integer numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	/**
	 * Get the customWardNumber of RoomManagement.
	 *
	 * @return the customWardNumber
	 */
	public String getCustomWardNumber() {
		return customWardNumber;
	}

	/**
	 * Set the customWardNumber of RoomManagement.
	 *
	 * @param customWardNumber the customWardNumber to set
	 */
	public void setCustomWardNumber(String customWardNumber) {
		this.customWardNumber = customWardNumber;
	}

	/**
	 * Get the floorNumber of RoomManagement.
	 *
	 * @return the floorNumber
	 */
	public Integer getFloorNumber() {
		return floorNumber;
	}

	/**
	 * Set the floorNumber of RoomManagement.
	 *
	 * @param floorNumber the floorNumber to set
	 */
	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}
	
}
