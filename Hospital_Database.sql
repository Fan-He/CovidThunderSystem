-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- USE `mydb` ;

DROP DATABASE IF EXISTS `mydb`;
CREATE DATABASE `mydb`; 
USE `mydb`;

-- -----------------------------------------------------
-- Table `mydb`.`Department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Department` (
  `department` VARCHAR(45) NOT NULL,
  `managerID` INT(20) NULL,
  PRIMARY KEY (`department`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Doctor` (
  `dID` INT(20) NOT NULL,
  `dName` VARCHAR(45) NULL,
  `department` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`dID`),
  INDEX `fk_Doctor_Department1_idx` (`department` ASC) VISIBLE,
  CONSTRAINT `fk_Doctor_Department1`
    FOREIGN KEY (`department`)
    REFERENCES `mydb`.`Department` (`department`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ward`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ward` (
  `wardNumber` INT(10) NOT NULL,
  `Department_department` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`wardNumber`),
  INDEX `fk_Ward_Department1_idx` (`Department_department` ASC) VISIBLE,
  CONSTRAINT `fk_Ward_Department1`
    FOREIGN KEY (`Department_department`)
    REFERENCES `mydb`.`Department` (`department`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Covid_Test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Covid_Test` (
  `testNumber` INT(20) NOT NULL,
  `date` VARCHAR(15) NULL,
  `result` VARCHAR(15) NULL,
  `shotNum` INT(5) NULL,
  `Patient_medical_Number` INT(20) NOT NULL,
  PRIMARY KEY (`testNumber`, `Patient_medical_Number`),
  INDEX `fk_Covid_Test_Patient1_idx` (`Patient_medical_Number` ASC) VISIBLE,
  CONSTRAINT `fk_Covid_Test_Patient1`
    FOREIGN KEY (`Patient_medical_Number`)
    REFERENCES `mydb`.`Patient` (`medical_Number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Patient` (
  `medical_Number` INT(20) NOT NULL,
  `pName` VARCHAR(45) NULL,
  `Doctor_dID` INT(20) NOT NULL,
  `Ward_wardNumber` INT(10) NOT NULL,
  `Covid_Test_testNumber` INT(20) NOT NULL,
  PRIMARY KEY (`medical_Number`),
  INDEX `fk_Patient_Doctor1_idx` (`Doctor_dID` ASC) VISIBLE,
  INDEX `fk_Patient_Ward1_idx` (`Ward_wardNumber` ASC) VISIBLE,
  INDEX `fk_Patient_Covid_Test1_idx` (`Covid_Test_testNumber` ASC) VISIBLE,
  CONSTRAINT `fk_Patient_Doctor1`
    FOREIGN KEY (`Doctor_dID`)
    REFERENCES `mydb`.`Doctor` (`dID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Patient_Ward1`
    FOREIGN KEY (`Ward_wardNumber`)
    REFERENCES `mydb`.`Ward` (`wardNumber`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Patient_Covid_Test1`
    FOREIGN KEY (`Covid_Test_testNumber`)
    REFERENCES `mydb`.`Covid_Test` (`testNumber`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `mydb`.`Operating_room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Operating_room` (
  `optRoomNumber` INT(10) NOT NULL,
  `Department_department` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`optRoomNumber`),
  INDEX `fk_Operating_room_Department1_idx` (`Department_department` ASC) VISIBLE,
  CONSTRAINT `fk_Operating_room_Department1`
    FOREIGN KEY (`Department_department`)
    REFERENCES `mydb`.`Department` (`department`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Medical_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Medical_record` (
  `Patient_medical_Number` INT(20) NOT NULL,
  `disease` VARCHAR(45) NULL,
  `allergen` VARCHAR(45) NULL,
  PRIMARY KEY (`Patient_medical_Number`),
  CONSTRAINT `fk_Medical_record_Patient1`
    FOREIGN KEY (`Patient_medical_Number`)
    REFERENCES `mydb`.`Patient` (`medical_Number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;






#Department
INSERT INTO Department VALUES ('Dermatology', 123);
INSERT INTO Department VALUES ('Cardiology', 456);
INSERT INTO Department VALUES ('Oncology', 789);
INSERT INTO Department VALUES ('Hematology', 101112);
INSERT INTO Department VALUES ('Pulmonology', 131415);
INSERT INTO Department VALUES ('Urology', 161718);
INSERT INTO Department VALUES ('Orthopaedics', 192021);
INSERT INTO Department VALUES ('Dentistry', 222324);
#Doctor
INSERT INTO Doctor VALUES (123, 'John', 'Dermatology');
INSERT INTO Doctor VALUES (121, 'William', 'Dermatology');
INSERT INTO Doctor VALUES (122, 'David', 'Dermatology');
INSERT INTO Doctor VALUES (456, 'Anna', 'Cardiology');
INSERT INTO Doctor VALUES (452, 'Gabe', 'Cardiology');
INSERT INTO Doctor VALUES (454, 'Enno', 'Cardiology');
INSERT INTO Doctor VALUES (789, 'Bob', 'Oncology');
INSERT INTO Doctor VALUES (788, 'Daniel', 'Oncology');
INSERT INTO Doctor VALUES (786, 'Natalie', 'Oncology');
INSERT INTO Doctor VALUES (101112, 'Vincent', 'Hematology');
INSERT INTO Doctor VALUES (101113, 'Robert', 'Hematology');
INSERT INTO Doctor VALUES (101115, 'Marry', 'Hematology');
INSERT INTO Doctor VALUES (131415, 'Rambo', 'Pulmonology');
INSERT INTO Doctor VALUES (131417, 'Clark', 'Pulmonology');
INSERT INTO Doctor VALUES (131411, 'Julia', 'Pulmonology');
INSERT INTO Doctor VALUES (161718, 'Kent', 'Urology');
INSERT INTO Doctor VALUES (161710, 'Juan', 'Urology');
INSERT INTO Doctor VALUES (161711, 'Saul', 'Urology');
INSERT INTO Doctor VALUES (192021, 'Zoltan', 'Orthopaedics');
INSERT INTO Doctor VALUES (192023, 'Emma', 'Orthopaedics');
INSERT INTO Doctor VALUES (192029, 'Jim', 'Orthopaedics');
INSERT INTO Doctor VALUES (222324, 'Maxim', 'Dentistry');
INSERT INTO Doctor VALUES (222326, 'Colin', 'Dentistry');
INSERT INTO Doctor VALUES (222322, 'Marci', 'Dentistry');
#Patient
INSERT INTO Patient VALUES (001, 'Austin', 123, 001, 001);
INSERT INTO Patient VALUES (002, 'Mars', 456, 002, 002);
INSERT INTO Patient VALUES (003, 'Yonghao', 789, 003, 003);
INSERT INTO Patient VALUES (004, 'Orhan', 101112, 004, 004);
INSERT INTO Patient VALUES (005, 'Josh', 131415, 005, 005);
INSERT INTO Patient VALUES (006, 'Cooper', 161718, 006, 006);
INSERT INTO Patient VALUES (007, 'Victor', 192021, 007, 007);
INSERT INTO Patient VALUES (008, 'Akio', 222324, 008, 008);
INSERT INTO Patient VALUES (009, 'Thomas', 192023, 009, 009);
INSERT INTO Patient VALUES (010, 'Spongebob', 131411, 010, 010);
INSERT INTO Patient VALUES (011, 'Adrien', 161711, 011, 011);
INSERT INTO Patient VALUES (012, 'Rick', 101113, 012, 012);
INSERT INTO Patient VALUES (013, 'Paul', 222322, 013, 013);
INSERT INTO Patient VALUES (014, 'Andy', 454, 014, 014);
INSERT INTO Patient VALUES (015, 'Sherlock', 101113, 015, 015);
#Ward
INSERT INTO Ward VALUES(001, 'Dermatology');
INSERT INTO Ward VALUES(002, 'Cardiology');
INSERT INTO Ward VALUES(003, 'Oncology');
INSERT INTO Ward VALUES(004, 'Hematology');
INSERT INTO Ward VALUES(005, 'Pulmonology');
INSERT INTO Ward VALUES(006, 'Urology');
INSERT INTO Ward VALUES(007, 'Orthopaedics');
INSERT INTO Ward VALUES(008, 'Dentistry');
INSERT INTO Ward VALUES(009, 'Orthopaedics');
INSERT INTO Ward VALUES(010, 'Pulmonology');
INSERT INTO Ward VALUES(011, 'Urology');
INSERT INTO Ward VALUES(012, 'Hematology');
INSERT INTO Ward VALUES(013, 'Dentistry');
INSERT INTO Ward VALUES(014, 'Cardiology');
INSERT INTO Ward VALUES(015, 'Hematology');
#Covid Test
INSERT INTO Covid_Test VALUES (001, '2021/2/18', 'Neg', 001, 001);
INSERT INTO Covid_Test VALUES (002, '2021/9/18', 'Pos', 002, 002);
INSERT INTO Covid_Test VALUES (003, '2021/7/4', 'Neg', 003, 003);
INSERT INTO Covid_Test VALUES (004, '2021/6/6', 'Neg', 004, 004);
INSERT INTO Covid_Test VALUES (005, '2021/9/20', 'Neg', 005, 005);
INSERT INTO Covid_Test VALUES (006, '2021/2/6', 'Pos', 006, 006);
INSERT INTO Covid_Test VALUES (007, '2021/5/19', 'Neg', 007, 007);
INSERT INTO Covid_Test VALUES (008, '2021/8/1', 'Neg', 008, 008);
INSERT INTO Covid_Test VALUES (009, '2021/1/1', 'Pos', 009, 009);
INSERT INTO Covid_Test VALUES (010, '2021/8/2', 'Neg', 010, 010);
INSERT INTO Covid_Test VALUES (011, '2021/10/19', 'Pos', 011, 011);
INSERT INTO Covid_Test VALUES (012, '2021/11/9', 'Neg', 012, 012);
INSERT INTO Covid_Test VALUES (013, '2021/1/6', 'Pos', 013, 013);
INSERT INTO Covid_Test VALUES (014, '2021/9/6', 'Neg', 014, 014);
INSERT INTO Covid_Test VALUES (015, '2021/8/30', 'Pos', 015, 015);
#Operating Room
INSERT INTO Operating_Room VALUES (001, 'Dermatology');
INSERT INTO Operating_Room VALUES (002, 'Cardiology');
INSERT INTO Operating_Room VALUES (003, 'Oncology');
INSERT INTO Operating_Room VALUES (004, 'Hematology');
INSERT INTO Operating_Room VALUES (005, 'Pulmonology');
INSERT INTO Operating_Room VALUES (006, 'Urology');
INSERT INTO Operating_Room VALUES (007, 'Orthopaedics');
INSERT INTO Operating_Room VALUES (008, 'Dentistry');
#Medical Records
INSERT INTO Medical_record VALUES (001, 'eczema', 'None');
INSERT INTO Medical_record VALUES (002, 'heart diease', 'None');
INSERT INTO Medical_record VALUES (003, 'lung cancer', 'peanut');
INSERT INTO Medical_record VALUES (004, 'leukemia', 'None');
INSERT INTO Medical_record VALUES (005, 'respiratory tract infection', 'None');
INSERT INTO Medical_record VALUES (006, 'prostatitis', 'milk');
INSERT INTO Medical_record VALUES (007, 'leg broken', 'None');
INSERT INTO Medical_record VALUES (008, 'decayed teeth', 'None');
INSERT INTO Medical_record VALUES (009, 'back broken', 'None');
INSERT INTO Medical_record VALUES (010, 'lung cancer', 'peanut');
INSERT INTO Medical_record VALUES (011, 'urinary tract infection', 'Corn');
INSERT INTO Medical_record VALUES (012, 'scurvy', 'peanut');
INSERT INTO Medical_record VALUES (013, 'teeth broken', 'None');
INSERT INTO Medical_record VALUES (014, 'frequent heart attack', 'cat');
INSERT INTO Medical_record VALUES (015, 'anemia', 'milk');










SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
