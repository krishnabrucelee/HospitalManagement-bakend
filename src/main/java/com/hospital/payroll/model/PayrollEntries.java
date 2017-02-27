/**
 * 
 */
package com.hospital.payroll.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author admin
 *
 */
@Entity
public class PayrollEntries {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int payrollEntryId;
	
	@Temporal(TemporalType.DATE)
	private Date fromDate;
	
	@Temporal(TemporalType.DATE)
	private Date toDate;

	private Date creationDate;
	
	private int totalDays;

	public int getPayrollEntryId() {
		return payrollEntryId;
	}

	public void setPayrollEntryId(int payrollEntryId) {
		this.payrollEntryId = payrollEntryId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
