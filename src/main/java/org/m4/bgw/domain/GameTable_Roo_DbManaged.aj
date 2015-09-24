// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import java.util.Calendar;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.m4.bgw.Boardgame;
import org.m4.bgw.domain.GameTable;
import org.m4.bgw.domain.Played;
import org.m4.bgw.domain.TimeLimit;
import org.springframework.format.annotation.DateTimeFormat;

privileged aspect GameTable_Roo_DbManaged {
    
    @OneToMany(mappedBy = "gameTableId")
    private Set<Played> GameTable.playeds;
    
    @ManyToOne
    @JoinColumn(name = "boardgame_id", referencedColumnName = "boardgame_id", nullable = false)
    private Boardgame GameTable.boardgameId;
    
    @ManyToOne
    @JoinColumn(name = "time_limit_id", referencedColumnName = "time_limit_id", nullable = false)
    private TimeLimit GameTable.timeLimitId;
    
    @Column(name = "rated")
    @NotNull
    private boolean GameTable.rated;
    
    @Column(name = "video_recorded")
    @NotNull
    private boolean GameTable.videoRecorded;
    
    @Column(name = "created_dtm")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar GameTable.createdDtm;
    
    @Column(name = "game_started_dtm")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar GameTable.gameStartedDtm;
    
    @Column(name = "game_ended_dtm")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar GameTable.gameEndedDtm;
    
    public Set<Played> GameTable.getPlayeds() {
        return playeds;
    }
    
    public void GameTable.setPlayeds(Set<Played> playeds) {
        this.playeds = playeds;
    }
    
    public Boardgame GameTable.getBoardgameId() {
        return boardgameId;
    }
    
    public void GameTable.setBoardgameId(Boardgame boardgameId) {
        this.boardgameId = boardgameId;
    }
    
    public TimeLimit GameTable.getTimeLimitId() {
        return timeLimitId;
    }
    
    public void GameTable.setTimeLimitId(TimeLimit timeLimitId) {
        this.timeLimitId = timeLimitId;
    }
    
    public boolean GameTable.isRated() {
        return rated;
    }
    
    public void GameTable.setRated(boolean rated) {
        this.rated = rated;
    }
    
    public boolean GameTable.isVideoRecorded() {
        return videoRecorded;
    }
    
    public void GameTable.setVideoRecorded(boolean videoRecorded) {
        this.videoRecorded = videoRecorded;
    }
    
    public Calendar GameTable.getCreatedDtm() {
        return createdDtm;
    }
    
    public void GameTable.setCreatedDtm(Calendar createdDtm) {
        this.createdDtm = createdDtm;
    }
    
    public Calendar GameTable.getGameStartedDtm() {
        return gameStartedDtm;
    }
    
    public void GameTable.setGameStartedDtm(Calendar gameStartedDtm) {
        this.gameStartedDtm = gameStartedDtm;
    }
    
    public Calendar GameTable.getGameEndedDtm() {
        return gameEndedDtm;
    }
    
    public void GameTable.setGameEndedDtm(Calendar gameEndedDtm) {
        this.gameEndedDtm = gameEndedDtm;
    }
    
}
