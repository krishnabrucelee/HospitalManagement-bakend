package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.IEquipmentService;

@Controller
public class EquipmentController {
	@Autowired
	IEquipmentService iequipment;
	@RequestMapping(value = "/saveEuipment",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject saveEuipment(@RequestBody JSONObject equipmentdata){			
		 return iequipment.saveEuipment(equipmentdata);				
	}	
	@RequestMapping(value = "/viewEuipment")
	public 	@ResponseBody JSONObject viewEuipment(){		
		System.out.println("Enter into controller equipmentdata");
		 return iequipment.viewEuipment();			
	}
	@RequestMapping(value = "/getEuipmentById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getEuipmentById(@RequestBody JSONObject equipmentId){			
		 return iequipment.getEuipmentById(equipmentId);				
	}	
	////
	@RequestMapping(value = "/requestEuipmentChecklist",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject requestEuipmentChecklist(@RequestBody JSONObject euipmentChecklist){			
		 return iequipment.requestEuipmentChecklist(euipmentChecklist);	
	}
	@RequestMapping(value = "/viewEuipmentChecklist")
	public 	@ResponseBody JSONObject viewEuipmentChecklist(){			
		 return iequipment.viewEuipmentChecklist();
	}
	@RequestMapping(value = "/getEuipmentChecklistById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getEuipmentChecklistById(@RequestBody JSONObject checklistrequest){			
		 return iequipment.getEuipmentChecklistById(checklistrequest);	
	}
	@RequestMapping(value = "/updateEquipmentCheckRequest",method=RequestMethod.POST)///***
	public 	@ResponseBody JSONObject updateEquipmentCheckRequest(@RequestBody JSONObject updateStatus){			
		 return iequipment.updateEquipmentCheckRequest(updateStatus);
	}
	////
	//****//
	@RequestMapping(value = "/euipmentChecklistRequest",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject euipmentChecklistRequest(@RequestBody JSONObject euipmentChecklist){			
		 return iequipment.requestEuipmentChecklist(euipmentChecklist);	
	}
	
	@RequestMapping(value = "/saveEquipmentCheckMaster",method=RequestMethod.POST)///***
	public 	@ResponseBody JSONObject saveEquipmentCheckMaster(@RequestBody JSONObject equipmentCheckMaster){			
		 return iequipment.saveEquipmentCheckMaster(equipmentCheckMaster);
	}
	
	@RequestMapping(value = "/saveMaintananceSchedule",method=RequestMethod.POST)///***
	public 	@ResponseBody JSONObject saveMaintananceSchedule(@RequestBody JSONObject maintananceSchedule){			
		 return iequipment.saveMaintananceSchedule(maintananceSchedule);
	}
	//////========////
	@RequestMapping(value = "/orderEquipmentParts",method=RequestMethod.POST)///***
	public 	@ResponseBody JSONObject orderEquipmentParts(@RequestBody JSONObject orderEquipmentParts){			
		 return iequipment.orderEquipmentParts(orderEquipmentParts);
	}
	@RequestMapping(value = "/viewOrderEquipmentParts")///***
	public 	@ResponseBody JSONObject viewOrderEquipmentParts(){			
		 return iequipment.viewOrderEquipmentParts();
	}
	////========Maintanance Schedule With Sub Catogery//////
	@RequestMapping(value = "/saveScheduleMaintanance",method=RequestMethod.POST)///***
	public 	@ResponseBody JSONObject scheduleMaintanance(@RequestBody JSONObject scheduleMaintanance){			
		 return iequipment.scheduleMaintanance(scheduleMaintanance);
	}
	@RequestMapping(value = "/getScheduleMaintananceById",method=RequestMethod.POST)///***
	public 	@ResponseBody JSONObject getScheduleMaintananceById(@RequestBody JSONObject maintananceschedule){			
		 return iequipment.getScheduleMaintananceById(maintananceschedule);
	}
	@RequestMapping(value = "/saveRoutinescheduleCheckData",method=RequestMethod.POST)///***
	public 	@ResponseBody JSONObject saveRoutinescheduleCheckData(@RequestBody JSONObject routinescheduleCheckData){			
		 return iequipment.saveRoutinescheduleCheckData(routinescheduleCheckData);
	}
}
