/**
 * 
 */
package com.hospital.serviceImpl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dao.MaterialRecieveNoteDao;
import com.hospital.service.MaterialRecieveNoteService;

/**
 * @author Krishna
 *
 */
@Service
public class MaterialRecieveNoteServiceImpl implements MaterialRecieveNoteService {

	/**
	 * MaterialRecieveNote dao
	 */
	@Autowired
	MaterialRecieveNoteDao materialRecieveNotedao;
	
	@Override
	public JSONObject addMaterialRecieveNote(JSONObject materialRecieveNote) {
		return materialRecieveNotedao.addMaterialRecieveNote(materialRecieveNote);
	}

	@Override
	public JSONObject listMaterialRecieveNote() {
		return materialRecieveNotedao.listMaterialRecieveNote();
	}

	@Override
	public JSONObject updateMaterialRecieveNote(JSONObject materialRecieveNote) {
		return materialRecieveNotedao.updateMaterialRecieveNote(materialRecieveNote);
	}

	@Override
	public JSONObject deleteMaterialRecieveNote(JSONObject materialRecieveNoteId) {
		return materialRecieveNotedao.deleteMaterialRecieveNote(materialRecieveNoteId);
	}

	@Override
	public JSONObject getMrnByPurchaseId(JSONObject patient) {
		return materialRecieveNotedao.getMrnByPurchaseId(patient);
	}

}
