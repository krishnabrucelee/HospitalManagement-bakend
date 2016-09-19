<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PatientRegister</title>
<link  href="resources/bootcss/bootstrap.css" rel="stylesheet" type="text/css"/>
<link  href="resources/bootcss/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link  href="resources/bootcss/bootstrap-theme.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="resources/jqueryjs/jquery-2.1.4.js"></script>
<script type="text/javascript" src="resources/bootjs/bootstrap.js"></script>
<script type="text/javascript" src="resources/angular/angular.js"></script>
<script type="text/javascript" src="resources/angular/angular-route.js"></script>

</head>
 
<body ng-app="">
<h2 align="center">Register Form</h2>
<form class="form-horizontal" action="savePatient" method="post" >
   <div class="form-group">
 		<label class="col-sm-2 control-label">Patient name</label>
 			<div class="col-sm-3">
 			   <input type="text" class="form-control" name="patient_name"  placeholder=" Enter name"/>
 			</div>
 	</div>
 <div class="form-group">
 		<label class="col-sm-2 control-label">Patient age</label>
 			<div class="col-sm-3">
 			   <input type="text" class="form-control" name="patient_age"  placeholder=" Enter age"/>
 			</div>
 	</div>
 	<div class="form-group">
 		<label class="col-sm-2 control-label">Patient email</label>
 			<div class="col-sm-3">
 			   <input type="text" class="form-control" name="email"  placeholder=" Enter email"/>
 			</div>
 	</div>
 	 <div class="form-group">
 		       <label class="col-sm-2 control-label">User Type</label>
 		       <label class="radio-inline">
  <input type="radio" name="patient_gender" id="inlineRadio1" value="Male"> Male
  </label>
  <label class="radio-inline">
  <input type="radio" name="patient_gender" id="inlineRadio2" value="Female"> Female
  </label>  		     
 			</div>
 	<div class="form-group">
 		<label class="col-sm-2 control-label">User address</label>
 			<div class="col-sm-3">
 			<textarea name="patient_address"  style="width:800;height:60;border-radius:6px;" class="form-control" ></textarea>
 			   <!-- <input type="textarea" rows="2" cols="40"class="form-control" name="address"  placeholder=" "/> -->
 			</div>
 	</div>
 	<div class="form-group">
 		<label class="col-sm-2 control-label">Patient dob</label>
 			<div class="col-sm-3">
 			   <input type="date" class="form-control" name="patient_dob"/>
 			</div>
 	</div>
 	
 	
 	
 	
 	 <div class="form-group">
 		<label class="col-sm-2 control-label">PatientBloodgroup</label>
 			<div class="col-sm-3">
 			  <select class="form-control" name="bloodgroup" placeholder="Select bloodgroup">
               <option value = "A+">A+</option>
               <option value = "A-">A-</option>  
               <option value = "B+">B+</option>
               <option value = "B-">B-</option>  
               <option value = "O+">O+</option>
               <option value = "O-">O-</option>  
               <option value = "AB+">AB+</option>
               <option value = "AB-">AB-</option>               
             </select>
 			</div>
 	</div> 
 	<div class="form-group">
 		<label class="col-sm-2 control-label">Patient type</label>
 			<div class="col-sm-3">
 			 <select class="form-control" name="patient_type" placeholder=" Select gender">
               <option value = "1">Patient</option>
               <option value = "2">Charity</option>            
             </select>
 			
 			</div>
 	</div>
 	<div class="form-group">
 		<label class="col-sm-2 control-label">Patient phonenumber</label>
 			<div class="col-sm-3">
 			   <input type="text" class="form-control" name="phonenumber"  placeholder=" Enter phonenumber"/>
 			</div>
 	</div>
 	<div class="form-group">
 		<label class="col-sm-2 control-label">Patient/Guardian</label>
 			<div class="col-sm-3">
 			   <input type="text" class="form-control" name="patient_guardian"  placeholder=" "/>
 			</div>
 	</div>
 	 <div class="form-group">
						<div class="col-sm-offset-3 col-sm-4">
							<button class="btn btn-primary" type="submit">Register</button>
							<button class="btn btn-danger" type="reset" id="resetButton">Clear</button>
						</div>
				</div>
</form>
<!-- <input type="text" ng-model="ss">{{ss}}
<h4>ha ha ha Spring mvc configured</h4> -->
</body>
</html>