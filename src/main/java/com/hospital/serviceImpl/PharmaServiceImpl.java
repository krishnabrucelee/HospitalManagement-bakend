package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.PharmaDao;
import com.hospital.service.IPharmaService;
@Service
public class PharmaServiceImpl implements IPharmaService {
	
	@Autowired
	PharmaDao pharmadao;
	
	@Override
	public JSONObject orderMasterMedicine(JSONObject orderMasterMedicine) {
		return pharmadao.orderMasterMedicine(orderMasterMedicine);
	}
	@Override
	public JSONObject issueOrder(JSONObject productdetails) {
		
		return pharmadao.issueOrder(productdetails);
	}
	@Override
	public JSONObject getOrderById(JSONObject productdid) {
		return pharmadao.getOrderById(productdid);
	}
	@Override
	public JSONObject updateOrder(JSONObject productd) {
		return pharmadao.updateOrder(productd);
	}
	@Override
	public JSONObject deleteOrder(JSONObject productidd) {
		return pharmadao.deleteOrder(productidd);
	}
	@Override
	public JSONObject listOrder() {
		return pharmadao.listOrder();
	}
	
	@Override
	public JSONObject getMedicineId() {
		return pharmadao.getMedicineId();
	}
	@Override
	public JSONObject pharmacyMedicineRequest(JSONObject medicineRequest) {
		return pharmadao.pharmacyMedicineRequest(medicineRequest);
	}
	@Override
	public JSONObject medicineRequest(JSONObject medicineRequest) {
		return pharmadao.medicineRequest(medicineRequest);
	}
	@Override
	public JSONObject getMedicineRequestPharmacyById(JSONObject requestid) {
		return pharmadao.getMedicineRequestPharmacyById(requestid);
	}
	@Override
	public JSONObject getMedicineRequestMasterById(JSONObject requestid) {
		return pharmadao.getMedicineRequestMasterById(requestid);
	}
	@Override
	public JSONObject medicineIssuetoPharmacy(JSONObject medicinetoPharmacy) {
		return pharmadao.medicineIssuetoPharmacy(medicinetoPharmacy);
	}
	@Override
	public JSONObject medicinetoPatient(JSONObject medicinetoPatient) {
		return pharmadao.medicinetoPatient(medicinetoPatient);
	}
	@Override
	public JSONObject patientReturnMedicine(JSONObject patientReturnMedicine) {
		return pharmadao.patientReturnMedicine(patientReturnMedicine);
	}
	@Override
	public JSONObject addMasterEntry(JSONObject masterentry) {
		return pharmadao.addMasterEntryData(masterentry);
	}
	@Override
	public JSONObject listMasterEntry() {
		return pharmadao.listMasterEntry();
	}
	@Override
	public JSONObject updateMasterPharmacyReturn(JSONObject returnupdate) {
		return pharmadao.updateMasterPharmacyReturn(returnupdate);
	}
	@Override
	public JSONObject updatePharmacyRecived(JSONObject receiveupdate) {
		return pharmadao.updatePharmacyRecived(receiveupdate);
	}
	
	
	
	
	
	
	
}
