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
-- Table `speciality`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `speciality` ;

CREATE TABLE IF NOT EXISTS `speciality` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `subject` ;

CREATE TABLE IF NOT EXISTS `subject` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `speciality_subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `speciality_subject` ;

CREATE TABLE IF NOT EXISTS `speciality_subject` (
  `speciality_id` BIGINT(20) NOT NULL,
  `subject_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`speciality_id`, `subject_id`),
  INDEX `FKbvlrspwe5cj3t0fde37px7lyu` (`subject_id` ASC),
  CONSTRAINT `FKbvlrspwe5cj3t0fde37px7lyu`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subject` (`id`),
  CONSTRAINT `FKi1h4260k9cra32fo5a8u3shpf`
    FOREIGN KEY (`speciality_id`)
    REFERENCES `speciality` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usr`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usr` ;

CREATE TABLE IF NOT EXISTS `usr` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `speciality_id` BIGINT(20) NULL DEFAULT NULL,
  `role` ENUM('ADMIN', 'USER', 'GUEST') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UKdfui7gxngrgwn9ewee3ogtgym` (`username` ASC),
  INDEX `FKpxxdan81grbl691clwm0i4ux7` (`speciality_id` ASC),
  CONSTRAINT `FKpxxdan81grbl691clwm0i4ux7`
    FOREIGN KEY (`speciality_id`)
    REFERENCES `speciality` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `subject_grade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `subject_grade` ;

CREATE TABLE IF NOT EXISTS `subject_grade` (
  `grade` INT(11) NOT NULL,
  `subject_id` BIGINT(20) NOT NULL,
  `user_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`subject_id`, `user_id`),
  INDEX `FK947k4ym625vhlb4afylpuul8i` (`user_id` ASC),
  CONSTRAINT `FK7ndcktiy0re9epm98or2yu0f9`
    FOREIGN KEY (`subject_id`)
    REFERENCES `subject` (`id`),
  CONSTRAINT `FK947k4ym625vhlb4afylpuul8i`
    FOREIGN KEY (`user_id`)
    REFERENCES `usr` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_role` ;

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` BIGINT(20) NOT NULL,
  `role_name` VARCHAR(255) NULL DEFAULT NULL,
  INDEX `FKfpm8swft53ulq2hl11yplpr5` (`user_id` ASC),
  CONSTRAINT `FKfpm8swft53ulq2hl11yplpr5`
    FOREIGN KEY (`user_id`)
    REFERENCES `usr` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `speciality`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `speciality` (`id`, `name`) VALUES (1, 'Хімік');
INSERT INTO `speciality` (`id`, `name`) VALUES (2, 'Археолог');
INSERT INTO `speciality` (`id`, `name`) VALUES (3, 'Слюсар');
INSERT INTO `speciality` (`id`, `name`) VALUES (4, 'Ботанік');
INSERT INTO `speciality` (`id`, `name`) VALUES (5, 'Філософ');

COMMIT;


-- -----------------------------------------------------
-- Data for table `subject`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `subject` (`id`, `name`) VALUES (1, 'Математика');
INSERT INTO `subject` (`id`, `name`) VALUES (2, 'Українська мова');
INSERT INTO `subject` (`id`, `name`) VALUES (3, 'Біологія');
INSERT INTO `subject` (`id`, `name`) VALUES (4, 'Хімія');
INSERT INTO `subject` (`id`, `name`) VALUES (5, 'Образотворче мистецтво');
INSERT INTO `subject` (`id`, `name`) VALUES (6, 'Географія');
INSERT INTO `subject` (`id`, `name`) VALUES (7, 'Фізика');
INSERT INTO `subject` (`id`, `name`) VALUES (8, 'Технології');
INSERT INTO `subject` (`id`, `name`) VALUES (9, 'Інформатика');
INSERT INTO `subject` (`id`, `name`) VALUES (10, 'Графіка');

COMMIT;


