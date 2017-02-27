package com.hospital.leave.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
@Component
public class Financialyear  implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer financialyearid;
	
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date financialYear_From;
	
	@Column
	@JsonFormat(pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date financialYear_To;
	
	@Column
	private Integer  fromyear;
	@Column
	private Integer  toyear;
	public Integer getFinancialyearid() {
		return financialyearid;
	}

	public void setFinancialyearid(Integer financialyearid) {
		this.financialyearid = financialyearid;
	}

	public Date getFinancialYear_From() {
		return financialYear_From;
	}

	public void setFinancialYear_From(Date financialYear_From) {
		this.financialYear_From = financialYear_From;
	}

	public Date getFinancialYear_To() {
		return financialYear_To;
	}

	public void setFinancialYear_To(Date financialYear_To) {
		this.financialYear_To = financialYear_To;
	}

	public Integer getFromyear() {
		return fromyear;
	}

	public void setFromyear(Integer fromyear) {
		this.fromyear = fromyear;
	}

	public Integer getToyear() {
		return toyear;
	}

	public void setToyear(Integer toyear) {
		this.toyear = toyear;
	}

	@Override
	public String toString() {
		return "Financialyear [financialyearid=" + financialyearid
				+ ", financialYear_From=" + financialYear_From
				+ ", financialYear_To=" + financialYear_To + ", fromyear="
				+ fromyear + ", toyear=" + toyear + "]";
	}

	
	
}
