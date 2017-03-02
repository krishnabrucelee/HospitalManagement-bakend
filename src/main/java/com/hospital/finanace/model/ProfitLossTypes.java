/**
 * 
 */
package com.hospital.finanace.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author admin
 *
 */
@Entity
public class ProfitLossTypes {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer profitLossId;
	
	private String headingName;

	public Integer getProfitLossId() {
		return profitLossId;
	}

	public void setProfitLossId(Integer profitLossId) {
		this.profitLossId = profitLossId;
	}

	public String getHeadingName() {
		return headingName;
	}

	public void setHeadingName(String headingName) {
		this.headingName = headingName;
	}

	
}
