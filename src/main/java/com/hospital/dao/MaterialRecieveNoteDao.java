/**
 * 
 */
package com.hospital.dao;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Department;
import com.hospital.model.MaterialRecieveNote;
import com.hospital.model.MedicineItemMaster;
import com.hospital.model.Patient;
import com.hospital.model.PurchaseOrder;
import com.hospital.model.PurchaseOrderTransaction;
import com.hospital.model.StockMedicine;
import com.hospital.model.Supplier;
import com.hospital.service.StockMedicineService;

/**
 * @author Krishna
 *
 */
@Repository
public class MaterialRecieveNoteDao {

	/**
	 * The internal state of a SessionFactory is immutable. Once it is created
	 * this internal state is set. This internal state includes all of the
	 * metadata about Object/Relational Mapping.
	 */
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	StockMedicineService stockMedicineService;

	static {
		System.out.println("class MaterialRecieveNoteDao executed");
	}

	private Session session = null;
	private Transaction transaction = null;

	@SuppressWarnings("unchecked")
	public JSONObject addMaterialRecieveNote(JSONObject materialRecieveNote) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MaterialRecieveNote appoint = om.convertValue(materialRecieveNote, MaterialRecieveNote.class);
		appoint.setMrnNumber("MRN" + String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
		// Load PurchaseOrder
		PurchaseOrder purchaseOrderDetails = session.load(PurchaseOrder.class, appoint.getPurchaseOrder().getPurchaseOrderId());
		appoint.setPurchaseOrder(purchaseOrderDetails);
		
		List<PurchaseOrderTransaction> purchaseOrderList = purchaseOrderDetails.getPurchaseOrderTransaction();

		StockMedicine stock = om.convertValue(materialRecieveNote.get("stockMedicine"), StockMedicine.class);
		stock.setPurchaseOrder(appoint.getPurchaseOrder());
		stock.setMaterialRecieveNote(appoint);
		for (PurchaseOrderTransaction purchase : purchaseOrderList) {
//			purchase.setMaterialRecieveNote(appoint);
//			MedicineItemMaster medicine = purchase.getMedicineItemMaster();
//			stock.setMedicineItemMaster(medicine);
//			stock.setBatchId("STK" + String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
//			stock.setDepartment(purchaseOrderDetails.getDepartment());
//			stock.setExpiryDate(medicine);
//			stock.setItemName(medicine.getMedicineName());
//			stock.setItemUnits(medicine.getSalesUnit());
//			stock.setManufactureDate(medicine.get);
//			stock.setMaterialRecieveNote(materialRecieveNote);
//			stock.setNumberofUnits(numberofUnits);
//			stock.setNumbersInUnit(numbersInUnit);
//			stock.setPurchaseDate(purchaseDate);
//			stock.setPurchaseOrder(purchaseOrder);
//			stock.setPurchasePrice(purchasePrice);
//			stock.setQuantity(quantity);
//			stock.setSalesPrice(salesPrice);
//			stock.setStockMedicineId(stockMedicineId);
//			stock.setStockMedicineTransaction(stockMedicineTransaction);
		}

		try {
			System.out.println("Inside Dao11 PATIENT");
			session.save(appoint);
			session.save(stock);
			transaction.commit();
			System.out.println("Save materialRecieveNotes");
			status.put("success", "User details saved");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public JSONObject listMaterialRecieveNote() {
		System.out.println("Inside Dao1MaterialRecieveNote");
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<MaterialRecieveNote> materialRecieveNoteList = null;
		try {
			Query query = session.createQuery("FROM MaterialRecieveNote");
			materialRecieveNoteList = query.list();
			status.put("MaterialRecieveNote", materialRecieveNoteList);
			status.put("result", true);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public JSONObject updateMaterialRecieveNote(JSONObject materialRecieveNote) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			MaterialRecieveNote materialRecieveNoteDetails = session.load(MaterialRecieveNote.class,
					(Integer) materialRecieveNote.get("materialRecieveNoteId"));
			session.update(materialRecieveNoteDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	public MaterialRecieveNote getMaterialRecieveNote(Integer materialRecieveNoteId) {
		MaterialRecieveNote materialRecieveNote = null;
		try {
			session.beginTransaction();
			session.get(MaterialRecieveNote.class, materialRecieveNoteId);
			materialRecieveNote = (MaterialRecieveNote) session.get(MaterialRecieveNote.class, materialRecieveNoteId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		if (materialRecieveNote != null) {
			return materialRecieveNote;
		} else {
			return null;
		}
	}

	public JSONObject deleteMaterialRecieveNote(JSONObject materialRecieveNoteId) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			MaterialRecieveNote materialRecieveNoteDetails = session.load(MaterialRecieveNote.class,
					(Integer) materialRecieveNoteId.get("materialRecieveNoteId"));
			session.delete(materialRecieveNoteDetails);
			transaction.commit();
		} catch (Exception e) {
			status.put("status", false);
			status.put("reason", "Error happend");
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

	/**
	 * @param patient
	 * @return
	 */
	public JSONObject getMrnByPurchaseId(JSONObject patient) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		List<MaterialRecieveNote> patientDetailsList = null;
		
		PurchaseOrder purchaseId = session.load(PurchaseOrder.class, Integer.parseInt(patient.get("id").toString()));
		try {
			Query query = session.createQuery("FROM MaterialRecieveNote WHERE purchaseOrder = :id");
			query.setParameter("id", purchaseId);
			patientDetailsList = query.list();
			status.put("MaterialRecieveNote", patientDetailsList);
			System.out.println(" Inside Rest DAO Bus Status="+status);
			transaction.commit();
			return status;	
			
		} catch (Exception e) {
			e.printStackTrace();
			status.put("result", false);
		} finally {
			if (session.isOpen()) {
				// session.close();
			}
		}
		return status;
	}

}
