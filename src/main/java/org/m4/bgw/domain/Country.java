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
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "iso_alpha_2", length = 2)
    private String isoAlpha2;

	@Column(name = "iso_alpha_3", length = 3)
	@NotNull
    private String isoAlpha3;

	@Column(name = "name", length = 50, unique = true)
    @NotNull
    private String name;


	@OneToMany(mappedBy = "country")
    private Set<Player> players;

	
    public String getIsoAlpha2() {
        return this.isoAlpha2;
    }

    public void setIsoAlpha2(String id) {
        this.isoAlpha2 = id;
    }

	public String getIsoAlpha3() {
        return isoAlpha3;
    }

	public void setIsoAlpha3(String isoAlpha3) {
        this.isoAlpha3 = isoAlpha3;
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

	
    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .setExcludeFieldNames("players").toString();
    }
}
