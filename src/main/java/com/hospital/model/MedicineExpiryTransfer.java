package com.hospital.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table
@Component
public class MedicineExpiryTransfer implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer medicineExpiryTransferId;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")	
	@Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;
    
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")	
	@Temporal(TemporalType.TIMESTAMP)
    private Date toDate;
	
	
	private String manufactureName;
}
