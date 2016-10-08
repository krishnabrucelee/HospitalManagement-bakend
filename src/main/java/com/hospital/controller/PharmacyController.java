package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.IPharmaService;

@Controller
public class PharmacyController {
	
	@Autowired
	IPharmaService ipharma;
	@RequestMapping(value = "/addMasterEntry",method=RequestMethod.POST)//Working
	public 	@ResponseBody JSONObject addMasterEntry(@RequestBody JSONObject masterentry){		
		 return ipharma.addMasterEntry(masterentry);					
	}	
	@RequestMapping(value = "/issueOrder",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject issueOrder(@RequestBody JSONObject productdetails){		
		 return ipharma.issueOrder(productdetails);					
	}	
	@RequestMapping(value = "/listOrders")
	public 	@ResponseBody JSONObject listOrders(){		
		 return ipharma.listOrder();					
	}
	@RequestMapping(value = "/deleteOrder",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject deleteOrder(@RequestBody JSONObject productidd){		
		 return ipharma.issueOrder(productidd);					
	}
	@RequestMapping(value = "/medicineRequest",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject medicineRequest(@RequestBody JSONObject medicineRequest){		
		 return ipharma.medicineRequest(medicineRequest);				
	}	
	@RequestMapping(value = "/getMedicineRequestMasterById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getMedicineRequestById(@RequestBody JSONObject requestid){		
		 return ipharma.getMedicineRequestMasterById(requestid);			
	}
	@RequestMapping(value = "/getMedicineRequestPharmacyById",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject getMedicineRequestPharmacyById(@RequestBody JSONObject requestid){		
		 return ipharma.getMedicineRequestPharmacyById(requestid);			
	}
	@RequestMapping(value = "/updateMasterPharmacyStatus",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject updateMasterPharmacyReturn(@RequestBody JSONObject returnupdate){		
		 return ipharma.updateMasterPharmacyReturn(returnupdate);			
	}
	@RequestMapping(value = "/updatePharmacyRecived",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject updatePharmacyRecived(@RequestBody JSONObject receiveupdate){		
		 return ipharma.updatePharmacyRecived(receiveupdate);			
	}
	//Each department pharmacy receive medicine and add Medicine details.
	@RequestMapping(value = "/medicineIssuetoPharmacy",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject medicineIssuetoPharmacy(@RequestBody JSONObject medicinetoPharmacy){		
		 return ipharma.medicineIssuetoPharmacy(medicinetoPharmacy);				
	}
	@RequestMapping(value = "/medicineToPatient",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject medicineToPatient(@RequestBody JSONObject medicinetoPatient){		
		 return ipharma.medicinetoPatient(medicinetoPatient);			
	}
	@RequestMapping(value = "/patientReturnMedicine",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject patientReturnMedicine(@RequestBody JSONObject patientReturnMedicine){		
		 return ipharma.patientReturnMedicine(patientReturnMedicine);			
	}
}
