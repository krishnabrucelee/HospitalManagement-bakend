package com.hospital.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
@Entity
@Table
@Component
public class MedicineItemMaster implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer medicineId;
	
	@Column
	private String medicineName;
	
	@Column
	private String genericName;
	
	@Column
	private String itemType;
			
	@Column
	private String companyName;
	
	@Column
	private String itemDescription;
	
	@Column
	private String purchaseUnit;
	
	@Column
	private String pack;
	
	@Column
	private String salesUnit;
	
	@Column
	private String category;
	
	@Column
	private Integer reorderQuantity;
	
	@Column
	private String rack;
	
	public MedicineItemMaster() {
		super();
	}

	
	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}


	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}


	public String getGenericName() {
		return genericName;
	}


	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getPurchaseUnit() {
		return purchaseUnit;
	}


	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}


	public String getPack() {
		return pack;
	}


	public void setPack(String pack) {
		this.pack = pack;
	}


	public String getSalesUnit() {
		return salesUnit;
	}


	public void setSalesUnit(String salesUnit) {
		this.salesUnit = salesUnit;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Integer getReorderQuantity() {
		return reorderQuantity;
	}


	public void setReorderQuantity(Integer reorderQuantity) {
		this.reorderQuantity = reorderQuantity;
	}


	public String getRack() {
		return rack;
	}


	public void setRack(String rack) {
		this.rack = rack;
	}


	@Override
	public String toString() {
		return "MedicineItemMaster [medicineId=" + medicineId
				+ ", medicineName=" + medicineName + ", genericName="
				+ genericName + ", itemType=" + itemType + ", companyName="
				+ companyName + ", itemDescription=" + itemDescription
				+ ", purchaseUnit=" + purchaseUnit + ", pack=" + pack
				+ ", salesUnit=" + salesUnit + ", category=" + category
				+ ", reorderQuantity=" + reorderQuantity + ", rack=" + rack
				+ "]";
	}	
		
}
