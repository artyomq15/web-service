-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema touragency
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema touragency
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `touragency` DEFAULT CHARACTER SET utf8 ;
USE `touragency` ;

-- -----------------------------------------------------
-- Table `touragency`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `touragency`.`country` (
  `country_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `country_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`country_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `touragency`.`tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `touragency`.`tour` (
  `tour_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `tour_description` LONGTEXT NOT NULL,
  `tour_date` TIMESTAMP NOT NULL,
  `tour_cost` DECIMAL NOT NULL,
  `country_country_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`tour_id`),
  INDEX `fk_tour_country1_idx` (`country_country_id` ASC),
  CONSTRAINT `fk_tour_country1`
    FOREIGN KEY (`country_country_id`)
    REFERENCES `touragency`.`country` (`country_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `touragency`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `touragency`.`client` (
  `client_id` BIGINT(20) NOT NULL,
  `client_username` VARCHAR(45) NOT NULL,
  `client_password` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `client_username_UNIQUE` (`client_username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `touragency`.`tour_has_client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `touragency`.`tour_has_client` (
  `tour_tour_id` BIGINT(20) NOT NULL,
  `client_client_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`tour_tour_id`, `client_client_id`),
  INDEX `fk_tour_has_client_client1_idx` (`client_client_id` ASC),
  INDEX `fk_tour_has_client_tour1_idx` (`tour_tour_id` ASC),
  CONSTRAINT `fk_tour_has_client_tour1`
    FOREIGN KEY (`tour_tour_id`)
    REFERENCES `touragency`.`tour` (`tour_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tour_has_client_client1`
    FOREIGN KEY (`client_client_id`)
    REFERENCES `touragency`.`client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
