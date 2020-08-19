-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: localhost    Database: community
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Account` (
  `usr_name` char(11) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `type` char(20) NOT NULL,
  PRIMARY KEY (`usr_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES ('13331934111','21120b5b9040b874d4d98edb8eed9626','resident'),('13613005486','c77bfc84f19aeccdbdbf83b4bb3074e9','resident'),('13817633888','309e53b817c8f14df76b2733feb43069','super_admin'),('13912341078','c5728c531abbdf75a5dc8637bed54172','resident'),('13918290305','c6bff625bdb0393992c9d4db0c6bbe45','admin'),('13918988815','a36b598abb934e4528412e5a2127b931','admin'),('17721102878','BCF483D12AE518D413738C4FAC121299','resident'),('17987609876','D6FBD9DA8C02197717C48BA61C46E127','resident'),('18015872567','22638a3131d0f0a7346b178fd29f939c','resident'),('18107620945','6cf1749ae4dbe380d5af6ed7d9ed1b53','resident'),('18221885088','cba8d853ce1628a86170e76e8e401099','admin'),('18826490423','e918945ddf574637d6027c0cf779abe6','resident'),('18913457890','a5031aca1a0b2c9e261d628a3d6d4412','resident'),('18964399185','71C497BC340EFFE11DA0230AD26D353A','resident');
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Activity`
--

DROP TABLE IF EXISTS `Activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Activity` (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `room_id` int NOT NULL,
  `activity_start` datetime NOT NULL,
  `activity_end` datetime NOT NULL,
  `resident_id` int NOT NULL,
  `admin_id` int NOT NULL,
  `activity_usage` char(100) NOT NULL,
  `status` char(10) NOT NULL,
  PRIMARY KEY (`activity_id`),
  KEY `room_id` (`room_id`),
  KEY `resident_id` (`resident_id`),
  CONSTRAINT `Activity_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `Room` (`room_id`),
  CONSTRAINT `Activity_ibfk_2` FOREIGN KEY (`resident_id`) REFERENCES `Resident` (`resident_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Activity`
--

LOCK TABLES `Activity` WRITE;
/*!40000 ALTER TABLE `Activity` DISABLE KEYS */;
INSERT INTO `Activity` VALUES (1,1,'2020-07-21 15:00:00','2020-07-21 17:00:00',1,2,'羽毛球','待处理'),(2,2,'2020-07-23 15:00:00','2020-07-23 17:00:00',1,2,'羽毛球','已拒绝'),(3,2,'2020-07-25 08:00:00','2020-07-25 10:00:00',3,1,'麻将','已通过'),(4,4,'2020-07-27 10:00:00','2020-07-27 12:00:00',2,1,'麻将','审核中');
/*!40000 ALTER TABLE `Activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Admin`
--

DROP TABLE IF EXISTS `Admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_name` char(10) NOT NULL,
  `admin_sex` char(5) DEFAULT NULL,
  `admin_tele` char(11) NOT NULL,
  PRIMARY KEY (`admin_id`),
  KEY `admin_tele` (`admin_tele`),
  CONSTRAINT `Admin_ibfk_1` FOREIGN KEY (`admin_tele`) REFERENCES `Account` (`usr_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admin`
--

LOCK TABLES `Admin` WRITE;
/*!40000 ALTER TABLE `Admin` DISABLE KEYS */;
INSERT INTO `Admin` VALUES (1,'陈迪茂','男','13918290305'),(2,'马小红','女','13918988815'),(3,'张心颖','女','13817633888'),(4,'吴宝钢','男','18221885088');
/*!40000 ALTER TABLE `Admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Box`
--

DROP TABLE IF EXISTS `Box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Box` (
  `box_id` int NOT NULL AUTO_INCREMENT,
  `box_time` datetime NOT NULL,
  `resident_id` int NOT NULL,
  `box_title` char(20) NOT NULL,
  `box_content` char(200) DEFAULT NULL,
  `box_status` char(4) NOT NULL,
  PRIMARY KEY (`box_id`),
  KEY `resident_id` (`resident_id`),
  CONSTRAINT `Box_ibfk_1` FOREIGN KEY (`resident_id`) REFERENCES `Resident` (`resident_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Box`
--

LOCK TABLES `Box` WRITE;
/*!40000 ALTER TABLE `Box` DISABLE KEYS */;
INSERT INTO `Box` VALUES (1,'2020-07-21 15:00:00',2,'物业维修太慢','物业维修太慢','未处理'),(2,'2020-07-22 16:00:00',3,'草坪杂乱','草坪杂乱','已查看');
/*!40000 ALTER TABLE `Box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Notice`
--

DROP TABLE IF EXISTS `Notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT,
  `notice_time` datetime NOT NULL,
  `admin_id` int NOT NULL,
  `notice_title` char(50) NOT NULL,
  `notice_content` text,
  `type` char(10) NOT NULL,
  PRIMARY KEY (`notice_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `Notice_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `Admin` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notice`
--

LOCK TABLES `Notice` WRITE;
/*!40000 ALTER TABLE `Notice` DISABLE KEYS */;
INSERT INTO `Notice` VALUES (1,'2020-01-01 15:01:00',1,'官元峁社区开展“铭记红色初心 传承红色精神”庆“八一”主题系列活动','为庆祝中国人民解放军建军93周年，进一步加强军民融合，弘扬革命精神。7月30日，怀远街道官元峁社区联合双报到单位区政法委组织辖区退役军人参观档案馆。','01'),(2,'2020-02-01 15:22:00',2,'古城路社区携手双退所开展“致敬八一 不忘初心” 建军节座谈会','建军节来临之际，为了进一步加强军民团结，7月29日上午，古城路社区携手双退所召开了“致敬八一  不忘初心”建军节座谈会。会上邀请了辖区内10名退伍军人代表参会，共叙军民鱼水情。退伍军人事务局副局长曹国栋、双退所副所长李艳玲参加了座谈。','01'),(3,'2020-02-05 08:24:17',2,'关爱生命健康 共建美好社区','为了进一步提高居民的疾病防范意识，养成良好的卫生行为习惯，近日，崇德路街道办怀民社区特携共建单位纪工委、流动办以及和美物业公司共同举办健康知识讲座。','01'),(4,'2020-07-23 15:59:01',2,'古城路社区携手新华书店开办书法公益兴趣班','7月22日，“双报到”单位新华书店与怀远街道古城路社区联合启动公益书法兴趣班，出席“开班仪式”的有新华书店副经理贾鹏、新华书店办公室主任顾雨芳、怀远街道办书记赵春荣、书法老师苗正英、古城路社区书记陈宏雄以及古城路社区主任杨进花。','01'),(5,'2020-07-21 15:55:55',4,'崇德路街道紫瑞社区落成揭牌仪式举行','是否要进行社区落成揭牌仪式','02');
/*!40000 ALTER TABLE `Notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Repair`
--

DROP TABLE IF EXISTS `Repair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Repair` (
  `repair_id` int NOT NULL AUTO_INCREMENT,
  `repair_time` datetime NOT NULL,
  `resident_id` int NOT NULL,
  `admin_id` int NOT NULL,
  `repair_address` char(20) NOT NULL,
  `repair_content` char(100) NOT NULL,
  `repair_status` char(20) NOT NULL,
  PRIMARY KEY (`repair_id`),
  KEY `resident_id` (`resident_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `Repair_ibfk_1` FOREIGN KEY (`resident_id`) REFERENCES `Resident` (`resident_id`),
  CONSTRAINT `Repair_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `Admin` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Repair`
--

LOCK TABLES `Repair` WRITE;
/*!40000 ALTER TABLE `Repair` DISABLE KEYS */;
INSERT INTO `Repair` VALUES (1,'2020-07-21 15:55:55',2,1,'17栋','5楼楼梯扶手毁坏','待处理'),(2,'2020-06-24 15:33:12',1,3,'5栋','3楼感应灯不亮','待处理'),(3,'2020-06-18 17:14:48',2,1,'草坪','草坪草枯死','处理中'),(4,'2020-02-21 13:30:26',3,2,'大门','感应门不灵敏','处理完毕');
/*!40000 ALTER TABLE `Repair` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Resident`
--

DROP TABLE IF EXISTS `Resident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Resident` (
  `resident_id` int NOT NULL AUTO_INCREMENT,
  `resident_name` char(10) NOT NULL,
  `resident_tele` char(11) NOT NULL,
  `resident_address` char(20) NOT NULL,
  `resident_sex` char(5) DEFAULT NULL,
  PRIMARY KEY (`resident_id`),
  KEY `resident_tele` (`resident_tele`),
  CONSTRAINT `Resident_ibfk_1` FOREIGN KEY (`resident_tele`) REFERENCES `Account` (`usr_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Resident`
--

LOCK TABLES `Resident` WRITE;
/*!40000 ALTER TABLE `Resident` DISABLE KEYS */;
INSERT INTO `Resident` VALUES (1,'李明','13613005486','17栋305号','男'),(2,'刘晓明','18913457890','18栋407号','男'),(3,'张颖','18826490423','8栋207号','女'),(4,'刘晶晶','13331934111','8栋101号','女'),(5,'刘成刚','18015872567','15栋105号','男'),(6,'李二丽','18107620945','12栋307号','女'),(7,'张晓峰','13912341078','5栋201号','男'),(9,'张赛','17721102878','17栋203','男'),(10,'张丽','17987609876','17栋605号','女'),(13,'赵无筵','18964399185','17栋101号','女');
/*!40000 ALTER TABLE `Resident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Room` (
  `room_id` int NOT NULL AUTO_INCREMENT,
  `room_usage` char(50) NOT NULL,
  `room_num` int NOT NULL,
  `room_address` char(8) NOT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES (1,'乒乓室',20,'3幢204'),(2,'麻将室',25,'3幢205'),(3,'羽毛球室',30,'3幢206'),(4,'棋牌室',20,'3幢207');
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VoteDetails`
--

DROP TABLE IF EXISTS `VoteDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `VoteDetails` (
  `VD_id` int NOT NULL AUTO_INCREMENT,
  `resident_id` int NOT NULL,
  `vote_id` int NOT NULL,
  `is_agree` char(2) NOT NULL,
  PRIMARY KEY (`VD_id`),
  KEY `resident_id` (`resident_id`),
  KEY `vote_id` (`vote_id`),
  CONSTRAINT `VoteDetails_ibfk_1` FOREIGN KEY (`resident_id`) REFERENCES `Resident` (`resident_id`),
  CONSTRAINT `VoteDetails_ibfk_2` FOREIGN KEY (`vote_id`) REFERENCES `Notice` (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VoteDetails`
--

LOCK TABLES `VoteDetails` WRITE;
/*!40000 ALTER TABLE `VoteDetails` DISABLE KEYS */;
INSERT INTO `VoteDetails` VALUES (1,1,5,'1'),(2,3,5,'1'),(3,2,5,'0'),(4,7,5,'1'),(5,4,5,'0');
/*!40000 ALTER TABLE `VoteDetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-10 10:02:11
