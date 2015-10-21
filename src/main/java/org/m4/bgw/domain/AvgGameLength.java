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
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

@Entity
@Table(name = "avg_game_length")
@RooJavaBean
@RooJpaEntity(identifierType = AvgGameLengthPK.class, versionField = "", table = "avg_game_length")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "boardgameId", "timeLimitId" })
public class AvgGameLength {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("boardgameId", "timeLimitId").toString();
    }

	@ManyToOne
    @JoinColumn(name = "boardgame_id", referencedColumnName = "boardgame_id", nullable = false, insertable = false, updatable = false)
    private Boardgame boardgameId;

	@ManyToOne
    @JoinColumn(name = "time_limit_id", referencedColumnName = "time_limit_id", nullable = false, insertable = false, updatable = false)
    private TimeLimit timeLimitId;

	@Column(name = "avg_length_minutes")
    @NotNull
    private Short avgLengthMinutes;

	public Boardgame getBoardgameId() {
        return boardgameId;
    }

	public void setBoardgameId(Boardgame boardgameId) {
        this.boardgameId = boardgameId;
    }

	public TimeLimit getTimeLimitId() {
        return timeLimitId;
    }

	public void setTimeLimitId(TimeLimit timeLimitId) {
        this.timeLimitId = timeLimitId;
    }

	public Short getAvgLengthMinutes() {
        return avgLengthMinutes;
    }

	public void setAvgLengthMinutes(Short avgLengthMinutes) {
        this.avgLengthMinutes = avgLengthMinutes;
    }

	@EmbeddedId
    private AvgGameLengthPK id;

	public AvgGameLengthPK getId() {
        return this.id;
    }

	public void setId(AvgGameLengthPK id) {
        this.id = id;
    }
}
