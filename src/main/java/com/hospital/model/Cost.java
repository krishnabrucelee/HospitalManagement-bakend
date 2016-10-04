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
@Table(name = "cost")
public class Cost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cost_id")
	private Integer costId;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "cost_description")
	private String costDescription;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_COST_FK"))
	private Department department;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.DATE)
	@Column(name = "cost_date")
	private Date costDate;

	/**
	 * Get the costId of Cost.
	 *
	 * @return the costId
	 */
	public Integer getCostId() {
		return costId;
	}

	/**
	 * Set the costId of Cost.
	 *
	 * @param costId the costId to set
	 */
	public void setCostId(Integer costId) {
		this.costId = costId;
	}

	/**
	 * Get the itemName of Cost.
	 *
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Set the itemName of Cost.
	 *
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Get the price of Cost.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Set the price of Cost.
	 *
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Get the costDescription of Cost.
	 *
	 * @return the costDescription
	 */
	public String getCostDescription() {
		return costDescription;
	}

	/**
	 * Set the costDescription of Cost.
	 *
	 * @param costDescription the costDescription to set
	 */
	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}

	/**
	 * Get the department of Cost.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Cost.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the costDate of Cost.
	 *
	 * @return the costDate
	 */
	public Date getCostDate() {
		return costDate;
	}

	/**
	 * Set the costDate of Cost.
	 *
	 * @param costDate the costDate to set
	 */
	public void setCostDate(Date costDate) {
		this.costDate = costDate;
	}
	
}
