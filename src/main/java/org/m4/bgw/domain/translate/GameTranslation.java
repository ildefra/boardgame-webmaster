package org.m4.bgw.domain.translate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.m4.bgw.domain.Boardgame;
import org.m4.bgw.domain.Language;


@Entity
@Table(name = "game_translation")
public class GameTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

	
    @Column(name = "name", length = 100)
    @NotNull
    private String name;

    @Column(name = "rules")
    @NotNull
    private String rules;

    @Column(name = "rules_link", length = 255, unique = true)
    @NotNull
    private String rulesLink;

    
	@ManyToOne
    @JoinColumn(name = "boardgame_id", referencedColumnName = "boardgame_id", nullable = false)
    private Boardgame boardgameId;

	@ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "iso_code", nullable = false)
    private Language languageCode;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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
	
	
    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
