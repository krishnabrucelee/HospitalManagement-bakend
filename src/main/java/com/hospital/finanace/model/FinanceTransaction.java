/**
 * 
 */
package com.hospital.finanace.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hospital.util.DateSerializer;

/**
 * @author admin
 *
 */
@Entity
public class FinanceTransaction implements Comparable<Integer>{

	@Id
	@GeneratedValue
	private Integer financeTransactionId;
	
	private double debit;
	
	private double credit;
	
	private double begin_balance;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using=DateSerializer.class)
	private Date transactionDate;
	
	private String notes;

	@ManyToOne
	@JoinColumn(name="accountDetailTypeId",foreignKey=@ForeignKey(name="AccountDetailType_FinanceTransaction_FK"))
	private AccountDetailType accountDetailType;
	
	public Integer getFinanceTransactionId() {
		return financeTransactionId;
	}

	public void setFinanceTransactionId(Integer financeTransactionId) {
		this.financeTransactionId = financeTransactionId;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getBegin_balance() {
		return begin_balance;
	}

	public void setBegin_balance(double begin_balance) {
		this.begin_balance = begin_balance;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int compareTo(Integer o) {
		System.out.println("Am Executing");
		return 1;
	}

	public AccountDetailType getAccountDetailType() {
		return accountDetailType;
	}

	public void setAccountDetailType(AccountDetailType accountDetailType) {
		this.accountDetailType = accountDetailType;
	}

	
	
}
