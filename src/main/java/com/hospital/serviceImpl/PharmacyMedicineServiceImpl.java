/**
 * 
 */
package com.hospital.serviceImpl;

import java.io.IOException;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hospital.dao.PharmacyMedicineDao;
import com.hospital.service.PharmacyMedicineService;

/**
 * @author Krishna
 *
 */
@Service
public class PharmacyMedicineServiceImpl implements PharmacyMedicineService {

	/**
	 * PharmacyMedicine dao
	 */
	@Autowired
	PharmacyMedicineDao pharmacyMedicinedao;
	
	@Override
	public JSONObject addPharmacyMedicine(JSONObject pharmacyMedicine) throws JsonParseException, JsonMappingException, IOException, JSONException {
		return pharmacyMedicinedao.addPharmacyMedicine(pharmacyMedicine);
	}

	@Override
	public JSONObject listPharmacyMedicine() {
		return pharmacyMedicinedao.listPharmacyMedicine();
	}

	@Override
	public JSONObject updatePharmacyMedicine(JSONObject pharmacyMedicine) {
		return pharmacyMedicinedao.updatePharmacyMedicine(pharmacyMedicine);
	}

	@Override
	public JSONObject deletePharmacyMedicine(JSONObject pharmacyMedicineId) {
		return pharmacyMedicinedao.deletePharmacyMedicine(pharmacyMedicineId);
	}

	@Override
	public JSONObject listByPatientId(JSONObject patientId) {
		return pharmacyMedicinedao.listByPatientId(patientId);
	}
}
