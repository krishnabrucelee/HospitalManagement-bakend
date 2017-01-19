/**
 * 
 */
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

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "floor")
public class Floor {

	@Column(name = "room_management_id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer floorId;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<RoomManagement> roomManagement;


	/**
	 * Get the floorId of Floor.
	 *
	 * @return the floorId
	 */
	public Integer getFloorId() {
		return floorId;
	}

	/**
	 * Set the floorId of Floor.
	 *
	 * @param floorId the floorId to set
	 */
	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	/**
	 * Get the roomManagement of Floor.
	 *
	 * @return the roomManagement
	 */
	public List<RoomManagement> getRoomManagement() {
		return roomManagement;
	}

	/**
	 * Set the roomManagement of Floor.
	 *
	 * @param roomManagement the roomManagement to set
	 */
	public void setRoomManagement(List<RoomManagement> roomManagement) {
		this.roomManagement = roomManagement;
	}
	
	
}
