CREATE DATABASE  IF NOT EXISTS `monopoly` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `monopoly`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: monopoly
-- ------------------------------------------------------
-- Server version	5.5.28-log

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
-- Table structure for table `app_role`
--

DROP TABLE IF EXISTS `app_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_role`
--

LOCK TABLES `app_role` WRITE;
/*!40000 ALTER TABLE `app_role` DISABLE KEYS */;
INSERT INTO `app_role` (`id`, `description`, `name`, `version`) VALUES (1,'user role','USER_ROLE',NULL);
INSERT INTO `app_role` (`id`, `description`, `name`, `version`) VALUES (3,'admin role','ROLE_ADMIN',0);
/*!40000 ALTER TABLE `app_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activation_code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `activation_date` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `locked` bit(1) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `nickname` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8_bin NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` (`id`, `activation_code`, `activation_date`, `email`, `enabled`, `locked`, `name`, `nickname`, `password`, `surname`, `version`) VALUES (1,'1957258440','2013-01-03 21:41:28','test1@test.com','','\0','test1','test1','c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec','test1',1);
INSERT INTO `app_user` (`id`, `activation_code`, `activation_date`, `email`, `enabled`, `locked`, `name`, `nickname`, `password`, `surname`, `version`) VALUES (2,'-1656783498','2013-01-04 22:30:45','test2@test.com','','\0','test1','test1','c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec','test1',1);
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user_role`
--

DROP TABLE IF EXISTS `app_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `approle` bigint(20) NOT NULL,
  `appuser` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9CE8F3CCD688422F` (`appuser`),
  KEY `FK9CE8F3CCD6856B85` (`approle`),
  CONSTRAINT `FK9CE8F3CCD6856B85` FOREIGN KEY (`approle`) REFERENCES `app_role` (`id`),
  CONSTRAINT `FK9CE8F3CCD688422F` FOREIGN KEY (`appuser`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user_role`
--

LOCK TABLES `app_user_role` WRITE;
/*!40000 ALTER TABLE `app_user_role` DISABLE KEYS */;
INSERT INTO `app_user_role` (`id`, `version`, `approle`, `appuser`) VALUES (1,NULL,1,1);
INSERT INTO `app_user_role` (`id`, `version`, `approle`, `appuser`) VALUES (2,0,3,1);
/*!40000 ALTER TABLE `app_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `en`
--

DROP TABLE IF EXISTS `en`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `en` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `en`
--

LOCK TABLES `en` WRITE;
/*!40000 ALTER TABLE `en` DISABLE KEYS */;
/*!40000 ALTER TABLE `en` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_chance`
--

DROP TABLE IF EXISTS `game_chance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_chance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) NOT NULL,
  `money_diff` smallint(6) NOT NULL,
  `move` smallint(6) NOT NULL,
  `move_to_class` varchar(255) DEFAULT NULL,
  `stop_turn` smallint(6) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `theme` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE518D021A70D5837` (`theme`),
  CONSTRAINT `FKE518D021A70D5837` FOREIGN KEY (`theme`) REFERENCES `game_theme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_chance`
--

LOCK TABLES `game_chance` WRITE;
/*!40000 ALTER TABLE `game_chance` DISABLE KEYS */;
INSERT INTO `game_chance` (`id`, `message`, `money_diff`, `move`, `move_to_class`, `stop_turn`, `version`, `theme`) VALUES (1,'getmoney',200,0,'',0,0,1);
INSERT INTO `game_chance` (`id`, `message`, `money_diff`, `move`, `move_to_class`, `stop_turn`, `version`, `theme`) VALUES (2,'incometax',-200,0,'',0,0,1);
INSERT INTO `game_chance` (`id`, `message`, `money_diff`, `move`, `move_to_class`, `stop_turn`, `version`, `theme`) VALUES (3,'jail',0,0,'',1,0,1);
INSERT INTO `game_chance` (`id`, `message`, `money_diff`, `move`, `move_to_class`, `stop_turn`, `version`, `theme`) VALUES (4,'parking',0,0,'',0,0,1);
INSERT INTO `game_chance` (`id`, `message`, `money_diff`, `move`, `move_to_class`, `stop_turn`, `version`, `theme`) VALUES (5,'gotojail',0,-20,'',0,0,1);
INSERT INTO `game_chance` (`id`, `message`, `money_diff`, `move`, `move_to_class`, `stop_turn`, `version`, `theme`) VALUES (6,'tax',-75,0,'',0,0,1);
INSERT INTO `game_chance` (`id`, `message`, `money_diff`, `move`, `move_to_class`, `stop_turn`, `version`, `theme`) VALUES (7,'stop',0,0,'',1,1,1);
/*!40000 ALTER TABLE `game_chance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_chest`
--

DROP TABLE IF EXISTS `game_chest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_chest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `money_diff` smallint(6) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `theme` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK38F05874A70D5837` (`theme`),
  CONSTRAINT `FK38F05874A70D5837` FOREIGN KEY (`theme`) REFERENCES `game_theme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_chest`
--

LOCK TABLES `game_chest` WRITE;
/*!40000 ALTER TABLE `game_chest` DISABLE KEYS */;
INSERT INTO `game_chest` (`id`, `message`, `money_diff`, `version`, `theme`) VALUES (1,'getmoney',70,0,1);
/*!40000 ALTER TABLE `game_chest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_estate`
--

DROP TABLE IF EXISTS `game_estate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_estate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `build_cost` smallint(6) NOT NULL,
  `colour` varchar(255) NOT NULL,
  `cost` smallint(6) NOT NULL,
  `house1` smallint(6) NOT NULL,
  `house2` smallint(6) NOT NULL,
  `house3` smallint(6) NOT NULL,
  `house4` smallint(6) NOT NULL,
  `house5` smallint(6) NOT NULL,
  `house_cost` smallint(6) NOT NULL,
  `mortage` smallint(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `rent` smallint(6) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `theme` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE925F839A70D5837` (`theme`),
  CONSTRAINT `FKE925F839A70D5837` FOREIGN KEY (`theme`) REFERENCES `game_theme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_estate`
--

LOCK TABLES `game_estate` WRITE;
/*!40000 ALTER TABLE `game_estate` DISABLE KEYS */;
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (1,50,'purple',60,10,30,90,160,250,50,30,'Mediterranean Avenue',2,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (2,50,'purple',60,20,60,180,320,450,50,30,'Baltic Avenue',4,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (3,50,'blue',100,30,90,270,400,550,50,50,'Oriental Avenue',6,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (4,50,'blue',100,30,90,270,400,550,50,50,'Vermont Avenue',6,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (5,50,'blue',120,40,100,300,450,600,50,60,'Connecticut Avenue',8,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (6,100,'cyan',140,50,150,450,625,750,100,70,'St. Charles Place',10,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (7,100,'cyan',140,50,150,450,625,750,100,70,'States Avenue',10,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (8,100,'cyan',160,60,180,500,700,900,100,80,'Virginia Avenue',12,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (9,100,'orange',180,70,200,550,750,950,100,90,'St. James Place',14,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (10,100,'orange',180,70,200,550,750,950,100,90,'Tennessee Avenue',14,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (11,100,'orange',200,80,220,600,800,1000,100,100,'New York Avenue',16,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (12,150,'red',220,90,250,700,875,1050,150,110,'Kentucky Avenue',18,1,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (13,150,'red',220,90,250,700,875,1050,150,110,'Indiana Avenue',18,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (14,150,'red',240,100,300,750,925,1100,150,120,'Illinois Avenue',20,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (15,150,'yellow',260,110,330,800,975,1150,150,130,'Atlantic Avenue',22,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (16,150,'yellow',260,110,330,800,975,1150,150,130,'Ventnor Avenue',22,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (17,150,'yellow',280,120,360,850,1025,1200,150,140,'Marvin Gardens',24,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (18,200,'green',300,130,390,900,1100,1275,200,150,'Pacific Avenue',26,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (19,200,'green',300,130,390,900,1100,1275,200,150,'North Carolina Avenue',26,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (20,200,'green',320,150,450,1000,1200,1400,200,160,'Pennsylvania Avenue',28,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (21,200,'black',350,175,500,1100,1300,1500,200,175,'Park Place',35,0,1);
INSERT INTO `game_estate` (`id`, `build_cost`, `colour`, `cost`, `house1`, `house2`, `house3`, `house4`, `house5`, `house_cost`, `mortage`, `name`, `rent`, `version`, `theme`) VALUES (22,200,'black',400,200,600,1400,1700,2000,200,200,'Boardwalk',50,0,1);
/*!40000 ALTER TABLE `game_estate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_railroad`
--

DROP TABLE IF EXISTS `game_railroad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_railroad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cost` smallint(6) NOT NULL,
  `mortage` smallint(6) NOT NULL,
  `rent1` smallint(6) NOT NULL,
  `rent2` smallint(6) NOT NULL,
  `rent3` smallint(6) NOT NULL,
  `rent4` smallint(6) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `theme` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB39CE75FA70D5837` (`theme`),
  CONSTRAINT `FKB39CE75FA70D5837` FOREIGN KEY (`theme`) REFERENCES `game_theme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_railroad`
--

LOCK TABLES `game_railroad` WRITE;
/*!40000 ALTER TABLE `game_railroad` DISABLE KEYS */;
INSERT INTO `game_railroad` (`id`, `cost`, `mortage`, `rent1`, `rent2`, `rent3`, `rent4`, `version`, `theme`, `name`) VALUES (1,200,100,25,50,100,200,0,1,'South Railroad');
/*!40000 ALTER TABLE `game_railroad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_statistics`
--

DROP TABLE IF EXISTS `game_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `winner` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8E6C2F90D6D7D502` (`winner`),
  CONSTRAINT `FK8E6C2F90D6D7D502` FOREIGN KEY (`winner`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_statistics`
--

LOCK TABLES `game_statistics` WRITE;
/*!40000 ALTER TABLE `game_statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `game_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_statistics_players`
--

DROP TABLE IF EXISTS `game_statistics_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_statistics_players` (
  `game_statistics` bigint(20) NOT NULL,
  `players` bigint(20) NOT NULL,
  PRIMARY KEY (`game_statistics`,`players`),
  KEY `FKC8700803E8609675` (`players`),
  KEY `FKC87008039395932E` (`game_statistics`),
  CONSTRAINT `FKC87008039395932E` FOREIGN KEY (`game_statistics`) REFERENCES `game_statistics` (`id`),
  CONSTRAINT `FKC8700803E8609675` FOREIGN KEY (`players`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_statistics_players`
--

LOCK TABLES `game_statistics_players` WRITE;
/*!40000 ALTER TABLE `game_statistics_players` DISABLE KEYS */;
/*!40000 ALTER TABLE `game_statistics_players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_theme`
--

DROP TABLE IF EXISTS `game_theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_theme` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_theme`
--

LOCK TABLES `game_theme` WRITE;
/*!40000 ALTER TABLE `game_theme` DISABLE KEYS */;
INSERT INTO `game_theme` (`id`, `name`, `version`) VALUES (1,'standard',0);
INSERT INTO `game_theme` (`id`, `name`, `version`) VALUES (2,'custom 1',0);
/*!40000 ALTER TABLE `game_theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_utility`
--

DROP TABLE IF EXISTS `game_utility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_utility` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cost` smallint(6) NOT NULL,
  `mortage` smallint(6) NOT NULL,
  `rent_mul1` tinyint(4) NOT NULL,
  `rent_mul2` tinyint(4) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `theme` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8B1B721FA70D5837` (`theme`),
  CONSTRAINT `FK8B1B721FA70D5837` FOREIGN KEY (`theme`) REFERENCES `game_theme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_utility`
--

LOCK TABLES `game_utility` WRITE;
/*!40000 ALTER TABLE `game_utility` DISABLE KEYS */;
INSERT INTO `game_utility` (`id`, `cost`, `mortage`, `rent_mul1`, `rent_mul2`, `version`, `theme`, `name`) VALUES (1,150,75,4,10,1,1,'Electric Company');
INSERT INTO `game_utility` (`id`, `cost`, `mortage`, `rent_mul1`, `rent_mul2`, `version`, `theme`, `name`) VALUES (2,150,75,4,10,1,1,'Water Works');
/*!40000 ALTER TABLE `game_utility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_game`
--

DROP TABLE IF EXISTS `history_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history_game` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8_bin NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `history_game_players`
--

DROP TABLE IF EXISTS `history_game_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history_game_players` (
  `history_game` bigint(20) NOT NULL,
  `players` bigint(20) NOT NULL,
  PRIMARY KEY (`history_game`,`players`),
  KEY `FKDA153830B229DC3E` (`players`),
  KEY `FKDA15383061EF971A` (`history_game`),
  CONSTRAINT `FKDA15383061EF971A` FOREIGN KEY (`history_game`) REFERENCES `history_game` (`id`),
  CONSTRAINT `FKDA153830B229DC3E` FOREIGN KEY (`players`) REFERENCES `history_player` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `history_player`
--

DROP TABLE IF EXISTS `history_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history_player` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `capital` int(11) NOT NULL,
  `kicked` bit(1) DEFAULT NULL,
  `money` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `player_id` bigint(20) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'monopoly'
--

--
-- Dumping routines for database 'monopoly'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-01-29 22:24:25
