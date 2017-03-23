/**
 * 
 */
package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.MaterialRecieveNoteService;

/**
 * @author Krishna
 *
 */
@Controller
public class MaterialRecieveNoteController {

	/**
	 * MaterialRecieveNote Service.
	 */
	@Autowired
	private MaterialRecieveNoteService materialRecieveNoteService;

	/**
	 * Create MaterialRecieveNote.
	 * 
	 * @param materialRecieveNote
	 * @return materialRecieveNote
	 */
	@RequestMapping(value = "/addMaterialRecieveNote", method = RequestMethod.POST)
	public @ResponseBody JSONObject addMaterialRecieveNote(@RequestBody JSONObject materialRecieveNote) {
		return materialRecieveNoteService.addMaterialRecieveNote(materialRecieveNote);
	}

	/**
	 * List materialRecieveNote.
	 * 
	 * @param materialRecieveNote
	 * @return materialRecieveNote
	 */
	@RequestMapping(value = "/listMaterialRecieveNote")
	public @ResponseBody JSONObject listMaterialRecieveNote() {
		return materialRecieveNoteService.listMaterialRecieveNote();
	}
	
	/**
	 * Update MaterialRecieveNote.
	 * 
	 * @param materialRecieveNote
	 * @return materialRecieveNote
	 */
	@RequestMapping(value = "/updateMaterialRecieveNote")
	public @ResponseBody JSONObject updateMaterialRecieveNote(@RequestBody JSONObject materialRecieveNote) {
		return materialRecieveNoteService.updateMaterialRecieveNote(materialRecieveNote);
	}

	/**
	 * Delete materialRecieveNote.
	 * 
	 * @param materialRecieveNote
	 * @return materialRecieveNote
	 */
	@RequestMapping(value = "/deleteMaterialRecieveNote", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteMaterialRecieveNote(@RequestBody JSONObject materialRecieveNoteId) {
		return materialRecieveNoteService.deleteMaterialRecieveNote(materialRecieveNoteId);
	}
	
	@RequestMapping(value = "/getMrnByPurchaseId")
	public @ResponseBody JSONObject getMrnByPurchaseId(@RequestBody JSONObject patient) {
	
		System.out.println(patient);
		return materialRecieveNoteService.getMrnByPurchaseId(patient);
	}
}
