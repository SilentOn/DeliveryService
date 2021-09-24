-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema delivery_service
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema delivery_service
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `delivery_service` DEFAULT CHARACTER SET utf8 ;
USE `delivery_service` ;

-- -----------------------------------------------------
-- Table `delivery_service`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`role` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` ENUM("user", "manager") NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`user` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phone_number` VARCHAR(19) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role1_idx` (`role_id` ASC) VISIBLE,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE,
  CONSTRAINT `fk_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `delivery_service`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `delivery_service`.`user_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`user_details` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`user_details` (
  `id` INT NOT NULL,
  `email` VARCHAR(255) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_details_user1_idx` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_user_details_user1`
    FOREIGN KEY (`id`)
    REFERENCES `delivery_service`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`region`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`region` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`region` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `region` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `region_UNIQUE` (`region` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`city` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `region_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_city_region1_idx` (`region_id` ASC) VISIBLE,
  CONSTRAINT `fk_city_region1`
    FOREIGN KEY (`region_id`)
    REFERENCES `delivery_service`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`address` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `city_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_city1_idx` (`city_id` ASC) VISIBLE,
  CONSTRAINT `fk_address_city1`
    FOREIGN KEY (`city_id`)
    REFERENCES `delivery_service`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`tariff_zone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`tariff_zone` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`tariff_zone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tariff_zone` ENUM("1", "2", "3", "4", "city", "region") NOT NULL,
  `term` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `tariff_zone_UNIQUE` (`tariff_zone` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`region_has_region`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`region_has_region` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`region_has_region` (
  `region_1_id` INT NOT NULL,
  `region_2_id` INT NOT NULL,
  `tariff_zone_id` INT NOT NULL,
  PRIMARY KEY (`region_1_id`, `region_2_id`),
  INDEX `fk_region_has_region_region2_idx` (`region_2_id` ASC) VISIBLE,
  INDEX `fk_region_has_region_region1_idx` (`region_1_id` ASC) VISIBLE,
  INDEX `fk_region_has_region_tariff_zone1_idx` (`tariff_zone_id` ASC) VISIBLE,
  CONSTRAINT `fk_region_has_region_region1`
    FOREIGN KEY (`region_1_id`)
    REFERENCES `delivery_service`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_region_has_region_region2`
    FOREIGN KEY (`region_2_id`)
    REFERENCES `delivery_service`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_region_has_region_tariff_zone1`
    FOREIGN KEY (`tariff_zone_id`)
    REFERENCES `delivery_service`.`tariff_zone` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`invoice_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`invoice_status` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`invoice_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` ENUM('new', 'canceled', 'processed', 'paid', 'delivered') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`invoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`invoice` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`invoice` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `address_from_id` INT NOT NULL,
  `address_to_id` INT NOT NULL,
  `tariff_zone_id` INT NOT NULL,
  `processed_by_id` INT NULL,
  `invoice_status_id` INT NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `estimate` DOUBLE NOT NULL,
  `delivery_term` INT NOT NULL,
  `total_weight` FLOAT NOT NULL,
  `total_volume` FLOAT NOT NULL,
  `number_of_cargo` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_receipt_status1_idx` (`invoice_status_id` ASC) VISIBLE,
  INDEX `fk_receipt_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_receipt_user2_idx` (`processed_by_id` ASC) VISIBLE,
  INDEX `fk_receipt_address1_idx` (`address_from_id` ASC) VISIBLE,
  INDEX `fk_receipt_address2_idx` (`address_to_id` ASC) VISIBLE,
  INDEX `fk_receipt_tariff_zone1_idx` (`tariff_zone_id` ASC) VISIBLE,
  CONSTRAINT `fk_receipt_invoice_status1`
    FOREIGN KEY (`invoice_status_id`)
    REFERENCES `delivery_service`.`invoice_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receipt_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `delivery_service`.`user` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receipt_user2`
    FOREIGN KEY (`processed_by_id`)
    REFERENCES `delivery_service`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receipt_address1`
    FOREIGN KEY (`address_from_id`)
    REFERENCES `delivery_service`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receipt_address2`
    FOREIGN KEY (`address_to_id`)
    REFERENCES `delivery_service`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receipt_tariff_zone1`
    FOREIGN KEY (`tariff_zone_id`)
    REFERENCES `delivery_service`.`tariff_zone` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`cargo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`cargo` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`cargo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` ENUM('parcels and cargoes', 'documents') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`invoice_has_cargo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`invoice_has_cargo` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`invoice_has_cargo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `invoice_id` INT NOT NULL,
  `cargo_id` INT NOT NULL,
  `weight` FLOAT NOT NULL,
  `length` FLOAT NOT NULL,
  `width` FLOAT NOT NULL,
  `height` FLOAT NOT NULL,
  INDEX `fk_receipt_has_cargo_cargo1_idx` (`cargo_id` ASC) VISIBLE,
  INDEX `fk_receipt_has_cargo_receipt1_idx` (`invoice_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_invoice_has_cargo_invoice1`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `delivery_service`.`invoice` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_has_cargo_cargo1`
    FOREIGN KEY (`cargo_id`)
    REFERENCES `delivery_service`.`cargo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`receipt_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`receipt_status` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`receipt_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` ENUM('not paid', 'paid') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`receipt` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`receipt` (
  `id` INT NOT NULL,
  `to_pay` DOUBLE NOT NULL,
  `receipt_status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_receipt_invoice1_idx` (`id` ASC) VISIBLE,
  INDEX `fk_receipt_receipt_status1_idx` (`receipt_status_id` ASC) VISIBLE,
  CONSTRAINT `fk_receipt_invoice1`
    FOREIGN KEY (`id`)
    REFERENCES `delivery_service`.`invoice` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receipt_receipt_status1`
    FOREIGN KEY (`receipt_status_id`)
    REFERENCES `delivery_service`.`receipt_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`weight_tariff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`weight_tariff` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`weight_tariff` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `weight` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `weight_UNIQUE` (`weight` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`tariff_zone_has_weight_tariff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`tariff_zone_has_weight_tariff` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`tariff_zone_has_weight_tariff` (
  `tariff_zone_id` INT NOT NULL,
  `weight_tariff_id` INT NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`tariff_zone_id`, `weight_tariff_id`),
  INDEX `fk_tariff_zone_has_weight_tariff_weight_tariff1_idx` (`weight_tariff_id` ASC) VISIBLE,
  INDEX `fk_tariff_zone_has_weight_tariff_tariff_zone1_idx` (`tariff_zone_id` ASC) VISIBLE,
  CONSTRAINT `fk_tariff_zone_has_weight_tariff_tariff_zone1`
    FOREIGN KEY (`tariff_zone_id`)
    REFERENCES `delivery_service`.`tariff_zone` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tariff_zone_has_weight_tariff_weight_tariff1`
    FOREIGN KEY (`weight_tariff_id`)
    REFERENCES `delivery_service`.`weight_tariff` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`volumetric_tariff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `delivery_service`.`volumetric_tariff` ;

CREATE TABLE IF NOT EXISTS `delivery_service`.`volumetric_tariff` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `volume` INT NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `volume_UNIQUE` (`volume` ASC) VISIBLE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
