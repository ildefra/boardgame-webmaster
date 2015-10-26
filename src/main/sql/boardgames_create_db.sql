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
-- Table `publisher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `publisher` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `publisher` (
  `name` VARCHAR(50) NOT NULL COMMENT '',
  `website` VARCHAR(255) NOT NULL COMMENT '',
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
  `software_version` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`boardgame_id`)  COMMENT '',
  UNIQUE INDEX `original_name_UNIQUE` (`original_name` ASC)  COMMENT '',
  UNIQUE INDEX `boardgame_id_UNIQUE` (`boardgame_id` ASC)  COMMENT '',
  INDEX `boardgame_has_publisher_fk1_idx` (`publisher` ASC)  COMMENT '',
  CONSTRAINT `boardgame_has_publisher_fk1`
    FOREIGN KEY (`publisher`)
    REFERENCES `publisher` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

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
-- Table `language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `language` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `language` (
  `iso_code` CHAR(3) NOT NULL COMMENT '',
  `name` VARCHAR(50) NOT NULL COMMENT '',
  PRIMARY KEY (`iso_code`)  COMMENT '',
  UNIQUE INDEX `language_code_UNIQUE` (`iso_code` ASC)  COMMENT '',
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
    REFERENCES `language` (`iso_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `user_level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_level` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `user_level` (
  `level_id` TINYINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(20) NOT NULL COMMENT '',
  `can_play` BIT NOT NULL DEFAULT 1 COMMENT '',
  `can_shout` BIT NOT NULL DEFAULT 0 COMMENT '',
  `can_downgrade` BIT NOT NULL DEFAULT 0 COMMENT '',
  PRIMARY KEY (`level_id`)  COMMENT '',
  UNIQUE INDEX `level_id_UNIQUE` (`level_id` ASC)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`name` ASC)  COMMENT '')
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gender`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gender` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `gender` (
  `iso_code` TINYINT UNSIGNED NOT NULL COMMENT '',
  `name` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`iso_code`)  COMMENT '',
  UNIQUE INDEX `gender_id_UNIQUE` (`iso_code` ASC)  COMMENT '',
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
  `motto` TEXT NULL COMMENT '',
  `level_id` TINYINT UNSIGNED NOT NULL COMMENT '',
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
    REFERENCES `gender` (`iso_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

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
    REFERENCES `language` (`iso_code`)
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
    REFERENCES `language` (`iso_code`)
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
    REFERENCES `language` (`iso_code`)
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
  `level_id` TINYINT UNSIGNED NOT NULL COMMENT '',
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
    REFERENCES `language` (`iso_code`)
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
    REFERENCES `language` (`iso_code`)
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
