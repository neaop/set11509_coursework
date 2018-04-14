CREATE DATABASE  IF NOT EXISTS `share_trader` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `share_trader`;
-- MySQL dump 10.16  Distrib 10.1.26-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: share_trader
-- ------------------------------------------------------
-- Server version	10.1.26-MariaDB-0+deb9u1

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
-- Table structure for table `alert`
--

DROP TABLE IF EXISTS `alert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alert` (
  `alert_id` int(11) NOT NULL AUTO_INCREMENT,
  `alert_date` datetime NOT NULL,
  `alert_shareholer_id` int(11) NOT NULL,
  `alert_content` varchar(45) NOT NULL,
  PRIMARY KEY (`alert_id`),
  UNIQUE KEY `alert_id_UNIQUE` (`alert_id`),
  KEY `fk_alert_shareholer_id_idx` (`alert_shareholer_id`),
  CONSTRAINT `fk_alert_shareholer_id` FOREIGN KEY (`alert_shareholer_id`) REFERENCES `shareholder` (`shareholder_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert`
--

LOCK TABLES `alert` WRITE;
/*!40000 ALTER TABLE `alert` DISABLE KEYS */;
/*!40000 ALTER TABLE `alert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `broker`
--

DROP TABLE IF EXISTS `broker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `broker` (
  `broker_id` int(11) NOT NULL AUTO_INCREMENT,
  `broker_name` varchar(45) NOT NULL,
  `broker_contact` varchar(45) NOT NULL,
  `broker_grade` decimal(5,2) NOT NULL,
  PRIMARY KEY (`broker_id`),
  UNIQUE KEY `broker_id_UNIQUE` (`broker_id`),
  UNIQUE KEY `broker_name_UNIQUE` (`broker_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `broker`
--

LOCK TABLES `broker` WRITE;
/*!40000 ALTER TABLE `broker` DISABLE KEYS */;
INSERT INTO `broker` VALUES (1,'Chad Hannigan','chad@mail.com',95.00),(2,'Ana Cappucino','ana@coffee.org',97.20),(3,'Jimmy Wong','wong@wonga.com',27.80),(4,'Fearnhal McGurrnigan','fmg@abc.ie',90.00),(5,'Frances Tootley','toot@toot.fr',68.44);
/*!40000 ALTER TABLE `broker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(45) NOT NULL,
  `company_code` varchar(45) NOT NULL,
  PRIMARY KEY (`company_id`),
  UNIQUE KEY `company_id_UNIQUE` (`company_id`),
  UNIQUE KEY `company_name_UNIQUE` (`company_name`),
  UNIQUE KEY `company_code_UNIQUE` (`company_code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Internet of Tings','iot'),(2,'Crypto Creeps','cc'),(3,'Russian Airlines','ral'),(4,'Queen Bee Cosmetics','qbc'),(5,'Beef Boy Butchers','bbb'),(6,'Mobility Scooter Emporium','mse'),(7,'Soy Boy Inc.','sbi'),(8,'The Ottoman Empire.','toe');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domain`
--

DROP TABLE IF EXISTS `domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domain` (
  `domain_id` int(11) NOT NULL AUTO_INCREMENT,
  `domain_type` varchar(45) NOT NULL,
  PRIMARY KEY (`domain_id`),
  UNIQUE KEY `domain_id_UNIQUE` (`domain_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domain`
--

LOCK TABLES `domain` WRITE;
/*!40000 ALTER TABLE `domain` DISABLE KEYS */;
INSERT INTO `domain` VALUES (1,'IoT'),(2,'Cryptocurrency'),(3,'Airlines'),(4,'Rubber Chickens'),(5,'Cosmetics'),(6,'Meat'),(7,'Mobility Scooters'),(8,'Milk'),(9,'Antiques');
/*!40000 ALTER TABLE `domain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expertise`
--

DROP TABLE IF EXISTS `expertise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expertise` (
  `expertise_id` int(11) NOT NULL AUTO_INCREMENT,
  `expertise_broker_id` int(11) NOT NULL,
  `expertise_domain_id` int(11) NOT NULL,
  PRIMARY KEY (`expertise_id`),
  UNIQUE KEY `broker_domain_id_UNIQUE` (`expertise_id`),
  KEY `fk_expertise_domain_id_idx` (`expertise_domain_id`),
  KEY `fk_expertise_broker_id` (`expertise_broker_id`),
  CONSTRAINT `fk_expertise_broker_id` FOREIGN KEY (`expertise_broker_id`) REFERENCES `broker` (`broker_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_expertise_domain_id` FOREIGN KEY (`expertise_domain_id`) REFERENCES `domain` (`domain_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expertise`
--

LOCK TABLES `expertise` WRITE;
/*!40000 ALTER TABLE `expertise` DISABLE KEYS */;
INSERT INTO `expertise` VALUES (1,1,1),(2,1,2),(3,2,3),(4,5,6),(5,3,4),(6,4,9);
/*!40000 ALTER TABLE `expertise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `field`
--

DROP TABLE IF EXISTS `field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `field` (
  `field_id` int(11) NOT NULL AUTO_INCREMENT,
  `field_company_id` int(11) NOT NULL,
  `field_domain_id` int(11) NOT NULL,
  PRIMARY KEY (`field_id`),
  UNIQUE KEY `field_id_UNIQUE` (`field_id`),
  KEY `fk_field_domain_id_idx` (`field_domain_id`),
  KEY `fk_field_comapny_id_idx` (`field_company_id`),
  CONSTRAINT `fk_field_comapny_id` FOREIGN KEY (`field_company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_field_domain_id` FOREIGN KEY (`field_domain_id`) REFERENCES `domain` (`domain_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `field`
--

LOCK TABLES `field` WRITE;
/*!40000 ALTER TABLE `field` DISABLE KEYS */;
INSERT INTO `field` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,5),(5,5,6),(6,6,7),(7,7,8),(8,8,9);
/*!40000 ALTER TABLE `field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `share`
--

DROP TABLE IF EXISTS `share`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `share` (
  `share_id` int(11) NOT NULL AUTO_INCREMENT,
  `share_company_id` int(11) NOT NULL,
  `share_price` int(20) NOT NULL,
  `share_value` int(20) NOT NULL,
  `share_quantity` int(11) NOT NULL,
  PRIMARY KEY (`share_id`),
  UNIQUE KEY `share_id_UNIQUE` (`share_id`),
  KEY `fk_share_comapny_id_idx` (`share_company_id`),
  CONSTRAINT `fk_share_comapny_id` FOREIGN KEY (`share_company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `share`
--

LOCK TABLES `share` WRITE;
/*!40000 ALTER TABLE `share` DISABLE KEYS */;
INSERT INTO `share` VALUES (1,5,9000,15000,100),(2,8,1000,1050,55);
/*!40000 ALTER TABLE `share` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shareholder`
--

DROP TABLE IF EXISTS `shareholder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shareholder` (
  `shareholder_id` int(11) NOT NULL AUTO_INCREMENT,
  `shareholder_name` varchar(45) NOT NULL,
  PRIMARY KEY (`shareholder_id`),
  UNIQUE KEY `shareholder_id_UNIQUE` (`shareholder_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shareholder`
--

LOCK TABLES `shareholder` WRITE;
/*!40000 ALTER TABLE `shareholder` DISABLE KEYS */;
INSERT INTO `shareholder` VALUES (1,'John Smith'),(2,'John Doe'),(3,'John Hancock'),(4,'Alice Person'),(5,'Eve Listener'),(6,'Hunter Guy'),(7,'Sally Truman'),(8,'Beth Grade'),(9,'Lilly Sand');
/*!40000 ALTER TABLE `shareholder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stake`
--

DROP TABLE IF EXISTS `stake`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stake` (
  `stake_id` int(11) NOT NULL AUTO_INCREMENT,
  `stake_stakeholder_id` int(11) NOT NULL,
  `stake_company_id` int(11) NOT NULL,
  `stake_quantity` int(11) NOT NULL,
  PRIMARY KEY (`stake_id`),
  UNIQUE KEY `stake_id_UNIQUE` (`stake_id`),
  KEY `fk_stake_company_id_idx` (`stake_company_id`),
  KEY `fk_stake_shareholer_id_idx` (`stake_stakeholder_id`),
  CONSTRAINT `fk_stake_company_id` FOREIGN KEY (`stake_company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_stake_shareholer_id` FOREIGN KEY (`stake_stakeholder_id`) REFERENCES `shareholder` (`shareholder_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stake`
--

LOCK TABLES `stake` WRITE;
/*!40000 ALTER TABLE `stake` DISABLE KEYS */;
INSERT INTO `stake` VALUES (1,1,3,25),(2,3,2,5),(3,6,5,50),(4,9,1,10);
/*!40000 ALTER TABLE `stake` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `track` (
  `track_id` int(11) NOT NULL AUTO_INCREMENT,
  `track_user_id` int(11) NOT NULL,
  `track_share_id` int(11) NOT NULL,
  `track_min` int(20) NOT NULL,
  `track_max` int(20) NOT NULL,
  PRIMARY KEY (`track_user_id`,`track_share_id`),
  UNIQUE KEY `interest_id_UNIQUE` (`track_id`),
  KEY `fk_interest_user_id_idx` (`track_user_id`),
  KEY `fk_interest_share_id_idx` (`track_share_id`),
  CONSTRAINT `fk_track_share_id` FOREIGN KEY (`track_share_id`) REFERENCES `share` (`share_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_track_user_id` FOREIGN KEY (`track_user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (7,2,1,8000,20000),(8,2,2,500,15000);
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trade` (
  `trade_id` int(11) NOT NULL AUTO_INCREMENT,
  `trade_stake_id` int(11) NOT NULL,
  `trade_date` date NOT NULL,
  `trade_quantity` int(11) NOT NULL,
  `trade_price` int(20) NOT NULL,
  `trade_seller_id` int(11) NOT NULL,
  `trade_buyer_id` int(11) NOT NULL,
  `trade_broker_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`trade_id`),
  UNIQUE KEY `trade_id_UNIQUE` (`trade_id`),
  KEY `fk_trade_seller_id_idx` (`trade_seller_id`),
  KEY `fk_trade_buyer_id_idx` (`trade_buyer_id`),
  KEY `fk_trade_broker_id_idx` (`trade_broker_id`),
  KEY `fk_trade_stake_id_idx` (`trade_stake_id`),
  CONSTRAINT `fk_trade_broker_id` FOREIGN KEY (`trade_broker_id`) REFERENCES `broker` (`broker_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_trade_buyer_id` FOREIGN KEY (`trade_buyer_id`) REFERENCES `shareholder` (`shareholder_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_trade_seller_id` FOREIGN KEY (`trade_seller_id`) REFERENCES `shareholder` (`shareholder_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_trade_stake_id` FOREIGN KEY (`trade_stake_id`) REFERENCES `stake` (`stake_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
INSERT INTO `trade` VALUES (1,3,'2016-05-09',50,23400,2,6,5),(2,1,'2017-06-11',25,230000,5,1,2),(3,2,'2018-01-23',5,5000,8,3,1),(4,4,'2018-04-01',10,10,4,9,1);
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `user_password` varchar(45) NOT NULL,
  `user_admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `id_UNIQUE` (`user_id`),
  UNIQUE KEY `name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin',1),(2,'test','test',0),(3,'new','pass',0),(4,'bert','bert',0),(5,'sam','test',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'share_trader'
--

--
-- Dumping routines for database 'share_trader'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-14 18:29:45
