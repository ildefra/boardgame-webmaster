-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema boardgames
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `boardgames` ;

-- -----------------------------------------------------
-- Schema boardgames
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `boardgames` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
SHOW WARNINGS;
USE `boardgames` ;

-- -----------------------------------------------------
-- Table `country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `country` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `country` (
  `iso_alpha_2` CHAR(2) NOT NULL COMMENT '',
  `iso_alpha_3` CHAR(3) NULL COMMENT '',
  `name` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`iso_alpha_2`)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)  COMMENT '',
  UNIQUE INDEX `country_code_UNIQUE` (`iso_alpha_2` ASC)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `user_level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_level` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `user_level` (
  `level_id` TINYINT NOT NULL COMMENT '',
  `can_play` BIT NOT NULL DEFAULT 1 COMMENT '',
  `can_shout` BIT NOT NULL DEFAULT 0 COMMENT '',
  `can_downgrade` BIT NOT NULL DEFAULT 0 COMMENT '',
  PRIMARY KEY (`level_id`)  COMMENT '',
  UNIQUE INDEX `level_id_UNIQUE` (`level_id` ASC)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gender`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gender` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `gender` (
  `gender_id` TINYINT UNSIGNED NOT NULL COMMENT '',
  `name` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`gender_id`)  COMMENT '',
  UNIQUE INDEX `gender_id_UNIQUE` (`gender_id` ASC)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `player` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `player` (
  `username` VARCHAR(20) NOT NULL COMMENT '',
  `email` VARCHAR(90) NULL COMMENT '',
  `password` VARCHAR(20) NOT NULL COMMENT '',
  `registration_dtm` DATE NOT NULL COMMENT '',
  `sex` TINYINT UNSIGNED NOT NULL COMMENT '',
  `birth_date` DATE NULL COMMENT '',
  `country` CHAR(2) NOT NULL COMMENT '',
  `bio` TEXT NULL COMMENT '',
  `is_premium` BIT NOT NULL DEFAULT 0 COMMENT '',
  `level_id` TINYINT NOT NULL COMMENT '',
  PRIMARY KEY (`username`)  COMMENT '',
  UNIQUE INDEX `username_UNIQUE` (`username` ASC)  COMMENT '',
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)  COMMENT '',
  INDEX `website_user_has_country_fk1_idx` (`country` ASC)  COMMENT '',
  INDEX `player_has_user_level_fk1_idx` (`level_id` ASC)  COMMENT '',
  INDEX `player_has_gender_fk1_idx` (`sex` ASC)  COMMENT '',
  CONSTRAINT `website_user_has_country_fk1`
    FOREIGN KEY (`country`)
    REFERENCES `country` (`iso_alpha_2`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `player_has_user_level_fk1`
    FOREIGN KEY (`level_id`)
    REFERENCES `user_level` (`level_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `player_has_gender_fk1`
    FOREIGN KEY (`sex`)
    REFERENCES `gender` (`gender_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `publisher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `publisher` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `publisher` (
  `name` VARCHAR(50) NOT NULL COMMENT '',
  `website` VARCHAR(255) NOT NULL COMMENT '',
  `logo` BLOB NOT NULL COMMENT '',
  PRIMARY KEY (`name`)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)  COMMENT '',
  UNIQUE INDEX `website_UNIQUE` (`website` ASC)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `boardgame`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boardgame` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `boardgame` (
  `boardgame_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `original_name` VARCHAR(50) NOT NULL COMMENT '',
  `publisher` VARCHAR(50) NULL COMMENT '',
  `publication_year` SMALLINT NULL COMMENT '',
  `players_min` TINYINT UNSIGNED NOT NULL COMMENT '',
  `players_max` TINYINT UNSIGNED NOT NULL COMMENT '',
  `avg_length_minutes` SMALLINT UNSIGNED NOT NULL COMMENT '',
  `complexity_score` TINYINT UNSIGNED NOT NULL COMMENT '',
  `strategy_score` TINYINT UNSIGNED NOT NULL COMMENT '',
  `luck_score` TINYINT UNSIGNED NOT NULL COMMENT '',
  `interaction_score` TINYINT UNSIGNED NOT NULL COMMENT '',
  `developed_by` VARCHAR(20) NULL COMMENT '',
  `software_version` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`boardgame_id`)  COMMENT '',
  INDEX `boardgame_has_website_user_fk1_idx` (`developed_by` ASC)  COMMENT '',
  UNIQUE INDEX `original_name_UNIQUE` (`original_name` ASC)  COMMENT '',
  UNIQUE INDEX `boardgame_id_UNIQUE` (`boardgame_id` ASC)  COMMENT '',
  INDEX `boardgame_has_publisher_fk1_idx` (`publisher` ASC)  COMMENT '',
  CONSTRAINT `boardgame_has_website_user_fk1`
    FOREIGN KEY (`developed_by`)
    REFERENCES `player` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `boardgame_has_publisher_fk1`
    FOREIGN KEY (`publisher`)
    REFERENCES `publisher` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `language` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `language` (
  `language_code` CHAR(3) NOT NULL COMMENT '',
  `name` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`language_code`)  COMMENT '',
  UNIQUE INDEX `language_code_UNIQUE` (`language_code` ASC)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `game_translation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_translation` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `game_translation` (
  `boardgame_id` INT UNSIGNED NOT NULL COMMENT '',
  `language_code` CHAR(3) NOT NULL COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `rules` MEDIUMTEXT NOT NULL COMMENT '',
  `rules_link` VARCHAR(255) NOT NULL COMMENT '',
  PRIMARY KEY (`boardgame_id`, `language_code`)  COMMENT '',
  INDEX `boardgame_language_has_language_fk1_idx` (`language_code` ASC)  COMMENT '',
  INDEX `boardgame_language_has_boardgame_fk1_idx` (`boardgame_id` ASC)  COMMENT '',
  UNIQUE INDEX `rules_link_UNIQUE` (`rules_link` ASC)  COMMENT '',
  CONSTRAINT `boardgame_language_has_boardgame_fk1`
    FOREIGN KEY (`boardgame_id`)
    REFERENCES `boardgame` (`boardgame_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `boardgame_language_has_language_fk1`
    FOREIGN KEY (`language_code`)
    REFERENCES `language` (`language_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `external_link`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `external_link` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `external_link` (
  `external_link_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `boardgame_id` INT UNSIGNED NOT NULL COMMENT '',
  `language_code` CHAR(3) NOT NULL COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `is_video` BIT NOT NULL DEFAULT 0 COMMENT '',
  PRIMARY KEY (`external_link_id`)  COMMENT '',
  UNIQUE INDEX `external_link_id_UNIQUE` (`external_link_id` ASC)  COMMENT '',
  INDEX `external_link_has_boardgame_fk1_idx` (`boardgame_id` ASC)  COMMENT '',
  INDEX `external_link_has_language_fk1_idx` (`language_code` ASC)  COMMENT '',
  CONSTRAINT `external_link_has_boardgame_fk1`
    FOREIGN KEY (`boardgame_id`)
    REFERENCES `boardgame` (`boardgame_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `external_link_has_language_fk1`
    FOREIGN KEY (`language_code`)
    REFERENCES `language` (`language_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `game_designer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_designer` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `game_designer` (
  `game_designer_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `first_name` VARCHAR(50) NOT NULL COMMENT '',
  `surname` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`game_designer_id`)  COMMENT '',
  UNIQUE INDEX `game_designer_id_UNIQUE` (`game_designer_id` ASC)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `artwork`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `artwork` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `artwork` (
  `boardgame_id` INT UNSIGNED NOT NULL COMMENT '',
  `game_designer_id` INT UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`boardgame_id`, `game_designer_id`)  COMMENT '',
  INDEX `boardgame_game_designer_has_game_designer_fk1_idx` (`game_designer_id` ASC)  COMMENT '',
  INDEX `boardgame_game_designer_has_boardgame_fk1_idx` (`boardgame_id` ASC)  COMMENT '',
  CONSTRAINT `boardgame_game_designer_has_boardgame_fk1`
    FOREIGN KEY (`boardgame_id`)
    REFERENCES `boardgame` (`boardgame_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `boardgame_game_designer_has_game_designer_fk1`
    FOREIGN KEY (`game_designer_id`)
    REFERENCES `game_designer` (`game_designer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `autorship`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `autorship` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `autorship` (
  `boardgame_id` INT UNSIGNED NOT NULL COMMENT '',
  `game_designer_id` INT UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`boardgame_id`, `game_designer_id`)  COMMENT '',
  INDEX `game_designer_boardgame_has_boardgame_fk1_idx` (`boardgame_id` ASC)  COMMENT '',
  INDEX `game_designer_boardgame_has_game_designer_fk1_idx` (`game_designer_id` ASC)  COMMENT '',
  CONSTRAINT `game_designer_boardgame_has_game_designer_fk1`
    FOREIGN KEY (`game_designer_id`)
    REFERENCES `game_designer` (`game_designer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `game_designer_boardgame_has_boardgame_fk1`
    FOREIGN KEY (`boardgame_id`)
    REFERENCES `boardgame` (`boardgame_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `friendship`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `friendship` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `friendship` (
  `username1` VARCHAR(20) NOT NULL COMMENT '',
  `username2` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`username1`, `username2`)  COMMENT '',
  INDEX `player_player_has_player_fk2_idx` (`username2` ASC)  COMMENT '',
  INDEX `player_player_has_player_fk1_idx` (`username1` ASC)  COMMENT '',
  CONSTRAINT `player_player_has_player_fk1`
    FOREIGN KEY (`username1`)
    REFERENCES `player` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `player_player_has_player_fk2`
    FOREIGN KEY (`username2`)
    REFERENCES `player` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `can_speak`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `can_speak` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `can_speak` (
  `username` VARCHAR(20) NOT NULL COMMENT '',
  `language_code` CHAR(3) NOT NULL COMMENT '',
  PRIMARY KEY (`username`, `language_code`)  COMMENT '',
  INDEX `language_player_has_player_fk1_idx` (`username` ASC)  COMMENT '',
  INDEX `language_player_has_language_fk1_idx` (`language_code` ASC)  COMMENT '',
  CONSTRAINT `language_player_has_language_fk1`
    FOREIGN KEY (`language_code`)
    REFERENCES `language` (`language_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `language_player_has_player_fk1`
    FOREIGN KEY (`username`)
    REFERENCES `player` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `game_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_tag` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `game_tag` (
  `name` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`name`)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `has_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `has_tag` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `has_tag` (
  `boardgame_id` INT UNSIGNED NOT NULL COMMENT '',
  `tag_name` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`boardgame_id`, `tag_name`)  COMMENT '',
  INDEX `game_tag_boardgame_has_boardgame_fk1_idx` (`boardgame_id` ASC)  COMMENT '',
  INDEX `game_tag_boardgame_has_game_tag_fk1_idx` (`tag_name` ASC)  COMMENT '',
  CONSTRAINT `game_tag_boardgame_has_game_tag_fk1`
    FOREIGN KEY (`tag_name`)
    REFERENCES `game_tag` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `game_tag_boardgame_has_boardgame_fk1`
    FOREIGN KEY (`boardgame_id`)
    REFERENCES `boardgame` (`boardgame_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `tag_translation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tag_translation` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `tag_translation` (
  `tag_name` VARCHAR(20) NOT NULL COMMENT '',
  `language_code` CHAR(3) NOT NULL COMMENT '',
  `name` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`tag_name`, `language_code`)  COMMENT '',
  INDEX `game_tag_language_has_language_fk1_idx` (`language_code` ASC)  COMMENT '',
  INDEX `game_tag_language_has_game_tag_fk1_idx` (`tag_name` ASC)  COMMENT '',
  CONSTRAINT `game_tag_language_has_game_tag_fk1`
    FOREIGN KEY (`tag_name`)
    REFERENCES `game_tag` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `game_tag_language_has_language_fk1`
    FOREIGN KEY (`language_code`)
    REFERENCES `language` (`language_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `level_translation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `level_translation` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `level_translation` (
  `level_id` TINYINT NOT NULL COMMENT '',
  `language_code` CHAR(3) NOT NULL COMMENT '',
  `name` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`level_id`, `language_code`)  COMMENT '',
  INDEX `user_level_language_has_language_fk1_idx` (`language_code` ASC)  COMMENT '',
  INDEX `user_level_language_has_user_level_fk1_idx` (`level_id` ASC)  COMMENT '',
  CONSTRAINT `user_level_language_has_user_level_fk1`
    FOREIGN KEY (`level_id`)
    REFERENCES `user_level` (`level_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_level_language_has_language_fk1`
    FOREIGN KEY (`language_code`)
    REFERENCES `language` (`language_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `achievement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `achievement` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `achievement` (
  `achievement_id` TINYINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `points` SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '',
  `name` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`achievement_id`)  COMMENT '',
  UNIQUE INDEX `achievement_id_UNIQUE` (`achievement_id` ASC)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `achievement_translation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `achievement_translation` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `achievement_translation` (
  `achievement_id` TINYINT UNSIGNED NOT NULL COMMENT '',
  `language_code` CHAR(3) NOT NULL COMMENT '',
  `name` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`achievement_id`, `language_code`)  COMMENT '',
  INDEX `achievement_language_has_language_fk1_idx` (`language_code` ASC)  COMMENT '',
  INDEX `achievement_language_has_achievement_fk1_idx` (`achievement_id` ASC)  COMMENT '',
  CONSTRAINT `achievement_language_has_achievement_fk1`
    FOREIGN KEY (`achievement_id`)
    REFERENCES `achievement` (`achievement_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `achievement_language_has_language_fk1`
    FOREIGN KEY (`language_code`)
    REFERENCES `language` (`language_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `achieved`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `achieved` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `achieved` (
  `username` VARCHAR(20) NOT NULL COMMENT '',
  `boardgame_id` INT UNSIGNED NOT NULL COMMENT '',
  `achievement_id` TINYINT UNSIGNED NOT NULL COMMENT '',
  `on_date` DATE NOT NULL COMMENT '',
  PRIMARY KEY (`username`, `boardgame_id`, `achievement_id`)  COMMENT '',
  INDEX `has_achievement_has_boardgame_fk1_idx` (`boardgame_id` ASC)  COMMENT '',
  INDEX `has_achievement_has_achievement_fk1_idx` (`achievement_id` ASC)  COMMENT '',
  CONSTRAINT `has_achievement_has_player_fk1`
    FOREIGN KEY (`username`)
    REFERENCES `player` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `has_achievement_has_boardgame_fk1`
    FOREIGN KEY (`boardgame_id`)
    REFERENCES `boardgame` (`boardgame_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `has_achievement_has_achievement_fk1`
    FOREIGN KEY (`achievement_id`)
    REFERENCES `achievement` (`achievement_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `time_limit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `time_limit` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `time_limit` (
  `time_limit_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(50) NULL COMMENT '',
  `turn-based` BIT NOT NULL DEFAULT 0 COMMENT '',
  PRIMARY KEY (`time_limit_id`)  COMMENT '',
  UNIQUE INDEX `time_limit_id_UNIQUE` (`time_limit_id` ASC)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `game_table`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_table` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `game_table` (
  `game_table_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `boardgame_id` INT UNSIGNED NOT NULL COMMENT '',
  `time_limit_id` INT UNSIGNED NOT NULL COMMENT '',
  `rated` BIT NOT NULL DEFAULT 1 COMMENT '',
  `video_recorded` BIT NOT NULL DEFAULT 0 COMMENT '',
  `created_dtm` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  `game_started_dtm` DATETIME NULL COMMENT '',
  `game_ended_dtm` DATETIME NULL COMMENT '',
  PRIMARY KEY (`game_table_id`)  COMMENT '',
  UNIQUE INDEX `game_table_id_UNIQUE` (`game_table_id` ASC)  COMMENT '',
  INDEX `game_table_has_boardgame_fk1_idx` (`boardgame_id` ASC)  COMMENT '',
  INDEX `game_table_has_time_limit_fk1_idx` (`time_limit_id` ASC)  COMMENT '',
  CONSTRAINT `game_table_has_boardgame_fk1`
    FOREIGN KEY (`boardgame_id`)
    REFERENCES `boardgame` (`boardgame_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `game_table_has_time_limit_fk1`
    FOREIGN KEY (`time_limit_id`)
    REFERENCES `time_limit` (`time_limit_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `avg_game_length`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `avg_game_length` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `avg_game_length` (
  `boardgame_id` INT UNSIGNED NOT NULL COMMENT '',
  `time_limit_id` INT UNSIGNED NOT NULL COMMENT '',
  `avg_length_minutes` SMALLINT UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`boardgame_id`, `time_limit_id`)  COMMENT '',
  INDEX `boardgame_time_limit_has_time_limit_fk1_idx` (`time_limit_id` ASC)  COMMENT '',
  INDEX `boardgame_time_limit_has_boardgame_fk1_idx` (`boardgame_id` ASC)  COMMENT '',
  CONSTRAINT `boardgame_time_limit_has_boardgame_fk1`
    FOREIGN KEY (`boardgame_id`)
    REFERENCES `boardgame` (`boardgame_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `boardgame_time_limit_has_time_limit_fk1`
    FOREIGN KEY (`time_limit_id`)
    REFERENCES `time_limit` (`time_limit_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `played`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `played` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `played` (
  `username` VARCHAR(20) NOT NULL COMMENT '',
  `game_table_id` INT UNSIGNED NOT NULL COMMENT '',
  `score` SMALLINT NOT NULL COMMENT '',
  PRIMARY KEY (`username`, `game_table_id`)  COMMENT '',
  INDEX `game_table_player_has_player_fk1_idx` (`username` ASC)  COMMENT '',
  INDEX `game_table_player_has_game_table_fk1_idx` (`game_table_id` ASC)  COMMENT '',
  CONSTRAINT `game_table_player_has_game_table_fk1`
    FOREIGN KEY (`game_table_id`)
    REFERENCES `game_table` (`game_table_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `game_table_player_has_player_fk1`
    FOREIGN KEY (`username`)
    REFERENCES `player` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `country`
-- -----------------------------------------------------
START TRANSACTION;
USE `boardgames`;
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ad', 'and', 'Andorra');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ae', 'are', 'United Arab Emirates');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('af', 'afg', 'Afghanistan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ag', 'atg', 'Antigua and Barbuda');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ai', 'aia', 'Anguilla');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('al', 'alb', 'Albania');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('am', 'arm', 'Armenia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ao', 'ago', 'Angola');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('aq', '', 'Antarctica');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ar', 'arg', 'Argentina');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('as', 'asm', 'American Samoa');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('at', 'aut', 'Austria');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('au', 'aus', 'Australia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('aw', 'abw', 'Aruba');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ax', 'ala', 'Aland Islands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('az', 'aze', 'Azerbaijan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ba', 'bih', 'Bosnia and Herzegovina');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bb', 'brb', 'Barbados');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bd', 'bgd', 'Bangladesh');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('be', 'bel', 'Belgium');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bf', 'bfa', 'Burkina Faso');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bg', 'bgr', 'Bulgaria');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bh', 'bhr', 'Bahrain');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bi', 'bdi', 'Burundi');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bj', 'ben', 'Benin');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bl', 'blm', 'Saint Barthelemy');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bm', 'bmu', 'Bermuda');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bn', 'brn', 'Brunei Darussalam');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bo', 'bol', 'Bolivia, Plurinational State of');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bq', 'bes', 'Bonaire, Sint Eustatius and Saba');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('br', 'bra', 'Brazil');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bs', 'bhs', 'Bahamas');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bt', 'btn', 'Bhutan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bv', '', 'Bouvet Island');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bw', 'bwa', 'Botswana');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('by', 'blr', 'Belarus');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('bz', 'blz', 'Belize');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ca', 'can', 'Canada');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cc', '', 'Cocos (Keeling) Islands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cd', 'cod', 'Congo, The Democratic Republic of the');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cf', 'caf', 'Central African Republic');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cg', 'cog', 'Congo');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ch', 'che', 'Switzerland');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ci', 'civ', 'Cote d\'Ivoire');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ck', 'cok', 'Cook Islands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cl', 'chl', 'Chile');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cm', 'cmr', 'Cameroon');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cn', 'chn', 'China');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('co', 'col', 'Colombia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cr', 'cri', 'Costa Rica');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cu', 'cub', 'Cuba');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cv', 'cpv', 'Cape Verde');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cw', 'cuw', 'Curacao');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cx', '', 'Christmas Island');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cy', 'cyp', 'Cyprus');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('cz', 'cze', 'Czech Republic');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('de', 'deu', 'Germany');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('dj', 'dji', 'Djibouti');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('dk', 'dnk', 'Denmark');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('dm', 'dma', 'Dominica');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('do', 'dom', 'Dominican Republic');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('dz', 'dza', 'Algeria');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ec', 'ecu', 'Ecuador');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ee', 'est', 'Estonia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('eg', 'egy', 'Egypt');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('eh', 'esh', 'Western Sahara');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('er', 'eri', 'Eritrea');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('es', 'esp', 'Spain');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('et', 'eth', 'Ethiopia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('fi', 'fin', 'Finland');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('fj', 'fji', 'Fiji');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('fk', 'flk', 'Falkland Islands (Malvinas)');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('fm', 'fsm', 'Micronesia, Federated States of');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('fo', 'fro', 'Faroe Islands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('fr', 'fra', 'France');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ga', 'gab', 'Gabon');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gb', 'gbr', 'United Kingdom');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gd', 'grd', 'Grenada');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ge', 'geo', 'Georgia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gf', 'guf', 'French Guiana');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gg', 'ggy', 'Guernsey');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gh', 'gha', 'Ghana');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gi', 'gib', 'Gibraltar');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gl', 'grl', 'Greenland');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gm', 'gmb', 'Gambia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gn', 'gin', 'Guinea');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gp', 'glp', 'Guadeloupe');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gq', 'gnq', 'Equatorial Guinea');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gr', 'grc', 'Greece');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gs', '', 'South Georgia and The South Sandwich Islands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gt', 'gtm', 'Guatemala');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gu', 'gum', 'Guam');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gw', 'gnb', 'Guinea-Bissau');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('gy', 'guy', 'Guyana');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('hk', 'hkg', 'Hong Kong');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('hm', '', 'Heard Island and McDonald Islands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('hn', 'hnd', 'Honduras');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('hr', 'hrv', 'Croatia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ht', 'hti', 'Haiti');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('hu', 'hun', 'Hungary');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('id', 'idn', 'Indonesia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ie', 'irl', 'Ireland');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('il', 'isr', 'Israel');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('im', 'imn', 'Isle of Man');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('in', 'ind', 'India');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('io', '', 'British Indian Ocean Territory');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('iq', 'irq', 'Iraq');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ir', 'irn', 'Iran, Islamic Republic of');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('is', 'isl', 'Iceland');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('it', 'ita', 'Italy');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('je', 'jey', 'Jersey');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('jm', 'jam', 'Jamaica');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('jo', 'jor', 'Jordan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('jp', 'jpn', 'Japan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ke', 'ken', 'Kenya');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('kg', 'kgz', 'Kyrgyzstan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('kh', 'khm', 'Cambodia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ki', 'kir', 'Kiribati');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('km', 'com', 'Comoros');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('kn', 'kna', 'Saint Kitts and Nevis');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('kp', 'prk', 'Korea, Democratic People\'s Republic of');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('kr', 'kor', 'Korea, Republic of');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('kw', 'kwt', 'Kuwait');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ky', 'cym', 'Cayman Islands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('kz', 'kaz', 'Kazakhstan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('la', 'lao', 'Lao People\'s Democratic Republic');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('lb', 'lbn', 'Lebanon');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('lc', 'lca', 'Saint Lucia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('li', 'lie', 'Liechtenstein');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('lk', 'lka', 'Sri Lanka');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('lr', 'lbr', 'Liberia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ls', 'lso', 'Lesotho');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('lt', 'ltu', 'Lithuania');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('lu', 'lux', 'Luxembourg');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('lv', 'lva', 'Latvia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ly', 'lby', 'Libyan Arab Jamahiriya');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ma', 'mar', 'Morocco');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mc', 'mco', 'Monaco');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('md', 'mda', 'Moldova, Republic of');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('me', 'mne', 'Montenegro');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mf', 'maf', 'Saint Martin (French Part)');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mg', 'mdg', 'Madagascar');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mh', 'mhl', 'Marshall Islands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mk', 'mkd', 'Macedonia, The former Yugoslav Republic of');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ml', 'mli', 'Mali');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mm', 'mmr', 'Myanmar');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mn', 'mng', 'Mongolia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mo', 'mac', 'Macao');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mp', 'mnp', 'Northern Mariana Islands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mq', 'mtq', 'Martinique');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mr', 'mrt', 'Mauritania');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ms', 'msr', 'Montserrat');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mt', 'mlt', 'Malta');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mu', 'mus', 'Mauritius');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mv', 'mdv', 'Maldives');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mw', 'mwi', 'Malawi');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mx', 'mex', 'Mexico');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('my', 'mys', 'Malaysia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('mz', 'moz', 'Mozambique');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('na', 'nam', 'Namibia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('nc', 'ncl', 'New Caledonia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ne', 'ner', 'Niger');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('nf', 'nfk', 'Norfolk Island');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ng', 'nga', 'Nigeria');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ni', 'nic', 'Nicaragua');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('nl', 'nld', 'Netherlands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('no', 'nor', 'Norway');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('np', 'npl', 'Nepal');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('nr', 'nru', 'Nauru');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('nu', 'niu', 'Niue');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('nz', 'nzl', 'New Zealand');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('om', 'omn', 'Oman');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('pa', 'pan', 'Panama');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('pe', 'per', 'Peru');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('pf', 'pyf', 'French Polynesia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('pg', 'png', 'Papua New Guinea');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ph', 'phl', 'Philippines');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('pk', 'pak', 'Pakistan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('pl', 'pol', 'Poland');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('pm', 'spm', 'Saint Pierre and Miquelon');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('pn', 'pcn', 'Pitcairn');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('pr', 'pri', 'Puerto Rico');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ps', 'pse', 'Palestinian Territory, Occupied');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('pt', 'prt', 'Portugal');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('pw', 'plw', 'Palau');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('py', 'pry', 'Paraguay');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('qa', 'qat', 'Qatar');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('re', 'reu', 'Reunion');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ro', 'rou', 'Romania');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('rs', 'srb', 'Serbia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ru', 'rus', 'Russian Federation');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('rw', 'rwa', 'Rwanda');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sa', 'sau', 'Saudi Arabia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sb', 'slb', 'Solomon Islands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sc', 'syc', 'Seychelles');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sd', 'sdn', 'Sudan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('se', 'swe', 'Sweden');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sg', 'sgp', 'Singapore');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sh', 'shn', 'Saint Helena, Ascension and Tristan Da Cunha');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('si', 'svn', 'Slovenia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sj', 'sjm', 'Svalbard and Jan Mayen');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sk', 'svk', 'Slovakia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sl', 'sle', 'Sierra Leone');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sm', 'smr', 'San Marino');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sn', 'sen', 'Senegal');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('so', 'som', 'Somalia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sr', 'sur', 'Suriname');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ss', 'ssd', 'South Sudan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('st', 'stp', 'Sao Tome and Principe');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sv', 'slv', 'El Salvador');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sx', 'sxm', 'Sint Maarten (Dutch Part)');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sy', 'syr', 'Syrian Arab Republic');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('sz', 'swz', 'Swaziland');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tc', 'tca', 'Turks and Caicos Islands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('td', 'tcd', 'Chad');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tf', '', 'French Southern Territories');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tg', 'tgo', 'Togo');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('th', 'tha', 'Thailand');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tj', 'tjk', 'Tajikistan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tk', 'tkl', 'Tokelau');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tl', 'tls', 'Timor-Leste');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tm', 'tkm', 'Turkmenistan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tn', 'tun', 'Tunisia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('to', 'ton', 'Tonga');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tr', 'tur', 'Turkey');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tt', 'tto', 'Trinidad and Tobago');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tv', 'tuv', 'Tuvalu');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tw', '', 'Taiwan, Province of China');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('tz', 'tza', 'Tanzania, United Republic of');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ua', 'ukr', 'Ukraine');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ug', 'uga', 'Uganda');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('um', '', 'United States Minor Outlying Islands');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('us', 'usa', 'United States');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('uy', 'ury', 'Uruguay');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('uz', 'uzb', 'Uzbekistan');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('va', 'vat', 'Holy See (Vatican City State)');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('vc', 'vct', 'Saint Vincent and The Grenadines');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ve', 'ven', 'Venezuela, Bolivarian Republic of');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('vg', 'vgb', 'Virgin Islands, British');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('vi', 'vir', 'Virgin Islands, U.S.');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('vn', 'vnm', 'Viet Nam');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('vu', 'vut', 'Vanuatu');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('wf', 'wlf', 'Wallis and Futuna');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ws', 'wsm', 'Samoa');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('ye', 'yem', 'Yemen');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('yt', 'myt', 'Mayotte');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('za', 'zaf', 'South Africa');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('zm', 'zmb', 'Zambia');
INSERT INTO `country` (`iso_alpha_2`, `iso_alpha_3`, `name`) VALUES ('zw', 'zwe', 'Zimbabwe');

COMMIT;


-- -----------------------------------------------------
-- Data for table `gender`
-- -----------------------------------------------------
START TRANSACTION;
USE `boardgames`;
INSERT INTO `gender` (`gender_id`, `name`) VALUES (0, 'not known');
INSERT INTO `gender` (`gender_id`, `name`) VALUES (1, 'male');
INSERT INTO `gender` (`gender_id`, `name`) VALUES (2, 'female');
INSERT INTO `gender` (`gender_id`, `name`) VALUES (9, 'not applicable');

COMMIT;


-- -----------------------------------------------------
-- Data for table `language`
-- -----------------------------------------------------
START TRANSACTION;
USE `boardgames`;
INSERT INTO `language` (`language_code`, `name`) VALUES ('ITA', 'Italian');
INSERT INTO `language` (`language_code`, `name`) VALUES ('ENG', 'English');
INSERT INTO `language` (`language_code`, `name`) VALUES ('RUS', 'Russian');
INSERT INTO `language` (`language_code`, `name`) VALUES ('FRA', 'French');
INSERT INTO `language` (`language_code`, `name`) VALUES ('SPA', 'Spanish');
INSERT INTO `language` (`language_code`, `name`) VALUES ('POR', 'Portuguese');
INSERT INTO `language` (`language_code`, `name`) VALUES ('DEU', 'German');
INSERT INTO `language` (`language_code`, `name`) VALUES ('HBS', 'Serbo-Croatian');
INSERT INTO `language` (`language_code`, `name`) VALUES ('RON', 'Romanian');
INSERT INTO `language` (`language_code`, `name`) VALUES ('SWE', 'Swedish');

COMMIT;

