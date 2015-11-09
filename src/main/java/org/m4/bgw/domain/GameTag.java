package org.m4.bgw.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.m4.bgw.domain.translate.TagTranslation;


@Entity
@Table(name = "game_tag")
public class GameTag {

	@Id
    @Column(name = "name", length = 20)
    private String name;


	@ManyToMany(mappedBy = "gameTags")
    private Set<Boardgame> boardgames;

	@OneToMany(mappedBy = "tagName")
    private Set<TagTranslation> tagTranslations;

	
    public String getName() {
        return this.name;
    }

    public void setName(String id) {
        this.name = id;
    }

    
	public Set<Boardgame> getBoardgames() {
        return boardgames;
    }

	public void setBoardgames(Set<Boardgame> boardgames) {
        this.boardgames = boardgames;
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
                .setExcludeFieldNames("boardgames2", "tagTranslations").toString();
    }
}
