/**
 * 
 */
package com.hospital.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "wantedStock")
public class WantedStock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wanted_stock_id")
	private Integer wantedStockId;
	
	@OneToOne(targetEntity = StockLedger.class)
	@JoinColumn(name = "stock_ledger_id", referencedColumnName = "stock_ledger_id", foreignKey = @ForeignKey(name = "stock_ledger_WSTOCK_FK"))
	private StockLedger stockLedger;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_WSTOCK_FK"))
	private Department department;
	
	@Column(name = "wanted_count")
	private Integer wantedCount;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.DATE)
	@Column(name = "wanted_date")
	private Date wantedDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<WantedStockList> wantedList;

	/**
	 * Get the wantedStockId of WantedStock.
	 *
	 * @return the wantedStockId
	 */
	public Integer getWantedStockId() {
		return wantedStockId;
	}

	/**
	 * Set the wantedStockId of WantedStock.
	 *
	 * @param wantedStockId the wantedStockId to set
	 */
	public void setWantedStockId(Integer wantedStockId) {
		this.wantedStockId = wantedStockId;
	}

	/**
	 * Get the stockLedger of WantedStock.
	 *
	 * @return the stockLedger
	 */
	public StockLedger getStockLedger() {
		return stockLedger;
	}

	/**
	 * Set the stockLedger of WantedStock.
	 *
	 * @param stockLedger the stockLedger to set
	 */
	public void setStockLedger(StockLedger stockLedger) {
		this.stockLedger = stockLedger;
	}

	/**
	 * Get the department of WantedStock.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of WantedStock.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the wantedCount of WantedStock.
	 *
	 * @return the wantedCount
	 */
	public Integer getWantedCount() {
		return wantedCount;
	}

	/**
	 * Set the wantedCount of WantedStock.
	 *
	 * @param wantedCount the wantedCount to set
	 */
	public void setWantedCount(Integer wantedCount) {
		this.wantedCount = wantedCount;
	}

	/**
	 * Get the wantedDate of WantedStock.
	 *
	 * @return the wantedDate
	 */
	public Date getWantedDate() {
		return wantedDate;
	}

	/**
	 * Set the wantedDate of WantedStock.
	 *
	 * @param wantedDate the wantedDate to set
	 */
	public void setWantedDate(Date wantedDate) {
		this.wantedDate = wantedDate;
	}

	/**
	 * Get the wantedList of WantedStock.
	 *
	 * @return the wantedList
	 */
	public List<WantedStockList> getWantedList() {
		return wantedList;
	}

	/**
	 * Set the wantedList of WantedStock.
	 *
	 * @param wantedList the wantedList to set
	 */
	public void setWantedList(List<WantedStockList> wantedList) {
		this.wantedList = wantedList;
	}
	
}
