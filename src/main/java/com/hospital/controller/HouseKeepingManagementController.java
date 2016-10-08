package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.IHouseKeepingManagementService;

@Controller
public class HouseKeepingManagementController {
	@Autowired
	private IHouseKeepingManagementService iHouseKeeping;
	
	@RequestMapping(value = "/saveHouseKeepItemMaster",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject saveHouseKeepItemMaster(@RequestBody JSONObject itemMaster){			
		 return iHouseKeeping.saveHouseKeepItemMaster(itemMaster);			
	}	
	/*@RequestMapping(value = "/saveHouseKeepRequest",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject saveHouseKeepRequest(@RequestBody JSONObject houseKeep){			
		 return iHouseKeeping.saveHouseKeepRequest(houseKeep);			
	}
	@RequestMapping(value = "/getHouseKeepRequestById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getHouseKeepRequestById(@RequestBody JSONObject houseKeepid){			
		 return iHouseKeeping.getHouseKeepRequestById(houseKeepid);			
	}
	@RequestMapping(value = "/updateHouseKeepRequest",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject updateHouseKeepRequest(@RequestBody JSONObject houseKeepupdate){			
		 return iHouseKeeping.updateHouseKeepRequest(houseKeepupdate);			
	}*/
	@RequestMapping(value = "/laundryprocessState",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject laundryprocessState(@RequestBody JSONObject processState){			
		 return iHouseKeeping.laundryprocessState(processState);			
	}
	@RequestMapping(value = "/requestHouseKeepRequest",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject requestHouseKeep(@RequestBody JSONObject houseKeepData){			
		 return iHouseKeeping.requestHouseKeep(houseKeepData);			
	}
	@RequestMapping(value = "/getHouseKeepRequestId",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getHouseKeepRequestId(@RequestBody JSONObject houseKeepId){			
		 return iHouseKeeping.getHouseKeepRequestId(houseKeepId);			
	}
	@RequestMapping(value = "/houseKeepRequestUpdate",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject houseKeepRequestUpdate(@RequestBody JSONObject houseKeepUpdate){			
		 return iHouseKeeping.houseKeepRequestUpdate(houseKeepUpdate);			
	}
}
