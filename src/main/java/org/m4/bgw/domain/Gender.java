package org.m4.bgw.domain;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "gender")
public class Gender {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iso_code")
    private Short isoCode;

	public Short getIsoCode() {
        return this.isoCode;
    }

	public void setIsoCode(Short id) {
        this.isoCode = id;
    }

	@OneToMany(mappedBy = "sex")
    private Set<Player> players;

	@Column(name = "name", length = 20, unique = true)
    @NotNull
    private String name;

	public Set<Player> getPlayers() {
        return players;
    }

	public void setPlayers(Set<Player> players) {
        this.players = players;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("players").toString();
    }
}
