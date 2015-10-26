use `boardgames`;

INSERT INTO `publisher` (`name`, `website`)
VALUES
	('Ystari Games', 'http://www.ystari.com'),
	('Hans Im Gluck', 'http://www.hans-im-glueck.de/'),
	('Amigo', 'https://www.amigo-spiele.de/'),
	('Level 99 Games', 'http://www.lvl99games.com/'),
	('Libellud', 'http://www.libellud.com/');

INSERT INTO `game_designer` (`first_name`, `surname`)
VALUES
	('William', 'Attia'),
	('Arnaud', 'Demaegd'),
	('Michael', 'Tummelhofer'),
	('Michael', 'Menzel'),
	('Wolfgang', 'Kramer'),
	('Franz', 'Vohwinkel'),
	('Brad', 'Talton, Jr.'),
	('Fabio', 'Fontes'),
	('Regis', 'Bonnessee'),
	('Xavier', 'Durin'),
	('Michael', 'Kiesling');

INSERT INTO `game_tag` (`name`)
VALUES
	('abstract'), ('prizewinner'), ('prototype'), ('classic'), ('fantasy'),
	('scifi'), ('history'), ('adventure'), ('exploration'), ('conquest'),
	('building'), ('cardgame'), ('dice'), ('workers'), ('bluffing'),
	('tiles'), ('race');


INSERT INTO `boardgame`
(
	`original_name`, `publisher`, `publication_year`,
	`players_min`, `players_max`, `avg_length_minutes`,
	`complexity_score`, `strategy_score`, `luck_score`, `interaction_score`,
	`software_version`
)
VALUES
	(
	'Gomoku', null, 700,
	2, 2, 10,
	1, 4, 0, 0,
	'2.0.2'
	),
	(
	'Chess', null, 1475,
	2, 2, 30,
	1, 4, 0, 0,
	'2.1.0'
	),
	(
	'Caylus', 'Ystari Games', 2005,
	2, 5, 90,
	4, 5, 0, 2,
	'2.0.6'
	),
	(
	'Stone Age', 'Hans Im Gluck', 2008,
	2, 4, 60,
	3, 3, 3, 1,
	'2.2.6'
	),
	(
	'6 Nimmt!', 'Amigo', 1994,
	2, 10, 20,
	1, 1, 4, 3,
	'1.5.5'
	),
	(
	'Noir: Killer vs. Inspector', 'Level 99 Games', 2012,
	2, 2, 10,
	2, 3, 3, 0,
	'2.3.3'
	),
	(
	'Machiavelli', null, null,
	2, 5, 30,
	1, 1, 3, 0,
	'2.0.1'
	),
	(
	'Seasons', 'Libellud', 2012,
	2, 4, 40,
	3, 4, 2, 1,
	'1.9.3'
	),
	(
	'Carrara', 'Hans Im Gluck', 2012,
	2, 4, 40,
	2, 2, 1, 1,
	'0.0.2'
	);
