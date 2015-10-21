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

@Entity
@Table(name = "played")
public class Played {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("gameTableId", "username").toString();
    }

	@EmbeddedId
    private PlayedPK id;

	public PlayedPK getId() {
        return this.id;
    }

	public void setId(PlayedPK id) {
        this.id = id;
    }

	@ManyToOne
    @JoinColumn(name = "game_table_id", referencedColumnName = "game_table_id", nullable = false, insertable = false, updatable = false)
    private GameTable gameTableId;

	@ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    private Player username;

	@Column(name = "score")
    @NotNull
    private Short score;

	public GameTable getGameTableId() {
        return gameTableId;
    }

	public void setGameTableId(GameTable gameTableId) {
        this.gameTableId = gameTableId;
    }

	public Player getUsername() {
        return username;
    }

	public void setUsername(Player username) {
        this.username = username;
    }

	public Short getScore() {
        return score;
    }

	public void setScore(Short score) {
        this.score = score;
    }
}
