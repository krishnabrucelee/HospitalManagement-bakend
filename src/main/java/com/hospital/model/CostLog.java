/**
 * 
 */
package com.hospital.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "costLog")
public class CostLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cost_log_id")
	private Integer costLogId;
	
	@OneToOne(targetEntity = Cost.class)
	@JoinColumn(name = "cost_id", referencedColumnName = "cost_id", foreignKey = @ForeignKey(name = "cost_CLOG_FK"))
	private Cost cost;
	
	@Column(name = "cost_log_description")
	private String costLogDescription;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.DATE)
	@Column(name = "cost_modified_date")
	private Date costModifiedDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.DATE)
	@Column(name = "cost_old_date")
	private Date costOldDate;

	/**
	 * Get the costLogId of CostLog.
	 *
	 * @return the costLogId
	 */
	public Integer getCostLogId() {
		return costLogId;
	}

	/**
	 * Set the costLogId of CostLog.
	 *
	 * @param costLogId the costLogId to set
	 */
	public void setCostLogId(Integer costLogId) {
		this.costLogId = costLogId;
	}

	/**
	 * Get the cost of CostLog.
	 *
	 * @return the cost
	 */
	public Cost getCost() {
		return cost;
	}

	/**
	 * Set the cost of CostLog.
	 *
	 * @param cost the cost to set
	 */
	public void setCost(Cost cost) {
		this.cost = cost;
	}

	/**
	 * Get the costLogDescription of CostLog.
	 *
	 * @return the costLogDescription
	 */
	public String getCostLogDescription() {
		return costLogDescription;
	}

	/**
	 * Set the costLogDescription of CostLog.
	 *
	 * @param costLogDescription the costLogDescription to set
	 */
	public void setCostLogDescription(String costLogDescription) {
		this.costLogDescription = costLogDescription;
	}

	/**
	 * Get the costModifiedDate of CostLog.
	 *
	 * @return the costModifiedDate
	 */
	public Date getCostModifiedDate() {
		return costModifiedDate;
	}

	/**
	 * Set the costModifiedDate of CostLog.
	 *
	 * @param costModifiedDate the costModifiedDate to set
	 */
	public void setCostModifiedDate(Date costModifiedDate) {
		this.costModifiedDate = costModifiedDate;
	}

	/**
	 * Get the costOldDate of CostLog.
	 *
	 * @return the costOldDate
	 */
	public Date getCostOldDate() {
		return costOldDate;
	}

	/**
	 * Set the costOldDate of CostLog.
	 *
	 * @param costOldDate the costOldDate to set
	 */
	public void setCostOldDate(Date costOldDate) {
		this.costOldDate = costOldDate;
	}

}
