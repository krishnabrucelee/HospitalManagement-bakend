package com.hospital.model.equipmenttracking;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MaintananceConfiguration implements Serializable {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer maintanaceConfigId;
	
	@Column()
	private String maintanaceType;
	
	@Column()
	private String maintanaceName;
	
	@Column()
	private Integer periodDays;
	
	@Enumerated(EnumType.STRING)
	PeriodicCheckType periodicType;
	
	private Integer periodNumbers;
	
	
	public String getMaintanaceType() {
		return maintanaceType;
	}

	public void setMaintanaceType(String maintanaceType) {
		this.maintanaceType = maintanaceType;
	}

	public String getMaintanaceName() {
		return maintanaceName;
	}

	public void setMaintanaceName(String maintanaceName) {
		this.maintanaceName = maintanaceName;
	}

	public Integer getPeriodDays() {
		return periodDays;
	}

	public void setPeriodDays(Integer periodDays) {
		this.periodDays = periodDays;
	}

	public PeriodicCheckType getPeriodicType() {
		return periodicType;
	}

	public void setPeriodicType(PeriodicCheckType periodicType) {
		this.periodicType = periodicType;
	}

	public Integer getPeriodNumbers() {
		return periodNumbers;
	}

	public void setPeriodNumbers(Integer periodNumbers) {
		this.periodNumbers = periodNumbers;
	}

	public Integer getMaintanaceConfigId() {
		return maintanaceConfigId;
	}

	public void setMaintanaceConfigId(Integer maintanaceConfigId) {
		this.maintanaceConfigId = maintanaceConfigId;
	}
	
	
}

enum PeriodicCheckType {
	DAILY,
	WEEKLY,
	MONTHLY,
	MONTHLYTWICE,
	HALFYEARLY,
	YEARLY
}
