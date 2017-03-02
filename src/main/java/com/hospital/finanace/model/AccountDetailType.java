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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author admin
 *
 */
@Entity
public class AccountDetailType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer accountDetailTypeId;
	
	private String name;
	
	private String description;
	
	private double balance;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="accountDetailType")
	private Set<FinanceTransaction> financeTransactions = new HashSet<FinanceTransaction>();

	@ManyToOne(cascade=CascadeType.ALL,targetEntity=AccountType.class)
	@JoinColumn(name="accountTypeId",foreignKey=@ForeignKey(name="AccountType_AccountDetailType_FK"))
	private AccountType accountType;
	
	public Integer getAccountDetailTypeId() {
		return accountDetailTypeId;
	}

	public void setAccountDetailTypeId(Integer accountDetailTypeId) {
		this.accountDetailTypeId = accountDetailTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Set<FinanceTransaction> getFinanceTransactions() {
		return financeTransactions;
	}

	public void setFinanceTransactions(Set<FinanceTransaction> financeTransactions) {
		this.financeTransactions = financeTransactions;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	
}
