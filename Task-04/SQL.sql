DROP DATABASE IF EXISTS internetstore;

CREATE DATABASE internetstore
CHARACTER SET utf8mb4
COLLATE utf8mb4_0900_ai_ci;

USE internetstore;

CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  firstName varchar(255) NOT NULL,
  lastName varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 19,
AVG_ROW_LENGTH = 1024,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;
