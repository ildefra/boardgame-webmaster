package org.m4.bgw.web;

import org.apache.commons.codec.binary.Base64;
import org.m4.bgw.domain.Achieved;
import org.m4.bgw.domain.AchievedPK;
import org.m4.bgw.domain.AchievedRepository;
import org.m4.bgw.domain.Achievement;
import org.m4.bgw.domain.AchievementRepository;
import org.m4.bgw.domain.AchievementTranslation;
import org.m4.bgw.domain.AchievementTranslationPK;
import org.m4.bgw.domain.AchievementTranslationRepository;
import org.m4.bgw.domain.AvgGameLength;
import org.m4.bgw.domain.AvgGameLengthPK;
import org.m4.bgw.domain.AvgGameLengthRepository;
import org.m4.bgw.domain.Boardgame;
import org.m4.bgw.domain.BoardgameRepository;
import org.m4.bgw.domain.Country;
import org.m4.bgw.domain.CountryRepository;
import org.m4.bgw.domain.ExternalLink;
import org.m4.bgw.domain.ExternalLinkRepository;
import org.m4.bgw.domain.GameDesigner;
import org.m4.bgw.domain.GameDesignerRepository;
import org.m4.bgw.domain.GameTable;
import org.m4.bgw.domain.GameTableRepository;
import org.m4.bgw.domain.GameTag;
import org.m4.bgw.domain.GameTagRepository;
import org.m4.bgw.domain.GameTranslation;
import org.m4.bgw.domain.GameTranslationPK;
import org.m4.bgw.domain.GameTranslationRepository;
import org.m4.bgw.domain.Gender;
import org.m4.bgw.domain.GenderRepository;
import org.m4.bgw.domain.Language;
import org.m4.bgw.domain.LanguageRepository;
import org.m4.bgw.domain.LevelTranslation;
import org.m4.bgw.domain.LevelTranslationPK;
import org.m4.bgw.domain.LevelTranslationRepository;
import org.m4.bgw.domain.Played;
import org.m4.bgw.domain.PlayedPK;
import org.m4.bgw.domain.PlayedRepository;
import org.m4.bgw.domain.Player;
import org.m4.bgw.domain.PlayerRepository;
import org.m4.bgw.domain.Publisher;
import org.m4.bgw.domain.PublisherRepository;
import org.m4.bgw.domain.TagTranslation;
import org.m4.bgw.domain.TagTranslationPK;
import org.m4.bgw.domain.TagTranslationRepository;
import org.m4.bgw.domain.TimeLimit;
import org.m4.bgw.domain.TimeLimitRepository;
import org.m4.bgw.domain.UserLevel;
import org.m4.bgw.domain.UserLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.annotations.converter.RooConversionService;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	@Autowired
    AchievedRepository achievedRepository;

	@Autowired
    AchievementRepository achievementRepository;

	@Autowired
    AchievementTranslationRepository achievementTranslationRepository;

	@Autowired
    AvgGameLengthRepository avgGameLengthRepository;

	@Autowired
    BoardgameRepository boardgameRepository;

	@Autowired
    CountryRepository countryRepository;

	@Autowired
    ExternalLinkRepository externalLinkRepository;

	@Autowired
    GameDesignerRepository gameDesignerRepository;

	@Autowired
    GameTableRepository gameTableRepository;

	@Autowired
    GameTagRepository gameTagRepository;

	@Autowired
    GameTranslationRepository gameTranslationRepository;

	@Autowired
    GenderRepository genderRepository;

	@Autowired
    LanguageRepository languageRepository;

	@Autowired
    LevelTranslationRepository levelTranslationRepository;

	@Autowired
    PlayedRepository playedRepository;

	@Autowired
    PlayerRepository playerRepository;

	@Autowired
    PublisherRepository publisherRepository;

	@Autowired
    TagTranslationRepository tagTranslationRepository;

	@Autowired
    TimeLimitRepository timeLimitRepository;

	@Autowired
    UserLevelRepository userLevelRepository;

	public Converter<Achieved, String> getAchievedToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.Achieved, java.lang.String>() {
            public String convert(Achieved achieved) {
                return new StringBuilder().append(achieved.getOnDate()).toString();
            }
        };
    }

	public Converter<AchievedPK, Achieved> getIdToAchievedConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.AchievedPK, org.m4.bgw.domain.Achieved>() {
            public org.m4.bgw.domain.Achieved convert(org.m4.bgw.domain.AchievedPK id) {
                return achievedRepository.findOne(id);
            }
        };
    }

	public Converter<String, Achieved> getStringToAchievedConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.Achieved>() {
            public org.m4.bgw.domain.Achieved convert(String id) {
                return getObject().convert(getObject().convert(id, AchievedPK.class), Achieved.class);
            }
        };
    }

	public Converter<Achievement, String> getAchievementToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.Achievement, java.lang.String>() {
            public String convert(Achievement achievement) {
                return new StringBuilder().append(achievement.getPoints()).append(' ').append(achievement.getName()).toString();
            }
        };
    }

	public Converter<Short, Achievement> getIdToAchievementConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Short, org.m4.bgw.domain.Achievement>() {
            public org.m4.bgw.domain.Achievement convert(java.lang.Short id) {
                return achievementRepository.findOne(id);
            }
        };
    }

	public Converter<String, Achievement> getStringToAchievementConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.Achievement>() {
            public org.m4.bgw.domain.Achievement convert(String id) {
                return getObject().convert(getObject().convert(id, Short.class), Achievement.class);
            }
        };
    }

	public Converter<AchievementTranslation, String> getAchievementTranslationToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.AchievementTranslation, java.lang.String>() {
            public String convert(AchievementTranslation achievementTranslation) {
                return new StringBuilder().append(achievementTranslation.getName()).toString();
            }
        };
    }

	public Converter<AchievementTranslationPK, AchievementTranslation> getIdToAchievementTranslationConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.AchievementTranslationPK, org.m4.bgw.domain.AchievementTranslation>() {
            public org.m4.bgw.domain.AchievementTranslation convert(org.m4.bgw.domain.AchievementTranslationPK id) {
                return achievementTranslationRepository.findOne(id);
            }
        };
    }

	public Converter<String, AchievementTranslation> getStringToAchievementTranslationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.AchievementTranslation>() {
            public org.m4.bgw.domain.AchievementTranslation convert(String id) {
                return getObject().convert(getObject().convert(id, AchievementTranslationPK.class), AchievementTranslation.class);
            }
        };
    }

	public Converter<AvgGameLength, String> getAvgGameLengthToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.AvgGameLength, java.lang.String>() {
            public String convert(AvgGameLength avgGameLength) {
                return new StringBuilder().append(avgGameLength.getAvgLengthMinutes()).toString();
            }
        };
    }

	public Converter<AvgGameLengthPK, AvgGameLength> getIdToAvgGameLengthConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.AvgGameLengthPK, org.m4.bgw.domain.AvgGameLength>() {
            public org.m4.bgw.domain.AvgGameLength convert(org.m4.bgw.domain.AvgGameLengthPK id) {
                return avgGameLengthRepository.findOne(id);
            }
        };
    }

	public Converter<String, AvgGameLength> getStringToAvgGameLengthConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.AvgGameLength>() {
            public org.m4.bgw.domain.AvgGameLength convert(String id) {
                return getObject().convert(getObject().convert(id, AvgGameLengthPK.class), AvgGameLength.class);
            }
        };
    }

	public Converter<Boardgame, String> getBoardgameToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.Boardgame, java.lang.String>() {
            public String convert(Boardgame boardgame) {
                return new StringBuilder().append(boardgame.getOriginalName()).append(' ').append(boardgame.getPublicationYear()).append(' ').append(boardgame.getPlayersMin()).append(' ').append(boardgame.getPlayersMax()).toString();
            }
        };
    }

	public Converter<Integer, Boardgame> getIdToBoardgameConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.m4.bgw.domain.Boardgame>() {
            public org.m4.bgw.domain.Boardgame convert(java.lang.Integer id) {
                return boardgameRepository.findOne(id);
            }
        };
    }

	public Converter<String, Boardgame> getStringToBoardgameConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.Boardgame>() {
            public org.m4.bgw.domain.Boardgame convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Boardgame.class);
            }
        };
    }

	public Converter<Country, String> getCountryToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.Country, java.lang.String>() {
            public String convert(Country country) {
                return new StringBuilder().append(country.getIsoAlpha3()).append(' ').append(country.getName()).toString();
            }
        };
    }

	public Converter<String, Country> getIdToCountryConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.Country>() {
            public org.m4.bgw.domain.Country convert(java.lang.String id) {
                return countryRepository.findOne(id);
            }
        };
    }

	public Converter<ExternalLink, String> getExternalLinkToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.ExternalLink, java.lang.String>() {
            public String convert(ExternalLink externalLink) {
                return new StringBuilder().append(externalLink.getName()).toString();
            }
        };
    }

	public Converter<Integer, ExternalLink> getIdToExternalLinkConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.m4.bgw.domain.ExternalLink>() {
            public org.m4.bgw.domain.ExternalLink convert(java.lang.Integer id) {
                return externalLinkRepository.findOne(id);
            }
        };
    }

	public Converter<String, ExternalLink> getStringToExternalLinkConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.ExternalLink>() {
            public org.m4.bgw.domain.ExternalLink convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), ExternalLink.class);
            }
        };
    }

	public Converter<GameDesigner, String> getGameDesignerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.GameDesigner, java.lang.String>() {
            public String convert(GameDesigner gameDesigner) {
                return new StringBuilder().append(gameDesigner.getFirstName()).append(' ').append(gameDesigner.getSurname()).toString();
            }
        };
    }

	public Converter<Integer, GameDesigner> getIdToGameDesignerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.m4.bgw.domain.GameDesigner>() {
            public org.m4.bgw.domain.GameDesigner convert(java.lang.Integer id) {
                return gameDesignerRepository.findOne(id);
            }
        };
    }

	public Converter<String, GameDesigner> getStringToGameDesignerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.GameDesigner>() {
            public org.m4.bgw.domain.GameDesigner convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), GameDesigner.class);
            }
        };
    }

	public Converter<GameTable, String> getGameTableToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.GameTable, java.lang.String>() {
            public String convert(GameTable gameTable) {
                return new StringBuilder().append(gameTable.getCreatedDtm()).append(' ').append(gameTable.getGameStartedDtm()).append(' ').append(gameTable.getGameEndedDtm()).toString();
            }
        };
    }

	public Converter<Integer, GameTable> getIdToGameTableConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.m4.bgw.domain.GameTable>() {
            public org.m4.bgw.domain.GameTable convert(java.lang.Integer id) {
                return gameTableRepository.findOne(id);
            }
        };
    }

	public Converter<String, GameTable> getStringToGameTableConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.GameTable>() {
            public org.m4.bgw.domain.GameTable convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), GameTable.class);
            }
        };
    }

	public Converter<GameTag, String> getGameTagToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.GameTag, java.lang.String>() {
            public String convert(GameTag gameTag) {
                return "(no displayable fields)";
            }
        };
    }

	public Converter<String, GameTag> getIdToGameTagConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.GameTag>() {
            public org.m4.bgw.domain.GameTag convert(java.lang.String id) {
                return gameTagRepository.findOne(id);
            }
        };
    }

	public Converter<GameTranslation, String> getGameTranslationToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.GameTranslation, java.lang.String>() {
            public String convert(GameTranslation gameTranslation) {
                return new StringBuilder().append(gameTranslation.getName()).append(' ').append(gameTranslation.getRules()).append(' ').append(gameTranslation.getRulesLink()).toString();
            }
        };
    }

	public Converter<GameTranslationPK, GameTranslation> getIdToGameTranslationConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.GameTranslationPK, org.m4.bgw.domain.GameTranslation>() {
            public org.m4.bgw.domain.GameTranslation convert(org.m4.bgw.domain.GameTranslationPK id) {
                return gameTranslationRepository.findOne(id);
            }
        };
    }

	public Converter<String, GameTranslation> getStringToGameTranslationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.GameTranslation>() {
            public org.m4.bgw.domain.GameTranslation convert(String id) {
                return getObject().convert(getObject().convert(id, GameTranslationPK.class), GameTranslation.class);
            }
        };
    }

	public Converter<Gender, String> getGenderToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.Gender, java.lang.String>() {
            public String convert(Gender gender) {
                return new StringBuilder().append(gender.getName()).toString();
            }
        };
    }

	public Converter<Short, Gender> getIdToGenderConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Short, org.m4.bgw.domain.Gender>() {
            public org.m4.bgw.domain.Gender convert(java.lang.Short id) {
                return genderRepository.findOne(id);
            }
        };
    }

	public Converter<String, Gender> getStringToGenderConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.Gender>() {
            public org.m4.bgw.domain.Gender convert(String id) {
                return getObject().convert(getObject().convert(id, Short.class), Gender.class);
            }
        };
    }

	public Converter<Language, String> getLanguageToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.Language, java.lang.String>() {
            public String convert(Language language) {
                return new StringBuilder().append(language.getName()).toString();
            }
        };
    }

	public Converter<String, Language> getIdToLanguageConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.Language>() {
            public org.m4.bgw.domain.Language convert(java.lang.String id) {
                return languageRepository.findOne(id);
            }
        };
    }

	public Converter<LevelTranslation, String> getLevelTranslationToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.LevelTranslation, java.lang.String>() {
            public String convert(LevelTranslation levelTranslation) {
                return new StringBuilder().append(levelTranslation.getName()).toString();
            }
        };
    }

	public Converter<LevelTranslationPK, LevelTranslation> getIdToLevelTranslationConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.LevelTranslationPK, org.m4.bgw.domain.LevelTranslation>() {
            public org.m4.bgw.domain.LevelTranslation convert(org.m4.bgw.domain.LevelTranslationPK id) {
                return levelTranslationRepository.findOne(id);
            }
        };
    }

	public Converter<String, LevelTranslation> getStringToLevelTranslationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.LevelTranslation>() {
            public org.m4.bgw.domain.LevelTranslation convert(String id) {
                return getObject().convert(getObject().convert(id, LevelTranslationPK.class), LevelTranslation.class);
            }
        };
    }

	public Converter<Played, String> getPlayedToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.Played, java.lang.String>() {
            public String convert(Played played) {
                return new StringBuilder().append(played.getScore()).toString();
            }
        };
    }

	public Converter<PlayedPK, Played> getIdToPlayedConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.PlayedPK, org.m4.bgw.domain.Played>() {
            public org.m4.bgw.domain.Played convert(org.m4.bgw.domain.PlayedPK id) {
                return playedRepository.findOne(id);
            }
        };
    }

	public Converter<String, Played> getStringToPlayedConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.Played>() {
            public org.m4.bgw.domain.Played convert(String id) {
                return getObject().convert(getObject().convert(id, PlayedPK.class), Played.class);
            }
        };
    }

	public Converter<Player, String> getPlayerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.Player, java.lang.String>() {
            public String convert(Player player) {
                return new StringBuilder().append(player.getEmail()).append(' ').append(player.getPassword()).append(' ').append(player.getRegistrationDtm()).append(' ').append(player.getBirthDate()).toString();
            }
        };
    }

	public Converter<String, Player> getIdToPlayerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.Player>() {
            public org.m4.bgw.domain.Player convert(java.lang.String id) {
                return playerRepository.findOne(id);
            }
        };
    }

	public Converter<Publisher, String> getPublisherToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.Publisher, java.lang.String>() {
            public String convert(Publisher publisher) {
                return new StringBuilder().append(publisher.getWebsite()).toString();
            }
        };
    }

	public Converter<String, Publisher> getIdToPublisherConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.Publisher>() {
            public org.m4.bgw.domain.Publisher convert(java.lang.String id) {
                return publisherRepository.findOne(id);
            }
        };
    }

	public Converter<TagTranslation, String> getTagTranslationToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.TagTranslation, java.lang.String>() {
            public String convert(TagTranslation tagTranslation) {
                return new StringBuilder().append(tagTranslation.getName()).toString();
            }
        };
    }

	public Converter<TagTranslationPK, TagTranslation> getIdToTagTranslationConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.TagTranslationPK, org.m4.bgw.domain.TagTranslation>() {
            public org.m4.bgw.domain.TagTranslation convert(org.m4.bgw.domain.TagTranslationPK id) {
                return tagTranslationRepository.findOne(id);
            }
        };
    }

	public Converter<String, TagTranslation> getStringToTagTranslationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.TagTranslation>() {
            public org.m4.bgw.domain.TagTranslation convert(String id) {
                return getObject().convert(getObject().convert(id, TagTranslationPK.class), TagTranslation.class);
            }
        };
    }

	public Converter<TimeLimit, String> getTimeLimitToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.TimeLimit, java.lang.String>() {
            public String convert(TimeLimit timeLimit) {
                return new StringBuilder().append(timeLimit.getName()).toString();
            }
        };
    }

	public Converter<Integer, TimeLimit> getIdToTimeLimitConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.m4.bgw.domain.TimeLimit>() {
            public org.m4.bgw.domain.TimeLimit convert(java.lang.Integer id) {
                return timeLimitRepository.findOne(id);
            }
        };
    }

	public Converter<String, TimeLimit> getStringToTimeLimitConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.TimeLimit>() {
            public org.m4.bgw.domain.TimeLimit convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), TimeLimit.class);
            }
        };
    }

	public Converter<UserLevel, String> getUserLevelToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.UserLevel, java.lang.String>() {
            public String convert(UserLevel userLevel) {
                return "(no displayable fields)";
            }
        };
    }

	public Converter<Short, UserLevel> getIdToUserLevelConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Short, org.m4.bgw.domain.UserLevel>() {
            public org.m4.bgw.domain.UserLevel convert(java.lang.Short id) {
                return userLevelRepository.findOne(id);
            }
        };
    }

	public Converter<String, UserLevel> getStringToUserLevelConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.UserLevel>() {
            public org.m4.bgw.domain.UserLevel convert(String id) {
                return getObject().convert(getObject().convert(id, Short.class), UserLevel.class);
            }
        };
    }

	public Converter<String, AchievedPK> getJsonToAchievedPKConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.AchievedPK>() {
            public AchievedPK convert(String encodedJson) {
                return AchievedPK.fromJsonToAchievedPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<AchievedPK, String> getAchievedPKToJsonConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.AchievedPK, java.lang.String>() {
            public String convert(AchievedPK achievedPK) {
                return Base64.encodeBase64URLSafeString(achievedPK.toJson().getBytes());
            }
        };
    }

	public Converter<String, TagTranslationPK> getJsonToTagTranslationPKConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.TagTranslationPK>() {
            public TagTranslationPK convert(String encodedJson) {
                return TagTranslationPK.fromJsonToTagTranslationPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<TagTranslationPK, String> getTagTranslationPKToJsonConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.TagTranslationPK, java.lang.String>() {
            public String convert(TagTranslationPK tagTranslationPK) {
                return Base64.encodeBase64URLSafeString(tagTranslationPK.toJson().getBytes());
            }
        };
    }

	public Converter<String, AchievementTranslationPK> getJsonToAchievementTranslationPKConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.AchievementTranslationPK>() {
            public AchievementTranslationPK convert(String encodedJson) {
                return AchievementTranslationPK.fromJsonToAchievementTranslationPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<AchievementTranslationPK, String> getAchievementTranslationPKToJsonConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.AchievementTranslationPK, java.lang.String>() {
            public String convert(AchievementTranslationPK achievementTranslationPK) {
                return Base64.encodeBase64URLSafeString(achievementTranslationPK.toJson().getBytes());
            }
        };
    }

	public Converter<String, PlayedPK> getJsonToPlayedPKConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.PlayedPK>() {
            public PlayedPK convert(String encodedJson) {
                return PlayedPK.fromJsonToPlayedPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<PlayedPK, String> getPlayedPKToJsonConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.PlayedPK, java.lang.String>() {
            public String convert(PlayedPK playedPK) {
                return Base64.encodeBase64URLSafeString(playedPK.toJson().getBytes());
            }
        };
    }

	public Converter<String, GameTranslationPK> getJsonToGameTranslationPKConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.GameTranslationPK>() {
            public GameTranslationPK convert(String encodedJson) {
                return GameTranslationPK.fromJsonToGameTranslationPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<GameTranslationPK, String> getGameTranslationPKToJsonConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.GameTranslationPK, java.lang.String>() {
            public String convert(GameTranslationPK gameTranslationPK) {
                return Base64.encodeBase64URLSafeString(gameTranslationPK.toJson().getBytes());
            }
        };
    }

	public Converter<String, AvgGameLengthPK> getJsonToAvgGameLengthPKConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.AvgGameLengthPK>() {
            public AvgGameLengthPK convert(String encodedJson) {
                return AvgGameLengthPK.fromJsonToAvgGameLengthPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<AvgGameLengthPK, String> getAvgGameLengthPKToJsonConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.AvgGameLengthPK, java.lang.String>() {
            public String convert(AvgGameLengthPK avgGameLengthPK) {
                return Base64.encodeBase64URLSafeString(avgGameLengthPK.toJson().getBytes());
            }
        };
    }

	public Converter<String, LevelTranslationPK> getJsonToLevelTranslationPKConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.domain.LevelTranslationPK>() {
            public LevelTranslationPK convert(String encodedJson) {
                return LevelTranslationPK.fromJsonToLevelTranslationPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<LevelTranslationPK, String> getLevelTranslationPKToJsonConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.domain.LevelTranslationPK, java.lang.String>() {
            public String convert(LevelTranslationPK levelTranslationPK) {
                return Base64.encodeBase64URLSafeString(levelTranslationPK.toJson().getBytes());
            }
        };
    }

	public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getAchievedToStringConverter());
        registry.addConverter(getIdToAchievedConverter());
        registry.addConverter(getStringToAchievedConverter());
        registry.addConverter(getAchievementToStringConverter());
        registry.addConverter(getIdToAchievementConverter());
        registry.addConverter(getStringToAchievementConverter());
        registry.addConverter(getAchievementTranslationToStringConverter());
        registry.addConverter(getIdToAchievementTranslationConverter());
        registry.addConverter(getStringToAchievementTranslationConverter());
        registry.addConverter(getAvgGameLengthToStringConverter());
        registry.addConverter(getIdToAvgGameLengthConverter());
        registry.addConverter(getStringToAvgGameLengthConverter());
        registry.addConverter(getBoardgameToStringConverter());
        registry.addConverter(getIdToBoardgameConverter());
        registry.addConverter(getStringToBoardgameConverter());
        registry.addConverter(getCountryToStringConverter());
        registry.addConverter(getIdToCountryConverter());
        registry.addConverter(getExternalLinkToStringConverter());
        registry.addConverter(getIdToExternalLinkConverter());
        registry.addConverter(getStringToExternalLinkConverter());
        registry.addConverter(getGameDesignerToStringConverter());
        registry.addConverter(getIdToGameDesignerConverter());
        registry.addConverter(getStringToGameDesignerConverter());
        registry.addConverter(getGameTableToStringConverter());
        registry.addConverter(getIdToGameTableConverter());
        registry.addConverter(getStringToGameTableConverter());
        registry.addConverter(getGameTagToStringConverter());
        registry.addConverter(getIdToGameTagConverter());
        registry.addConverter(getGameTranslationToStringConverter());
        registry.addConverter(getIdToGameTranslationConverter());
        registry.addConverter(getStringToGameTranslationConverter());
        registry.addConverter(getGenderToStringConverter());
        registry.addConverter(getIdToGenderConverter());
        registry.addConverter(getStringToGenderConverter());
        registry.addConverter(getLanguageToStringConverter());
        registry.addConverter(getIdToLanguageConverter());
        registry.addConverter(getLevelTranslationToStringConverter());
        registry.addConverter(getIdToLevelTranslationConverter());
        registry.addConverter(getStringToLevelTranslationConverter());
        registry.addConverter(getPlayedToStringConverter());
        registry.addConverter(getIdToPlayedConverter());
        registry.addConverter(getStringToPlayedConverter());
        registry.addConverter(getPlayerToStringConverter());
        registry.addConverter(getIdToPlayerConverter());
        registry.addConverter(getPublisherToStringConverter());
        registry.addConverter(getIdToPublisherConverter());
        registry.addConverter(getTagTranslationToStringConverter());
        registry.addConverter(getIdToTagTranslationConverter());
        registry.addConverter(getStringToTagTranslationConverter());
        registry.addConverter(getTimeLimitToStringConverter());
        registry.addConverter(getIdToTimeLimitConverter());
        registry.addConverter(getStringToTimeLimitConverter());
        registry.addConverter(getUserLevelToStringConverter());
        registry.addConverter(getIdToUserLevelConverter());
        registry.addConverter(getStringToUserLevelConverter());
        registry.addConverter(getJsonToAchievedPKConverter());
        registry.addConverter(getAchievedPKToJsonConverter());
        registry.addConverter(getJsonToTagTranslationPKConverter());
        registry.addConverter(getTagTranslationPKToJsonConverter());
        registry.addConverter(getJsonToAchievementTranslationPKConverter());
        registry.addConverter(getAchievementTranslationPKToJsonConverter());
        registry.addConverter(getJsonToPlayedPKConverter());
        registry.addConverter(getPlayedPKToJsonConverter());
        registry.addConverter(getJsonToGameTranslationPKConverter());
        registry.addConverter(getGameTranslationPKToJsonConverter());
        registry.addConverter(getJsonToAvgGameLengthPKConverter());
        registry.addConverter(getAvgGameLengthPKToJsonConverter());
        registry.addConverter(getJsonToLevelTranslationPKConverter());
        registry.addConverter(getLevelTranslationPKToJsonConverter());
    }

	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
