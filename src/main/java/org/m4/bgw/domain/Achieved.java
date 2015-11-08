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
            name = "username", referencedColumnName = "username",
            nullable = false, insertable = false, updatable = false)
    private Player username;

	@ManyToOne
    @JoinColumn(
            name = "boardgame_id", referencedColumnName = "boardgame_id",
            nullable = false, insertable = false, updatable = false)
    private Boardgame boardgameId;

    @ManyToOne
    @JoinColumn(
            name = "achievement_id", referencedColumnName = "achievement_id",
            nullable = false, insertable = false, updatable = false)
    private Achievement achievementId;    
    
    
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

    
    public Player getUsername() {
        return username;
    }

    public void setUsername(Player username) {
        this.username = username;
    }

	public Boardgame getBoardgameId() {
        return boardgameId;
    }

	public void setBoardgameId(Boardgame boardgameId) {
        this.boardgameId = boardgameId;
    }

    public Achievement getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Achievement achievementId) {
        this.achievementId = achievementId;
    }
	
	
    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .setExcludeFieldNames("achievementId", "boardgameId", "username").toString();
    }
}
