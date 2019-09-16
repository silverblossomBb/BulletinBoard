-- --------------------------------------------------------
-- 호스트:                          mariadb
-- 서버 버전:                        10.4.7-MariaDB - MariaDB Server
-- 서버 OS:                        Linux
-- HeidiSQL 버전:                  10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- OAuth 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `OAuth` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `OAuth`;

-- 테이블 OAuth.download 구조 내보내기
CREATE TABLE IF NOT EXISTS `download` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(12) NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `timeLog` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 OAuth.loginInfo 구조 내보내기
CREATE TABLE IF NOT EXISTS `loginInfo` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(12) NOT NULL,
  `name` varchar(12) NOT NULL,
  `timeLog` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 OAuth.notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `notice` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(12) NOT NULL,
  `timeLog` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `delYn` char(1) DEFAULT 'N',
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 OAuth.upload 구조 내보내기
CREATE TABLE IF NOT EXISTS `upload` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `noticeNo` int(11) NOT NULL,
  `id` varchar(12) NOT NULL,
  `name` varchar(12) NOT NULL,
  `originName` varchar(255) NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `timeLog` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
