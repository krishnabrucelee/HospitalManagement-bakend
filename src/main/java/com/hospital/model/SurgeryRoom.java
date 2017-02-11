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
@Table(name = "surgeryRoom")
public class SurgeryRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "surgery_room_id")
	private Integer surgeryRoomId;
	
	@Column(name = "surgery_floor")
	private String surgeryFloor;
	
	@Column(name = "surgery_room_number")
	private String surgeryRoomNumber;
	
	@Column(name = "surgery_room_description")
	private String surgeryRoomDescription;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_SURROOM_FK"))
	private Department department;

	/**
	 * Get the surgeryRoomId of SurgeryRoom.
	 *
	 * @return the surgeryRoomId
	 */
	public Integer getSurgeryRoomId() {
		return surgeryRoomId;
	}

	/**
	 * Set the surgeryRoomId of SurgeryRoom.
	 *
	 * @param surgeryRoomId the surgeryRoomId to set
	 */
	public void setSurgeryRoomId(Integer surgeryRoomId) {
		this.surgeryRoomId = surgeryRoomId;
	}

	/**
	 * Get the surgeryFloor of SurgeryRoom.
	 *
	 * @return the surgeryFloor
	 */
	public String getSurgeryFloor() {
		return surgeryFloor;
	}

	/**
	 * Set the surgeryFloor of SurgeryRoom.
	 *
	 * @param surgeryFloor the surgeryFloor to set
	 */
	public void setSurgeryFloor(String surgeryFloor) {
		this.surgeryFloor = surgeryFloor;
	}

	/**
	 * Get the surgeryRoomNumber of SurgeryRoom.
	 *
	 * @return the surgeryRoomNumber
	 */
	public String getSurgeryRoomNumber() {
		return surgeryRoomNumber;
	}

	/**
	 * Set the surgeryRoomNumber of SurgeryRoom.
	 *
	 * @param surgeryRoomNumber the surgeryRoomNumber to set
	 */
	public void setSurgeryRoomNumber(String surgeryRoomNumber) {
		this.surgeryRoomNumber = surgeryRoomNumber;
	}

	/**
	 * Get the surgeryRoomDescription of SurgeryRoom.
	 *
	 * @return the surgeryRoomDescription
	 */
	public String getSurgeryRoomDescription() {
		return surgeryRoomDescription;
	}

	/**
	 * Set the surgeryRoomDescription of SurgeryRoom.
	 *
	 * @param surgeryRoomDescription the surgeryRoomDescription to set
	 */
	public void setSurgeryRoomDescription(String surgeryRoomDescription) {
		this.surgeryRoomDescription = surgeryRoomDescription;
	}

	/**
	 * Get the department of SurgeryRoom.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of SurgeryRoom.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