-- -----------------------------------------------------
-- Data for table `speciality_subject`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `speciality_subject` (`speciality_id`, `subject_id`) VALUES (1, 1);
INSERT INTO `speciality_subject` (`speciality_id`, `subject_id`) VALUES (1, 4);
INSERT INTO `speciality_subject` (`speciality_id`, `subject_id`) VALUES (1, 7);
INSERT INTO `speciality_subject` (`speciality_id`, `subject_id`) VALUES (2, 2);
INSERT INTO `speciality_subject` (`speciality_id`, `subject_id`) VALUES (2, 3);
INSERT INTO `speciality_subject` (`speciality_id`, `subject_id`) VALUES (2, 4);
INSERT INTO `speciality_subject` (`speciality_id`, `subject_id`) VALUES (2, 10);
INSERT INTO `speciality_subject` (`speciality_id`, `subject_id`) VALUES (3, 1);
INSERT INTO `speciality_subject` (`speciality_id`, `subject_id`) VALUES (3, 3);
INSERT INTO `speciality_subject` (`speciality_id`, `subject_id`) VALUES (3, 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `usr`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `usr` (`id`, `email`, `first_name`, `last_name`, `password`, `username`, `speciality_id`, `role`) VALUES (1,'a@ua', 'a', 'a', '0cc175b9c0f1b6a831c399e269772661', 'a', null, 'ADMIN');
INSERT INTO `usr` (`id`, `email`, `first_name`, `last_name`, `password`, `username`, `speciality_id`, `role`) VALUES (2,'z@ua', 'z', 'z', 'fbade9e36a3f36d3d676c1b808451dd7', 'z', 2, 'USER');
INSERT INTO `usr` (`id`, `email`, `first_name`, `last_name`, `password`, `username`, `speciality_id`, `role`) VALUES (3,'x@ua', 'x', 'x', '9dd4e461268c8034f5c8564e155c67a6', 'x', 3, 'USER');
INSERT INTO `usr` (`id`, `email`, `first_name`, `last_name`, `password`, `username`, `speciality_id`, `role`) VALUES (4,'q@ua', 'q', 'q', '7694f4a66316e53c8cdd9d9954bd611d', 'q', 1, 'USER');
INSERT INTO `usr` (`id`, `email`, `first_name`, `last_name`, `password`, `username`, `speciality_id`, `role`) VALUES (5,'s@ua', 's', 's', '03c7c0ace395d80182db07ae2c30f034', 's', null, 'USER');
INSERT INTO `usr` (`id`, `email`, `first_name`, `last_name`, `password`, `username`, `speciality_id`, `role`) VALUES (6,'w@ua', 'w', 'w', 'f1290186a5d0b1ceab27f4e77c0c5d68', 'w', 4, 'USER');
INSERT INTO `usr` (`id`, `email`, `first_name`, `last_name`, `password`, `username`, `speciality_id`, `role`) VALUES (7,'admin@ua', 'Admin', 'ADMIN', '21232f297a57a5a743894a0e4a801fc3', 'admin', null, 'ADMIN');
INSERT INTO `usr` (`id`, `email`, `first_name`, `last_name`, `password`, `username`, `speciality_id`, `role`) VALUES (8,'user@ua', 'User', 'USER', 'ee11cbb19052e40b07aac0ca060c23ee', 'user', null, 'USER');
INSERT INTO `usr` (`id`, `email`, `first_name`, `last_name`, `password`, `username`, `speciality_id`, `role`) VALUES (9,'student@ua', 'Student', 'STUDENT', 'cd73502828457d15655bbd7a63fb0bc8', 'student', null, 'USER');

COMMIT;


-- -----------------------------------------------------
-- Data for table `subject_grade`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `subject_grade` (`grade`, `subject_id`, `user_id`) VALUES (70, 2, 2);
INSERT INTO `subject_grade` (`grade`, `subject_id`, `user_id`) VALUES (85, 3, 2);
INSERT INTO `subject_grade` (`grade`, `subject_id`, `user_id`) VALUES (90, 10, 2);
INSERT INTO `subject_grade` (`grade`, `subject_id`, `user_id`) VALUES (100, 1, 3);
INSERT INTO `subject_grade` (`grade`, `subject_id`, `user_id`) VALUES (65, 5, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_role`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `user_role` (`user_id`, `role_name`) VALUES (1, 'ADMIN');
INSERT INTO `user_role` (`user_id`, `role_name`) VALUES (2, 'USER');
INSERT INTO `user_role` (`user_id`, `role_name`) VALUES (3, 'USER');
INSERT INTO `user_role` (`user_id`, `role_name`) VALUES (4, 'USER');
INSERT INTO `user_role` (`user_id`, `role_name`) VALUES (5, 'USER');
INSERT INTO `user_role` (`user_id`, `role_name`) VALUES (6, 'USER');
INSERT INTO `user_role` (`user_id`, `role_name`) VALUES (7, 'ADMIN');
INSERT INTO `user_role` (`user_id`, `role_name`) VALUES (8, 'USER');
INSERT INTO `user_role` (`user_id`, `role_name`) VALUES (9, 'USER');

COMMIT;

