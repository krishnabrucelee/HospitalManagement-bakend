-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hospitalmanagement
-- ------------------------------------------------------
-- Server version	5.6.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Appointment`
--

DROP TABLE IF EXISTS `Appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Appointment` (
  `appoint_id` int(11) NOT NULL,
  `appoint_time` datetime DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`appoint_id`),
  KEY `FKi709n02w3rvted5gxfjx4rivb` (`department_id`),
  KEY `FK8bjepnkkwf5vyleh8oj7nn7pb` (`doctor_id`),
  KEY `FK9xuhrl1rl98s5asle907lphqn` (`patient_id`),
  CONSTRAINT `FK8bjepnkkwf5vyleh8oj7nn7pb` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  CONSTRAINT `FK9xuhrl1rl98s5asle907lphqn` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `FKi709n02w3rvted5gxfjx4rivb` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Appointment`
--

LOCK TABLES `Appointment` WRITE;
/*!40000 ALTER TABLE `Appointment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EquipmentCheckData`
--

DROP TABLE IF EXISTS `EquipmentCheckData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EquipmentCheckData` (
  `checkId` int(11) NOT NULL,
  `equipmentId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`checkId`),
  UNIQUE KEY `UK_q31i0wj9mu5ppe5y1u6a75pix` (`equipmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EquipmentCheckData`
--

LOCK TABLES `EquipmentCheckData` WRITE;
/*!40000 ALTER TABLE `EquipmentCheckData` DISABLE KEYS */;
/*!40000 ALTER TABLE `EquipmentCheckData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EquipmentCheckSubCategeries`
--

DROP TABLE IF EXISTS `EquipmentCheckSubCategeries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EquipmentCheckSubCategeries` (
  `checkCatogeryId` int(11) NOT NULL,
  `checkCatogeryName` varchar(255) DEFAULT NULL,
  `equipCheck_RequestId` int(11) DEFAULT NULL,
  PRIMARY KEY (`checkCatogeryId`),
  KEY `equipCheckReq_NameCategry_FK` (`equipCheck_RequestId`),
  CONSTRAINT `equipCheckReq_NameCategry_FK` FOREIGN KEY (`equipCheck_RequestId`) REFERENCES `equipmentCheckRequest` (`equipCheck_RequestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EquipmentCheckSubCategeries`
--

LOCK TABLES `EquipmentCheckSubCategeries` WRITE;
/*!40000 ALTER TABLE `EquipmentCheckSubCategeries` DISABLE KEYS */;
/*!40000 ALTER TABLE `EquipmentCheckSubCategeries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EquipmentCheckSubcategry`
--

DROP TABLE IF EXISTS `EquipmentCheckSubcategry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EquipmentCheckSubcategry` (
  `checkCatogeryId` int(11) NOT NULL,
  `checkCatogeryName` varchar(255) DEFAULT NULL,
  `checkYes` varchar(255) DEFAULT NULL,
  `equipmentId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`checkCatogeryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EquipmentCheckSubcategry`
--

LOCK TABLES `EquipmentCheckSubcategry` WRITE;
/*!40000 ALTER TABLE `EquipmentCheckSubcategry` DISABLE KEYS */;
/*!40000 ALTER TABLE `EquipmentCheckSubcategry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EquipmentData`
--

DROP TABLE IF EXISTS `EquipmentData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EquipmentData` (
  `equipmentId` int(11) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `equipmentName` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `manufactureDate` datetime DEFAULT NULL,
  `manufactureName` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `routineCheck` varchar(255) DEFAULT NULL,
  `supplierName` varchar(255) DEFAULT NULL,
  `waranty` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`equipmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EquipmentData`
--

LOCK TABLES `EquipmentData` WRITE;
/*!40000 ALTER TABLE `EquipmentData` DISABLE KEYS */;
/*!40000 ALTER TABLE `EquipmentData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ExternalLab`
--

DROP TABLE IF EXISTS `ExternalLab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ExternalLab` (
  `externalLabId` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `externalLabName` varchar(255) DEFAULT NULL,
  `externalLabType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`externalLabId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExternalLab`
--

LOCK TABLES `ExternalLab` WRITE;
/*!40000 ALTER TABLE `ExternalLab` DISABLE KEYS */;
/*!40000 ALTER TABLE `ExternalLab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ExternalLabRequest`
--

DROP TABLE IF EXISTS `ExternalLabRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ExternalLabRequest` (
  `externalLabrequestId` int(11) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `referenceDoctor` varchar(255) DEFAULT NULL,
  `requestTime` datetime DEFAULT NULL,
  `testId` int(11) DEFAULT NULL,
  `testName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`externalLabrequestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ExternalLabRequest`
--

LOCK TABLES `ExternalLabRequest` WRITE;
/*!40000 ALTER TABLE `ExternalLabRequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `ExternalLabRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HouseKeepingItemMaster`
--

DROP TABLE IF EXISTS `HouseKeepingItemMaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HouseKeepingItemMaster` (
  `itemId` bigint(20) NOT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HouseKeepingItemMaster`
--

LOCK TABLES `HouseKeepingItemMaster` WRITE;
/*!40000 ALTER TABLE `HouseKeepingItemMaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `HouseKeepingItemMaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `InvetoryMedicine`
--

DROP TABLE IF EXISTS `InvetoryMedicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `InvetoryMedicine` (
  `invetoryId` bigint(20) NOT NULL,
  `expiredDate` datetime DEFAULT NULL,
  `medicineBatchId` varchar(255) DEFAULT NULL,
  `medicineCount` bigint(20) DEFAULT NULL,
  `medicineId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`invetoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `InvetoryMedicine`
--

LOCK TABLES `InvetoryMedicine` WRITE;
/*!40000 ALTER TABLE `InvetoryMedicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `InvetoryMedicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LabTest`
--

DROP TABLE IF EXISTS `LabTest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LabTest` (
  `labtestId` int(11) NOT NULL,
  `testInstruction` varchar(255) DEFAULT NULL,
  `testMethodology` varchar(255) DEFAULT NULL,
  `testName` varchar(255) DEFAULT NULL,
  `testReport` varchar(255) DEFAULT NULL,
  `testSample` varchar(255) DEFAULT NULL,
  `testStorage` varchar(255) DEFAULT NULL,
  `testVolume` varchar(255) DEFAULT NULL,
  `testcost` double DEFAULT NULL,
  PRIMARY KEY (`labtestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LabTest`
--

LOCK TABLES `LabTest` WRITE;
/*!40000 ALTER TABLE `LabTest` DISABLE KEYS */;
/*!40000 ALTER TABLE `LabTest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LabTestName`
--

DROP TABLE IF EXISTS `LabTestName`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LabTestName` (
  `testId` int(11) NOT NULL,
  `testName` varchar(255) DEFAULT NULL,
  `labrequestId` int(11) DEFAULT NULL,
  PRIMARY KEY (`testId`),
  KEY `Labrequest_NameCategry_FK` (`labrequestId`),
  CONSTRAINT `Labrequest_NameCategry_FK` FOREIGN KEY (`labrequestId`) REFERENCES `NewLabRequest` (`labrequestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LabTestName`
--

LOCK TABLES `LabTestName` WRITE;
/*!40000 ALTER TABLE `LabTestName` DISABLE KEYS */;
/*!40000 ALTER TABLE `LabTestName` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LabrequestByPatient`
--

DROP TABLE IF EXISTS `LabrequestByPatient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LabrequestByPatient` (
  `labrequestId` int(11) NOT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `doctorId` int(11) DEFAULT NULL,
  `patientId` int(11) DEFAULT NULL,
  `patientName` varchar(255) DEFAULT NULL,
  `requestStatus` varchar(255) DEFAULT NULL,
  `requestTime` datetime DEFAULT NULL,
  PRIMARY KEY (`labrequestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LabrequestByPatient`
--

LOCK TABLES `LabrequestByPatient` WRITE;
/*!40000 ALTER TABLE `LabrequestByPatient` DISABLE KEYS */;
/*!40000 ALTER TABLE `LabrequestByPatient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LabtestTypes`
--

DROP TABLE IF EXISTS `LabtestTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LabtestTypes` (
  `labtest_id` int(11) NOT NULL,
  `testDescription` varchar(255) DEFAULT NULL,
  `testName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`labtest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LabtestTypes`
--

LOCK TABLES `LabtestTypes` WRITE;
/*!40000 ALTER TABLE `LabtestTypes` DISABLE KEYS */;
/*!40000 ALTER TABLE `LabtestTypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LaundryProcessState`
--

DROP TABLE IF EXISTS `LaundryProcessState`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LaundryProcessState` (
  `laundryProcessStateId` int(11) NOT NULL,
  `dryerState` varchar(255) DEFAULT NULL,
  `hydroextractorState` varchar(255) DEFAULT NULL,
  `ironState` varchar(255) DEFAULT NULL,
  `processTime` datetime DEFAULT NULL,
  `washingState` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`laundryProcessStateId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LaundryProcessState`
--

LOCK TABLES `LaundryProcessState` WRITE;
/*!40000 ALTER TABLE `LaundryProcessState` DISABLE KEYS */;
/*!40000 ALTER TABLE `LaundryProcessState` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MaintananceSchedule`
--

DROP TABLE IF EXISTS `MaintananceSchedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MaintananceSchedule` (
  `scheduleId` int(11) NOT NULL,
  `datee` datetime DEFAULT NULL,
  `datee2` datetime DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `equipmentId` varchar(255) DEFAULT NULL,
  `equipmentName` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `maintanancePersonId` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`scheduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MaintananceSchedule`
--

LOCK TABLES `MaintananceSchedule` WRITE;
/*!40000 ALTER TABLE `MaintananceSchedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `MaintananceSchedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MaintananceScheduleSubCategeries`
--

DROP TABLE IF EXISTS `MaintananceScheduleSubCategeries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MaintananceScheduleSubCategeries` (
  `checkCatogeryId` int(11) NOT NULL,
  `checkCatogeryName` varchar(255) DEFAULT NULL,
  `checkYes` varchar(255) DEFAULT NULL,
  `checkedBy` varchar(255) DEFAULT NULL,
  `testDate` datetime DEFAULT NULL,
  `maintananceScheduleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`checkCatogeryId`),
  KEY `Equipmentcheck_Maintanance_FK` (`maintananceScheduleId`),
  CONSTRAINT `Equipmentcheck_Maintanance_FK` FOREIGN KEY (`maintananceScheduleId`) REFERENCES `MaintananceScheduling` (`maintananceScheduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MaintananceScheduleSubCategeries`
--

LOCK TABLES `MaintananceScheduleSubCategeries` WRITE;
/*!40000 ALTER TABLE `MaintananceScheduleSubCategeries` DISABLE KEYS */;
/*!40000 ALTER TABLE `MaintananceScheduleSubCategeries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MaintananceScheduling`
--

DROP TABLE IF EXISTS `MaintananceScheduling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MaintananceScheduling` (
  `maintananceScheduleId` int(11) NOT NULL,
  `datee2` datetime DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `equipmentId` varchar(255) DEFAULT NULL,
  `equipmentName` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `maintanancePersonId` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `requestDatee` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maintananceScheduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MaintananceScheduling`
--

LOCK TABLES `MaintananceScheduling` WRITE;
/*!40000 ALTER TABLE `MaintananceScheduling` DISABLE KEYS */;
/*!40000 ALTER TABLE `MaintananceScheduling` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Medicine`
--

DROP TABLE IF EXISTS `Medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Medicine` (
  `medicineAddId` int(11) NOT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `expiredDate` datetime DEFAULT NULL,
  `medicineBatchId` varchar(255) DEFAULT NULL,
  `medicineComposition` varchar(255) DEFAULT NULL,
  `medicineCount` int(11) DEFAULT NULL,
  `medicineId` int(11) DEFAULT NULL,
  `medicineName` varchar(255) DEFAULT NULL,
  `medicinePower` varchar(255) DEFAULT NULL,
  `medicinePrice` double DEFAULT NULL,
  `medicineType` varchar(255) DEFAULT NULL,
  `pharmacyId` int(11) DEFAULT NULL,
  PRIMARY KEY (`medicineAddId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Medicine`
--

LOCK TABLES `Medicine` WRITE;
/*!40000 ALTER TABLE `Medicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `Medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MedicineEntryMaster`
--

DROP TABLE IF EXISTS `MedicineEntryMaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MedicineEntryMaster` (
  `entryId` bigint(20) NOT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `expiryDate` datetime DEFAULT NULL,
  `manufactureDate` datetime DEFAULT NULL,
  `medicineBatchId` varchar(255) DEFAULT NULL,
  `medicineComposition` varchar(255) DEFAULT NULL,
  `medicineCount` bigint(20) DEFAULT NULL,
  `medicineId` int(11) DEFAULT NULL,
  `medicineName` varchar(255) DEFAULT NULL,
  `medicinePower` varchar(255) DEFAULT NULL,
  `medicinePrice` double DEFAULT NULL,
  `medicineType` varchar(255) DEFAULT NULL,
  `purchaseDate` datetime DEFAULT NULL,
  `supplierName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`entryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MedicineEntryMaster`
--

LOCK TABLES `MedicineEntryMaster` WRITE;
/*!40000 ALTER TABLE `MedicineEntryMaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `MedicineEntryMaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MedicineIssueToPharmacy`
--

DROP TABLE IF EXISTS `MedicineIssueToPharmacy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MedicineIssueToPharmacy` (
  `medicineIssueId` int(11) NOT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `issueDate` datetime DEFAULT NULL,
  `medicineCount` int(11) DEFAULT NULL,
  `medicineId` int(11) DEFAULT NULL,
  `medicineType` varchar(255) DEFAULT NULL,
  `requestId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`medicineIssueId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MedicineIssueToPharmacy`
--

LOCK TABLES `MedicineIssueToPharmacy` WRITE;
/*!40000 ALTER TABLE `MedicineIssueToPharmacy` DISABLE KEYS */;
/*!40000 ALTER TABLE `MedicineIssueToPharmacy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MedicineIssued`
--

DROP TABLE IF EXISTS `MedicineIssued`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MedicineIssued` (
  `medicineIssuedId` int(11) NOT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `medicineCount` int(11) DEFAULT NULL,
  `medicineId` int(11) DEFAULT NULL,
  `pharmacyId` int(11) DEFAULT NULL,
  `requestId` int(11) DEFAULT NULL,
  `returnDate` datetime DEFAULT NULL,
  PRIMARY KEY (`medicineIssuedId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MedicineIssued`
--

LOCK TABLES `MedicineIssued` WRITE;
/*!40000 ALTER TABLE `MedicineIssued` DISABLE KEYS */;
/*!40000 ALTER TABLE `MedicineIssued` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MedicineMaster`
--

DROP TABLE IF EXISTS `MedicineMaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MedicineMaster` (
  `entryId` bigint(20) NOT NULL,
  `expiryDate` date DEFAULT NULL,
  `manufactureDate` date DEFAULT NULL,
  `medicineBatchId` varchar(255) DEFAULT NULL,
  `medicineComposition` varchar(255) DEFAULT NULL,
  `medicineCount` bigint(20) DEFAULT NULL,
  `medicineId` int(11) DEFAULT NULL,
  `medicineName` varchar(255) DEFAULT NULL,
  `medicinePower` varchar(255) DEFAULT NULL,
  `medicinePrice` double DEFAULT NULL,
  `medicineType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`entryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MedicineMaster`
--

LOCK TABLES `MedicineMaster` WRITE;
/*!40000 ALTER TABLE `MedicineMaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `MedicineMaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MedicineRequest`
--

DROP TABLE IF EXISTS `MedicineRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MedicineRequest` (
  `requestId` int(11) NOT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `medicineCount` int(11) DEFAULT NULL,
  `medicineId` varchar(255) DEFAULT NULL,
  `medicineType` varchar(255) DEFAULT NULL,
  `receivedDate` datetime DEFAULT NULL,
  `receivedStatus` varchar(255) DEFAULT NULL,
  `requestDate` datetime DEFAULT NULL,
  PRIMARY KEY (`requestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MedicineRequest`
--

LOCK TABLES `MedicineRequest` WRITE;
/*!40000 ALTER TABLE `MedicineRequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `MedicineRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MedicineRequestByPharmacy`
--

DROP TABLE IF EXISTS `MedicineRequestByPharmacy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MedicineRequestByPharmacy` (
  `requestId` int(11) NOT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `medicineCount` int(11) DEFAULT NULL,
  `medicineId` int(11) DEFAULT NULL,
  `medicineType` varchar(255) DEFAULT NULL,
  `pharmacyId` int(11) DEFAULT NULL,
  `receivedDate` datetime DEFAULT NULL,
  `receivedStatus` varchar(255) DEFAULT NULL,
  `requestDate` datetime DEFAULT NULL,
  PRIMARY KEY (`requestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MedicineRequestByPharmacy`
--

LOCK TABLES `MedicineRequestByPharmacy` WRITE;
/*!40000 ALTER TABLE `MedicineRequestByPharmacy` DISABLE KEYS */;
/*!40000 ALTER TABLE `MedicineRequestByPharmacy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MedicineRequestToMaster`
--

DROP TABLE IF EXISTS `MedicineRequestToMaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MedicineRequestToMaster` (
  `requestId` int(11) NOT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `medicineCount` int(11) DEFAULT NULL,
  `medicineId` int(11) DEFAULT NULL,
  `medicineType` varchar(255) DEFAULT NULL,
  `pharmacyId` int(11) DEFAULT NULL,
  `receivedStatus` varchar(255) DEFAULT NULL,
  `requestDate` datetime DEFAULT NULL,
  `returnDate` datetime DEFAULT NULL,
  `returnStatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`requestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MedicineRequestToMaster`
--

LOCK TABLES `MedicineRequestToMaster` WRITE;
/*!40000 ALTER TABLE `MedicineRequestToMaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `MedicineRequestToMaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MedicineTypes`
--

DROP TABLE IF EXISTS `MedicineTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MedicineTypes` (
  `entryId` int(11) NOT NULL,
  `medicineComposition` varchar(255) DEFAULT NULL,
  `medicineCount` bigint(20) DEFAULT NULL,
  `medicineId` int(11) DEFAULT NULL,
  `medicineName` varchar(255) DEFAULT NULL,
  `medicinePower` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`entryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MedicineTypes`
--

LOCK TABLES `MedicineTypes` WRITE;
/*!40000 ALTER TABLE `MedicineTypes` DISABLE KEYS */;
/*!40000 ALTER TABLE `MedicineTypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NewLabRequest`
--

DROP TABLE IF EXISTS `NewLabRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NewLabRequest` (
  `labrequestId` int(11) NOT NULL,
  `patientName` varchar(255) DEFAULT NULL,
  `requestStatus` varchar(255) DEFAULT NULL,
  `requestTime` datetime DEFAULT NULL,
  `testNames` varchar(255) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `patientId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`labrequestId`),
  KEY `labrequest_Doctor_FK` (`doctor_id`),
  KEY `labrequest_Patient_FK` (`patientId`),
  CONSTRAINT `labrequest_Doctor_FK` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  CONSTRAINT `labrequest_Patient_FK` FOREIGN KEY (`patientId`) REFERENCES `PatientData` (`patientId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NewLabRequest`
--

LOCK TABLES `NewLabRequest` WRITE;
/*!40000 ALTER TABLE `NewLabRequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `NewLabRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderRepairProduct`
--

DROP TABLE IF EXISTS `OrderRepairProduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderRepairProduct` (
  `repairOrderid` int(11) NOT NULL,
  `maintananceDate` datetime DEFAULT NULL,
  `orderIssueDate` datetime DEFAULT NULL,
  `repairPerson` varchar(255) DEFAULT NULL,
  `repairRequest` varchar(255) DEFAULT NULL,
  `replacementPart` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `statusBB` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`repairOrderid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderRepairProduct`
--

LOCK TABLES `OrderRepairProduct` WRITE;
/*!40000 ALTER TABLE `OrderRepairProduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderRepairProduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PatientData`
--

DROP TABLE IF EXISTS `PatientData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PatientData` (
  `patientId` bigint(20) NOT NULL,
  `bloodGroup` varchar(255) DEFAULT NULL,
  `patientAddress` varchar(255) DEFAULT NULL,
  `patientAge` int(11) DEFAULT NULL,
  `patientDob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `patientGender` varchar(255) DEFAULT NULL,
  `patientGuardian` varchar(255) DEFAULT NULL,
  `patientName` varchar(255) DEFAULT NULL,
  `phoneNumber` bigint(20) DEFAULT NULL,
  `labTestt_testId` int(11) DEFAULT NULL,
  PRIMARY KEY (`patientId`),
  UNIQUE KEY `UK_c19ut9onbqptheayhwe8muplx` (`email`),
  KEY `FKbnuxjoq1cul497ly4d4i7xip6` (`labTestt_testId`),
  CONSTRAINT `FKbnuxjoq1cul497ly4d4i7xip6` FOREIGN KEY (`labTestt_testId`) REFERENCES `LabTestName` (`testId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PatientData`
--

LOCK TABLES `PatientData` WRITE;
/*!40000 ALTER TABLE `PatientData` DISABLE KEYS */;
/*!40000 ALTER TABLE `PatientData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PatientLabReportSubcategeriesNames`
--

DROP TABLE IF EXISTS `PatientLabReportSubcategeriesNames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PatientLabReportSubcategeriesNames` (
  `catogeryId` int(11) NOT NULL,
  `catogeryName` varchar(255) DEFAULT NULL,
  `checkedby` int(11) DEFAULT NULL,
  `methods` varchar(255) DEFAULT NULL,
  `rangeLowHigh` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `labtestReportId` int(11) DEFAULT NULL,
  PRIMARY KEY (`catogeryId`),
  KEY `LabreReport_Subcategery_FK` (`labtestReportId`),
  CONSTRAINT `LabreReport_Subcategery_FK` FOREIGN KEY (`labtestReportId`) REFERENCES `PatientLabtestReportNames` (`labtestReportId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PatientLabReportSubcategeriesNames`
--

LOCK TABLES `PatientLabReportSubcategeriesNames` WRITE;
/*!40000 ALTER TABLE `PatientLabReportSubcategeriesNames` DISABLE KEYS */;
/*!40000 ALTER TABLE `PatientLabReportSubcategeriesNames` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PatientLabtestReportNames`
--

DROP TABLE IF EXISTS `PatientLabtestReportNames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PatientLabtestReportNames` (
  `labtestReportId` int(11) NOT NULL,
  `age_sex` varchar(255) DEFAULT NULL,
  `doctorId` int(11) DEFAULT NULL,
  `patientId` int(11) DEFAULT NULL,
  `patientName` varchar(255) DEFAULT NULL,
  `referingDoctor` varchar(255) DEFAULT NULL,
  `testName` varchar(255) DEFAULT NULL,
  `testreportTime` datetime DEFAULT NULL,
  PRIMARY KEY (`labtestReportId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PatientLabtestReportNames`
--

LOCK TABLES `PatientLabtestReportNames` WRITE;
/*!40000 ALTER TABLE `PatientLabtestReportNames` DISABLE KEYS */;
/*!40000 ALTER TABLE `PatientLabtestReportNames` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PatientRequestLabTest`
--

DROP TABLE IF EXISTS `PatientRequestLabTest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PatientRequestLabTest` (
  `testId` int(11) NOT NULL,
  `patientId` int(11) DEFAULT NULL,
  `testIdd` int(11) DEFAULT NULL,
  `testName` varchar(255) DEFAULT NULL,
  `labrequestId` int(11) DEFAULT NULL,
  PRIMARY KEY (`testId`),
  KEY `Labrequest_Labrequest_FK` (`labrequestId`),
  CONSTRAINT `Labrequest_Labrequest_FK` FOREIGN KEY (`labrequestId`) REFERENCES `LabrequestByPatient` (`labrequestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PatientRequestLabTest`
--

LOCK TABLES `PatientRequestLabTest` WRITE;
/*!40000 ALTER TABLE `PatientRequestLabTest` DISABLE KEYS */;
/*!40000 ALTER TABLE `PatientRequestLabTest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PatientRequestSubCatTest`
--

DROP TABLE IF EXISTS `PatientRequestSubCatTest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PatientRequestSubCatTest` (
  `catogery_Id` int(11) NOT NULL,
  `catogeryName` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `testName` varchar(255) DEFAULT NULL,
  `testId` int(11) DEFAULT NULL,
  PRIMARY KEY (`catogery_Id`),
  KEY `Labrequest_PatientRequest_FK` (`testId`),
  CONSTRAINT `Labrequest_PatientRequest_FK` FOREIGN KEY (`testId`) REFERENCES `PatientRequestLabTest` (`testId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PatientRequestSubCatTest`
--

LOCK TABLES `PatientRequestSubCatTest` WRITE;
/*!40000 ALTER TABLE `PatientRequestSubCatTest` DISABLE KEYS */;
/*!40000 ALTER TABLE `PatientRequestSubCatTest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PeriodicMaintanaceSubCategeries`
--

DROP TABLE IF EXISTS `PeriodicMaintanaceSubCategeries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PeriodicMaintanaceSubCategeries` (
  `checkCatogeryId` int(11) NOT NULL,
  `checkCatogeryName` varchar(255) DEFAULT NULL,
  `checkYes` varchar(255) DEFAULT NULL,
  `checkedBy` varchar(255) DEFAULT NULL,
  `testDate` datetime DEFAULT NULL,
  `periodicMaintanaceId` int(11) DEFAULT NULL,
  PRIMARY KEY (`checkCatogeryId`),
  KEY `Equipmentcheck_PeriodicSchedule_FK` (`periodicMaintanaceId`),
  CONSTRAINT `Equipmentcheck_PeriodicSchedule_FK` FOREIGN KEY (`periodicMaintanaceId`) REFERENCES `PeriodicScheduleMaintanaceData` (`periodicMaintanaceId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PeriodicMaintanaceSubCategeries`
--

LOCK TABLES `PeriodicMaintanaceSubCategeries` WRITE;
/*!40000 ALTER TABLE `PeriodicMaintanaceSubCategeries` DISABLE KEYS */;
/*!40000 ALTER TABLE `PeriodicMaintanaceSubCategeries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PeriodicScheduleMaintanaceData`
--

DROP TABLE IF EXISTS `PeriodicScheduleMaintanaceData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PeriodicScheduleMaintanaceData` (
  `periodicMaintanaceId` int(11) NOT NULL,
  `datee2` datetime DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `equipmentId` varchar(255) DEFAULT NULL,
  `equipmentName` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `maintanancePersonId` int(11) DEFAULT NULL,
  `maintananceScheduleId` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `requestDatee` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`periodicMaintanaceId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PeriodicScheduleMaintanaceData`
--

LOCK TABLES `PeriodicScheduleMaintanaceData` WRITE;
/*!40000 ALTER TABLE `PeriodicScheduleMaintanaceData` DISABLE KEYS */;
/*!40000 ALTER TABLE `PeriodicScheduleMaintanaceData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pharmacy`
--

DROP TABLE IF EXISTS `Pharmacy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pharmacy` (
  `pharmacy_id` int(11) NOT NULL,
  `expiredDate` datetime DEFAULT NULL,
  `medicineBatchId` int(11) DEFAULT NULL,
  `medicineCount` int(11) DEFAULT NULL,
  `medicineId` bigint(20) DEFAULT NULL,
  `medicineName` varchar(255) DEFAULT NULL,
  `medicinePercentage` int(11) DEFAULT NULL,
  `medicinePrice` int(11) DEFAULT NULL,
  `medicineType` varchar(255) DEFAULT NULL,
  `departmentt_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pharmacy_id`),
  KEY `department_Pharmacy_FK` (`departmentt_id`),
  CONSTRAINT `department_Pharmacy_FK` FOREIGN KEY (`departmentt_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pharmacy`
--

LOCK TABLES `Pharmacy` WRITE;
/*!40000 ALTER TABLE `Pharmacy` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pharmacy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PharmacyOrder`
--

DROP TABLE IF EXISTS `PharmacyOrder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PharmacyOrder` (
  `orderId` bigint(20) NOT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `itemCount` int(11) DEFAULT NULL,
  `itemDescription` varchar(255) DEFAULT NULL,
  `itemDetails` varchar(255) DEFAULT NULL,
  `medicineName` varchar(255) DEFAULT NULL,
  `orderDate` datetime DEFAULT NULL,
  `supplierName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PharmacyOrder`
--

LOCK TABLES `PharmacyOrder` WRITE;
/*!40000 ALTER TABLE `PharmacyOrder` DISABLE KEYS */;
/*!40000 ALTER TABLE `PharmacyOrder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SupplierInformation`
--

DROP TABLE IF EXISTS `SupplierInformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SupplierInformation` (
  `supplierId` int(11) NOT NULL,
  `supplierAddress` varchar(255) DEFAULT NULL,
  `supplierMobileNumber` bigint(20) DEFAULT NULL,
  `supplierName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`supplierId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SupplierInformation`
--

LOCK TABLES `SupplierInformation` WRITE;
/*!40000 ALTER TABLE `SupplierInformation` DISABLE KEYS */;
/*!40000 ALTER TABLE `SupplierInformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TestSubCategeries`
--

DROP TABLE IF EXISTS `TestSubCategeries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TestSubCategeries` (
  `catogeryId` int(11) NOT NULL,
  `catogeryName` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `rangeLowHigh` varchar(255) DEFAULT NULL,
  `rangeLowHigh_Female` varchar(255) DEFAULT NULL,
  `rangeLowHigh_Male` varchar(255) DEFAULT NULL,
  `testId` int(11) DEFAULT NULL,
  PRIMARY KEY (`catogeryId`),
  KEY `Labtest_Categories_FK` (`testId`),
  CONSTRAINT `Labtest_Categories_FK` FOREIGN KEY (`testId`) REFERENCES `LabTestName` (`testId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TestSubCategeries`
--

LOCK TABLES `TestSubCategeries` WRITE;
/*!40000 ALTER TABLE `TestSubCategeries` DISABLE KEYS */;
/*!40000 ALTER TABLE `TestSubCategeries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ambulance`
--

DROP TABLE IF EXISTS `ambulance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ambulance` (
  `ambulance_id` int(11) NOT NULL,
  `ambulance_number` int(11) DEFAULT NULL,
  `communication_facilities` varchar(255) DEFAULT NULL,
  `facilities` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ambulance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ambulance`
--

LOCK TABLES `ambulance` WRITE;
/*!40000 ALTER TABLE `ambulance` DISABLE KEYS */;
/*!40000 ALTER TABLE `ambulance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ambulanceRequest`
--

DROP TABLE IF EXISTS `ambulanceRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ambulanceRequest` (
  `ambulance_request_id` int(11) NOT NULL,
  `ambulance_description` varchar(255) DEFAULT NULL,
  `patient_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `ambulance_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `driver_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ambulance_request_id`),
  KEY `ambulance_AREQ_FK` (`ambulance_id`),
  KEY `doctor_AREQ_FK` (`doctor_id`),
  KEY `driver_AREQ_FK` (`driver_id`),
  CONSTRAINT `ambulance_AREQ_FK` FOREIGN KEY (`ambulance_id`) REFERENCES `ambulance` (`ambulance_id`),
  CONSTRAINT `doctor_AREQ_FK` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  CONSTRAINT `driver_AREQ_FK` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ambulanceRequest`
--

LOCK TABLES `ambulanceRequest` WRITE;
/*!40000 ALTER TABLE `ambulanceRequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `ambulanceRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bloodBank`
--

DROP TABLE IF EXISTS `bloodBank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bloodBank` (
  `blood_bank_id` int(11) NOT NULL,
  `blood_bag_number` int(11) DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `blood_quantity` varchar(255) DEFAULT NULL,
  `blood_unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`blood_bank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bloodBank`
--

LOCK TABLES `bloodBank` WRITE;
/*!40000 ALTER TABLE `bloodBank` DISABLE KEYS */;
/*!40000 ALTER TABLE `bloodBank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bloodRequest`
--

DROP TABLE IF EXISTS `bloodRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bloodRequest` (
  `blood_request_id` int(11) NOT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `blood_request_description` varchar(255) DEFAULT NULL,
  `blood_requested_by` varchar(255) DEFAULT NULL,
  `blood_requested_date` datetime DEFAULT NULL,
  `blood_unit` int(11) DEFAULT NULL,
  `blood_ward` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`blood_request_id`),
  KEY `department_BREQ_FK` (`dept_id`),
  KEY `patient_BREQ_FK` (`patient_id`),
  CONSTRAINT `department_BREQ_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `patient_BREQ_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bloodRequest`
--

LOCK TABLES `bloodRequest` WRITE;
/*!40000 ALTER TABLE `bloodRequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `bloodRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bloodReturn`
--

DROP TABLE IF EXISTS `bloodReturn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bloodReturn` (
  `return_blood_id` int(11) NOT NULL,
  `blood_returned_date` datetime DEFAULT NULL,
  `returned_by` varchar(255) DEFAULT NULL,
  `returned_ward_number` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `inter_blood_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`return_blood_id`),
  KEY `inter_blood_id_BRETN_FK` (`inter_blood_id`),
  KEY `department_BRETN_FK` (`dept_id`),
  CONSTRAINT `department_BRETN_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `inter_blood_id_BRETN_FK` FOREIGN KEY (`inter_blood_id`) REFERENCES `interBloodTransfusion` (`inter_blood_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bloodReturn`
--

LOCK TABLES `bloodReturn` WRITE;
/*!40000 ALTER TABLE `bloodReturn` DISABLE KEYS */;
/*!40000 ALTER TABLE `bloodReturn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cashCounter`
--

DROP TABLE IF EXISTS `cashCounter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cashCounter` (
  `cash_counter_id` int(11) NOT NULL,
  `advance_amount` varchar(255) DEFAULT NULL,
  `due_amount` varchar(255) DEFAULT NULL,
  `payment_mode` varchar(255) DEFAULT NULL,
  `patient_billing_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cash_counter_id`),
  KEY `patient_billing_CCOUNT_FK` (`patient_billing_id`),
  CONSTRAINT `patient_billing_CCOUNT_FK` FOREIGN KEY (`patient_billing_id`) REFERENCES `patientBilling` (`patient_billing_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cashCounter`
--

LOCK TABLES `cashCounter` WRITE;
/*!40000 ALTER TABLE `cashCounter` DISABLE KEYS */;
/*!40000 ALTER TABLE `cashCounter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cost`
--

DROP TABLE IF EXISTS `cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cost` (
  `cost_id` int(11) NOT NULL,
  `cost_date` date DEFAULT NULL,
  `cost_description` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cost_id`),
  KEY `department_COST_FK` (`dept_id`),
  CONSTRAINT `department_COST_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cost`
--

LOCK TABLES `cost` WRITE;
/*!40000 ALTER TABLE `cost` DISABLE KEYS */;
/*!40000 ALTER TABLE `cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `costLog`
--

DROP TABLE IF EXISTS `costLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `costLog` (
  `cost_log_id` int(11) NOT NULL,
  `cost_log_description` varchar(255) DEFAULT NULL,
  `cost_modified_date` date DEFAULT NULL,
  `cost_old_date` date DEFAULT NULL,
  `cost_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cost_log_id`),
  KEY `cost_CLOG_FK` (`cost_id`),
  CONSTRAINT `cost_CLOG_FK` FOREIGN KEY (`cost_id`) REFERENCES `cost` (`cost_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `costLog`
--

LOCK TABLES `costLog` WRITE;
/*!40000 ALTER TABLE `costLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `costLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cssd`
--

DROP TABLE IF EXISTS `cssd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cssd` (
  `cssd_id` int(11) NOT NULL,
  `incharge_name` varchar(255) DEFAULT NULL,
  `requested_date` datetime DEFAULT NULL,
  `return_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cssd_id`),
  KEY `department_CSSD_FK` (`dept_id`),
  CONSTRAINT `department_CSSD_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cssd`
--

LOCK TABLES `cssd` WRITE;
/*!40000 ALTER TABLE `cssd` DISABLE KEYS */;
/*!40000 ALTER TABLE `cssd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cssdItemList`
--

DROP TABLE IF EXISTS `cssdItemList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cssdItemList` (
  `item_id` int(11) NOT NULL,
  `item_description` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cssdItemList`
--

LOCK TABLES `cssdItemList` WRITE;
/*!40000 ALTER TABLE `cssdItemList` DISABLE KEYS */;
/*!40000 ALTER TABLE `cssdItemList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cssd_cssdItemList`
--

DROP TABLE IF EXISTS `cssd_cssdItemList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cssd_cssdItemList` (
  `Cssd_cssd_id` int(11) NOT NULL,
  `cssdItemList_item_id` int(11) NOT NULL,
  UNIQUE KEY `UK_hiy3h46gfgi6h2dugl67f8xc2` (`cssdItemList_item_id`),
  KEY `FK51e38j48rna62d7l8iutob95` (`Cssd_cssd_id`),
  CONSTRAINT `FK51e38j48rna62d7l8iutob95` FOREIGN KEY (`Cssd_cssd_id`) REFERENCES `cssd` (`cssd_id`),
  CONSTRAINT `FKbc3da0ypq9e03pj3c83qe7mwn` FOREIGN KEY (`cssdItemList_item_id`) REFERENCES `cssdItemList` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cssd_cssdItemList`
--

LOCK TABLES `cssd_cssdItemList` WRITE;
/*!40000 ALTER TABLE `cssd_cssdItemList` DISABLE KEYS */;
/*!40000 ALTER TABLE `cssd_cssdItemList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL,
  `department_name` varchar(255) DEFAULT NULL,
  `department_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diet`
--

DROP TABLE IF EXISTS `diet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diet` (
  `diet_id` int(11) NOT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`diet_id`),
  KEY `doctor_DIET_FK` (`doctor_id`),
  KEY `patient_DIET_FK` (`patient_id`),
  CONSTRAINT `doctor_DIET_FK` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  CONSTRAINT `patient_DIET_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diet`
--

LOCK TABLES `diet` WRITE;
/*!40000 ALTER TABLE `diet` DISABLE KEYS */;
/*!40000 ALTER TABLE `diet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dietList`
--

DROP TABLE IF EXISTS `dietList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dietList` (
  `diet_list_id` int(11) NOT NULL,
  `calories` int(11) DEFAULT NULL,
  `food_description` varchar(255) DEFAULT NULL,
  `food_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`diet_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dietList`
--

LOCK TABLES `dietList` WRITE;
/*!40000 ALTER TABLE `dietList` DISABLE KEYS */;
/*!40000 ALTER TABLE `dietList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diet_dietList`
--

DROP TABLE IF EXISTS `diet_dietList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diet_dietList` (
  `Diet_diet_id` int(11) NOT NULL,
  `dietList_diet_list_id` int(11) NOT NULL,
  UNIQUE KEY `UK_lkybg7el756ilurc2grdmxr75` (`dietList_diet_list_id`),
  KEY `FKhttbdpjif7pn2grnblbf4x2gb` (`Diet_diet_id`),
  CONSTRAINT `FK3k927hnfhi6ss9gs3k1iojdc6` FOREIGN KEY (`dietList_diet_list_id`) REFERENCES `dietList` (`diet_list_id`),
  CONSTRAINT `FKhttbdpjif7pn2grnblbf4x2gb` FOREIGN KEY (`Diet_diet_id`) REFERENCES `diet` (`diet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diet_dietList`
--

LOCK TABLES `diet_dietList` WRITE;
/*!40000 ALTER TABLE `diet_dietList` DISABLE KEYS */;
/*!40000 ALTER TABLE `diet_dietList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `doctor_id` int(11) NOT NULL,
  `appointment_end_time` datetime DEFAULT NULL,
  `appointment_start_time` datetime DEFAULT NULL,
  `doctor_age` int(11) DEFAULT NULL,
  `doctor_description` varchar(255) DEFAULT NULL,
  `doctor_name` varchar(255) DEFAULT NULL,
  `doctor_reg_id` int(11) DEFAULT NULL,
  `doctor_type` int(11) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `personal_details` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`doctor_id`),
  KEY `department_doctor_FK` (`dept_id`),
  CONSTRAINT `department_doctor_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donor`
--

DROP TABLE IF EXISTS `donor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `donor` (
  `donor_id` int(11) NOT NULL,
  `donor_blading_disorder` bit(1) DEFAULT NULL,
  `donor_blood_test` bit(1) DEFAULT NULL,
  `donor_blood_transfusion` bit(1) DEFAULT NULL,
  `donor_chest_cardiac` bit(1) DEFAULT NULL,
  `date_of_register` datetime DEFAULT NULL,
  `donor_address` varchar(255) DEFAULT NULL,
  `donor_age` int(11) DEFAULT NULL,
  `donor_blood_group` varchar(255) DEFAULT NULL,
  `donor_gender` varchar(255) DEFAULT NULL,
  `donor_name` varchar(255) DEFAULT NULL,
  `donor_phone_number` int(11) DEFAULT NULL,
  `donor_reg_id` int(11) DEFAULT NULL,
  `donor_signature` varchar(255) DEFAULT NULL,
  `donor_email_id` varchar(255) DEFAULT NULL,
  `donor_fainting_spells` bit(1) DEFAULT NULL,
  `donor_hyper_tension_diabates` bit(1) DEFAULT NULL,
  `donor_jaundice` bit(1) DEFAULT NULL,
  `donor_taring_medicines` bit(1) DEFAULT NULL,
  PRIMARY KEY (`donor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donor`
--

LOCK TABLES `donor` WRITE;
/*!40000 ALTER TABLE `donor` DISABLE KEYS */;
/*!40000 ALTER TABLE `donor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver` (
  `driver_id` int(11) NOT NULL,
  `driver_reg_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`driver_id`),
  KEY `department_driver_FK` (`dept_id`),
  CONSTRAINT `department_driver_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eMedicalReport`
--

DROP TABLE IF EXISTS `eMedicalReport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eMedicalReport` (
  `emr_id` bigint(20) NOT NULL,
  `medical_report` varchar(255) DEFAULT NULL,
  `time_shedule` datetime DEFAULT NULL,
  `pharmacy_medicine_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `laboratory_id` bigint(20) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emr_id`),
  KEY `pharmacy_EMR_FK` (`pharmacy_medicine_id`),
  KEY `doctor_EMR_FK` (`doctor_id`),
  KEY `laboratory_EMR_FK` (`laboratory_id`),
  KEY `patient_EMR_FK` (`patient_id`),
  CONSTRAINT `doctor_EMR_FK` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  CONSTRAINT `laboratory_EMR_FK` FOREIGN KEY (`laboratory_id`) REFERENCES `laboratory` (`laboratory_id`),
  CONSTRAINT `patient_EMR_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `pharmacy_EMR_FK` FOREIGN KEY (`pharmacy_medicine_id`) REFERENCES `pharmacyMedicine` (`pharmacy_medicine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eMedicalReport`
--

LOCK TABLES `eMedicalReport` WRITE;
/*!40000 ALTER TABLE `eMedicalReport` DISABLE KEYS */;
/*!40000 ALTER TABLE `eMedicalReport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency`
--

DROP TABLE IF EXISTS `emergency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emergency` (
  `emergency_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `admitted_date` datetime DEFAULT NULL,
  `guardian_name` varchar(255) DEFAULT NULL,
  `guardian_signature` varchar(255) DEFAULT NULL,
  `problem_description` varchar(255) DEFAULT NULL,
  `in_patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emergency_id`),
  KEY `in_patient_EMRG_FK` (`in_patient_id`),
  CONSTRAINT `in_patient_EMRG_FK` FOREIGN KEY (`in_patient_id`) REFERENCES `inPatient` (`in_patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency`
--

LOCK TABLES `emergency` WRITE;
/*!40000 ALTER TABLE `emergency` DISABLE KEYS */;
/*!40000 ALTER TABLE `emergency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipmentCheckRequest`
--

DROP TABLE IF EXISTS `equipmentCheckRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipmentCheckRequest` (
  `equipCheck_RequestId` int(11) NOT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `equipmentId` int(11) DEFAULT NULL,
  `receiveStatus` varchar(255) DEFAULT NULL,
  `repairName` varchar(255) DEFAULT NULL,
  `requestDepartment` varchar(255) DEFAULT NULL,
  `requestTime` datetime DEFAULT NULL,
  `returnDate` datetime DEFAULT NULL,
  `returnStatus` varchar(255) DEFAULT NULL,
  `staffId` int(11) DEFAULT NULL,
  PRIMARY KEY (`equipCheck_RequestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipmentCheckRequest`
--

LOCK TABLES `equipmentCheckRequest` WRITE;
/*!40000 ALTER TABLE `equipmentCheckRequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipmentCheckRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expenseApproval`
--

DROP TABLE IF EXISTS `expenseApproval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expenseApproval` (
  `expense_approval_id` int(11) NOT NULL,
  `expense_amount` double DEFAULT NULL,
  `expense_date` datetime DEFAULT NULL,
  `expense_description` varchar(255) DEFAULT NULL,
  `product_type` varchar(255) DEFAULT NULL,
  `purchase_id` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'OPEN',
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`expense_approval_id`),
  KEY `department_EXPAP_FK` (`dept_id`),
  CONSTRAINT `department_EXPAP_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expenseApproval`
--

LOCK TABLES `expenseApproval` WRITE;
/*!40000 ALTER TABLE `expenseApproval` DISABLE KEYS */;
/*!40000 ALTER TABLE `expenseApproval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `houseKeeping`
--

DROP TABLE IF EXISTS `houseKeeping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `houseKeeping` (
  `houseKeeper_id` int(11) NOT NULL,
  `houseKeeper_address` varchar(255) DEFAULT NULL,
  `houseKeeper_dob` date DEFAULT NULL,
  `houseKeeper_email` varchar(255) DEFAULT NULL,
  `houseKeeper_phone` int(11) DEFAULT NULL,
  `houseKeeper_reg_id` int(11) DEFAULT NULL,
  `houseKeeper_shift` varchar(255) DEFAULT NULL,
  `houseKeeper_type` varchar(255) DEFAULT NULL,
  `houseKeeper_ward_number` int(11) DEFAULT NULL,
  `staff_id` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `ward_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`houseKeeper_id`),
  KEY `department_houseKeeping_FK` (`dept_id`),
  KEY `ward_houseKeeping_FK` (`ward_id`),
  CONSTRAINT `department_houseKeeping_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `ward_houseKeeping_FK` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`ward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `houseKeeping`
--

LOCK TABLES `houseKeeping` WRITE;
/*!40000 ALTER TABLE `houseKeeping` DISABLE KEYS */;
/*!40000 ALTER TABLE `houseKeeping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inPatient`
--

DROP TABLE IF EXISTS `inPatient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inPatient` (
  `in_patient_id` int(11) NOT NULL,
  `appointed_doctor` varchar(255) DEFAULT NULL,
  `doctor_reference` varchar(255) DEFAULT NULL,
  `in_patient_description` varchar(255) DEFAULT NULL,
  `ward_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`in_patient_id`),
  KEY `ward_INP_FK` (`ward_id`),
  CONSTRAINT `ward_INP_FK` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`ward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inPatient`
--

LOCK TABLES `inPatient` WRITE;
/*!40000 ALTER TABLE `inPatient` DISABLE KEYS */;
/*!40000 ALTER TABLE `inPatient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interBloodTransfusion`
--

DROP TABLE IF EXISTS `interBloodTransfusion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interBloodTransfusion` (
  `inter_blood_id` int(11) NOT NULL,
  `blood_bag_number` int(11) DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `blood_unit` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`inter_blood_id`),
  KEY `department_INTB_FK` (`dept_id`),
  KEY `patient_INTB_FK` (`patient_id`),
  CONSTRAINT `department_INTB_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `patient_INTB_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interBloodTransfusion`
--

LOCK TABLES `interBloodTransfusion` WRITE;
/*!40000 ALTER TABLE `interBloodTransfusion` DISABLE KEYS */;
/*!40000 ALTER TABLE `interBloodTransfusion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratory`
--

DROP TABLE IF EXISTS `laboratory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laboratory` (
  `laboratory_id` bigint(20) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`laboratory_id`),
  KEY `patient_LAB_FK` (`patient_id`),
  CONSTRAINT `patient_LAB_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratory`
--

LOCK TABLES `laboratory` WRITE;
/*!40000 ALTER TABLE `laboratory` DISABLE KEYS */;
/*!40000 ALTER TABLE `laboratory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratoryBilling`
--

DROP TABLE IF EXISTS `laboratoryBilling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laboratoryBilling` (
  `laboratory_billing_id` int(11) NOT NULL,
  `laboratory_billing_details` varchar(255) DEFAULT NULL,
  `cost_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`laboratory_billing_id`),
  KEY `cost_LBILL_FK` (`cost_id`),
  KEY `patient_LBILL_FK` (`patient_id`),
  CONSTRAINT `cost_LBILL_FK` FOREIGN KEY (`cost_id`) REFERENCES `cost` (`cost_id`),
  CONSTRAINT `patient_LBILL_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratoryBilling`
--

LOCK TABLES `laboratoryBilling` WRITE;
/*!40000 ALTER TABLE `laboratoryBilling` DISABLE KEYS */;
/*!40000 ALTER TABLE `laboratoryBilling` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratoryTest`
--

DROP TABLE IF EXISTS `laboratoryTest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laboratoryTest` (
  `id` int(11) NOT NULL,
  `test_description` varchar(255) DEFAULT NULL,
  `test_id` varchar(255) DEFAULT NULL,
  `test_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratoryTest`
--

LOCK TABLES `laboratoryTest` WRITE;
/*!40000 ALTER TABLE `laboratoryTest` DISABLE KEYS */;
/*!40000 ALTER TABLE `laboratoryTest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratory_laboratoryTest`
--

DROP TABLE IF EXISTS `laboratory_laboratoryTest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laboratory_laboratoryTest` (
  `Laboratory_laboratory_id` bigint(20) NOT NULL,
  `laboratoryTest_id` int(11) NOT NULL,
  UNIQUE KEY `UK_j7hfshmo2xwac5j7fqrjp3n7j` (`laboratoryTest_id`),
  KEY `FK35poumipk2e6str780gqveld` (`Laboratory_laboratory_id`),
  CONSTRAINT `FK35poumipk2e6str780gqveld` FOREIGN KEY (`Laboratory_laboratory_id`) REFERENCES `laboratory` (`laboratory_id`),
  CONSTRAINT `FKbrtr8mlg92bnq62m8j9o8g329` FOREIGN KEY (`laboratoryTest_id`) REFERENCES `laboratoryTest` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratory_laboratoryTest`
--

LOCK TABLES `laboratory_laboratoryTest` WRITE;
/*!40000 ALTER TABLE `laboratory_laboratoryTest` DISABLE KEYS */;
/*!40000 ALTER TABLE `laboratory_laboratoryTest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicineList`
--

DROP TABLE IF EXISTS `medicineList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicineList` (
  `id` int(11) NOT NULL,
  `medicine_description` varchar(255) DEFAULT NULL,
  `medicine_id` varchar(255) DEFAULT NULL,
  `medicine_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicineList`
--

LOCK TABLES `medicineList` WRITE;
/*!40000 ALTER TABLE `medicineList` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicineList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicineToPatient`
--

DROP TABLE IF EXISTS `medicineToPatient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicineToPatient` (
  `medicine_issueid` int(11) NOT NULL,
  `issue_time` datetime DEFAULT NULL,
  `medicine_batchid` varchar(255) DEFAULT NULL,
  `medicine_composition` varchar(255) DEFAULT NULL,
  `medicine_count` int(11) DEFAULT NULL,
  `medicine_id` int(11) DEFAULT NULL,
  `medicine_name` varchar(255) DEFAULT NULL,
  `medicine_type` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`medicine_issueid`),
  KEY `patient_Medicine_FK` (`patient_id`),
  CONSTRAINT `patient_Medicine_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicineToPatient`
--

LOCK TABLES `medicineToPatient` WRITE;
/*!40000 ALTER TABLE `medicineToPatient` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicineToPatient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nurse`
--

DROP TABLE IF EXISTS `nurse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nurse` (
  `nurse_id` int(11) NOT NULL,
  `nurse_dob` date DEFAULT NULL,
  `nurse_email` varchar(255) DEFAULT NULL,
  `nurse_phone` int(11) DEFAULT NULL,
  `nurse_reg_id` int(11) DEFAULT NULL,
  `nurse_shift` varchar(255) DEFAULT NULL,
  `nurse_type` varchar(255) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `ward_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`nurse_id`),
  KEY `department_NURSE_FK` (`dept_id`),
  KEY `ward_NURSE_FK` (`ward_id`),
  CONSTRAINT `department_NURSE_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `ward_NURSE_FK` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`ward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurse`
--

LOCK TABLES `nurse` WRITE;
/*!40000 ALTER TABLE `nurse` DISABLE KEYS */;
/*!40000 ALTER TABLE `nurse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `outerBloodTransfusion`
--

DROP TABLE IF EXISTS `outerBloodTransfusion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `outerBloodTransfusion` (
  `outer_blood_id` int(11) NOT NULL,
  `blood_bag_number` int(11) DEFAULT NULL,
  `date_of_transfusion` datetime DEFAULT NULL,
  `patient_address` varchar(255) DEFAULT NULL,
  `patient_age` int(11) DEFAULT NULL,
  `patient_blood_group` varchar(255) DEFAULT NULL,
  `patient_confirmation` bit(1) DEFAULT NULL,
  `patient_gender` varchar(255) DEFAULT NULL,
  `patient_name` varchar(255) DEFAULT NULL,
  `patient_phone_number` int(11) DEFAULT NULL,
  `transfer_bank_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`outer_blood_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outerBloodTransfusion`
--

LOCK TABLES `outerBloodTransfusion` WRITE;
/*!40000 ALTER TABLE `outerBloodTransfusion` DISABLE KEYS */;
/*!40000 ALTER TABLE `outerBloodTransfusion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `patient_address` varchar(255) DEFAULT NULL,
  `patient_age` int(11) DEFAULT NULL,
  `patient_dob` date DEFAULT NULL,
  `patient_gender` varchar(255) DEFAULT NULL,
  `patient_guardian` varchar(255) DEFAULT NULL,
  `patient_name` varchar(255) DEFAULT NULL,
  `patient_type` varchar(255) DEFAULT NULL,
  `phone_number` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`patient_id`),
  UNIQUE KEY `UK_bawli8xm92f30ei6x9p3h8eju` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientBilling`
--

DROP TABLE IF EXISTS `patientBilling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientBilling` (
  `patient_billing_id` int(11) NOT NULL,
  `patient_billing_date` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `laboratory_billing_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`patient_billing_id`),
  KEY `department_PBILL_FK` (`dept_id`),
  KEY `laboratory_billing_PBILL_FK` (`laboratory_billing_id`),
  KEY `patient_PBILL_FK` (`patient_id`),
  CONSTRAINT `department_PBILL_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `laboratory_billing_PBILL_FK` FOREIGN KEY (`laboratory_billing_id`) REFERENCES `laboratoryBilling` (`laboratory_billing_id`),
  CONSTRAINT `patient_PBILL_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientBilling`
--

LOCK TABLES `patientBilling` WRITE;
/*!40000 ALTER TABLE `patientBilling` DISABLE KEYS */;
/*!40000 ALTER TABLE `patientBilling` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `amount_due` double DEFAULT NULL,
  `amount_paid` double DEFAULT NULL,
  `payment_amount` double DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `payment_mode` varchar(255) DEFAULT NULL,
  `petty_cash_id` int(11) DEFAULT NULL,
  `purchase_billing_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `petty_cash_PAYBill_FK` (`petty_cash_id`),
  KEY `purchase_billing_PAYBill_FK` (`purchase_billing_id`),
  CONSTRAINT `petty_cash_PAYBill_FK` FOREIGN KEY (`petty_cash_id`) REFERENCES `pettyCash` (`petty_cash_id`),
  CONSTRAINT `purchase_billing_PAYBill_FK` FOREIGN KEY (`purchase_billing_id`) REFERENCES `purchaseBilling` (`purchase_billing_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pettyCash`
--

DROP TABLE IF EXISTS `pettyCash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pettyCash` (
  `petty_cash_id` int(11) NOT NULL,
  `cash_amount` double DEFAULT NULL,
  `petty_cash_date` datetime DEFAULT NULL,
  PRIMARY KEY (`petty_cash_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pettyCash`
--

LOCK TABLES `pettyCash` WRITE;
/*!40000 ALTER TABLE `pettyCash` DISABLE KEYS */;
/*!40000 ALTER TABLE `pettyCash` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmacyMedicine`
--

DROP TABLE IF EXISTS `pharmacyMedicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pharmacyMedicine` (
  `pharmacy_medicine_id` int(11) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pharmacy_medicine_id`),
  KEY `patient_PHR_MED_FK` (`patient_id`),
  CONSTRAINT `patient_PHR_MED_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmacyMedicine`
--

LOCK TABLES `pharmacyMedicine` WRITE;
/*!40000 ALTER TABLE `pharmacyMedicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `pharmacyMedicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmacyMedicine_medicineList`
--

DROP TABLE IF EXISTS `pharmacyMedicine_medicineList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pharmacyMedicine_medicineList` (
  `PharmacyMedicine_pharmacy_medicine_id` int(11) NOT NULL,
  `medicineList_id` int(11) NOT NULL,
  UNIQUE KEY `UK_9cj8sj0hwi9q6xmxfuuo4mygm` (`medicineList_id`),
  KEY `FKow762hmcu2edi5ha89vcnsqww` (`PharmacyMedicine_pharmacy_medicine_id`),
  CONSTRAINT `FK9kic3xkjwm6v4dwpwxy8ax9a6` FOREIGN KEY (`medicineList_id`) REFERENCES `medicineList` (`id`),
  CONSTRAINT `FKow762hmcu2edi5ha89vcnsqww` FOREIGN KEY (`PharmacyMedicine_pharmacy_medicine_id`) REFERENCES `pharmacyMedicine` (`pharmacy_medicine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmacyMedicine_medicineList`
--

LOCK TABLES `pharmacyMedicine_medicineList` WRITE;
/*!40000 ALTER TABLE `pharmacyMedicine_medicineList` DISABLE KEYS */;
/*!40000 ALTER TABLE `pharmacyMedicine_medicineList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaseBilling`
--

DROP TABLE IF EXISTS `purchaseBilling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchaseBilling` (
  `purchase_billing_id` int(11) NOT NULL,
  `billing_id` int(11) DEFAULT NULL,
  `payment_amount` double DEFAULT NULL,
  `payment_terms` double DEFAULT NULL,
  `purchase_billing_date` datetime DEFAULT NULL,
  `purchase_id` int(11) DEFAULT NULL,
  `expense_approval_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`purchase_billing_id`),
  KEY `expense_approval_PURBill_FK` (`expense_approval_id`),
  CONSTRAINT `expense_approval_PURBill_FK` FOREIGN KEY (`expense_approval_id`) REFERENCES `expenseApproval` (`expense_approval_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseBilling`
--

LOCK TABLES `purchaseBilling` WRITE;
/*!40000 ALTER TABLE `purchaseBilling` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchaseBilling` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaseOrder`
--

DROP TABLE IF EXISTS `purchaseOrder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchaseOrder` (
  `purchase_order_id` int(11) NOT NULL,
  `issued_date` date DEFAULT NULL,
  `item_count` int(11) DEFAULT NULL,
  `item_description` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `supplier_company_name` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `stock_ledger_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`purchase_order_id`),
  KEY `department_PURODR_FK` (`dept_id`),
  KEY `stock_ledger_PURODR_FK` (`stock_ledger_id`),
  CONSTRAINT `department_PURODR_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `stock_ledger_PURODR_FK` FOREIGN KEY (`stock_ledger_id`) REFERENCES `stockLedger` (`stock_ledger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseOrder`
--

LOCK TABLES `purchaseOrder` WRITE;
/*!40000 ALTER TABLE `purchaseOrder` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchaseOrder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requestHouseKeep`
--

DROP TABLE IF EXISTS `requestHouseKeep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requestHouseKeep` (
  `houseKeep_RequestId` int(11) NOT NULL,
  `giveHousekeppDate` datetime DEFAULT NULL,
  `giveToLaundaryStatus` varchar(255) DEFAULT NULL,
  `issuetoLaundryDate` datetime DEFAULT NULL,
  `receiveDeptId` int(11) DEFAULT NULL,
  `receiveFromLaundryDate` datetime DEFAULT NULL,
  `receiveStatus` varchar(255) DEFAULT NULL,
  `receiveWards` varchar(255) DEFAULT NULL,
  `receivedLaundaryStatus` varchar(255) DEFAULT NULL,
  `returnHousekeppDate` datetime DEFAULT NULL,
  `returnStaus` varchar(255) DEFAULT NULL,
  `returnToWards` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`houseKeep_RequestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requestHouseKeep`
--

LOCK TABLES `requestHouseKeep` WRITE;
/*!40000 ALTER TABLE `requestHouseKeep` DISABLE KEYS */;
/*!40000 ALTER TABLE `requestHouseKeep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requestHouseKeepSubCat`
--

DROP TABLE IF EXISTS `requestHouseKeepSubCat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requestHouseKeepSubCat` (
  `serial_Id` int(11) NOT NULL,
  `itemCount` int(11) DEFAULT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `houseKeep_RequestId` int(11) DEFAULT NULL,
  PRIMARY KEY (`serial_Id`),
  KEY `HousekeepRequest_NameCategry_FK` (`houseKeep_RequestId`),
  CONSTRAINT `HousekeepRequest_NameCategry_FK` FOREIGN KEY (`houseKeep_RequestId`) REFERENCES `requestHouseKeep` (`houseKeep_RequestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requestHouseKeepSubCat`
--

LOCK TABLES `requestHouseKeepSubCat` WRITE;
/*!40000 ALTER TABLE `requestHouseKeepSubCat` DISABLE KEYS */;
/*!40000 ALTER TABLE `requestHouseKeepSubCat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `staff_id` int(11) NOT NULL,
  `employee_id` varchar(255) DEFAULT NULL,
  `staff_address` varchar(255) DEFAULT NULL,
  `staff_age` varchar(255) DEFAULT NULL,
  `staff_doj` date DEFAULT NULL,
  `staff_gender` varchar(255) DEFAULT NULL,
  `staff_name` varchar(255) DEFAULT NULL,
  `staff_role` varchar(255) DEFAULT NULL,
  `staff_ward_number` int(11) DEFAULT NULL,
  `staff_work` varchar(255) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `driver_id` int(11) DEFAULT NULL,
  `houseKeeper_id` int(11) DEFAULT NULL,
  `nurse_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`staff_id`),
  KEY `doctor_STF_FK` (`doctor_id`),
  KEY `driver_STF_FK` (`driver_id`),
  KEY `house_keeping_STF_FK` (`houseKeeper_id`),
  KEY `nurse_STF_FK` (`nurse_id`),
  CONSTRAINT `doctor_STF_FK` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  CONSTRAINT `driver_STF_FK` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`),
  CONSTRAINT `house_keeping_STF_FK` FOREIGN KEY (`houseKeeper_id`) REFERENCES `houseKeeping` (`houseKeeper_id`),
  CONSTRAINT `nurse_STF_FK` FOREIGN KEY (`nurse_id`) REFERENCES `nurse` (`nurse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockLedger`
--

DROP TABLE IF EXISTS `stockLedger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stockLedger` (
  `stock_ledger_id` int(11) NOT NULL,
  `expire_date` date DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product_type` varchar(255) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `stock_count` int(11) DEFAULT NULL,
  `supplier_company_name` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`stock_ledger_id`),
  KEY `department_STKLED_FK` (`dept_id`),
  CONSTRAINT `department_STKLED_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockLedger`
--

LOCK TABLES `stockLedger` WRITE;
/*!40000 ALTER TABLE `stockLedger` DISABLE KEYS */;
/*!40000 ALTER TABLE `stockLedger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `surgery`
--

DROP TABLE IF EXISTS `surgery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `surgery` (
  `surgery_id` bigint(20) NOT NULL,
  `surgery_date` datetime DEFAULT NULL,
  `surgery_description` varchar(255) DEFAULT NULL,
  `cssd_id` int(11) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `nurse_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`surgery_id`),
  KEY `cssd_SUR_FK` (`cssd_id`),
  KEY `department_SUR_FK` (`dept_id`),
  KEY `doctor_SUR_FK` (`doctor_id`),
  KEY `nurse_SUR_FK` (`nurse_id`),
  KEY `patient_SUR_FK` (`patient_id`),
  CONSTRAINT `cssd_SUR_FK` FOREIGN KEY (`cssd_id`) REFERENCES `cssd` (`cssd_id`),
  CONSTRAINT `department_SUR_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `doctor_SUR_FK` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  CONSTRAINT `nurse_SUR_FK` FOREIGN KEY (`nurse_id`) REFERENCES `nurse` (`nurse_id`),
  CONSTRAINT `patient_SUR_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surgery`
--

LOCK TABLES `surgery` WRITE;
/*!40000 ALTER TABLE `surgery` DISABLE KEYS */;
/*!40000 ALTER TABLE `surgery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tariff`
--

DROP TABLE IF EXISTS `tariff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tariff` (
  `tariff_id` int(11) NOT NULL,
  `tariff_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tariff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariff`
--

LOCK TABLES `tariff` WRITE;
/*!40000 ALTER TABLE `tariff` DISABLE KEYS */;
/*!40000 ALTER TABLE `tariff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferDoctorDetails`
--

DROP TABLE IF EXISTS `transferDoctorDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transferDoctorDetails` (
  `transfer_doctor_id` int(11) NOT NULL,
  `appointment_end_time` datetime DEFAULT NULL,
  `appointment_start_time` datetime DEFAULT NULL,
  `transfer_doctor_description` varchar(255) DEFAULT NULL,
  `transfer_doctor_name` varchar(255) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`transfer_doctor_id`),
  KEY `doctor_TRAN_DOC_FK` (`doctor_id`),
  KEY `patient_TRAN_DOC_FK` (`patient_id`),
  CONSTRAINT `doctor_TRAN_DOC_FK` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  CONSTRAINT `patient_TRAN_DOC_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferDoctorDetails`
--

LOCK TABLES `transferDoctorDetails` WRITE;
/*!40000 ALTER TABLE `transferDoctorDetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `transferDoctorDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wantedStock`
--

DROP TABLE IF EXISTS `wantedStock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wantedStock` (
  `wanted_stock_id` int(11) NOT NULL,
  `wanted_count` int(11) DEFAULT NULL,
  `wanted_date` date DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `stock_ledger_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`wanted_stock_id`),
  KEY `department_WSTOCK_FK` (`dept_id`),
  KEY `stock_ledger_WSTOCK_FK` (`stock_ledger_id`),
  CONSTRAINT `department_WSTOCK_FK` FOREIGN KEY (`dept_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `stock_ledger_WSTOCK_FK` FOREIGN KEY (`stock_ledger_id`) REFERENCES `stockLedger` (`stock_ledger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wantedStock`
--

LOCK TABLES `wantedStock` WRITE;
/*!40000 ALTER TABLE `wantedStock` DISABLE KEYS */;
/*!40000 ALTER TABLE `wantedStock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wantedStockList`
--

DROP TABLE IF EXISTS `wantedStockList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wantedStockList` (
  `wanted_stock_list_id` int(11) NOT NULL,
  `wanted_stock_list_description` varchar(255) DEFAULT NULL,
  `wanted_stock_list_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`wanted_stock_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wantedStockList`
--

LOCK TABLES `wantedStockList` WRITE;
/*!40000 ALTER TABLE `wantedStockList` DISABLE KEYS */;
/*!40000 ALTER TABLE `wantedStockList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wantedStock_wantedStockList`
--

DROP TABLE IF EXISTS `wantedStock_wantedStockList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wantedStock_wantedStockList` (
  `WantedStock_wanted_stock_id` int(11) NOT NULL,
  `wantedList_wanted_stock_list_id` int(11) NOT NULL,
  UNIQUE KEY `UK_qh7knglda5tm3s5p0lxu4hleo` (`wantedList_wanted_stock_list_id`),
  KEY `FKs6t58e0ratpl6fvpe5nduodf1` (`WantedStock_wanted_stock_id`),
  CONSTRAINT `FKl61s9ec4y60fl0tyes95b53qo` FOREIGN KEY (`wantedList_wanted_stock_list_id`) REFERENCES `wantedStockList` (`wanted_stock_list_id`),
  CONSTRAINT `FKs6t58e0ratpl6fvpe5nduodf1` FOREIGN KEY (`WantedStock_wanted_stock_id`) REFERENCES `wantedStock` (`wanted_stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wantedStock_wantedStockList`
--

LOCK TABLES `wantedStock_wantedStockList` WRITE;
/*!40000 ALTER TABLE `wantedStock_wantedStockList` DISABLE KEYS */;
/*!40000 ALTER TABLE `wantedStock_wantedStockList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward`
--

DROP TABLE IF EXISTS `ward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ward` (
  `ward_id` int(11) NOT NULL,
  `bed_details` varchar(255) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `ward_bed_number` int(11) DEFAULT NULL,
  `ward_number` int(11) DEFAULT NULL,
  `ward_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward`
--

LOCK TABLES `ward` WRITE;
/*!40000 ALTER TABLE `ward` DISABLE KEYS */;
/*!40000 ALTER TABLE `ward` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-15 14:12:23
