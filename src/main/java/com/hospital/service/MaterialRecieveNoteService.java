/**
 * 
 */
package com.hospital.service;

import org.json.simple.JSONObject;

/**
 * @author Krishna					
 *
 */
public interface MaterialRecieveNoteService {

	/**
	 * Create MaterialRecieveNote.
	 * 
	 * @param materialRecieveNote
	 * @return materialRecieveNote
	 */
	public JSONObject addMaterialRecieveNote(JSONObject materialRecieveNote);

	/**
	 * List MaterialRecieveNote.
	 * 
	 * @param materialRecieveNote
	 * @return materialRecieveNote
	 */
	public JSONObject listMaterialRecieveNote();

	/**
	 * Update MaterialRecieveNote.
	 * 
	 * @param materialRecieveNote
	 * @return materialRecieveNote
	 */
	public JSONObject updateMaterialRecieveNote(JSONObject materialRecieveNote);

	/**
	 * Delete MaterialRecieveNote.
	 * 
	 * @param materialRecieveNote
	 * @return materialRecieveNote
	 */
	public JSONObject deleteMaterialRecieveNote(JSONObject materialRecieveNoteId);

}
