USE `boardgames`;

CREATE TABLE IF NOT EXISTS `openjpa_sequence_table` (
	`id`				TINYINT UNSIGNED NOT NULL,
	`sequence_value`	BIGINT(20) DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;

SHOW WARNINGS;
