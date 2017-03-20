/**
 * 
 */
package com.hospital.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.hospital.finanace.model.AccountDetailType;
import com.hospital.finanace.model.AccountType;

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "expense")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="expense_id")
	private Integer expenseId;
	
    /** Staff */
    @OneToOne(targetEntity = Staff.class)
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", foreignKey = @ForeignKey(name = "staff_EXP_FK"))
    private Staff staff;

    /** account category */
    @OneToOne(targetEntity = AccountCategoryChart.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "account_category_chart_id", referencedColumnName = "account_category_chart_id", foreignKey = @ForeignKey(name = "account_chart_EXP_FK"))
    private AccountCategoryChart accountCategoryChart;
    
	@Temporal(TemporalType.DATE)
	@Column(name = "payment_date")
	private Date paymentDate;
	
	@Column(name = "payment_method")
	private String paymentMethod;
	
    /** account category */
    @OneToOne(targetEntity = AccountType.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "account_type_id", referencedColumnName = "accountTypeId", foreignKey = @ForeignKey(name = "account_type_EXP_FK"))
    private AccountType accountType;
    
	@Column(name = "account_description")
	private String accountDescription;
	
	@Column(name = "account_amount")
	private Double accountAmount;

	@OneToMany(cascade=CascadeType.ALL)	
	List<ExpenseItems> expenseItems;

	/**
	 * Get the expenseId of Expense.
	 *
	 * @return the expenseId
	 */
	public Integer getExpenseId() {
		return expenseId;
	}

	/**
	 * Set the expenseId of Expense.
	 *
	 * @param expenseId the expenseId to set
	 */
	public void setExpenseId(Integer expenseId) {
		this.expenseId = expenseId;
	}

	/**
	 * Get the staff of Expense.
	 *
	 * @return the staff
	 */
	public Staff getStaff() {
		return staff;
	}

	/**
	 * Set the staff of Expense.
	 *
	 * @param staff the staff to set
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/**
	 * Get the accountCategoryChart of Expense.
	 *
	 * @return the accountCategoryChart
	 */
	public AccountCategoryChart getAccountCategoryChart() {
		return accountCategoryChart;
	}

	/**
	 * Set the accountCategoryChart of Expense.
	 *
	 * @param accountCategoryChart the accountCategoryChart to set
	 */
	public void setAccountCategoryChart(AccountCategoryChart accountCategoryChart) {
		this.accountCategoryChart = accountCategoryChart;
	}

	/**
	 * Get the paymentDate of Expense.
	 *
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * Set the paymentDate of Expense.
	 *
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * Get the paymentMethod of Expense.
	 *
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Set the paymentMethod of Expense.
	 *
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * Get the accountType of Expense.
	 *
	 * @return the accountType
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * Set the accountType of Expense.
	 *
	 * @param accountType the accountType to set
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	/**
	 * Get the accountDescription of Expense.
	 *
	 * @return the accountDescription
	 */
	public String getAccountDescription() {
		return accountDescription;
	}

	/**
	 * Set the accountDescription of Expense.
	 *
	 * @param accountDescription the accountDescription to set
	 */
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	/**
	 * Get the accountAmount of Expense.
	 *
	 * @return the accountAmount
	 */
	public Double getAccountAmount() {
		return accountAmount;
	}

	/**
	 * Set the accountAmount of Expense.
	 *
	 * @param accountAmount the accountAmount to set
	 */
	public void setAccountAmount(Double accountAmount) {
		this.accountAmount = accountAmount;
	}

	/**
	 * Get the expenseItems of Expense.
	 *
	 * @return the expenseItems
	 */
	public List<ExpenseItems> getExpenseItems() {
		return expenseItems;
	}

	/**
	 * Set the expenseItems of Expense.
	 *
	 * @param expenseItems the expenseItems to set
	 */
	public void setExpenseItems(List<ExpenseItems> expenseItems) {
		this.expenseItems = expenseItems;
	}
	
}
