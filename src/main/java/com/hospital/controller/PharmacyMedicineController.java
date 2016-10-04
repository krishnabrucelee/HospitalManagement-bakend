/**
 * 
 */
package com.hospital.controller;

import java.io.IOException;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hospital.service.PharmacyMedicineService;

/**
 * @author Krishna
 *
 */
@Controller
public class PharmacyMedicineController {

	/**
	 * PharmacyMedicine Service.
	 */
	@Autowired
	private PharmacyMedicineService pharmacyMedicineService;

	/**
	 * Create PharmacyMedicine.
	 * 
	 * @param pharmacyMedicine
	 * @return pharmacyMedicine
	 */
	@RequestMapping(value = "/addPharmacyMedicine", method = RequestMethod.POST)
	public @ResponseBody JSONObject addPharmacyMedicine(@RequestBody JSONObject pharmacyMedicine) throws JsonParseException, JsonMappingException, IOException, JSONException {
		return pharmacyMedicineService.addPharmacyMedicine(pharmacyMedicine);
	}

	/**
	 * List pharmacyMedicine.
	 * 
	 * @param pharmacyMedicine
	 * @return pharmacyMedicine
	 */
	@RequestMapping(value = "/listPharmacyMedicine")
	public @ResponseBody JSONObject listPharmacyMedicine() {
		return pharmacyMedicineService.listPharmacyMedicine();
	}
	
	/**
	 * Update PharmacyMedicine.
	 * 
	 * @param pharmacyMedicine
	 * @return pharmacyMedicine
	 */
	@RequestMapping(value = "/updatePharmacyMedicine")
	public @ResponseBody JSONObject updatePharmacyMedicine(@RequestBody JSONObject pharmacyMedicine) {
		return pharmacyMedicineService.updatePharmacyMedicine(pharmacyMedicine);
	}

	/**
	 * Delete pharmacyMedicine.
	 * 
	 * @param pharmacyMedicine
	 * @return pharmacyMedicine
	 */
	@RequestMapping(value = "/deletePharmacyMedicine", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deletePharmacyMedicine(@RequestBody JSONObject pharmacyMedicineId) {
		return pharmacyMedicineService.deletePharmacyMedicine(pharmacyMedicineId);
	}
	
	/**
	 * list EMR by Patient Id.
	 * 
	 * @param eMedicalReport
	 * @return eMedicalReport
	 */
	@RequestMapping(value = "/listPharMedByPatientId")
	public @ResponseBody JSONObject listByPatientId(@RequestBody JSONObject patientId) {
		return pharmacyMedicineService.listByPatientId(patientId);
	}
}
