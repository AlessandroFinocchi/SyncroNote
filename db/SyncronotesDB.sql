-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SyncroNotesDB
-- -----------------------------------------------------
-- ISWP Project A.A. 2022/2023 Data Base, by Alessandro Finocchi
DROP SCHEMA IF EXISTS `SyncroNotesDB` ;

-- -----------------------------------------------------
-- Schema SyncroNotesDB
--
-- ISWP Project A.A. 2022/2023 Data Base, by Alessandro Finocchi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SyncroNotesDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `SyncroNotesDB` ;

-- -----------------------------------------------------
-- Table `SyncroNotesDB`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SyncroNotesDB`.`User` (
  `Username` VARCHAR(45) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` CHAR(8) NOT NULL,
  `Role` ENUM('Student', 'Professor', 'Admin') NOT NULL,
  PRIMARY KEY (`Username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SyncroNotesDB`.`Professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SyncroNotesDB`.`Professor` (
  `Username` VARCHAR(45) NOT NULL,
  `University` VARCHAR(45) NULL,
  PRIMARY KEY (`Username`),
  CONSTRAINT `ProfessorUser`
    FOREIGN KEY (`Username`)
    REFERENCES `SyncroNotesDB`.`User` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SyncroNotesDB`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SyncroNotesDB`.`Category` (
  `Name` VARCHAR(45) NOT NULL,
  `Macroarea` VARCHAR(45) NOT NULL,
  `Grade` ENUM('Primary', 'Secondary', 'University') NOT NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SyncroNotesDB`.`Course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SyncroNotesDB`.`Course` (
  `IdCourse` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Grade` ENUM('Primary', 'Secondary', 'University') NOT NULL,
  `Professor` VARCHAR(45) NOT NULL,
  `Category` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`IdCourse`),
  CONSTRAINT `CourseProfessor`
    FOREIGN KEY (`Professor`)
    REFERENCES `SyncroNotesDB`.`Professor` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CategoryCourseProfessor`
    FOREIGN KEY (`Category`)
    REFERENCES `SyncroNotesDB`.`Category` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `Professor_idx` ON `SyncroNotesDB`.`Course` (`Professor` ASC) VISIBLE;

CREATE INDEX `CategoryCourseProfessor_idx` ON `SyncroNotesDB`.`Course` (`Category` ASC) VISIBLE;

CREATE UNIQUE INDEX `UniqueNameProf` ON `SyncroNotesDB`.`Course` (`Name` ASC, `Professor` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `SyncroNotesDB`.`Note`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SyncroNotesDB`.`Note` (
  `Title` VARCHAR(45) NOT NULL,
  `Author` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(256) NULL,
  `File` VARCHAR(512) NOT NULL,
  `Visibility` ENUM('Public', 'Private') NOT NULL,
  `Category` VARCHAR(45) NULL,
  PRIMARY KEY (`Title`),
  CONSTRAINT `NoteAuthor`
    FOREIGN KEY (`Author`)
    REFERENCES `SyncroNotesDB`.`User` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CategoryNote`
    FOREIGN KEY (`Category`)
    REFERENCES `SyncroNotesDB`.`Category` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `Author_idx` ON `SyncroNotesDB`.`Note` (`Author` ASC) VISIBLE;

CREATE INDEX `CategoryNote_idx` ON `SyncroNotesDB`.`Note` (`Category` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `SyncroNotesDB`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SyncroNotesDB`.`Student` (
  `Username` VARCHAR(45) NOT NULL,
  `freshman` CHAR(8) NULL,
  PRIMARY KEY (`Username`),
  CONSTRAINT `StudentUser`
    FOREIGN KEY (`Username`)
    REFERENCES `SyncroNotesDB`.`User` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SyncroNotesDB`.`Subscribed`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SyncroNotesDB`.`Subscribed` (
  `Student` VARCHAR(45) NOT NULL,
  `Course` INT NOT NULL,
  PRIMARY KEY (`Student`, `Course`),
  CONSTRAINT `StudentSubscribed`
    FOREIGN KEY (`Student`)
    REFERENCES `SyncroNotesDB`.`Student` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CourseSubscribed`
    FOREIGN KEY (`Course`)
    REFERENCES `SyncroNotesDB`.`Course` (`IdCourse`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `CourseSubscribed_idx` ON `SyncroNotesDB`.`Subscribed` (`Course` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `SyncroNotesDB`.`Publication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SyncroNotesDB`.`Publication` (
  `Note` VARCHAR(45) NOT NULL,
  `Course` INT NOT NULL,
  PRIMARY KEY (`Note`),
  CONSTRAINT `NotePublication`
    FOREIGN KEY (`Note`)
    REFERENCES `SyncroNotesDB`.`Note` (`Title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `CoursePublication`
    FOREIGN KEY (`Course`)
    REFERENCES `SyncroNotesDB`.`Course` (`IdCourse`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `CoursePublication_idx` ON `SyncroNotesDB`.`Publication` (`Course` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `SyncroNotesDB`.`Revision`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SyncroNotesDB`.`Revision` (
  `Note` VARCHAR(45) NOT NULL,
  `Professor` VARCHAR(45) NOT NULL,
  `StudentQuestion` VARCHAR(255) NULL,
  `ProfessorResponse` VARCHAR(255) NULL,
  PRIMARY KEY (`Note`),
  CONSTRAINT `NoteRevision`
    FOREIGN KEY (`Note`)
    REFERENCES `SyncroNotesDB`.`Note` (`Title`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `ProfRevision`
    FOREIGN KEY (`Professor`)
    REFERENCES `SyncroNotesDB`.`Professor` (`Username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `ProfRevision_idx` ON `SyncroNotesDB`.`Revision` (`Professor` ASC) VISIBLE;

USE `SyncroNotesDB`;

DELIMITER $$
USE `SyncroNotesDB`$$
CREATE DEFINER = CURRENT_USER TRIGGER `SyncroNotesDB`.`Note_BEFORE_INSERT` BEFORE INSERT ON `Note` FOR EACH ROW
BEGIN
	if exists (select * from note where title = New.title) then
		signal sqlstate "45001"
        set message_text = "Note title already exists";
	end if;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `SyncroNotesDB`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `SyncroNotesDB`;
INSERT INTO `SyncroNotesDB`.`User` (`Username`, `Name`, `Surname`, `Email`, `Password`, `Role`) VALUES ('Alex', 'Alessandro', 'Finocchi', 'a@a.it', '1234', 'Student');
INSERT INTO `SyncroNotesDB`.`User` (`Username`, `Name`, `Surname`, `Email`, `Password`, `Role`) VALUES ('Barto', 'Daniele', 'Bartolucci', 'd@d.en', '1111', 'Professor');
INSERT INTO `SyncroNotesDB`.`User` (`Username`, `Name`, `Surname`, `Email`, `Password`, `Role`) VALUES ('d', 'd', 'd', 'd2@d.it', 'd', 'Professor');

COMMIT;


-- -----------------------------------------------------
-- Data for table `SyncroNotesDB`.`Professor`
-- -----------------------------------------------------
START TRANSACTION;
USE `SyncroNotesDB`;
INSERT INTO `SyncroNotesDB`.`Professor` (`Username`, `University`) VALUES ('Barto', 'Tor Vergata');
INSERT INTO `SyncroNotesDB`.`Professor` (`Username`, `University`) VALUES ('d', 'd');

COMMIT;


-- -----------------------------------------------------
-- Data for table `SyncroNotesDB`.`Category`
-- -----------------------------------------------------
START TRANSACTION;
USE `SyncroNotesDB`;
INSERT INTO `SyncroNotesDB`.`Category` (`Name`, `Macroarea`, `Grade`) VALUES ('Analisi', 'Math', 'University');
INSERT INTO `SyncroNotesDB`.`Category` (`Name`, `Macroarea`, `Grade`) VALUES ('Piano', 'Music', 'Primary');
INSERT INTO `SyncroNotesDB`.`Category` (`Name`, `Macroarea`, `Grade`) VALUES ('Fun', 'General', 'Primary');

COMMIT;


-- -----------------------------------------------------
-- Data for table `SyncroNotesDB`.`Course`
-- -----------------------------------------------------
START TRANSACTION;
USE `SyncroNotesDB`;
INSERT INTO `SyncroNotesDB`.`Course` (`IdCourse`, `Name`, `Grade`, `Professor`, `Category`) VALUES (1, 'Analisi 1', 'University', 'Barto', 'Analisi');

COMMIT;


-- -----------------------------------------------------
-- Data for table `SyncroNotesDB`.`Student`
-- -----------------------------------------------------
START TRANSACTION;
USE `SyncroNotesDB`;
INSERT INTO `SyncroNotesDB`.`Student` (`Username`, `freshman`) VALUES ('Alex', '12345678');

COMMIT;


-- -----------------------------------------------------
-- Data for table `SyncroNotesDB`.`Subscribed`
-- -----------------------------------------------------
START TRANSACTION;
USE `SyncroNotesDB`;
INSERT INTO `SyncroNotesDB`.`Subscribed` (`Student`, `Course`) VALUES ('Alex', 1);

COMMIT;

