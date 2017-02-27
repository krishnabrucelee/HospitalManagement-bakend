package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.EquipmentDataService;

@Controller
public class EquipmentDataController {
	
	@Autowired
	EquipmentDataService iequipmentservice;
	
	
	@RequestMapping(value = "/saveDevice",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject saveDevicee(@RequestBody JSONObject devicedata){			
		 return iequipmentservice.saveDevice(devicedata);			
	}	
	
	@RequestMapping(value = "/saveBuilding",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject saveBuildingg(@RequestBody JSONObject buildingFloorRoom){			
		 return iequipmentservice.saveBuildingFloorRoom(buildingFloorRoom);		
	}
	
	@RequestMapping(value = "/saveEquipmentData",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject saveEuipment(@RequestBody JSONObject equipmentDetails){			
		 return iequipmentservice.saveEquipment(equipmentDetails);			
	}	
	
	@RequestMapping(value = "/saveMaintanceConfig",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject saveMaintance(@RequestBody JSONObject maintanaceConfiguration){			
		 return iequipmentservice.saveMaintanaceConfiguration(maintanaceConfiguration);		
	}
	
	@RequestMapping(value = "/listBuilding")
	public 	@ResponseBody JSONObject listBuilding(){			
		 return iequipmentservice.listBuildig();		
	}	
	
	@RequestMapping(value = "/listDevice")
	public 	@ResponseBody JSONObject listDevice(){			
		 return iequipmentservice.listDevice();			
	}	
	
	@RequestMapping(value = "/getGroupByEquipment")
	public 	@ResponseBody JSONObject getGroupByEquipment(){			
		 return iequipmentservice.getGroupByEquipment();			
	}
	
	@RequestMapping(value = "/listMaintanaceConfiguration")
	public 	@ResponseBody JSONObject listMaintanaceConfiguration(){			
		 return iequipmentservice.listMaintanaceConfiguration();	
	}	
	
	//getLastEquipmentCheck(JSONObject lastCheckt)
	@RequestMapping(value = "/getLastCheckRequest",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getLastCheckRequest(@RequestBody JSONObject lastCheckt){			
		 return iequipmentservice.getLastEquipmentCheck(lastCheckt);
	}
	@RequestMapping(value = "/saveEquipmentCheckRequest",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject saveEquipmentCheck(@RequestBody JSONObject equipmentCheckRequest){			
		 return iequipmentservice.saveEquipmentCheckRequest(equipmentCheckRequest);
	}
	
	@RequestMapping(value = "/getEquipmentCheckPending")
	public 	@ResponseBody JSONObject getEquipmentCheckPending(){			
		 return iequipmentservice.getEquipmentCheckPending();			
	}
	@RequestMapping(value = "/checkEquipmentMaintanance",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject checkEquipmentMaintanance(@RequestBody JSONObject checkRequest){			
		 return iequipmentservice.checkEquipmentMaintanance(checkRequest);
	}
	@RequestMapping(value = "/updateEquipmentCheckStatus",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject updateEquipmentCheckStatus(@RequestBody JSONObject equipmentCheck){			
		 return iequipmentservice.updateEquipmentCheckStatus(equipmentCheck);
	}

}
