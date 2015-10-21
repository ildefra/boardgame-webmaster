package org.m4.bgw.domain;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "game_tag")
public class GameTag {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "name", length = 20)
    private String name;

	public String getName() {
        return this.name;
    }

	public void setName(String id) {
        this.name = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("boardgames2", "tagTranslations").toString();
    }

	@ManyToMany(mappedBy = "gameTags2")
    private Set<Boardgame> boardgames2;

	@OneToMany(mappedBy = "tagName")
    private Set<TagTranslation> tagTranslations;

	public Set<Boardgame> getBoardgames2() {
        return boardgames2;
    }

	public void setBoardgames2(Set<Boardgame> boardgames2) {
        this.boardgames2 = boardgames2;
    }

	public Set<TagTranslation> getTagTranslations() {
        return tagTranslations;
    }

	public void setTagTranslations(Set<TagTranslation> tagTranslations) {
        this.tagTranslations = tagTranslations;
    }
}
