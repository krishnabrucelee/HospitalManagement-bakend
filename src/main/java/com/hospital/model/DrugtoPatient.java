package com.hospital.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class DrugtoPatient {
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long drugissueId;
	
	private String medicineCode;
	
	private String medicineName;
	
	private Double medicinePrice;
	
	private Integer medicineCount;
	
	
	
	
	/*@ManyToOne(cascade = {CascadeType.ALL})
	@JoinTable(name="DRUG_PATIENT", 
				joinColumns={@JoinColumn(name="drugissueId")}, 
				inverseJoinColumns={@JoinColumn(name="patient_Id")})
	private Set<Patient> patient = new HashSet<Patient>();*/
}
