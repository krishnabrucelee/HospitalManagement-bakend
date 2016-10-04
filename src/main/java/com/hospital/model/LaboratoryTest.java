/**
 * 
 */
package com.hospital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "laboratoryTest")
public class LaboratoryTest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name="test_id")
	private String testId;
	
	@Column(name="test_name")
	private String testName;
	
	@Column(name="test_description")
	private String testDescription;

	/**
	 * Get the id of LaboratoryTest.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the id of LaboratoryTest.
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get the testId of LaboratoryTest.
	 *
	 * @return the testId
	 */
	public String getTestId() {
		return testId;
	}

	/**
	 * Set the testId of LaboratoryTest.
	 *
	 * @param testId the testId to set
	 */
	public void setTestId(String testId) {
		this.testId = testId;
	}

	/**
	 * Get the testName of LaboratoryTest.
	 *
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}

	/**
	 * Set the testName of LaboratoryTest.
	 *
	 * @param testName the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}

	/**
	 * Get the testDescription of LaboratoryTest.
	 *
	 * @return the testDescription
	 */
	public String getTestDescription() {
		return testDescription;
	}

	/**
	 * Set the testDescription of LaboratoryTest.
	 *
	 * @param testDescription the testDescription to set
	 */
	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}
	
}
