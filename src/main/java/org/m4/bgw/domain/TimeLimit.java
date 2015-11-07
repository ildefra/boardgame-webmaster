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
@Table(name = "time_limit")
public class TimeLimit {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_limit_id")
    private Integer timeLimitId;

	public Integer getTimeLimitId() {
        return this.timeLimitId;
    }

	public void setTimeLimitId(Integer id) {
        this.timeLimitId = id;
    }

	@OneToMany(mappedBy = "timeLimitId")
    private Set<AvgGameLength> avgGameLengths;

	@OneToMany(mappedBy = "timeLimitId")
    private Set<GameTable> gameTables;

	@Column(name = "name", length = 50)
    private String name;

	@Column(name = "turn-based")
    @NotNull
    private boolean turnBased;

	public Set<AvgGameLength> getAvgGameLengths() {
        return avgGameLengths;
    }

	public void setAvgGameLengths(Set<AvgGameLength> avgGameLengths) {
        this.avgGameLengths = avgGameLengths;
    }

	public Set<GameTable> getGameTables() {
        return gameTables;
    }

	public void setGameTables(Set<GameTable> gameTables) {
        this.gameTables = gameTables;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public boolean isTurnBased() {
        return turnBased;
    }

	public void setTurnBased(boolean turnBased) {
        this.turnBased = turnBased;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("avgGameLengths", "gameTables").toString();
    }
}
