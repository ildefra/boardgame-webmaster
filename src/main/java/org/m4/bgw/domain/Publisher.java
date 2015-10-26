package org.m4.bgw.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Entity
@Table(name = "publisher")
public class Publisher {
	
    @Id
    @Column(name = "name", length = 50)
    private String name;

	@Column(name = "website", length = 255, unique = true)
    @NotNull
    private String website;

	
    @OneToMany(mappedBy = "publisher")
    private Set<Boardgame> boardgames;


    public String getName() {
        return this.name;
    }

    public void setName(String id) {
        this.name = id;
    }

	public String getWebsite() {
        return website;
    }

	public void setWebsite(String website) {
        this.website = website;
    }


    public Set<Boardgame> getBoardgames() {
        return boardgames;
    }

    public void setBoardgames(Set<Boardgame> boardgames) {
        this.boardgames = boardgames;
    }
	
	
    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .setExcludeFieldNames("boardgames").toString();
    }
}
