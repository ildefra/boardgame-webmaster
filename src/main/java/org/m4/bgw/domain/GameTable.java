package org.m4.bgw.domain;
import java.util.Calendar;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

@Entity
@Table(name = "game_table")
@RooJavaBean
@RooJpaEntity(versionField = "", table = "game_table")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "playeds", "boardgameId", "timeLimitId" })
public class GameTable {

	@OneToMany(mappedBy = "gameTableId")
    private Set<Played> playeds;

	@ManyToOne
    @JoinColumn(name = "boardgame_id", referencedColumnName = "boardgame_id", nullable = false)
    private Boardgame boardgameId;

	@ManyToOne
    @JoinColumn(name = "time_limit_id", referencedColumnName = "time_limit_id", nullable = false)
    private TimeLimit timeLimitId;

	@Column(name = "rated")
    @NotNull
    private boolean rated;

	@Column(name = "video_recorded")
    @NotNull
    private boolean videoRecorded;

	@Column(name = "created_dtm")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar createdDtm;

	@Column(name = "game_started_dtm")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar gameStartedDtm;

	@Column(name = "game_ended_dtm")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar gameEndedDtm;

	public Set<Played> getPlayeds() {
        return playeds;
    }

	public void setPlayeds(Set<Played> playeds) {
        this.playeds = playeds;
    }

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

	public boolean isRated() {
        return rated;
    }

	public void setRated(boolean rated) {
        this.rated = rated;
    }

	public boolean isVideoRecorded() {
        return videoRecorded;
    }

	public void setVideoRecorded(boolean videoRecorded) {
        this.videoRecorded = videoRecorded;
    }

	public Calendar getCreatedDtm() {
        return createdDtm;
    }

	public void setCreatedDtm(Calendar createdDtm) {
        this.createdDtm = createdDtm;
    }

	public Calendar getGameStartedDtm() {
        return gameStartedDtm;
    }

	public void setGameStartedDtm(Calendar gameStartedDtm) {
        this.gameStartedDtm = gameStartedDtm;
    }

	public Calendar getGameEndedDtm() {
        return gameEndedDtm;
    }

	public void setGameEndedDtm(Calendar gameEndedDtm) {
        this.gameEndedDtm = gameEndedDtm;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "game_table_id")
    private Integer gameTableId;

	public Integer getGameTableId() {
        return this.gameTableId;
    }

	public void setGameTableId(Integer id) {
        this.gameTableId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("playeds", "boardgameId", "timeLimitId").toString();
    }
}
