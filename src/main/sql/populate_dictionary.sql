\. C:\M4\code\boardgame-webmaster\src\main\sql\country_data.sql
\. C:\M4\code\boardgame-webmaster\src\main\sql\gender_data.sql
\. C:\M4\code\boardgame-webmaster\src\main\sql\language_data.sql

use `boardgames`;

INSERT INTO `user_level` (`name`, `can_play`, `can_shout`, `can_downgrade`)
VALUES
	('newbie', 1, 0, 0),
	('regular', 1, 1, 0),
	('admin', 1, 1, 1),
	('silenced', 1, 0, 0),
	('blocked', 0, 0, 0);

INSERT INTO `time_limit` (`name`, `turn-based`)
VALUES
	('realtime - fast pace', 0),
	('realtime - normal pace', 0),
	('realtime - slow pace', 0),
	('turn-based - 2 moves per day', 1),
	('turn-based - 1 move per day', 1),
	('turn-based - 1 move each 2 days', 1),
	('turn-based - no limits', 1);

INSERT INTO `achievement` (`name`, `points`)
VALUES
	('tried the game', 10),
	('first blood', 20),
	('good player', 100),
	('expert', 250),
	('master', 800),
	('pro', 1500),
	('champion', 4000);
