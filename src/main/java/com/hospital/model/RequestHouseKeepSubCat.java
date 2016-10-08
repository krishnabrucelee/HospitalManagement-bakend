package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="requestHouseKeepSubCat")
public class RequestHouseKeepSubCat implements Serializable {
	@Column(name="serial_Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer serialId;
	private String itemName;
	private Integer itemCount;
	
	public RequestHouseKeepSubCat() {
		super();
	}
	public Integer getSerialId() {
		return serialId;
	}
	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	
}
