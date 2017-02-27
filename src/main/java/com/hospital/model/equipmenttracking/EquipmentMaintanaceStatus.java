package com.hospital.model.equipmenttracking;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hospital.enums.STATUS;
import com.hospital.model.Staff;
@Entity
public class EquipmentMaintanaceStatus implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer statusUpdateId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	private String statusNotes;
	
	
	

	@Enumerated(EnumType.STRING)
	STATUS status;
	
	public Integer getStatusUpdateId() {
		return statusUpdateId;
	}

	public void setStatusUpdateId(Integer statusUpdateId) {
		this.statusUpdateId = statusUpdateId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getStatusNotes() {
		return statusNotes;
	}

	public void setStatusNotes(String statusNotes) {
		this.statusNotes = statusNotes;
	}


	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	
	
	

}
