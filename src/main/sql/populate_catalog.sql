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
	`developed_by`, `software_version`
)
VALUES
	(
	'Gomoku', null, 700,
	2, 2, 5,
	1, 4, 0, 0,
	null, '1.4.1'
	);
