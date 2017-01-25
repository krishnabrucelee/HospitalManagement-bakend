/**
 * 
 */
package com.hospital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author admin
 *
 */
@Entity
@Table(name="radiologyReportSubCategeries")
public class RadiologyReportSubCategeries implements Serializable {
	@Column(name="report_catrgeryid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer reportCatrgeryId;
	
	@Column 
	private String categeryName;
	
	@Column 
	private String findings;
	
	@Column 
	private String impression;
	
	public RadiologyReportSubCategeries() {
		super();
	}

	public Integer getReportCatrgeryId() {
		return reportCatrgeryId;
	}

	public void setReportCatrgeryId(Integer reportCatrgeryId) {
		this.reportCatrgeryId = reportCatrgeryId;
	}

	public String getCategeryName() {
		return categeryName;
	}

	public void setCategeryName(String categeryName) {
		this.categeryName = categeryName;
	}

	public String getFindings() {
		return findings;
	}

	public void setFindings(String findings) {
		this.findings = findings;
	}

	public String getImpression() {
		return impression;
	}

	public void setImpression(String impression) {
		this.impression = impression;
	}	
	
	

}
