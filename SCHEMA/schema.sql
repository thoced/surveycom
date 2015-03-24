SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `AnalyserPhoobs_template` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `AnalyserPhoobs_template` ;

-- -----------------------------------------------------
-- Table `AnalyserPhoobs_template`.`t_identification`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `t_identification` (
  `numero` VARCHAR(45) NULL ,
  `nom` VARCHAR(45) NULL ,
  `prenom` VARCHAR(45) NULL ,
  `adresse` VARCHAR(45) NULL ,
  PRIMARY KEY (`numero`) );

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
