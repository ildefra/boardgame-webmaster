package org.m4.bgw.web;

import org.apache.commons.codec.binary.Base64;
import org.m4.bgw.domain.Achieved;
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


@Configurable
/** A central place to register application converters and formatters.  */
public class ApplicationConversionServiceFactoryBean
        extends FormattingConversionServiceFactoryBean {

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
	    
        return new Converter<Achieved, String>() {
            
            @Override
            public String convert(Achieved achieved) {
                return String.valueOf(achieved.getAchievedId());
            }
        };
    }

	public Converter<Integer, Achieved> getIdToAchievedConverter() {
	    
        return new Converter<Integer, Achieved>() {
            
            @Override
            public Achieved convert(Integer id) {
                return achievedRepository.findOne(id);
            }
        };
    }

	public Converter<String, Achieved> getStringToAchievedConverter() {
	    
        return new Converter<String, Achieved>() {
            
            @Override
            public Achieved convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Achieved.class);
            }
        };
    }

	
	public Converter<Achievement, String> getAchievementToStringConverter() {
	    
        return new Converter<Achievement, String>() {
            
            @Override
            public String convert(Achievement achievement) {return achievement.getName(); }
        };
    }

	public Converter<Short, Achievement> getIdToAchievementConverter() {
        return new Converter<Short, Achievement>() {
            public Achievement convert(Short id) {
                return achievementRepository.findOne(id);
            }
        };
    }

	public Converter<String, Achievement> getStringToAchievementConverter() {
        return new Converter<String, Achievement>() {
            public Achievement convert(String id) {
                return getObject().convert(getObject().convert(id, Short.class), Achievement.class);
            }
        };
    }

	
	public Converter<AchievementTranslation, String> getAchievementTranslationToStringConverter() {
        return new Converter<AchievementTranslation, String>() {
            public String convert(AchievementTranslation achievementTranslation) {
                return new StringBuilder().append(achievementTranslation.getName()).toString();
            }
        };
    }

	public Converter<AchievementTranslationPK, AchievementTranslation> getIdToAchievementTranslationConverter() {
        return new Converter<AchievementTranslationPK, AchievementTranslation>() {
            public AchievementTranslation convert(AchievementTranslationPK id) {
                return achievementTranslationRepository.findOne(id);
            }
        };
    }

	public Converter<String, AchievementTranslation> getStringToAchievementTranslationConverter() {
        return new Converter<String, AchievementTranslation>() {
            public AchievementTranslation convert(String id) {
                return getObject().convert(getObject().convert(id, AchievementTranslationPK.class), AchievementTranslation.class);
            }
        };
    }

	
	public Converter<AvgGameLength, String> getAvgGameLengthToStringConverter() {
        return new Converter<AvgGameLength, String>() {
            public String convert(AvgGameLength avgGameLength) {
                return new StringBuilder().append(avgGameLength.getAvgLengthMinutes()).toString();
            }
        };
    }

	public Converter<AvgGameLengthPK, AvgGameLength> getIdToAvgGameLengthConverter() {
        return new Converter<AvgGameLengthPK, AvgGameLength>() {
            public AvgGameLength convert(AvgGameLengthPK id) {
                return avgGameLengthRepository.findOne(id);
            }
        };
    }

	public Converter<String, AvgGameLength> getStringToAvgGameLengthConverter() {
        return new Converter<String, AvgGameLength>() {
            public AvgGameLength convert(String id) {
                return getObject().convert(getObject().convert(id, AvgGameLengthPK.class), AvgGameLength.class);
            }
        };
    }

	
	public Converter<Boardgame, String> getBoardgameToStringConverter() {
	    
        return new Converter<Boardgame, String>() {
            
            @Override
            public String convert(Boardgame boardgame) {return boardgame.getOriginalName(); }
        };
    }

	public Converter<Integer, Boardgame> getIdToBoardgameConverter() {
        return new Converter<Integer, Boardgame>() {
            public Boardgame convert(Integer id) {
                return boardgameRepository.findOne(id);
            }
        };
    }

	public Converter<String, Boardgame> getStringToBoardgameConverter() {
        return new Converter<String, Boardgame>() {
            public Boardgame convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Boardgame.class);
            }
        };
    }

	
	public Converter<Country, String> getCountryToStringConverter() {
	    
        return new Converter<Country, String>() {
            
            @Override
            public String convert(Country country) {return country.getName(); }
        };
    }

	
	public Converter<String, Country> getIdToCountryConverter() {
        return new Converter<String, Country>() {
            public Country convert(String id) {
                return countryRepository.findOne(id);
            }
        };
    }

	
	public Converter<ExternalLink, String> getExternalLinkToStringConverter() {
        return new Converter<ExternalLink, String>() {
            public String convert(ExternalLink externalLink) {
                return new StringBuilder().append(externalLink.getName()).toString();
            }
        };
    }

	public Converter<Integer, ExternalLink> getIdToExternalLinkConverter() {
        return new Converter<Integer, ExternalLink>() {
            public ExternalLink convert(Integer id) {
                return externalLinkRepository.findOne(id);
            }
        };
    }

	public Converter<String, ExternalLink> getStringToExternalLinkConverter() {
        return new Converter<String, ExternalLink>() {
            public ExternalLink convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), ExternalLink.class);
            }
        };
    }

	
	public Converter<GameDesigner, String> getGameDesignerToStringConverter() {
        return new Converter<GameDesigner, String>() {
            
            @Override
            public String convert(GameDesigner gameDesigner) {
                return gameDesigner.getFirstName() + ' ' + gameDesigner.getSurname();
            }
        };
    }

	public Converter<Integer, GameDesigner> getIdToGameDesignerConverter() {
        return new Converter<Integer, GameDesigner>() {
            public GameDesigner convert(Integer id) {
                return gameDesignerRepository.findOne(id);
            }
        };
    }

	public Converter<String, GameDesigner> getStringToGameDesignerConverter() {
        return new Converter<String, GameDesigner>() {
            public GameDesigner convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), GameDesigner.class);
            }
        };
    }

	
	public Converter<GameTable, String> getGameTableToStringConverter() {
	    
        return new Converter<GameTable, String>() {
            
            @Override
            public String convert(GameTable gameTable) {
                return String.valueOf(gameTable.getGameTableId());
            }
        };
    }

	public Converter<Integer, GameTable> getIdToGameTableConverter() {
	    
        return new Converter<Integer, GameTable>() {
            
            @Override
            public GameTable convert(Integer id) {
                return gameTableRepository.findOne(id);
            }
        };
    }

	public Converter<String, GameTable> getStringToGameTableConverter() {
	    
        return new Converter<String, GameTable>() {
            
            @Override
            public GameTable convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), GameTable.class);
            }
        };
    }

	
	public Converter<GameTag, String> getGameTagToStringConverter() {
	    
        return new Converter<GameTag, String>() {
            
            @Override
            public String convert(GameTag gameTag) {return gameTag.getName(); }
        };
    }

	public Converter<String, GameTag> getIdToGameTagConverter() {
        return new Converter<String, GameTag>() {
            public GameTag convert(String id) {
                return gameTagRepository.findOne(id);
            }
        };
    }

	
	public Converter<GameTranslation, String> getGameTranslationToStringConverter() {
        return new Converter<GameTranslation, String>() {
            public String convert(GameTranslation gameTranslation) {
                return new StringBuilder().append(gameTranslation.getName()).append(' ').append(gameTranslation.getRules()).append(' ').append(gameTranslation.getRulesLink()).toString();
            }
        };
    }

	public Converter<GameTranslationPK, GameTranslation> getIdToGameTranslationConverter() {
        return new Converter<GameTranslationPK, GameTranslation>() {
            public GameTranslation convert(GameTranslationPK id) {
                return gameTranslationRepository.findOne(id);
            }
        };
    }

	public Converter<String, GameTranslation> getStringToGameTranslationConverter() {
        return new Converter<String, GameTranslation>() {
            public GameTranslation convert(String id) {
                return getObject().convert(getObject().convert(id, GameTranslationPK.class), GameTranslation.class);
            }
        };
    }

	
	public Converter<Gender, String> getGenderToStringConverter() {
        return new Converter<Gender, String>() {
            
            @Override
            public String convert(Gender gender) {return gender.getName(); }
        };
    }

	public Converter<Short, Gender> getIdToGenderConverter() {
        return new Converter<Short, Gender>() {
            public Gender convert(Short id) {
                return genderRepository.findOne(id);
            }
        };
    }

	public Converter<String, Gender> getStringToGenderConverter() {
        return new Converter<String, Gender>() {
            public Gender convert(String id) {
                return getObject().convert(getObject().convert(id, Short.class), Gender.class);
            }
        };
    }

	
	public Converter<Language, String> getLanguageToStringConverter() {
	    
        return new Converter<Language, String>() {
            
            @Override
            public String convert(Language language) {return language.getName(); }
        };
    }

	public Converter<String, Language> getIdToLanguageConverter() {
        return new Converter<String, Language>() {
            public Language convert(String id) {
                return languageRepository.findOne(id);
            }
        };
    }

	
	public Converter<LevelTranslation, String> getLevelTranslationToStringConverter() {
        return new Converter<LevelTranslation, String>() {
            public String convert(LevelTranslation levelTranslation) {
                return new StringBuilder().append(levelTranslation.getName()).toString();
            }
        };
    }

	public Converter<LevelTranslationPK, LevelTranslation> getIdToLevelTranslationConverter() {
        return new Converter<LevelTranslationPK, LevelTranslation>() {
            public LevelTranslation convert(LevelTranslationPK id) {
                return levelTranslationRepository.findOne(id);
            }
        };
    }

	public Converter<String, LevelTranslation> getStringToLevelTranslationConverter() {
        return new Converter<String, LevelTranslation>() {
            public LevelTranslation convert(String id) {
                return getObject().convert(getObject().convert(id, LevelTranslationPK.class), LevelTranslation.class);
            }
        };
    }

	
	public Converter<Played, String> getPlayedToStringConverter() {
        return new Converter<Played, String>() {
            public String convert(Played played) {
                return new StringBuilder().append(played.getScore()).toString();
            }
        };
    }

	public Converter<PlayedPK, Played> getIdToPlayedConverter() {
        return new Converter<PlayedPK, Played>() {
            public Played convert(PlayedPK id) {
                return playedRepository.findOne(id);
            }
        };
    }

	public Converter<String, Played> getStringToPlayedConverter() {
        return new Converter<String, Played>() {
            public Played convert(String id) {
                return getObject().convert(getObject().convert(id, PlayedPK.class), Played.class);
            }
        };
    }

	
	public Converter<Player, String> getPlayerToStringConverter() {
	    
        return new Converter<Player, String>() {
            
            @Override
            public String convert(Player player) {return player.getUsername(); }
        };
    }

	public Converter<String, Player> getIdToPlayerConverter() {
        return new Converter<String, Player>() {
            public Player convert(String id) {
                return playerRepository.findOne(id);
            }
        };
    }

	
	public Converter<Publisher, String> getPublisherToStringConverter() {
	    
        return new Converter<Publisher, String>() {
            
            @Override
            public String convert(Publisher publisher) {return publisher.getName(); }
        };
    }

	public Converter<String, Publisher> getIdToPublisherConverter() {
        return new Converter<String, Publisher>() {
            public Publisher convert(String id) {
                return publisherRepository.findOne(id);
            }
        };
    }

	
	public Converter<TagTranslation, String> getTagTranslationToStringConverter() {
        return new Converter<TagTranslation, String>() {
            public String convert(TagTranslation tagTranslation) {
                return new StringBuilder().append(tagTranslation.getName()).toString();
            }
        };
    }

	public Converter<TagTranslationPK, TagTranslation> getIdToTagTranslationConverter() {
        return new Converter<TagTranslationPK, TagTranslation>() {
            public TagTranslation convert(TagTranslationPK id) {
                return tagTranslationRepository.findOne(id);
            }
        };
    }

	public Converter<String, TagTranslation> getStringToTagTranslationConverter() {
        return new Converter<String, TagTranslation>() {
            public TagTranslation convert(String id) {
                return getObject().convert(getObject().convert(id, TagTranslationPK.class), TagTranslation.class);
            }
        };
    }

	
	public Converter<TimeLimit, String> getTimeLimitToStringConverter() {
	    
        return new Converter<TimeLimit, String>() {
            
            @Override
            public String convert(TimeLimit timeLimit) {return timeLimit.getName(); }
        };
    }

	public Converter<Integer, TimeLimit> getIdToTimeLimitConverter() {
        return new Converter<Integer, TimeLimit>() {
            public TimeLimit convert(Integer id) {
                return timeLimitRepository.findOne(id);
            }
        };
    }

	public Converter<String, TimeLimit> getStringToTimeLimitConverter() {
        return new Converter<String, TimeLimit>() {
            public TimeLimit convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), TimeLimit.class);
            }
        };
    }

	
	public Converter<UserLevel, String> getUserLevelToStringConverter() {
	    
        return new Converter<UserLevel, String>() {
            
            @Override
            public String convert(UserLevel userLevel) {return userLevel.getName(); }
        };
    }

	public Converter<Short, UserLevel> getIdToUserLevelConverter() {
        return new Converter<Short, UserLevel>() {
            public UserLevel convert(Short id) {
                return userLevelRepository.findOne(id);
            }
        };
    }

	public Converter<String, UserLevel> getStringToUserLevelConverter() {
        return new Converter<String, UserLevel>() {
            public UserLevel convert(String id) {
                return getObject().convert(getObject().convert(id, Short.class), UserLevel.class);
            }
        };
    }

	public Converter<String, TagTranslationPK> getJsonToTagTranslationPKConverter() {
        return new Converter<String, TagTranslationPK>() {
            public TagTranslationPK convert(String encodedJson) {
                return TagTranslationPK.fromJsonToTagTranslationPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<TagTranslationPK, String> getTagTranslationPKToJsonConverter() {
        return new Converter<TagTranslationPK, String>() {
            public String convert(TagTranslationPK tagTranslationPK) {
                return Base64.encodeBase64URLSafeString(tagTranslationPK.toJson().getBytes());
            }
        };
    }

	public Converter<String, AchievementTranslationPK> getJsonToAchievementTranslationPKConverter() {
        return new Converter<String, AchievementTranslationPK>() {
            public AchievementTranslationPK convert(String encodedJson) {
                return AchievementTranslationPK.fromJsonToAchievementTranslationPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<AchievementTranslationPK, String> getAchievementTranslationPKToJsonConverter() {
        return new Converter<AchievementTranslationPK, String>() {
            public String convert(AchievementTranslationPK achievementTranslationPK) {
                return Base64.encodeBase64URLSafeString(achievementTranslationPK.toJson().getBytes());
            }
        };
    }

	public Converter<String, PlayedPK> getJsonToPlayedPKConverter() {
        return new Converter<String, PlayedPK>() {
            public PlayedPK convert(String encodedJson) {
                return PlayedPK.fromJsonToPlayedPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<PlayedPK, String> getPlayedPKToJsonConverter() {
        return new Converter<PlayedPK, String>() {
            public String convert(PlayedPK playedPK) {
                return Base64.encodeBase64URLSafeString(playedPK.toJson().getBytes());
            }
        };
    }

	public Converter<String, GameTranslationPK> getJsonToGameTranslationPKConverter() {
        return new Converter<String, GameTranslationPK>() {
            public GameTranslationPK convert(String encodedJson) {
                return GameTranslationPK.fromJsonToGameTranslationPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<GameTranslationPK, String> getGameTranslationPKToJsonConverter() {
        return new Converter<GameTranslationPK, String>() {
            public String convert(GameTranslationPK gameTranslationPK) {
                return Base64.encodeBase64URLSafeString(gameTranslationPK.toJson().getBytes());
            }
        };
    }

	public Converter<String, AvgGameLengthPK> getJsonToAvgGameLengthPKConverter() {
        return new Converter<String, AvgGameLengthPK>() {
            public AvgGameLengthPK convert(String encodedJson) {
                return AvgGameLengthPK.fromJsonToAvgGameLengthPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<AvgGameLengthPK, String> getAvgGameLengthPKToJsonConverter() {
        return new Converter<AvgGameLengthPK, String>() {
            public String convert(AvgGameLengthPK avgGameLengthPK) {
                return Base64.encodeBase64URLSafeString(avgGameLengthPK.toJson().getBytes());
            }
        };
    }

	public Converter<String, LevelTranslationPK> getJsonToLevelTranslationPKConverter() {
        return new Converter<String, LevelTranslationPK>() {
            public LevelTranslationPK convert(String encodedJson) {
                return LevelTranslationPK.fromJsonToLevelTranslationPK(new String(Base64.decodeBase64(encodedJson)));
            }
        };
    }

	public Converter<LevelTranslationPK, String> getLevelTranslationPKToJsonConverter() {
        return new Converter<LevelTranslationPK, String>() {
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

	@Override
	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
