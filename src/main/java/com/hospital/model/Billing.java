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

/**
 * @author Krishna
 *
 */
@Entity
@Table(name = "billing")
public class Billing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="billing_id")
	private Integer billingId;
	
	@Column(name = "payment_amount")
	private Double paymentAmount;
	
	@Column(name = "payment_terms")
	private String paymentTerms;
	
	@OneToOne(targetEntity = PurchaseOrder.class,cascade=CascadeType.ALL)
	@JoinColumn(name = "purchaseOrder_id", referencedColumnName = "purchase_order_id", foreignKey = @ForeignKey(name = "purchase_order_Bill_FK"))
	private PurchaseOrder purchaseOrder;
	
	@OneToOne(targetEntity = Patient.class,cascade=CascadeType.ALL)
	@JoinColumn(name ="patient_id", referencedColumnName="patient_id", foreignKey=@ForeignKey(name="patient_Bill_FK"))
	private Patient patient;
	
	@Column(name = "billing_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date billingDate;
	
	@Column(name = "due_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;
}
