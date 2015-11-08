package org.m4.bgw.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "achieved")
public class Achieved {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achieved_id")
    private Integer achievedId;

    
    @Column(name = "on_date")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date onDate;


    @ManyToOne
    @JoinColumn(
            name = "username", referencedColumnName = "username", nullable = false)
    private Player player;

	@ManyToOne
    @JoinColumn(
            name = "boardgame_id", referencedColumnName = "boardgame_id", nullable = false)
    private Boardgame boardgame;

    @ManyToOne
    @JoinColumn(
            name = "achievement_id", referencedColumnName = "achievement_id", nullable = false)
    private Achievement achievement;
    
    
    public Integer getAchievedId() {
        return achievedId;
    }
    
    public void setAchievedId(Integer achievedId) {
        this.achievedId = achievedId;
    }

    
    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }

    
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

	public Boardgame getBoardgame() {
        return boardgame;
    }

	public void setBoardgame(Boardgame boardgame) {
        this.boardgame = boardgame;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }
	
	
    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
