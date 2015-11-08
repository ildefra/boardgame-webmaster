package org.m4.bgw.domain;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "boardgame")
public class Boardgame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardgame_id")
    private Integer boardgameId;
	
	@Column(name = "original_name", length = 50, unique = true)
    @NotNull
    private String originalName;

	@Column(name = "publication_year")
    private Short publicationYear;

	@Column(name = "players_min")
    @NotNull
    private Short playersMin;

	@Column(name = "players_max")
    @NotNull
    private Short playersMax;

	@Column(name = "avg_length_minutes")
    @NotNull
    private Short avgLengthMinutes;

	@Column(name = "complexity_score")
    @NotNull
    private Short complexityScore;

	@Column(name = "strategy_score")
    @NotNull
    private Short strategyScore;

	@Column(name = "luck_score")
    @NotNull
    private Short luckScore;

	@Column(name = "interaction_score")
    @NotNull
    private Short interactionScore;

	@Column(name = "software_version", length = 20)
    @NotNull
    private String softwareVersion;

	
    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;


    @ManyToMany
    @JoinTable(name = "artwork",
            joinColumns = { @JoinColumn(name = "boardgame_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "game_designer_id", nullable = false) })
    private Set<GameDesigner> gameDesigners;

    @ManyToMany
    @JoinTable(name = "autorship",
            joinColumns = { @JoinColumn(name = "boardgame_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "game_designer_id", nullable = false) })
    private Set<GameDesigner> gameDesigners1;

    @ManyToMany
    @JoinTable(name = "has_tag",
            joinColumns = { @JoinColumn(name = "boardgame_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "tag_name", nullable = false) })
    private Set<GameTag> gameTags;

    @OneToMany(mappedBy = "boardgame")
    private Set<Achieved> achieveds;

    @OneToMany(mappedBy = "boardgameId")
    private Set<AvgGameLength> avgGameLengths;

    @OneToMany(mappedBy = "boardgameId")
    private Set<ExternalLink> externalLinks;

    @OneToMany(mappedBy = "boardgameId")
    private Set<GameTable> gameTables;

    @OneToMany(mappedBy = "boardgameId")
    private Set<GameTranslation> gameTranslations;

    
    public Integer getBoardgameId() {
        return this.boardgameId;
    }

    public void setBoardgameId(Integer id) {
        this.boardgameId = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Short getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Short publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Short getPlayersMin() {
        return playersMin;
    }

    public void setPlayersMin(Short playersMin) {
        this.playersMin = playersMin;
    }

    public Short getPlayersMax() {
        return playersMax;
    }

    public void setPlayersMax(Short playersMax) {
        this.playersMax = playersMax;
    }

    public Short getAvgLengthMinutes() {
        return avgLengthMinutes;
    }

    public void setAvgLengthMinutes(Short avgLengthMinutes) {
        this.avgLengthMinutes = avgLengthMinutes;
    }

    public Short getComplexityScore() {
        return complexityScore;
    }

    public void setComplexityScore(Short complexityScore) {
        this.complexityScore = complexityScore;
    }

    public Short getStrategyScore() {
        return strategyScore;
    }

    public void setStrategyScore(Short strategyScore) {
        this.strategyScore = strategyScore;
    }

    public Short getLuckScore() {
        return luckScore;
    }

    public void setLuckScore(Short luckScore) {
        this.luckScore = luckScore;
    }

    public Short getInteractionScore() {
        return interactionScore;
    }

    public void setInteractionScore(Short interactionScore) {
        this.interactionScore = interactionScore;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    
	public Set<GameDesigner> getGameDesigners() {
        return gameDesigners;
    }

	public void setGameDesigners(Set<GameDesigner> gameDesigners) {
        this.gameDesigners = gameDesigners;
    }

	public Set<GameDesigner> getGameDesigners1() {
        return gameDesigners1;
    }

	public void setGameDesigners1(Set<GameDesigner> gameDesigners1) {
        this.gameDesigners1 = gameDesigners1;
    }

	public Set<GameTag> getGameTags() {
        return gameTags;
    }

	public void setGameTags(Set<GameTag> gameTags) {
        this.gameTags = gameTags;
    }

	public Set<Achieved> getAchieveds() {
        return achieveds;
    }

	public void setAchieveds(Set<Achieved> achieveds) {
        this.achieveds = achieveds;
    }

	public Set<AvgGameLength> getAvgGameLengths() {
        return avgGameLengths;
    }

	public void setAvgGameLengths(Set<AvgGameLength> avgGameLengths) {
        this.avgGameLengths = avgGameLengths;
    }

	public Set<ExternalLink> getExternalLinks() {
        return externalLinks;
    }

	public void setExternalLinks(Set<ExternalLink> externalLinks) {
        this.externalLinks = externalLinks;
    }

	public Set<GameTable> getGameTables() {
        return gameTables;
    }

	public void setGameTables(Set<GameTable> gameTables) {
        this.gameTables = gameTables;
    }

	public Set<GameTranslation> getGameTranslations() {
        return gameTranslations;
    }

	public void setGameTranslations(Set<GameTranslation> gameTranslations) {
        this.gameTranslations = gameTranslations;
    }


	@Override
	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .setExcludeFieldNames(
                        "gameDesigners", "gameDesigners1", "gameTags", "achieveds",
                        "avgGameLengths", "externalLinks", "gameTables", "gameTranslations",
                        "publisher"
                ).toString();
    }
}
