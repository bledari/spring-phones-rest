DROP DATABASE IF EXISTS phones;
CREATE DATABASE IF NOT EXISTS phones;

ALTER DATABASE phones
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

USE phones;

CREATE TABLE IF NOT EXISTS phone_api_info (
  id            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  phone_id      VARCHAR(200) UNIQUE,
  brand         VARCHAR(200),
  technology    VARCHAR(200),
  gprs          VARCHAR(200),
  edge          VARCHAR(200),
  announced     VARCHAR(200),
  status        VARCHAR(200),
  dimensions    VARCHAR(200),
  weight        VARCHAR(200),
  sim           VARCHAR(200),
  display_type  VARCHAR(200),
  display_size  VARCHAR(200),
  INDEX(phone_id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS phones (
  id            INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  phone_id      INT UNSIGNED NOT NULL,
  availability  BOOLEAN,
  booked_at     DATETIME(0),
  booker        VARCHAR(200),
  FOREIGN KEY (phone_id) REFERENCES phone_api_info(id),
  INDEX(phone_id)
) engine=InnoDB;
