use `boardgames`;


INSERT INTO `player`
(
	`username`, `email`, `password_md5`,
	`registration_dtm`, `level_id`, `sex`, `birth_date`, `country`,
	`motto`
)
VALUES
	(									-- ferrari
	'alonso', 'alonso@gmail.com', '0911054d8ad47cc256400031197f3e97',
	'2012-06-27 05:25:00', 2, 1, '1981-07-29', 'es',
	'yo lo tiengo como todos'
	),
	(									-- lamerestbleu
	'lagrippe', 'lagrippe@gmail.com', 'dcfdd51f898af72dbc4119e23dedb5b9',
	'2014-10-26 23:23:23', 2, 2, '1987-04-20', 'fr',
	'heureux au jeu, malheureux en amour'
	),
	(									-- gaelic
	'rudegirl', null, '91fbba2125866f0dbdabac1ac6a1cd9b',
	'2014-02-17 17:02:00', 4, 2, '1986-02-08', 'ie',
	'stay hungry stay angry stay rugby'
	),
	(									-- maigret
	'jabbas', 'jabbas@gmail.com', '4dae79db14ec89cb83e820963c3b3fae',
	'2011-03-16 16:03:00', 5, 0, '1983-08-07', 'ua',
	'burning the candle at both ends'
	),
	(									-- magamago
	'sdrea', 'sdrevolution@hotmail.it', '0d29b72d9b3470d42182db0d2e30e5d8',
	'2012-12-21 21:12:12', 2, 2, '1987-03-08', 'it',
	'and the winner is... me!!!'
	),
	(									-- vivalavita
	'prode', 'prodens@gmail.com', 'ede4ca99b6487a1c4930232ff1e91673',
	'2014-08-13 13:08:00', 1, 0, '1979-05-02', 'it',
	'la machina de la sorte decide i destini'
	),
	(									-- fedorovich
	'ivankaramazov', null, 'b006307d25d890fdcee03231742fc912',
	'2013-07-15 15:07:00', 2, 1, '1985-10-14', 'ru',
	'popof is alive'
	),
	(									-- ilovetrains
	'jasper', 'jasper@gmail.com', 'cc0183b797782f01cd388a8cc1a9e76e',
	'2010-09-12 12:09:00', 3, 1, '2001-06-16', 'us',
	'play nice or leave'
	),
	(									-- noodleandsoya
	'pzue', 'zueg@live.com', 'cd4b61795de70bb43d352adb440f1950',
	'2010-01-01 00:00:00', 3, 1, '1972-11-13', 'cn',
	'if the rope is long, the kite will soar'
	),
	(									-- eviltwilight
	'omgponies', 'furrylover@aol.com', 'dc10a55d563c56f9d5deb0b038a4fc5c',
	'2015-03-22 03:23:00', 2, 1, '1980-12-31', 'us',
	'keep calm and wag your tail'
	);


INSERT INTO `achieved`
(
	`username`, `boardgame_id`, `achievement_id`, `on_date`
)
VALUES
	('alonso', 3, 1, '2015-02-07'),
	('alonso', 3, 2, '2015-02-07'),
	('prode', 3, 1, '2015-02-07'),
	('ivankaramazov', 3, 1, '2015-02-07'),

	('lagrippe', 3, 1, '2015-02-09'),
	('rudegirl', 3, 1, '2015-02-09'),
	('jasper', 3, 1, '2015-02-09'),
	('jasper', 3, 2, '2015-02-09'),
	
	('pzue', 3, 1, '2015-06-28'),

	
	('alonso', 1, 1, '2015-06-29'),
	('alonso', 1, 2, '2015-06-29'),
	('alonso', 1, 3, '2015-06-29'),
	('alonso', 1, 4, '2015-06-30'),
	('alonso', 1, 5, '2015-07-05'),
	('alonso', 1, 6, '2015-09-23'),
	
	('alonso', 2, 1, '2015-06-30'),
	('alonso', 2, 2, '2015-07-05'),
	
	('alonso', 8, 1, '2015-10-12'),
	('alonso', 8, 2, '2015-10-12'),

	
	('omgponies', 3, 1, '2013-11-03'),
	('omgponies', 3, 2, '2013-11-07'),
	('omgponies', 3, 3, '2014-04-24'),
	('omgponies', 3, 4, '2014-11-03'),
	('omgponies', 3, 5, '2015-05-08'),
	('omgponies', 3, 6, '2015-07-10'),
	('omgponies', 3, 7, '2015-10-24'),
	
	('omgponies', 6, 1, '2014-08-13'),
	('omgponies', 6, 2, '2014-08-14'),
	('omgponies', 6, 3, '2015-08-13'),
	('omgponies', 6, 4, '2015-09-03'),
	('omgponies', 6, 5, '2015-09-08'),
	('omgponies', 6, 6, '2015-09-23');


INSERT INTO `game_table`
(
	`boardgame_id`, `time_limit_id`, `rated`, `video_recorded`,
	`created_dtm`, `game_started_dtm`, `game_ended_dtm`
)
VALUES
	(
	1, 5, 1, 0,
	'2012-06-27 17:56:00', '2012-06-27 18:00:00', '2012-06-27 18:06:00'
	),
	
	(
	3, 1, 1, 0,
	'2015-02-07 11:23:00', '2015-02-07 11:38:00', '2015-02-07 14:20:00'
	),
	(
	3, 2, 0, 0,
	'2015-02-09 22:00:00', '2015-02-09 22:03:00', '2015-02-10 00:08:00'
	),
	(
	3, 2, 1, 0,
	'2015-06-28 14:08:00', '2015-06-28 14:15:00', '2015-06-28 15:36:00'
	),
	(
	3, 2, 1, 0,
	'2015-07-10 14:25:00', '2015-07-10 14:34:00', '2015-07-10 15:59:00'
	);



INSERT INTO `played` (`username`, `game_table_id`, `score`)
VALUES
	('pzue', 1, 1),
	('omgponies', 1, 0),
	
	('alonso', 2, 55),
	('ivankaramazov', 2, 46),
	('prode', 2, 27),
	
	('jasper', 3, 89),
	('lagrippe', 3, 61),
	('alonso', 3, 61),
	('ivankaramazov', 3, 34),
	('rudegirl', 3, 33),

	('alonso', 4, 109),
	('pzue', 4, 97),

	('omgponies', 5, 132),
	('alonso', 5, 103);
