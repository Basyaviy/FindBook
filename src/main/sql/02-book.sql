CREATE DATABASE  IF NOT EXISTS `my_schema`;
USE `my_schema`;

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `book_title` varchar(100) DEFAULT NULL,
  `genre` varchar(45) DEFAULT NULL,
  `annotation` varchar(200) DEFAULT NULL,
  `keywords` varchar(200) DEFAULT NULL,
  `str_date` varchar(20) DEFAULT NULL,
  `lang` varchar(20) DEFAULT NULL,
  `path` varchar(400) DEFAULT NULL,
  `file_name` varchar(200) DEFAULT NULL,
  `size` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
 