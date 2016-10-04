/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "pettyCash")
public class PettyCash {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "petty_cash_id")
	private Integer pettyCashId;
	
	@Column(name = "petty_cash_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date pettyCashDate;
	
	@Column(name = "cash_amount")
	private Double cashAmount;

	/**
	 * Get the pettyCashId of PettyCash.
	 *
	 * @return the pettyCashId
	 */
	public Integer getPettyCashId() {
		return pettyCashId;
	}

	/**
	 * Set the pettyCashId of PettyCash.
	 *
	 * @param pettyCashId the pettyCashId to set
	 */
	public void setPettyCashId(Integer pettyCashId) {
		this.pettyCashId = pettyCashId;
	}

	/**
	 * Get the pettyCashDate of PettyCash.
	 *
	 * @return the pettyCashDate
	 */
	public Date getPettyCashDate() {
		return pettyCashDate;
	}

	/**
	 * Set the pettyCashDate of PettyCash.
	 *
	 * @param pettyCashDate the pettyCashDate to set
	 */
	public void setPettyCashDate(Date pettyCashDate) {
		this.pettyCashDate = pettyCashDate;
	}

	/**
	 * Get the cashAmount of PettyCash.
	 *
	 * @return the cashAmount
	 */
	public Double getCashAmount() {
		return cashAmount;
	}

	/**
	 * Set the cashAmount of PettyCash.
	 *
	 * @param cashAmount the cashAmount to set
	 */
	public void setCashAmount(Double cashAmount) {
		this.cashAmount = cashAmount;
	}
	
	
}
