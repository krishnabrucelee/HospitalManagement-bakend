package com.hospital.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.service.ILabService;

@Controller
public class LabController {
	@Autowired
	ILabService irestlab;
	
	
	
	
	@RequestMapping(value = "/labConfigure",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject labConfigure(@RequestBody JSONObject labconfigure){		
		 return irestlab.addLabConfigure(labconfigure);					
	}	
	
	@RequestMapping(value = "/addLabTypes",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject addLabTypes(@RequestBody JSONObject addLabTypes){		
		 return irestlab.addLabTypes(addLabTypes);			
	}
	
	@RequestMapping(value = "/labrequesttoPatient",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject labrequesttoPatient(@RequestBody JSONObject labrequestToPatient){			
		 return irestlab.labrequestToPatient(labrequestToPatient);		
	}
	//New Types///
	/*@RequestMapping(value = "/patientRequestLabtest",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject patientRequestLabtest(@RequestBody JSONObject patientRequestLabtest){		
		System.out.println("Enter into controller patientRequestLabtest");
		 return irestlab.patientRequestLabtest(patientRequestLabtest);		
	}*/
	@RequestMapping(value = "/approveTestByLab",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject approveTestByLab(@RequestBody JSONObject approveTestByLab){		
		System.out.println("Enter into controller labrequesttoPatient");
		 return irestlab.approveTestByLab(approveTestByLab);		
	}
	@RequestMapping(value = "/addExternalLab",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject addExternalLab(@RequestBody JSONObject externalLab){		
		System.out.println("Enter into controller addExternalLab");
		 return irestlab.addExternalLab(externalLab);		
	}
	@RequestMapping(value = "/genarateLabResultMaster",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject genarateLabTestResultMaster(@RequestBody JSONObject genarateLabResultMaster){		
		System.out.println("Enter into controller addExternalLab");
		 return irestlab.genarateLabTestResultMaster(genarateLabResultMaster);		
	}
	///////Display Patient Labrequest///////
	@RequestMapping(value = "/labtestReportForPatient",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject labtestReportForPatient(@RequestBody JSONObject labtestReportForPatient){		
		System.out.println("Enter into controller addExternalLab");
		 return irestlab.labtestReportForPatient(labtestReportForPatient);	
	}
	///////Save Patient Labtest report////////
	@RequestMapping(value = "/savepatientLabtestReport",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject savepatientLabtestReport(@RequestBody JSONObject savepatientLabtestReport){		
		System.out.println("Enter into controller addExternalLab");
		 return irestlab.savePatientLabtestReport(savepatientLabtestReport);
	}
	////////View Lab test final report//////////
	@RequestMapping(value = "/viewPatientLabtestReport",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject viewPatientLabtestReport(@RequestBody JSONObject labtestReportId){		
		System.out.println("Enter into controller addExternalLab");
		 return irestlab.viewPatientLabtestReport(labtestReportId);
	}
	

	@RequestMapping(value = "/addMasterLabConfigure",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject addMasterLabConfigure(@RequestBody JSONObject masterLabConfigure){				
		return irestlab.addMasterLabConfigure(masterLabConfigure);				
	}	
	
	@RequestMapping(value = "/listMasterLabTest")
	public 	@ResponseBody JSONObject listMasterLabtest(){		
		 return irestlab.listMasterLabTest();					
	}	
	
	@RequestMapping(value = "/addMasterLabTestReport",method=RequestMethod.POST)//MasterLab Report//
	public 	@ResponseBody JSONObject saveMasterLabTestReport(@RequestBody JSONObject masterLabTestReport){		
		 return irestlab.saveMasterLabTestReport(masterLabTestReport);		
	}
	
	@RequestMapping(value = "/savePatientLabRequest",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject savePatientRequestLabTest(@RequestBody JSONObject patientRequestLabTest){		
		System.out.println("savePatientLabRequest web services"); 
		return irestlab.savePatientRequestLabTest(patientRequestLabTest);		
	}
	
	@RequestMapping(value = "/listPatientLabRequest")
	public 	@ResponseBody JSONObject listPatientLabRequest(){		
		 return irestlab.listPatientRequestLabTest();				
	}	
	
	@RequestMapping(value = "/updatePatientRequestLabTest",method=RequestMethod.POST)
	public 	@ResponseBody JSONObject updatePatientRequestLabTest(@RequestBody JSONObject labTestReport){		
		return irestlab.updatePatientRequestLabTest(labTestReport);		
	}
}
