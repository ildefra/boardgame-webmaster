package org.m4.bgw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


@Entity
@Table(name = "played")
public class Played {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "played_id")
    private Integer playedId;


    @Column(name = "score")
    @NotNull
    private Short score;

    
	@ManyToOne
    @JoinColumn(
            name = "game_table_id", referencedColumnName = "game_table_id",
            nullable = false)
    private GameTable gameTableId;

	@ManyToOne
    @JoinColumn(
            name = "username", referencedColumnName = "username",
            nullable = false)
    private Player player;

	    
    public Integer getPlayedId() {
        return playedId;
    }
    
    public void setPlayedId(Integer playedId) {
        this.playedId = playedId;
    }

    
    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    
	public GameTable getGameTableId() {
        return gameTableId;
    }

	public void setGameTableId(GameTable gameTableId) {
        this.gameTableId = gameTableId;
    }

	public Player getPlayer() {
        return player;
    }

	public void setPlayer(Player player) {
        this.player = player;
    }
	
	
	@Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
