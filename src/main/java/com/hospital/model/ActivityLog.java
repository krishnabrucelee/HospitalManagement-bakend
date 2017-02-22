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

/**
 * @author Krishna
 *
 */
@Entity
@Table(name="activityLog")
public class ActivityLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_log_id")
	private Integer activityLogId;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "login_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTime;

	/**
	 * Get the activityLogId of ActivityLog.
	 *
	 * @return the activityLogId
	 */
	public Integer getActivityLogId() {
		return activityLogId;
	}

	/**
	 * Set the activityLogId of ActivityLog.
	 *
	 * @param activityLogId the activityLogId to set
	 */
	public void setActivityLogId(Integer activityLogId) {
		this.activityLogId = activityLogId;
	}

	/**
	 * Get the userName of ActivityLog.
	 *
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set the userName of ActivityLog.
	 *
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Get the loginTime of ActivityLog.
	 *
	 * @return the loginTime
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * Set the loginTime of ActivityLog.
	 *
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * Get the userId of ActivityLog.
	 *
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Set the userId of ActivityLog.
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
