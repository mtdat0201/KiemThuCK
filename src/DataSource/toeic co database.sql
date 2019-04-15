CREATE DATABASE  IF NOT EXISTS `toeic` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `toeic`;
-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: localhost    Database: toeic
-- ------------------------------------------------------
-- Server version	5.7.24-log

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
-- Table structure for table `cauhoi`
--

DROP TABLE IF EXISTS `cauhoi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cauhoi` (
  `id_cauhoi` int(11) NOT NULL AUTO_INCREMENT,
  `id_theloai` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cauhoi` text COLLATE utf8_unicode_ci,
  `A` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `B` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `C` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `D` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dapan` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `audio` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_cauhoi`),
  KEY `id_theloai` (`id_theloai`),
  CONSTRAINT `fk_theloai_cauhoi` FOREIGN KEY (`id_theloai`) REFERENCES `theloai` (`id_theloai`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cauhoi`
--

LOCK TABLES `cauhoi` WRITE;
/*!40000 ALTER TABLE `cauhoi` DISABLE KEYS */;
INSERT INTO `cauhoi` VALUES (1,'P','The police charged him ________ at a wrong place.','park','to park','parking','with parkin','d','pic1.png',NULL),(2,'DK','Sometimes Harry thinks about ________ to another city',NULL,NULL,NULL,NULL,'moves',NULL,NULL),(3,'TN','He was found guilty ________ possessing illegal drugs.','with','in','from','of','d',NULL,NULL),(4,'A','At the exposition, there was a large collection of various ________ that drew thepublic?s interest.','machineries','machine','mechanical','machines','d',NULL,'01.mp3'),(5,'DK','While ________ fabric design has the influence of ancient Egypt, the actual furnitureexhibits a more European influence.',NULL,NULL,NULL,NULL,'the',NULL,NULL),(6,'TN','There were too ________ problems for us to solve in a day.','many','little','much','small','c',NULL,NULL),(7,'P','The couple bought ________ before they got married.','a lot of furnitures','a lot of furniture','many furniture','many furnitures','b','pic2.png',NULL),(8,'A','I have just finished ________ assignment.','a thirty pages','thirty-page','thirty pages','a thirty-page','c',NULL,'02.mp3'),(9,'A','He speaks English __________ than Spanish','many more rapidly','more much rapidly','much more rapidly','more rapidly than','c','','05.mp3'),(10,'A','My daughter will begin ________ Chinese next semester','will study','to study','studies','study','b','','04.mp3');
/*!40000 ALTER TABLE `cauhoi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diem`
--

DROP TABLE IF EXISTS `diem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diem` (
  `id_diem` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `diem` int(11) DEFAULT '0',
  `ngaythuchien` datetime DEFAULT NULL,
  PRIMARY KEY (`id_diem`),
  KEY `user_name` (`user_name`),
  CONSTRAINT `fk_nguoidung_diem` FOREIGN KEY (`user_name`) REFERENCES `nguoidung` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diem`
--

LOCK TABLES `diem` WRITE;
/*!40000 ALTER TABLE `diem` DISABLE KEYS */;
INSERT INTO `diem` VALUES (21,'bingo',0,NULL),(22,'bingio',0,NULL),(23,'bingo',0,'2019-02-04 21:40:19'),(24,'bingo',1,'2019-02-04 21:42:05'),(25,'bingo',1,'2019-02-04 21:49:37'),(26,'bingo',0,'2019-02-17 13:40:45'),(27,'bingo',1,'2019-02-17 13:41:03'),(28,'bingo',1,'2019-02-17 13:44:18'),(29,'bingo',1,'2019-02-17 13:46:00');
/*!40000 ALTER TABLE `diem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nguoidung`
--

DROP TABLE IF EXISTS `nguoidung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nguoidung` (
  `id_nguoidung` int(11) NOT NULL AUTO_INCREMENT,
  `id_phanquyen` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `pass` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `last_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_nguoidung`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  KEY `fk_phanquyen_id` (`id_phanquyen`),
  KEY `user_name` (`user_name`),
  CONSTRAINT `fk_loainguoidung_nguoidung` FOREIGN KEY (`id_phanquyen`) REFERENCES `phanquyen` (`id_phanquyen`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoidung`
--

LOCK TABLES `nguoidung` WRITE;
/*!40000 ALTER TABLE `nguoidung` DISABLE KEYS */;
INSERT INTO `nguoidung` VALUES (1,'A','bingo','123456','Mai','Dat','maidat@gmail.com'),(3,'T','bingio','123456','Mai','Dat','123@gmail.com');
/*!40000 ALTER TABLE `nguoidung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phanquyen`
--

DROP TABLE IF EXISTS `phanquyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phanquyen` (
  `id_phanquyen` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_phanquyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phanquyen`
--

LOCK TABLES `phanquyen` WRITE;
/*!40000 ALTER TABLE `phanquyen` DISABLE KEYS */;
INSERT INTO `phanquyen` VALUES ('A','Admin'),('S','Student'),('T','Teacher');
/*!40000 ALTER TABLE `phanquyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theloai`
--

DROP TABLE IF EXISTS `theloai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theloai` (
  `id_theloai` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `name_theloai` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_theloai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theloai`
--

LOCK TABLES `theloai` WRITE;
/*!40000 ALTER TABLE `theloai` DISABLE KEYS */;
INSERT INTO `theloai` VALUES ('A','Question Response'),('DK','Fill Sentences'),('P','Photo Question'),('TN','Multiple-Choice');
/*!40000 ALTER TABLE `theloai` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-19 16:07:20
