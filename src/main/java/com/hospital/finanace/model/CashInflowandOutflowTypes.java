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
public class CashInflowandOutflowTypes {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cashFlowId;
	
	private String headingName;

	public Integer getCashFlowId() {
		return cashFlowId;
	}

	public void setCashFlowId(Integer cashFlowId) {
		this.cashFlowId = cashFlowId;
	}

	public String getHeadingName() {
		return headingName;
	}

	public void setHeadingName(String headingName) {
		this.headingName = headingName;
	}
	
	
}
