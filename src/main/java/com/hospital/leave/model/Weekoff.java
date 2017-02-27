package com.hospital.leave.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table
@Component
public class Weekoff implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	@Column
	private Integer dayvalue;
	@Column
	private String dayname;
	@Column
	private Integer financialyearid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDayvalue() {
		return dayvalue;
	}
	public void setDayvalue(Integer dayvalue) {
		this.dayvalue = dayvalue;
	}
	public String getDayname() {
		return dayname;
	}
	public void setDayname(String dayname) {
		this.dayname = dayname;
	}
	public Integer getFinancialyearid() {
		return financialyearid;
	}
	public void setFinancialyearid(Integer financialyearid) {
		this.financialyearid = financialyearid;
	}
	
	

}
