package org.m4.bgw.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Entity
@Table(name = "language")
public class Language {

    @Id
    @Column(name = "iso_code", length = 3)
    private String isoCode;

    @Column(name = "name", length = 50, unique = true)
    @NotNull
    private String name;

    
	@ManyToMany
    @JoinTable(name = "can_speak",
            joinColumns = { @JoinColumn(name = "language_code", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "username", nullable = false) })
    private Set<Player> players;

	@OneToMany(mappedBy = "languageCode")
    private Set<AchievementTranslation> achievementTranslations;

	@OneToMany(mappedBy = "languageCode")
    private Set<ExternalLink> externalLinks;

	@OneToMany(mappedBy = "languageCode")
    private Set<GameTranslation> gameTranslations;

	@OneToMany(mappedBy = "languageCode")
    private Set<LevelTranslation> levelTranslations;

	@OneToMany(mappedBy = "languageCode")
    private Set<TagTranslation> tagTranslations;


    public String getIsoCode() {
        return this.isoCode;
    }

    public void setIsoCode(String id) {
        this.isoCode = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
	public Set<Player> getPlayers() {
        return players;
    }

	public void setPlayers(Set<Player> players) {
        this.players = players;
    }

	public Set<AchievementTranslation> getAchievementTranslations() {
        return achievementTranslations;
    }

	public void setAchievementTranslations(Set<AchievementTranslation> achievementTranslations) {
        this.achievementTranslations = achievementTranslations;
    }

	public Set<ExternalLink> getExternalLinks() {
        return externalLinks;
    }

	public void setExternalLinks(Set<ExternalLink> externalLinks) {
        this.externalLinks = externalLinks;
    }

	public Set<GameTranslation> getGameTranslations() {
        return gameTranslations;
    }

	public void setGameTranslations(Set<GameTranslation> gameTranslations) {
        this.gameTranslations = gameTranslations;
    }

	public Set<LevelTranslation> getLevelTranslations() {
        return levelTranslations;
    }

	public void setLevelTranslations(Set<LevelTranslation> levelTranslations) {
        this.levelTranslations = levelTranslations;
    }

	public Set<TagTranslation> getTagTranslations() {
        return tagTranslations;
    }

	public void setTagTranslations(Set<TagTranslation> tagTranslations) {
        this.tagTranslations = tagTranslations;
    }


	@Override
	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .setExcludeFieldNames(
                        "players", "achievementTranslations", "externalLinks",
                        "gameTranslations", "levelTranslations", "tagTranslations"
                ).toString();
    }
}
