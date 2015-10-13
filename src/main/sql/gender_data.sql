use `boardgames`;

-- List of genders as given in the standard
-- ISO/IEC 5218 Information technology — Codes for the representation of human sexes
-- created by ISO's Data Management and Interchange Technical Committee,
-- proposed in November 1976, and updated in July 2004

INSERT INTO `gender` (`iso_code`, `name`) VALUES
	(0, 'not known'),
	(1, 'male'),
	(2, 'female'),
	(9, 'not applicable');
