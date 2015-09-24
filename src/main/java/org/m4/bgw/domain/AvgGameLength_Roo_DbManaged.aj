// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.m4.bgw.Boardgame;
import org.m4.bgw.domain.AvgGameLength;
import org.m4.bgw.domain.TimeLimit;

privileged aspect AvgGameLength_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "boardgame_id", referencedColumnName = "boardgame_id", nullable = false, insertable = false, updatable = false)
    private Boardgame AvgGameLength.boardgameId;
    
    @ManyToOne
    @JoinColumn(name = "time_limit_id", referencedColumnName = "time_limit_id", nullable = false, insertable = false, updatable = false)
    private TimeLimit AvgGameLength.timeLimitId;
    
    @Column(name = "avg_length_minutes")
    @NotNull
    private Short AvgGameLength.avgLengthMinutes;
    
    public Boardgame AvgGameLength.getBoardgameId() {
        return boardgameId;
    }
    
    public void AvgGameLength.setBoardgameId(Boardgame boardgameId) {
        this.boardgameId = boardgameId;
    }
    
    public TimeLimit AvgGameLength.getTimeLimitId() {
        return timeLimitId;
    }
    
    public void AvgGameLength.setTimeLimitId(TimeLimit timeLimitId) {
        this.timeLimitId = timeLimitId;
    }
    
    public Short AvgGameLength.getAvgLengthMinutes() {
        return avgLengthMinutes;
    }
    
    public void AvgGameLength.setAvgLengthMinutes(Short avgLengthMinutes) {
        this.avgLengthMinutes = avgLengthMinutes;
    }
    
}
