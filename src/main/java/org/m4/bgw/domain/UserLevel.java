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
import org.m4.bgw.domain.translate.LevelTranslation;


@Entity
@Table(name = "user_level")
public class UserLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id")
    private Short levelId;

    @Column(name = "name", length = 20)
    private String name;
    
	@Column(name = "can_play")
    @NotNull
    private boolean canPlay;

	@Column(name = "can_shout")
    @NotNull
    private boolean canShout;

	@Column(name = "can_downgrade")
    @NotNull
    private boolean canDowngrade;

	
    @OneToMany(mappedBy = "levelId")
    private Set<LevelTranslation> levelTranslations;

    @OneToMany(mappedBy = "levelId")
    private Set<Player> players;


    public Short getLevelId() {
        return this.levelId;
    }

    public void setLevelId(Short id) {
        this.levelId = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
	public boolean isCanPlay() {
        return canPlay;
    }

	public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

	public boolean isCanShout() {
        return canShout;
    }

	public void setCanShout(boolean canShout) {
        this.canShout = canShout;
    }

	public boolean isCanDowngrade() {
        return canDowngrade;
    }

	public void setCanDowngrade(boolean canDowngrade) {
        this.canDowngrade = canDowngrade;
    }


    public Set<LevelTranslation> getLevelTranslations() {
        return levelTranslations;
    }

    public void setLevelTranslations(Set<LevelTranslation> levelTranslations) {
        this.levelTranslations = levelTranslations;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
	
	
    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .setExcludeFieldNames("levelTranslations", "players").toString();
    }
}
