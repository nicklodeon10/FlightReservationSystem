-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.17 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for frs_sql
CREATE DATABASE IF NOT EXISTS `frs_sql` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `frs_sql`;

-- Dumping structure for table frs_sql.airport
CREATE TABLE IF NOT EXISTS `airport` (
  `airport_name` varchar(255) NOT NULL,
  `airport_location` varchar(255) NOT NULL,
  `airport_code` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`airport_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table frs_sql.airport: ~7 rows (approximately)
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT IGNORE INTO `airport` (`airport_name`, `airport_location`, `airport_code`) VALUES
	('Kempegowda International Airport', 'Bengaluru', 'BLR'),
	('Indira Gandhi International Airport', 'Delhi', 'DEL'),
	('Rajiv Gandhi International Airport', 'Hyderabad', 'HYD'),
	('Chandigarh International Airport ', 'Chandigarh', 'IXC'),
	('Chennai International Airport ', 'Chennai', 'MAA'),
	('Chhatrapati Shivaji International Airport', 'Mumbai', 'MUM'),
	('Srinagar International Airport', 'Srinagar', 'SXR');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;

-- Dumping structure for table frs_sql.booking
CREATE TABLE IF NOT EXISTS `booking` (
  `booking_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `booking_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ticket_cost` double NOT NULL,
  `flight_number` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `passenger_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000000 DEFAULT CHARSET=latin1;

-- Dumping data for table frs_sql.booking: ~0 rows (approximately)
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;

-- Dumping structure for table frs_sql.flight
CREATE TABLE IF NOT EXISTS `flight` (
  `flight_number` bigint(20) NOT NULL AUTO_INCREMENT,
  `carrier_name` varchar(255) NOT NULL,
  `flight_model` varchar(255) NOT NULL,
  `seat_capacity` int(11) NOT NULL,
  PRIMARY KEY (`flight_number`)
) ENGINE=InnoDB AUTO_INCREMENT=12346 DEFAULT CHARSET=latin1;

-- Dumping data for table frs_sql.flight: ~5 rows (approximately)
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT IGNORE INTO `flight` (`flight_number`, `carrier_name`, `flight_model`, `seat_capacity`) VALUES
	(1, 'indigo', 'Boeing 747', 100),
	(2, 'indigo', 'Boeing 767', 170),
	(3, 'airindia', 'A330', 150),
	(4, 'airindia', 'A380', 180),
	(12345, 'BestCarrier', 'BestModel111', 50);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;

-- Dumping structure for table frs_sql.passenger
CREATE TABLE IF NOT EXISTS `passenger` (
  `pnr_number` bigint(20) NOT NULL AUTO_INCREMENT,
  `passenger_name` varchar(255) NOT NULL,
  `passenger_age` int(11) NOT NULL,
  `passenger_uin` bigint(20) NOT NULL,
  `booking_id` bigint(20) DEFAULT NULL,
  `flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`pnr_number`),
  KEY `booking_id` (`booking_id`),
  CONSTRAINT `passenger_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000000 DEFAULT CHARSET=latin1;

-- Dumping data for table frs_sql.passenger: ~0 rows (approximately)
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;

-- Dumping structure for table frs_sql.schedule
CREATE TABLE IF NOT EXISTS `schedule` (
  `source_airport` varchar(255) NOT NULL DEFAULT '',
  `destination_airport` varchar(255) NOT NULL DEFAULT '',
  `arrival_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `departure_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `airport_code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`source_airport`,`destination_airport`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table frs_sql.schedule: ~4 rows (approximately)
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT IGNORE INTO `schedule` (`source_airport`, `destination_airport`, `arrival_time`, `departure_time`, `airport_code`) VALUES
	('MUM', 'BLR', '2019-10-11 09:00:00', '2019-10-11 11:00:00', NULL),
	('MUM', 'HYD', '2019-10-11 08:00:00', '2019-10-11 09:00:00', NULL),
	('MUM', 'MAA', '2019-10-11 11:00:00', '2019-10-11 13:00:00', NULL),
	('MUM', 'SXR', '2019-10-11 15:00:00', '2019-10-11 17:15:00', NULL);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;

-- Dumping structure for table frs_sql.scheduled_flight
CREATE TABLE IF NOT EXISTS `scheduled_flight` (
  `flight_number` bigint(20) DEFAULT NULL,
  `available_seats` int(11) NOT NULL,
  `source_airport` varchar(255) DEFAULT NULL,
  `destination_airport` varchar(255) DEFAULT NULL,
  KEY `flight_number` (`flight_number`),
  KEY `source_airport` (`source_airport`,`destination_airport`),
  CONSTRAINT `scheduled_flight_ibfk_1` FOREIGN KEY (`flight_number`) REFERENCES `flight` (`flight_number`),
  CONSTRAINT `scheduled_flight_ibfk_2` FOREIGN KEY (`source_airport`, `destination_airport`) REFERENCES `schedule` (`source_airport`, `destination_airport`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table frs_sql.scheduled_flight: ~0 rows (approximately)
/*!40000 ALTER TABLE `scheduled_flight` DISABLE KEYS */;
/*!40000 ALTER TABLE `scheduled_flight` ENABLE KEYS */;

-- Dumping structure for table frs_sql.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_type` varchar(10) NOT NULL,
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_phone` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=latin1;

-- Dumping data for table frs_sql.user: ~1 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT IGNORE INTO `user` (`user_type`, `user_id`, `user_name`, `user_password`, `user_phone`, `email`, `flag`) VALUES
	('admin', 99999, 'ADMIN', 'ADMIN', 9999999999, 'admin@frs.com', 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
