<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient List</title>
</head>
<body>
  <h3>Patient List</h3>
    <table  align="center" class="table table-bordered" style="width:600px" >
 	<thead>
 	<tr>
 	   <th>PatientId</th><th>Patientname</th><th>Email</th><th>Patientage</th><th>Patientdob</th><th>Patientgender</th><th>Patienttype</th><th>Phonenumber</th><th>Patient/guardian</th><th>Bloodgroup</th>
 	</tr>
 	</thead>
 	<tbody>
 	 <tr>
 	 <c:if test="${!empty listPatient}">
 	  <c:forEach items="${listPatient}" var="patient">
 	  	<tr>
 	  	     <td>${patient.patient_id}</td>
 	  		 <td>${patient.patient_name}</td>
 	  		  <td>${patient.email}</td>
 	  		 <td>${patient.patient_age}</td> 	  				
 	  		 <td>${patient.patient_dob}</td>
 	  		 <td>${patient.patient_gender}</td>
 	  		 <td>${patient.patient_type}</td>
 	  		  <td>${patient.phonenumber}</td>
 	  		 <td>${patient.patient_guardian}</td>
 	  		 <td>${patient.bloodgroup}</td>		
 	  		
 	  		   		
 	  	</tr>
 	  </c:forEach>
 	  </c:if>
 	 </tr>
 	 </tbody>
 </table>
</body>
</html>