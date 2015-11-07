package org.m4.bgw.domain;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "game_designer")
public class GameDesigner {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_designer_id")
    private Integer gameDesignerId;

	public Integer getGameDesignerId() {
        return this.gameDesignerId;
    }

	public void setGameDesignerId(Integer id) {
        this.gameDesignerId = id;
    }

	@ManyToMany(mappedBy = "gameDesigners")
    private Set<Boardgame> boardgames;

	@ManyToMany(mappedBy = "gameDesigners1")
    private Set<Boardgame> boardgames1;

	@Column(name = "first_name", length = 50)
    @NotNull
    private String firstName;

	@Column(name = "surname", length = 50)
    @NotNull
    private String surname;

	public Set<Boardgame> getBoardgames() {
        return boardgames;
    }

	public void setBoardgames(Set<Boardgame> boardgames) {
        this.boardgames = boardgames;
    }

	public Set<Boardgame> getBoardgames1() {
        return boardgames1;
    }

	public void setBoardgames1(Set<Boardgame> boardgames1) {
        this.boardgames1 = boardgames1;
    }

	public String getFirstName() {
        return firstName;
    }

	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	public String getSurname() {
        return surname;
    }

	public void setSurname(String surname) {
        this.surname = surname;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("boardgames", "boardgames1").toString();
    }
}
