package com.hospital.model.equipmenttracking;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hospital.enums.STATUS;
import com.hospital.model.Staff;
@Entity
@Table
public class EquipmentMaintanaceRequest implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer equipmentMaintanaceRequestId;
	
	@OneToOne()
	@JoinColumn(name="statusUpdateId")
	private EquipmentMaintanaceStatus equipmentMaintanaceStatus;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestDate;
	
	@ManyToOne()
	@JoinColumn(name="equipmentId")
	private Equipment  equipmentdata;
	
	@OneToOne()
	@JoinColumn(name="staffId")
	private Staff  assignedStaff;
	
	@Enumerated(EnumType.STRING)
	STATUS status;

	public Integer getEquipmentMaintanaceRequestId() {
		return equipmentMaintanaceRequestId;
	}

	public void setEquipmentMaintanaceRequestId(Integer equipmentMaintanaceRequestId) {
		this.equipmentMaintanaceRequestId = equipmentMaintanaceRequestId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	
	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public Equipment getEquipmentdata() {
		return equipmentdata;
	}

	public void setEquipmentdata(Equipment equipmentdata) {
		this.equipmentdata = equipmentdata;
	}

	public Staff getAssignedStaff() {
		return assignedStaff;
	}

	public void setAssignedStaff(Staff assignedStaff) {
		this.assignedStaff = assignedStaff;
	}

	public EquipmentMaintanaceStatus getEquipmentMaintanaceStatus() {
		return equipmentMaintanaceStatus;
	}

	public void setEquipmentMaintanaceStatus(
			EquipmentMaintanaceStatus equipmentMaintanaceStatus) {
		this.equipmentMaintanaceStatus = equipmentMaintanaceStatus;
	}

	

	
	
}
 
