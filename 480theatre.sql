-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: ensf480theatredatabase
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `discountcodes`
--

DROP TABLE IF EXISTS `discountcodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discountcodes` (
  `Code` int NOT NULL AUTO_INCREMENT,
  `Discount` double DEFAULT NULL,
  `ExpirationDate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discountcodes`
--

LOCK TABLES `discountcodes` WRITE;
/*!40000 ALTER TABLE `discountcodes` DISABLE KEYS */;
/*!40000 ALTER TABLE `discountcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `MovieName` varchar(20) NOT NULL,
  `ReleaseDate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`MovieName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES ('Shrek','03-12-2022'),('Shrek 2','10-12-2022'),('Shrek 3','17-12-2022'),('Shrek 4','24-12-2022'),('The Godfather','11-12-2022');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderID` int NOT NULL AUTO_INCREMENT,
  `Email` varchar(45) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `RefundDate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`OrderID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registereduser`
--

DROP TABLE IF EXISTS `registereduser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registereduser` (
  `Email` varchar(45) NOT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Address` varchar(40) DEFAULT NULL,
  `CardInfo` varchar(20) DEFAULT NULL,
  `AnnualDate` varchar(20) DEFAULT NULL,
  `PaidFee` tinyint DEFAULT NULL,
  PRIMARY KEY (`Email`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registereduser`
--

LOCK TABLES `registereduser` WRITE;
/*!40000 ALTER TABLE `registereduser` DISABLE KEYS */;
INSERT INTO `registereduser` VALUES ('email@email.com','pass','john','school','1111-1111-1111-1111','03-12-2022',1),('jason.wu4325@gmail.com','password','Jason','home','4000-5000-6000-9000','03-12-2022',1);
/*!40000 ALTER TABLE `registereduser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showtime`
--

DROP TABLE IF EXISTS `showtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `showtime` (
  `Time` varchar(20) NOT NULL,
  `ShowtimeID` int NOT NULL AUTO_INCREMENT,
  `Screen` int NOT NULL,
  `MovieName` varchar(20) NOT NULL,
  `Date` varchar(20) NOT NULL,
  PRIMARY KEY (`ShowtimeID`),
  UNIQUE KEY `uniqueshowtime` (`Time`,`Screen`,`MovieName`,`Date`),
  KEY `MovieName_idx` (`MovieName`),
  CONSTRAINT `MovieName` FOREIGN KEY (`MovieName`) REFERENCES `movie` (`MovieName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showtime`
--

LOCK TABLES `showtime` WRITE;
/*!40000 ALTER TABLE `showtime` DISABLE KEYS */;
INSERT INTO `showtime` VALUES ('07:30PM',1,1,'Shrek','03-12-2022'),('07:30PM',5,3,'Shrek','03-12-2022'),('09:30PM',2,2,'Shrek','03-12-2022'),('09:30PM',7,2,'Shrek 2','10-12-2022'),('10:30PM',3,1,'Shrek','03-12-2022'),('10:30PM',8,1,'Shrek 2','10-12-2022'),('10:30PM',6,3,'Shrek','03-12-2022'),('10:30PM',11,3,'Shrek 2','11-12-2022'),('12:30AM',4,2,'Shrek','04-12-2022'),('12:30AM',9,2,'Shrek 2','11-12-2022'),('7:30PM',12,3,'Shrek 2','10-12-2022');
/*!40000 ALTER TABLE `showtime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `TicketID` int NOT NULL AUTO_INCREMENT,
  `OrderID` int NOT NULL,
  `SRow` int DEFAULT NULL,
  `SColumn` int DEFAULT NULL,
  `ShowtimeID` int DEFAULT NULL,
  PRIMARY KEY (`TicketID`,`OrderID`),
  UNIQUE KEY `uniqueticket` (`SRow`,`SColumn`,`ShowtimeID`),
  KEY `TicketForShowtime_idx` (`ShowtimeID`),
  KEY `OrderID_idx` (`OrderID`),
  CONSTRAINT `OrderID` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TicketForShowtime` FOREIGN KEY (`ShowtimeID`) REFERENCES `showtime` (`ShowtimeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-04 16:16:27
