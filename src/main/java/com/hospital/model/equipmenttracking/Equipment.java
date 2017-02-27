package com.hospital.model.equipmenttracking;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table
public class Equipment implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer equipmentId;
	
	@ManyToOne()
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name="deviceId")
	private Device device;
	
	@ManyToOne
	@JoinColumn(name="roomId")
	private RoomData roomData;
	
	@ManyToOne
	@JoinColumn(name="maintanaceConfigId")
	private MaintananceConfiguration maintanceConfiguration;
	
	
	private String equipmentName;
	
	
	
	private String manufactureName;
	
	
	private String  supplierName;
	
	
	private Double price;
	
	@JsonFormat(pattern="dd-MM-yyyy'T'HH:mm:ss.SSS'Z'")	
	//@JsonFormat(pattern="dd-MM-yyyy")	
	@Temporal(TemporalType.DATE)
	private Date manufactureDate;

	private String waranty;
	

	public Device getDevice() {
		return device;
	}


	public void setDevice(Device device) {
		this.device = device;
	}


	public RoomData getRoomData() {
		return roomData;
	}


	public void setRoomData(RoomData roomData) {
		this.roomData = roomData;
	}


	public MaintananceConfiguration getMaintanceConfiguration() {
		return maintanceConfiguration;
	}


	public void setMaintanceConfiguration(
			MaintananceConfiguration maintanceConfiguration) {
		this.maintanceConfiguration = maintanceConfiguration;
	}


	public String getEquipmentName() {
		return equipmentName;
	}


	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}


	public String getManufactureName() {
		return manufactureName;
	}


	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Date getManufactureDate() {
		return manufactureDate;
	}


	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}


	public Integer getEquipmentId() {
		return equipmentId;
	}


	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}


	public String getWaranty() {
		return waranty;
	}


	public void setWaranty(String waranty) {
		this.waranty = waranty;
	}
	
	

}