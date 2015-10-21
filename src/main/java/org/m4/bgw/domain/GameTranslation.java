package org.m4.bgw.domain;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "game_translation")
public class GameTranslation {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("boardgameId", "languageCode").toString();
    }

	@EmbeddedId
    private GameTranslationPK id;

	public GameTranslationPK getId() {
        return this.id;
    }

	public void setId(GameTranslationPK id) {
        this.id = id;
    }

	@ManyToOne
    @JoinColumn(name = "boardgame_id", referencedColumnName = "boardgame_id", nullable = false, insertable = false, updatable = false)
    private Boardgame boardgameId;

	@ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "iso_code", nullable = false, insertable = false, updatable = false)
    private Language languageCode;

	@Column(name = "name", length = 100)
    @NotNull
    private String name;

	@Column(name = "rules")
    @NotNull
    private String rules;

	@Column(name = "rules_link", length = 255, unique = true)
    @NotNull
    private String rulesLink;

	public Boardgame getBoardgameId() {
        return boardgameId;
    }

	public void setBoardgameId(Boardgame boardgameId) {
        this.boardgameId = boardgameId;
    }

	public Language getLanguageCode() {
        return languageCode;
    }

	public void setLanguageCode(Language languageCode) {
        this.languageCode = languageCode;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String getRules() {
        return rules;
    }

	public void setRules(String rules) {
        this.rules = rules;
    }

	public String getRulesLink() {
        return rulesLink;
    }

	public void setRulesLink(String rulesLink) {
        this.rulesLink = rulesLink;
    }
}
