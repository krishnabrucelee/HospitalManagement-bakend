package com.hospital.service;

import org.json.simple.JSONObject;

public interface IPharmaService {
	public JSONObject orderMasterMedicine(JSONObject orderMasterMedicine);
	public JSONObject issueOrder(JSONObject productdetails);
	public JSONObject addMasterEntry(JSONObject masterentry);
	public JSONObject listMasterEntry();
	public JSONObject getOrderById(JSONObject productdid);
	public JSONObject updateOrder(JSONObject productd);
	public JSONObject deleteOrder(JSONObject productidd);
	public JSONObject listOrder();
	public JSONObject getMedicineId();
	public JSONObject pharmacyMedicineRequest(JSONObject medicineRequest);
	public JSONObject medicineRequest(JSONObject medicineRequest);
	public JSONObject getMedicineRequestMasterById(JSONObject requestid);
	public JSONObject getMedicineRequestPharmacyById(JSONObject requestid);
	public JSONObject updateMasterPharmacyReturn(JSONObject returnupdate);
	public JSONObject updatePharmacyRecived(JSONObject receiveupdate);
	public JSONObject medicineIssuetoPharmacy(JSONObject medicinetoPharmacy);
	public JSONObject medicinetoPatient(JSONObject medicinetoPatient);
	public JSONObject patientReturnMedicine(JSONObject patientReturnMedicine);
	
}
