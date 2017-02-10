package com.hospital.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Department;
import com.hospital.model.Doctor;
import com.hospital.model.DrugIssuetoPatient;
import com.hospital.model.InvetoryMedicine;
import com.hospital.model.MasterMedicineOrder;
import com.hospital.model.MedicineItemMaster;
import com.hospital.model.MedicineRequest;
import com.hospital.model.Medicine;
import com.hospital.model.MedicineEntryMaster;
import com.hospital.model.MedicineIssued;
import com.hospital.model.MedicineMaster;
import com.hospital.model.MedicineRequestByPharmacy;
import com.hospital.model.MedicineRequestToMaster;
import com.hospital.model.MedicineToPatient;
import com.hospital.model.MedicineTypes;
import com.hospital.model.PatientMedicineList;
import com.hospital.model.PharmacyBill;
import com.hospital.model.PharmacyMasterEntry;
import com.hospital.model.PharmacyOrder;
import com.hospital.model.Patient;
import com.hospital.model.PharmacyRequestMedicine;
import com.hospital.model.Staff;
@Repository
public class PharmaDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("om")
    private ObjectMapper om;
	
	@Autowired
	@Qualifier("jsonViewObjectMapper")
    private ObjectMapper jsonViewObjectMapper;
	
	@SuppressWarnings("unchecked")
	public JSONObject saveMasterMedicineItem(JSONObject masterMedicineItem) {
		JSONObject status = new JSONObject();			
		Session session = null;				
		MedicineItemMaster medicineMaster = om.convertValue(masterMedicineItem, MedicineItemMaster.class);		
		try {		
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(medicineMaster);
			session.getTransaction().commit();
			status.put("status",true);	
			status.put("status","Master Medicine  isue is saved");	
		} catch (Exception e) {		
		  status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject listMasterMedicineItem() {
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null;
		List<MedicineItemMaster> master =null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM MedicineItemMaster");
			master = query.list();		
			if (master!=null && !master.isEmpty()) {
				status.put("MasterMedicineitem", master);
				status.put("status", true);	
				
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "MedicineItemMaster table have empty");
				status.put("status", false);
			}
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject savePharmacyMasterEntry(JSONObject pharmacyMasterEntry) {
		JSONObject status = new JSONObject();
		System.out.println("Inside webservice save controller");
		Session session = null;	
	    Date purchaseDate=null,mfgDate=null,expiryDate=null;	
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Integer medicineidd = (Integer)pharmacyMasterEntry.get("medicineId");		
		String medicineBatchId = pharmacyMasterEntry.get("batchId").toString();		
		System.out.println("medicineidd="+medicineidd);
	
		long quantity =0l;			
		try {
			purchaseDate= sdf.parse(pharmacyMasterEntry.get("purchaseDate").toString());
			mfgDate= sdf.parse(pharmacyMasterEntry.get("manufactureDate").toString());
			expiryDate= sdf.parse(pharmacyMasterEntry.get("expiryDate").toString());			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar dbexpiryDate =Calendar.getInstance();Calendar formexpiryDate =Calendar.getInstance();
		formexpiryDate.setTime(expiryDate);			
		formexpiryDate.set(Calendar.HOUR_OF_DAY, 0);
		formexpiryDate.set(Calendar.MINUTE, 0);
		formexpiryDate.set(Calendar.SECOND, 0);	
		List<PharmacyMasterEntry> pharmacyEntryMaster =null;
		PharmacyMasterEntry dbmedicineMasters = null;
		PharmacyMasterEntry pharmacyMasterEntries = om.convertValue(pharmacyMasterEntry, PharmacyMasterEntry.class);				
		pharmacyMasterEntries.setPurchaseDate(purchaseDate);
		pharmacyMasterEntries.setManufactureDate(mfgDate);
		pharmacyMasterEntries.setExpiryDate(expiryDate);
		Integer mcount = pharmacyMasterEntries.getNumbersInUnit();
		Integer numberofunits = pharmacyMasterEntries.getNumberofUnits();
		System.out.println("mcount="+mcount);
		System.out.println("numberofunits="+numberofunits);
		quantity = mcount*numberofunits;
		System.out.println("numberofunits="+quantity);
		try {		
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			MedicineItemMaster mt = new MedicineItemMaster();
			mt.setMedicineId(medicineidd);
			
			pharmacyMasterEntries.setMedicineMaster(mt);
					
			//MedicineItemMaster medicineItemMaster = session.load(MedicineItemMaster.class, (Integer)pharmacyMasterEntry.get("medicineId"));
			Query query = session.createQuery("FROM PharmacyMasterEntry WHERE medicineId =:searchA");	
			pharmacyEntryMaster=query.setParameter("searchA", medicineidd).list();
			if (pharmacyEntryMaster!=null && !pharmacyEntryMaster.isEmpty()) {
				dbmedicineMasters = pharmacyEntryMaster.get(0);
				Integer existmedicineid = (Integer)dbmedicineMasters.getMedicineMaster().getMedicineId();
				String batchId = dbmedicineMasters.getBatchId();
				Date expiryy = dbmedicineMasters.getExpiryDate();
				dbexpiryDate.setTime(expiryy);	
				if(existmedicineid.equals(medicineidd)){
					System.out.println("existmedicineid == medicineidd");
					if((batchId.equalsIgnoreCase(medicineBatchId))&&(dbexpiryDate.compareTo(formexpiryDate)==0)){
						System.out.println("Inside existmedicineid == existmedicineid ,batchid==batchid,equal expiry date");
						Long dbQuantity = dbmedicineMasters.getQuantity();
						dbQuantity = dbQuantity+quantity;					
						dbmedicineMasters.setQuantity(dbQuantity);
						session.merge(dbmedicineMasters);			
					} else if((existmedicineid.equals(medicineidd))&&(!batchId.equalsIgnoreCase(medicineBatchId))) {
						pharmacyMasterEntries.setQuantity(quantity);
						System.out.println("Inside existmedicineid == existmedicineid ,batchid=!batchid,not equal expiry date");
						session.save(pharmacyMasterEntries);
					}
				}else{
					System.out.println("Inside existmedicineid == and !existmedicineid");
					
					pharmacyMasterEntries.setQuantity(quantity);
					session.save(pharmacyMasterEntries);
				}
			
			} else if(	pharmacyEntryMaster==null || pharmacyEntryMaster.isEmpty()){				
				
					pharmacyMasterEntries.setQuantity(quantity);
					session.save(pharmacyMasterEntries);						
			}else{
					System.err.println("PharmacyEntryMaster is empty");
				    status.put("status",false);			
					status.put("originalErrorMsg", "PharmacyEntryMaster is empty");
					return status;
			}
			session.getTransaction().commit();
			status.put("status",true);	
			status.put("status","Master Medicine  isue is saved");	
		} catch (Exception e) {		
		  status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public JSONObject listPharmacyMasterEntry() {
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null;
		List<PharmacyMasterEntry> master =null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM PharmacyMasterEntry");
			master = query.list();	
			session.getTransaction().commit();
			System.out.println("PharmacyMasterEntry="+master.size());
			System.out.println("PharmacyMasterEntry="+master);
			if (master!=null && !master.isEmpty()) {
				status.put("PharmacyMasterStock", master);
				status.put("status", true);	
				
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "PharmacyMasterEntry table have empty");
				status.put("status", false);
			}
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	
	
	public JSONObject savePharmacyBill(JSONObject pharmacyBillDatas) {
		JSONObject status = new JSONObject();			
		Session session = null;		
		
		Integer patientid = Integer.parseInt(pharmacyBillDatas.get("patientId").toString());
		Integer doctorid = Integer.parseInt(pharmacyBillDatas.get("doctorId").toString());
		Integer stafid = Integer.parseInt(pharmacyBillDatas.get("staffId").toString());
		
		Patient patient = new Patient();
		patient.setPatientId(patientid);
		
		Doctor doctor =new Doctor();
		doctor.setDoctorId(doctorid);
		
		Staff s=new Staff();
			s.setStaffId(stafid);	
		PharmacyBill pharmacyBill = om.convertValue(pharmacyBillDatas, PharmacyBill.class);		
		pharmacyBill.setPatient(patient);
		
		pharmacyBill.setDoctor(doctor);
		pharmacyBill.setStaff(s);
		//PatientMedicineList lst = (PatientMedicineList) pharmacyBill.getPatientMedicineList();
		/*int a = (int) ((int)lst.getQuantity()*lst.getPrice());
		lst.setAmount();*/
		// Generate backend fields
		pharmacyBill.setBillDate(new Date());
		pharmacyBill.setBillNumber(RandomStringUtils.random(10));
		
		try {		
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			session.save(pharmacyBill);
			
			
			/** Update Quantity in Master table **/
			
			List<PatientMedicineList> medicneList = pharmacyBill.getPatientMedicineList();
			
			List<Long> medicineIds = medicneList.stream().map(v->
				{
					v.setAmount((int)(v.getQuantity() * v.getPrice()));
					return Long.valueOf(v.getPharmacyMasterId());
				})
				.collect(Collectors.toList());
			
			System.out.println(medicineIds);
			
			Criteria ct = session.createCriteria(PharmacyMasterEntry.class)
			.add(Restrictions.in("pharmacyMasterId", medicineIds));
			List<PharmacyMasterEntry> PMEList = ct.list();
			
			
			if(PMEList.size() != medicineIds.size()){
				System.err.println("Any one Pharmacy Bill Item is not matched or not founded in PharmacyMasterEntry table.");				
			}
			
			PMEList.stream().forEach(PME->{
				
				long pharmacyMedicineId = PME.getPharmacyMasterId();
				
				
				medicneList.stream().anyMatch(v->{					
					if(pharmacyMedicineId == v.getPharmacyMasterId().longValue())	
					{
						PME.setQuantity(PME.getQuantity() - v.getQuantity());		
						return true;
					}
					else 
					{						
						return false;	
					}
				});			
			
				
			});
			
			session.getTransaction().commit();
			status.put("status",true);	
			status.put("status","Master Medicine  isue is saved");	
		} catch (Exception e) {		
		  status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject orderMasterMedicine(JSONObject orderMasterMedicine) {
		JSONObject status = new JSONObject();	
		status.put("status", true);
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date orderDate = null;
		Session session = null;
		try {
			orderDate=sdf.parse(orderMasterMedicine.get("orderDate").toString());
		 } catch (ParseException e) {
			e.printStackTrace();
		}
		ObjectMapper om = new ObjectMapper();
		   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MasterMedicineOrder orderMedicine = om.convertValue(orderMasterMedicine, MasterMedicineOrder.class);
		orderMedicine.setOrderDate(orderDate);
		try {		
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(orderMedicine);
			session.getTransaction().commit();
			status.put("status",true);	
			status.put("status","Master Medicine Order isue is saved");	
		} catch (Exception e) {		
		  status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject addMasterEntryData(JSONObject masterentry) {		
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null; Date purchaseDate=null,mfgDate=null,expiryDate=null;	
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Integer medicineidd = (Integer)masterentry.get("medicineId");		
		String medicineBatchId = masterentry.get("medicineBatchId").toString();
		Integer mcount = (Integer)masterentry.get("medicineCount");
		String medPower = masterentry.get("medicinePower").toString();
		String medComposition = masterentry.get("medicineComposition").toString();
		long medcount = (long)mcount;		
		try {
			purchaseDate= sdf.parse(masterentry.get("purchaseDate").toString());
			mfgDate= sdf.parse(masterentry.get("manufactureDate").toString());
			expiryDate= sdf.parse(masterentry.get("expiryDate").toString());			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar dbexpiryDate =Calendar.getInstance();Calendar formexpiryDate =Calendar.getInstance();
		formexpiryDate.setTime(expiryDate);			
		formexpiryDate.set(Calendar.HOUR_OF_DAY, 0);
		formexpiryDate.set(Calendar.MINUTE, 0);
		formexpiryDate.set(Calendar.SECOND, 0);	
		MedicineEntryMaster medicineEntryMaster=null;MedicineTypes medicineTypes =null;
		MedicineMaster medicineMaster=null;Integer medicinid=null;
		List<MedicineMaster>medicineMasters=null;List<MedicineTypes>medicinetype= null;
		MedicineMaster dbmedicineMasters=null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			ObjectMapper om = new ObjectMapper();
			   om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			   medicineEntryMaster = om.convertValue(masterentry, MedicineEntryMaster.class);
			   medicineEntryMaster.setPurchaseDate(purchaseDate);medicineEntryMaster.setManufactureDate(mfgDate);
			   medicineEntryMaster.setExpiryDate(expiryDate);
			    medicineTypes = om.convertValue(masterentry, MedicineTypes.class);
			   medicineMaster = om.convertValue(masterentry, MedicineMaster.class);
			   medicineMaster.setManufactureDate(mfgDate);medicineMaster.setExpiryDate(expiryDate);
			 
			  
			   session.save(medicineEntryMaster);
			   Query query2 = session.createQuery("FROM MedicineMaster WHERE medicineId=:searchA AND medicineBatchId=:searchB AND expiryDate=:searchC");
				medicineMasters= query2.setParameter("searchA", medicineidd).setParameter("searchB", medicineBatchId).setParameter("searchC", expiryDate).list();
				Query query = session.createQuery("FROM MedicineTypes WHERE medicinePower=:searchA AND medicineComposition=:searchB");
				medicinetype = query.setParameter("searchA", masterentry.get("medicinePower").toString())
						.setParameter("searchB", masterentry.get("medicineComposition").toString()).list();
				
			   if (medicineMasters!=null &&!medicineMasters.isEmpty() ) {
				  
				   dbmedicineMasters=medicineMasters.get(0);
					Integer existmedicineid = (Integer)dbmedicineMasters.getMedicineId();
					String batchId = dbmedicineMasters.getMedicineBatchId();
					Date expiryy = dbmedicineMasters.getExpiryDate();
					dbexpiryDate.setTime(expiryy);					
					if(((existmedicineid.equals(medicineidd))&&(batchId.equalsIgnoreCase(medicineBatchId)))&&
		                    ((dbexpiryDate.compareTo(formexpiryDate)==0) &&(dbmedicineMasters.getMedicinePower().equals(masterentry.get("medicinePower"))))){
								Long count = dbmedicineMasters.getMedicineCount();
								count = count+medcount;
								
								dbmedicineMasters.setMedicineCount(count);						
								session.merge(dbmedicineMasters);
							}else if ((existmedicineid.equals(medicineidd))&&(!batchId.equalsIgnoreCase(medicineBatchId))) {
							
								session.save(medicineMaster);
							} 			
			      } else if (medicineMasters==null ||medicineMasters.isEmpty() ) {
				
					session.save(medicineMaster);
				  }else{
					
				  }
			   
			   if (medicinetype!=null&&!medicinetype.isEmpty()) {
					MedicineTypes medicineTypes2 = medicinetype.get(0);
					if((medicineTypes2.getMedicinePower().equals(medPower))&&(medicineTypes2.getMedicineComposition().equals(medComposition))){
						Long count = medicineTypes2.getMedicineCount();
						count+=medcount;
						medicineTypes2.setMedicineCount(count);
						session.merge(medicineTypes2);
					}
				} else if(medicinetype==null||medicinetype.isEmpty()) {
					 session.save(medicineTypes);
				}
			   /*else {

			}*/
			   session.getTransaction().commit();
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	@SuppressWarnings( "unchecked")
	public JSONObject listMasterEntry() {
		JSONObject status = new JSONObject();
		status.put("status", true);	
		Session session = null;
		List<MedicineMaster> master =null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM MedicineMaster");
			master = query.list();
			System.out.println("Web service Master size="+master.size());
			if (master!=null && !master.isEmpty()) {
				status.put("MasterMedicine", master);
				status.put("status", true);	
				
			} else {
				status.put("status", "Error happened");
				status.put("originalErrorMsg", "MedicineMaster table have empty");
				status.put("status", false);
			}
		} catch (Exception e) {
			status.put("status", "Error happened");
			status.put("originalErrorMsg", e.getMessage());
			status.put("status", false);
			e.printStackTrace();
				
		}finally{
			if(session!=null && session.isOpen()){
				session.close();
			}
		}
		return status;
	}
	@SuppressWarnings({ "unchecked", "null" })
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
			
			/*if( medicineMasters.isEmpty()){
				session.save(master);	
			}*/
			if(medicineMasters!=null&&!medicineMasters.isEmpty()){
				System.out.println("Inside not null &not empty");
				medicineMaster=medicineMasters.get(0);
				Integer existmedicineid = (Integer)medicineMaster.getMedicineId();
				String batchId = medicineMaster.getMedicineBatchId();
				Date expiryy = medicineMaster.getExpiryDate();
				calendar1.setTime(expiryy);
				if((existmedicineid.equals(medicineidd))&&(batchId.equalsIgnoreCase(medicineBatchId))&&(calendar.compareTo(calendar1)==0)){
					System.out.println("Merge");
					System.out.println("Add master entry same merge");
					Long count = medicineMaster.getMedicineCount();
					count = count+medcount;
					medicineMaster.setMedicineCount(count);
					System.out.println("Merge Part is Executing");
					session.merge(medicineMaster);
				}
				else if((existmedicineid.equals(medicineidd))&&(!batchId.equalsIgnoreCase(medicineBatchId))){
					System.out.println("Id same diff batch");
					System.out.println("Add master entry different to save ");
					session.save(master);
				}else{
					if((!existmedicineid.equals(medicineidd))&&(!batchId.equalsIgnoreCase(medicineBatchId))){
						System.out.println("New row");
						System.out.println("Add master entery new row");
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
			status.put("status","Medicine Order isue is saved");	
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
		} catch (Exception e) {
			status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject getMedicineId() {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = null;
		List<MedicineMaster> master = null;
		try {
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();	
			master =session.createQuery("FROM MedicineItemMaster").list();			
			if(master!=null&&!master.isEmpty()){
				status.put("MasterMedicine", master);
			}
			session.getTransaction().commit();
			status.put("status", true);
		} catch (Exception e) {
			status.put("status",false);			
			status.put("originalErrorMsg", e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	@SuppressWarnings("unchecked")
	public JSONObject pharmacyMedicineRequest(JSONObject medicineRequest) {
		JSONObject status = new JSONObject();
		status.put("status", true);
		Integer deptid =  Integer.parseInt(medicineRequest.get("departmentId").toString());
		Session session = null;
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date  date =null;
		try {
			date= sdf.parse(medicineRequest.get("requestDate").toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		try {
			PharmacyRequestMedicine requestmedicine = om.convertValue(medicineRequest, PharmacyRequestMedicine.class);	
			Department department = new Department();
			department.setDepartmentId(deptid);
			requestmedicine.setDepartment(department);			
			requestmedicine.setRequestDate(date);
			session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(requestmedicine);
			session.getTransaction().commit();
			status.put("status",true);	
			status.put("status","MedicineRequest isue is saved");	
		} catch (Exception e) {		
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
			// session.close();
		}
		if (medicine!=null) {
			return medicine;
		} else {
			return null;
		}
		
	}
	/**
	 * @return
	 */
	public JSONObject listPharamacyRequestDetails() {
		System.out.println("Inside Dao1Patient");
		JSONObject status = new JSONObject();
		status.put("status", true);
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<Patient> pharmacyRequestMedicine = null;
		try {
			Query query = session.createQuery("FROM PharmacyRequestMedicine");
			pharmacyRequestMedicine = query.list();
			status.put("PharmacyRequest", pharmacyRequestMedicine);
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
}
