-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema admission_servlets
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `admission_servlets` ;

-- -----------------------------------------------------
-- Schema admission_servlets
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `admission_servlets` DEFAULT CHARACTER SET utf8 ;
USE `admission_servlets` ;


-- -----------------------------------------------------
-- Table `admission_servlets`.`speciality`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admission_servlets`.`speciality` ;

CREATE TABLE IF NOT EXISTS `admission_servlets`.`speciality` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `admission_servlets`.`subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admission_servlets`.`subject` ;

CREATE TABLE IF NOT EXISTS `admission_servlets`.`subject` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `admission_servlets`.`speciality_subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admission_servlets`.`speciality_subject` ;

CREATE TABLE IF NOT EXISTS `admission_servlets`.`speciality_subject` (
  `speciality_id` BIGINT(20) NOT NULL,
  `subject_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`speciality_id`, `subject_id`),
  INDEX `FKbvlrspwe5cj3t0fde37px7lyu` (`subject_id` ASC),
  CONSTRAINT `FKbvlrspwe5cj3t0fde37px7lyu`
    FOREIGN KEY (`subject_id`)
    REFERENCES `admission_servlets`.`subject` (`id`),
  CONSTRAINT `FKi1h4260k9cra32fo5a8u3shpf`
    FOREIGN KEY (`speciality_id`)
    REFERENCES `admission_servlets`.`speciality` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `admission_servlets`.`usr`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admission_servlets`.`usr` ;

CREATE TABLE IF NOT EXISTS `admission_servlets`.`usr` (
  `id` BIGINT(20) NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `speciality_id` BIGINT(20) NULL DEFAULT NULL,
  `role` ENUM('ADMIN', 'USER', 'GUEST') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UKdfui7gxngrgwn9ewee3ogtgym` (`username` ASC),
  INDEX `FKpxxdan81grbl691clwm0i4ux7` (`speciality_id` ASC),
  CONSTRAINT `FKpxxdan81grbl691clwm0i4ux7`
    FOREIGN KEY (`speciality_id`)
    REFERENCES `admission_servlets`.`speciality` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `admission_servlets`.`subject_grade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admission_servlets`.`subject_grade` ;

CREATE TABLE IF NOT EXISTS `admission_servlets`.`subject_grade` (
  `grade` INT(11) NOT NULL,
  `subject_id` BIGINT(20) NOT NULL,
  `user_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`subject_id`, `user_id`),
  INDEX `FK947k4ym625vhlb4afylpuul8i` (`user_id` ASC),
  CONSTRAINT `FK7ndcktiy0re9epm98or2yu0f9`
    FOREIGN KEY (`subject_id`)
    REFERENCES `admission_servlets`.`subject` (`id`),
  CONSTRAINT `FK947k4ym625vhlb4afylpuul8i`
    FOREIGN KEY (`user_id`)
    REFERENCES `admission_servlets`.`usr` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `admission_servlets`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admission_servlets`.`user_role` ;

CREATE TABLE IF NOT EXISTS `admission_servlets`.`user_role` (
  `user_id` BIGINT(20) NOT NULL,
  `role_name` VARCHAR(255) NULL DEFAULT NULL,
  INDEX `FKfpm8swft53ulq2hl11yplpr5` (`user_id` ASC),
  CONSTRAINT `FKfpm8swft53ulq2hl11yplpr5`
    FOREIGN KEY (`user_id`)
    REFERENCES `admission_servlets`.`usr` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
