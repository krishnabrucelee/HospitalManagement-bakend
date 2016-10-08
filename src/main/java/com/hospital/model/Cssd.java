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
@Table(name = "cssd")
public class Cssd {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cssd_id")
	private Integer cssdId;
	
	@Column(name = "incharge_name")
	private String inchargeName;
	
	@OneToOne(targetEntity = Department.class)
	@JoinColumn(name = "dept_id", referencedColumnName = "department_id", foreignKey = @ForeignKey(name = "department_CSSD_FK"))
	private Department department;
	
	@Column(name = "requested_date")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestedDate;
	
	@Column(name = "return_date")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CssdItemList> cssdItemList;
	
	@Column(name = "status")
	private Status status;
	
	public enum Status {
		STERILIZED,
		UNSTERILIZED
	}

	/**
	 * Get the cssdId of Cssd.
	 *
	 * @return the cssdId
	 */
	public Integer getCssdId() {
		return cssdId;
	}

	/**
	 * Set the cssdId of Cssd.
	 *
	 * @param cssdId the cssdId to set
	 */
	public void setCssdId(Integer cssdId) {
		this.cssdId = cssdId;
	}

	/**
	 * Get the inchargeName of Cssd.
	 *
	 * @return the inchargeName
	 */
	public String getInchargeName() {
		return inchargeName;
	}

	/**
	 * Set the inchargeName of Cssd.
	 *
	 * @param inchargeName the inchargeName to set
	 */
	public void setInchargeName(String inchargeName) {
		this.inchargeName = inchargeName;
	}

	/**
	 * Get the department of Cssd.
	 *
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the department of Cssd.
	 *
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * Get the requestedDate of Cssd.
	 *
	 * @return the requestedDate
	 */
	public Date getRequestedDate() {
		return requestedDate;
	}

	/**
	 * Set the requestedDate of Cssd.
	 *
	 * @param requestedDate the requestedDate to set
	 */
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	/**
	 * Get the returnDate of Cssd.
	 *
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * Set the returnDate of Cssd.
	 *
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * Get the cssdItemList of Cssd.
	 *
	 * @return the cssdItemList
	 */
	public List<CssdItemList> getCssdItemList() {
		return cssdItemList;
	}

	/**
	 * Set the cssdItemList of Cssd.
	 *
	 * @param cssdItemList the cssdItemList to set
	 */
	public void setCssdItemList(List<CssdItemList> cssdItemList) {
		this.cssdItemList = cssdItemList;
	}

	/**
	 * Get the status of Cssd.
	 *
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Set the status of Cssd.
	 *
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
