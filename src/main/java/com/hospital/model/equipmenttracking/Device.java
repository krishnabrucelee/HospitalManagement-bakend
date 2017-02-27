package com.hospital.model.equipmenttracking;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity

public class Device implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer deviceId;
	
	@Column()
	private String deviceName;
	
	
	public Device() {
		super();
	}

	
	
/**
 * Set the deviceId
 * @param deviceId
 */
	
	
	/**
	 * Get the deviceId
	 * @return deviceId
	 */
	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}



	public Integer getDeviceId() {
		return deviceId;
	}



	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	
	

}
