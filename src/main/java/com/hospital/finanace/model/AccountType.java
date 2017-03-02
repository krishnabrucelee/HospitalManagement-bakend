/**
 * 
 */
package com.hospital.finanace.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author admin
 *
 */
@Entity
public class AccountType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer accountTypeId;
	
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="accountType")	
	Set<AccountDetailType> accountDetailedTyes = new HashSet<AccountDetailType>();
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="profitLossId")
	private ProfitLossTypes profileLossType;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cashFlowId")
	private CashInflowandOutflowTypes cashflowType;

	public Integer getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(Integer accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AccountDetailType> getAccountDetailedTyes() {
		return accountDetailedTyes;
	}

	public void setAccountDetailedTyes(Set<AccountDetailType> accountDetailedTyes) {
		this.accountDetailedTyes = accountDetailedTyes;
	}

	public ProfitLossTypes getProfileLossType() {
		return profileLossType;
	}

	public void setProfileLossType(ProfitLossTypes profileLossType) {
		this.profileLossType = profileLossType;
	}

	public CashInflowandOutflowTypes getCashflowType() {
		return cashflowType;
	}

	public void setCashflowType(CashInflowandOutflowTypes cashflowType) {
		this.cashflowType = cashflowType;
	}
	
	
}
