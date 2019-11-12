-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tax_system
-- ------------------------------------------------------
-- Server version	5.6.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `complaints`
--

DROP TABLE IF EXISTS `complaints`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaints` (
  `id_complaint` int(11) NOT NULL AUTO_INCREMENT,
  `id_person` int(11) NOT NULL,
  `content` varchar(45) DEFAULT NULL,
  `completion_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_complaint`),
  KEY `fk_complaints_persons1_idx` (`id_person`),
  CONSTRAINT `fk_complaints_persons1` FOREIGN KEY (`id_person`) REFERENCES `persons` (`id_person`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaints`
--

LOCK TABLES `complaints` WRITE;
/*!40000 ALTER TABLE `complaints` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaints` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persons` (
  `id_person` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `id_inspector` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_person`),
  KEY `fk_persons_persons1_idx` (`id_inspector`),
  CONSTRAINT `fk_persons_persons1` FOREIGN KEY (`id_inspector`) REFERENCES `persons` (`id_person`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,'ADMIN','ADMIN','admin@g.c','$2a$10$qCjEWt4nghscRGxiNKWq.OnfDw9fiT8SSYAK5.5sy9IkXyDHir4Re',1),(26,'client','client','client@g.c','$2a$10$qCjEWt4nghscRGxiNKWq.OnfDw9fiT8SSYAK5.5sy9IkXyDHir4Re',1);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons_has_roles`
--

DROP TABLE IF EXISTS `persons_has_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persons_has_roles` (
  `id_person` int(11) NOT NULL,
  `id_role` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_person`,`id_role`),
  KEY `fk_persons_has_roles_roles1_idx` (`id_role`),
  KEY `fk_persons_has_roles_persons1_idx` (`id_person`),
  CONSTRAINT `fk_persons_has_roles_persons1` FOREIGN KEY (`id_person`) REFERENCES `persons` (`id_person`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_persons_has_roles_roles1` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons_has_roles`
--

LOCK TABLES `persons_has_roles` WRITE;
/*!40000 ALTER TABLE `persons_has_roles` DISABLE KEYS */;
INSERT INTO `persons_has_roles` VALUES (1,0),(1,1),(26,1);
/*!40000 ALTER TABLE `persons_has_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons_has_taxable_items`
--

DROP TABLE IF EXISTS `persons_has_taxable_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persons_has_taxable_items` (
  `id_person` int(11) NOT NULL,
  `id_item` int(11) NOT NULL,
  PRIMARY KEY (`id_person`,`id_item`),
  KEY `fk_persons_has_taxable_items_taxable_items1_idx` (`id_item`),
  KEY `fk_persons_has_taxable_items_persons1_idx` (`id_person`),
  CONSTRAINT `fk_persons_has_taxable_items_persons1` FOREIGN KEY (`id_person`) REFERENCES `persons` (`id_person`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_persons_has_taxable_items_taxable_items1` FOREIGN KEY (`id_item`) REFERENCES `taxable_items` (`id_item`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons_has_taxable_items`
--

LOCK TABLES `persons_has_taxable_items` WRITE;
/*!40000 ALTER TABLE `persons_has_taxable_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `persons_has_taxable_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `id_report` int(11) NOT NULL AUTO_INCREMENT,
  `id_person` int(11) NOT NULL,
  `company_name` varchar(45) DEFAULT NULL,
  `taxpayer_code` varchar(45) DEFAULT NULL,
  `completion_time` timestamp NULL DEFAULT NULL,
  `total_amount_of_property` int(11) DEFAULT NULL,
  `is_accepted` tinyint(1) unsigned zerofill DEFAULT NULL,
  `should_be_changed` tinyint(1) unsigned zerofill DEFAULT NULL,
  `inspector_comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_report`),
  KEY `fk_reports_persons1_idx` (`id_person`),
  CONSTRAINT `fk_reports_persons1` FOREIGN KEY (`id_person`) REFERENCES `persons` (`id_person`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (15,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,1,NULL,NULL,NULL,0,0,0,NULL),(17,26,'frasdf','123','2019-07-29 13:39:45',0,0,0,NULL);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id_role` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name_role` varchar(15) NOT NULL,
  PRIMARY KEY (`id_role`),
  UNIQUE KEY `id` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (0,'INSPECTOR'),(1,'CLIENT');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxable_items`
--

DROP TABLE IF EXISTS `taxable_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taxable_items` (
  `id_item` int(11) NOT NULL AUTO_INCREMENT,
  `name_item` varchar(45) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_item`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxable_items`
--

LOCK TABLES `taxable_items` WRITE;
/*!40000 ALTER TABLE `taxable_items` DISABLE KEYS */;
INSERT INTO `taxable_items` VALUES (1,'square meter office space tax',9),(2,'employee tax',15),(3,'per one million income tax',33);
/*!40000 ALTER TABLE `taxable_items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-29 16:42:20
