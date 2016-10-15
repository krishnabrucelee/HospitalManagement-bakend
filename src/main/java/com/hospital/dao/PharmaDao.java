package com.hospital.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.DrugIssuetoPatient;
import com.hospital.model.InvetoryMedicine;
import com.hospital.model.MedicineRequest;
import com.hospital.model.Medicine;
import com.hospital.model.MedicineEntryMaster;
import com.hospital.model.MedicineIssued;
import com.hospital.model.MedicineMaster;
import com.hospital.model.MedicineRequestByPharmacy;
import com.hospital.model.MedicineRequestToMaster;
import com.hospital.model.MedicineToPatient;
import com.hospital.model.MedicineTypes;
import com.hospital.model.PatientData;
import com.hospital.model.PharmacyOrder;
import com.hospital.model.Patient;
@Repository
public class PharmaDao {
	
	@Autowired
	SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	public JSONObject addMasterEntry(JSONObject masterentry) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null; Date expiryDate=null;
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
		Integer medicineidd = (Integer)masterentry.get("medicineId");		
		String medicineBatchId = masterentry.get("medicineBatchId").toString();
		Integer mcount = (Integer)masterentry.get("medicineCount");
		long medcount = (long)mcount;
		try {
			expiryDate= sdf.parse(masterentry.get("expiryDate").toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Medicine ID="+medicineidd);
		System.out.println("Medicine Batch  ID="+medicineBatchId);
		System.out.println("Medicine Expiry date ID="+expiryDate);
		Calendar calendar =Calendar.getInstance();Calendar calendar1 =Calendar.getInstance();
		calendar.setTime(expiryDate);
		MedicineEntryMaster medicineEntryMaster=null;MedicineTypes medicineType =null;
		MedicineMaster medicineMaster=null;Integer medicinid=null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			ObjectMapper om = new ObjectMapper();
			   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			   MedicineEntryMaster medicine = om.convertValue(masterentry, MedicineEntryMaster.class);
			   MedicineTypes  medicineTypes = om.convertValue(masterentry, MedicineTypes.class);
			   MedicineMaster master = om.convertValue(masterentry, MedicineMaster.class);
			   session.save(medicine);
			   //if(!medicinid.equalsIgnoreCase(medicineid)){
				   session.save(medicineTypes);
			   //}
		
			Query query2 = session.createQuery("FROM MedicineMaster WHERE medicineId=:searchA AND medicineBatchId=:searchB AND expiryDate=:searchC");
			List<MedicineMaster>medicineMasters= query2.setParameter("searchA", medicineidd).setParameter("searchB", medicineBatchId).setParameter("searchC", expiryDate).list();
			System.out.println("SIZE="+medicineMasters.size());
			if(medicineMasters!=null&&!medicineMasters.isEmpty()){
				System.out.println("Inside not null &not empty");
				medicineMaster=medicineMasters.get(0);
				Integer existmedicineid = (Integer)medicineMaster.getMedicineId();
				String batchId = medicineMaster.getMedicineBatchId();
				Date expiryy = medicineMaster.getExpiryDate();
				calendar1.setTime(expiryy);
				if((existmedicineid.equals(medicineidd))&&(batchId.equalsIgnoreCase(medicineBatchId))&&(calendar.compareTo(calendar1)==0)){
					System.out.println("Merge");
					Long count = medicineMaster.getMedicineCount();
					count = count+medcount;
					medicineMaster.setMedicineCount(count);
					System.out.println("Merge Part is Executing");
					session.merge(medicineMaster);
				}
				else if((existmedicineid.equals(medicineidd))&&(!batchId.equalsIgnoreCase(medicineBatchId))){
					System.out.println("Id same diff batch");
					session.save(master);
				}else{
					if((!existmedicineid.equals(medicineidd))&&(!batchId.equalsIgnoreCase(medicineBatchId))&&(calendar1.after(calendar))){
						System.out.println("New row");
						session.save(master);
					}					
				}
			}		
			//session.save(master);	
			session.getTransaction().commit();
		} catch (NullPointerException e) {
			status.put("status",false);
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject issueOrder(JSONObject productdetails) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		PharmacyOrder medicine = om.convertValue(productdetails, PharmacyOrder.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(medicine);
			session.getTransaction().commit();
			status.put("status",true);	
			status.put("status","Order isue is saved");	
		} catch (Exception e) {		
		status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getOrderById(JSONObject productdid) {
		return null;
	}
	@SuppressWarnings("unchecked")
	public JSONObject updateOrder(JSONObject productd) {
		return null;
	}
	@SuppressWarnings("unchecked")
	public JSONObject deleteOrder(JSONObject productidd) {
		System.out.println("Inside Dao1 delete");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		Integer mid = (Integer)productidd.get("orderId");
		long orderid = (long)mid;
		System.out.println("Order ID="+orderid);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();	
			String hql = "DELETE FROM pharmacypurchase WHERE orderId='"+productidd.get("orderId")+"'";
			session.createQuery(hql);
			session.getTransaction().commit();
			status.put("status", true);
			status.put("status", "Order is deleted");
			System.out.println("Order is deleted");
		} catch (Exception e) {
			status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject listOrder() {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();	
			List<PharmacyOrder> pharmacyOrders =session.createQuery("FROM PharmacyOrder").list();			
			if(pharmacyOrders!=null&&!pharmacyOrders.isEmpty()){
				status.put("PharmacyOrder", pharmacyOrders);
			}
			session.getTransaction().commit();
			status.put("status", true);
		} catch (NullPointerException e) {
			status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject medicineRequest(JSONObject medicineRequest) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);	   
		   MedicineRequestToMaster medicinerequesttomaster = om.convertValue(medicineRequest, MedicineRequestToMaster.class);
		   MedicineRequestByPharmacy medicinerequestbypharmacy = om.convertValue(medicineRequest, MedicineRequestByPharmacy.class);
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(medicinerequesttomaster);
			session.save(medicinerequestbypharmacy);
			session.getTransaction().commit();
			status.put("status",true);	
			status.put("status","medicineRequest isue is saved");	
		} catch (Exception e) {		
		status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	//
	public JSONObject getMedicineRequestMasterById(JSONObject requestid) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		Integer requestId= (Integer)requestid.get("requestId");
		List<MedicineRequestToMaster> medicineRequest=null;
		try {	
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
		Query query = session.createQuery("FROM MedicineRequestToMaster WHERE requestId=:searchA");
		medicineRequest = query.setParameter("searchA", requestId).list();
		session.getTransaction().commit();
		if(medicineRequest!=null&&!medicineRequest.isEmpty()){
			status.put("RequestToMaster", medicineRequest);
			status.put("status", true);
		}
		} catch (NullPointerException e) {
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")//Give  to pharmacy And UPDATE COUNT MASTER
	public JSONObject medicineIssuetoPharmacy(JSONObject medicinetoPharmacy){
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Integer medicineId = (Integer)medicinetoPharmacy.get("medicineId");
		Integer requestId = (Integer)medicinetoPharmacy.get("requestId");
		Integer deptid = (Integer)medicinetoPharmacy.get("departmentId");
		Integer pharmacyId = (Integer)medicinetoPharmacy.get("pharmacyId");
		String batchid = medicinetoPharmacy.get("medicineBatchId").toString();
		Date expiryDatee=null;
		try {
			expiryDatee= sdf.parse(medicinetoPharmacy.get("expiryDate").toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		   MedicineRequestToMaster medicineRequest = null;List<MedicineRequestToMaster> medicineRequesta = null;
		List<MedicineMaster> medicineMaster =null;
		MedicineMaster masterStock =null;
		Integer quantity=0;
		Long medicinecount=0l;
		Long medicineupdatedcount=0l;	Long finalupdatedcount=0l;
		MedicineIssued medicineIssued = new MedicineIssued();//Long medicinecountt =null;
		Integer requestcount =null;
		Medicine medicine = new Medicine();
		try {		
			System.out.println("Inside Dao1 medicineIssuetoPharmacy try");
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM MedicineRequestToMaster WHERE requestId=:searchA");
			medicineRequesta = query.setParameter("searchA", requestId).list();
			if(medicineRequesta!=null && !medicineRequesta.isEmpty()){
				medicineRequest=medicineRequesta.get(0);
				medicine.setMedicineId(medicineRequest.getMedicineId());
				medicine.setDepartmentId(medicineRequest.getDepartmentId());
				medicine.setPharmacyId(medicineRequest.getPharmacyId());
				requestcount = medicineRequest.getMedicineCount();//Pharmacy request count				
				medicine.setMedicineCount(medicineRequest.getMedicineCount());
			}
			Query querya = session.createQuery("FROM MedicineMaster WHERE medicineId=:searchA AND medicineBatchId=:searchB AND expiryDate=:searchC");
			medicineMaster = querya.setParameter("searchA", medicineId).setParameter("searchB", batchid).setParameter("searchC", expiryDatee).list();
			if(medicineMaster!=null && !medicineMaster.isEmpty()){
				masterStock = medicineMaster.get(0);
				medicine.setExpiredDate(masterStock.getExpiryDate());
				medicine.setMedicineBatchId(masterStock.getMedicineBatchId());
				medicine.setMedicineComposition(masterStock.getMedicineComposition());
				medicine.setMedicineName(masterStock.getMedicineName());
				medicine.setMedicinePower(masterStock.getMedicinePower());
				medicine.setMedicinePrice(masterStock.getMedicinePrice());
				medicine.setMedicineType(masterStock.getMedicineType());
				for (MedicineMaster medicineMaster2 : medicineMaster) {
					medicinecount = medicineMaster2.getMedicineCount();                                                                
				}
			}
	      session.save(medicine);
			medicineupdatedcount =medicinecount;//db count
			finalupdatedcount=medicineupdatedcount-(long)requestcount;//update master
			masterStock.setMedicineCount(finalupdatedcount);
			session.merge(masterStock);
			medicineIssued.setRequestId(medicineRequest.getRequestId());
			medicineIssued.setPharmacyId(medicineRequest.getPharmacyId());
			medicineIssued.setDepartmentId(deptid);                                                                                                                    
			medicineIssued.setMedicineCount(requestcount);//quantity
			medicineIssued.setMedicineId(medicineId);
			medicineIssued.setReturnDate(new Date());			
			session.save(medicineIssued);
			session.getTransaction().commit();
			status.put("status", true);
		} catch (NullPointerException e) {
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getMedicineRequestPharmacyById(JSONObject requestid) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		Integer requestId= (Integer)requestid.get("requestId");
		List<MedicineRequestToMaster> medicineRequest=null;
		try {	
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
		Query query = session.createQuery("FROM MedicineRequestByPharmacy WHERE requestId=:searchA");
		medicineRequest = query.setParameter("searchA", requestId).list();
		session.getTransaction().commit();
		if(medicineRequest!=null&&!medicineRequest.isEmpty()){
			status.put("RequestByPharmacy", medicineRequest);
			status.put("status", true);
		}
		} catch (NullPointerException e) {
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject updateMasterPharmacyReturn(JSONObject returnupdate) {//MASTER PHARMACY STATUS UPDATE
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Integer requestId= (Integer)returnupdate.get("requestId");
		String returnstatuss = returnupdate.get("returnStatus").toString();
		Date retunnDate=null;
		try {
			retunnDate= sdf.parse(returnupdate.get("returnDate").toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("update MedicineRequestToMaster set returnStatus=:searchA ,returnDate=:searchB where requestId=:searchC");
			query.setParameter("searchA", returnstatuss).setParameter("searchB", retunnDate).setParameter("searchC", requestId);
			 query.executeUpdate();
			session.getTransaction().commit();
		} catch (NullPointerException e) {
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject updatePharmacyRecived(JSONObject receiveupdate) {//DEPT PHARMACY STAUS update
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Integer requestId= (Integer)receiveupdate.get("requestId");
		String receivestatuss = receiveupdate.get("receivedStatus").toString();
		Date receiveDate=null;
		try {
			receiveDate= sdf.parse(receiveupdate.get("receivedDate").toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("update MedicineRequestByPharmacy set receivedStatus=:searchA ,receivedDate=:searchB where requestId=:searchC");
			query.setParameter("searchA", receivestatuss).setParameter("searchB", receiveDate).setParameter("searchC", requestId);
			 query.executeUpdate();
			session.getTransaction().commit();
		} catch (NullPointerException e) {
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject medicinetoPatient(JSONObject medicinetoPatient) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		Integer medicineAddid = (Integer)medicinetoPatient.get("medicineAddId");
		Integer pid = (Integer)medicinetoPatient.get("patientId");
		Integer medicineid = (Integer)medicinetoPatient.get("medicineId");
		System.out.println("medicineid ="+medicineid);
		Patient patient=null;
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MedicineToPatient medicineToPatient = om.convertValue(medicinetoPatient, MedicineToPatient.class);
		Integer count = medicineToPatient.getMedicineCount();
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			patient = session.get(Patient.class, new Integer(pid));
			medicineToPatient.setPatient(patient);
		Medicine medicine = null;//getMedicineByIdPower(medicineid);
		List<Medicine>medicinee = session.createQuery("FROM Medicine WHERE medicineAddId=:searchA").setParameter("searchA", medicineAddid).list();
		if(medicinee!=null && !medicinee.isEmpty()){
			medicine = medicinee.get(0);
		}else {
			status.put("status", false);
			status.put("reason", "MedicineAddId is not matched");
		}
		medicineToPatient.setMedicineBatchId(medicine.getMedicineBatchId());
		medicineToPatient.setMedicineComposition(medicine.getMedicineComposition());
		Integer medcount = medicine.getMedicineCount();
		medcount = medcount-count;	
		medicine.setMedicineCount(medcount);
			session.save(medicineToPatient);
			session.merge(medicine);
			session.getTransaction().commit();
			status.put("status",true);	
			status.put("status","Medicine ToPatient  is updated or saved ");	
			
		} catch (NullPointerException e) {
			status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject patientReturnMedicine(JSONObject patientReturnMedicine) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		Integer patid = (Integer)patientReturnMedicine.get("patientId");
		Integer medicineissueid = (Integer)patientReturnMedicine.get("medicineIssueId");
		Integer medicineAddid = (Integer)patientReturnMedicine.get("medicineAddId");
		Integer medicineid = (Integer)patientReturnMedicine.get("medicineId");
		Integer mcount = (Integer)patientReturnMedicine.get("medicineCount");	
		MedicineToPatient medicineToPatient= null;
		Medicine medicine =null;
       try {
    	   session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();	
			Query query  =session.createQuery("FROM MedicineToPatient WHERE medicineIssueId=:searchA AND patientId=:searchB");
		List<MedicineToPatient>	 medicineToPatients	 = query.setParameter("searchA", medicineissueid).setParameter("searchB", patid).list();
		System.out.println("SIZE="+medicineToPatients.size());	
		if(medicineToPatients!=null && !medicineToPatients.isEmpty()){
			medicineToPatient= medicineToPatients.get(0);
		}
		Query query2 = session.createQuery("FROM Medicine WHERE medicineAddId=:searchA AND medicineId=:searchB");
		List<Medicine> medicines = query2.setParameter("searchA", medicineAddid).setParameter("searchB", medicineid).list();
		System.out.println("SIZE="+medicines.size());
		if(medicines!=null&&!medicines.isEmpty()){
			medicine = medicines.get(0);
		}
		Integer pmedcount = medicineToPatient.getMedicineCount();
		pmedcount=pmedcount-mcount;
		medicineToPatient.setMedicineCount(pmedcount);
		session.merge(medicineToPatient);
		Integer medicinescount = medicine.getMedicineCount();
		medicinescount=medicinescount+mcount;
		medicine.setMedicineCount(medicinescount);
		session.merge(medicine);
		status.put("status",true);		
		session.getTransaction().commit();
       } catch (NullPointerException e) {
			status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	public MedicineRequest getMadicineRequestId(long reid){
		MedicineRequest medicineRequest=null;
		Session session = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			medicineRequest = (MedicineRequest)session.get(MedicineRequest.class, reid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	if (medicineRequest!=null) {
		return medicineRequest;
	} else {
		return medicineRequest;
	}		
	}	
	public Medicine getMedicineByIdPower(Long mid){
		Medicine medicine=null;
		Session session = null;Transaction tr=null;	
		try {
			session = this.sessionFactory.openSession();
			tr=session.beginTransaction();
		 String query = "select * from medicine where medicineId=";	
		 medicine = (Medicine)session.createSQLQuery(query).list();
			tr.commit();
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		if(session.isOpen()){
			session.close();
		}
		if (medicine!=null) {
			return medicine;
		} else {
			return null;
		}
		
	}
}
