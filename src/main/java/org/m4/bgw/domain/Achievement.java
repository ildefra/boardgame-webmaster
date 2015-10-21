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
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

@Entity
@Table(name = "achievement")
@RooJavaBean
@RooJpaEntity(versionField = "", table = "achievement")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "achieveds", "achievementTranslations" })
public class Achievement {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("achieveds", "achievementTranslations").toString();
    }

	@OneToMany(mappedBy = "achievementId")
    private Set<Achieved> achieveds;

	@OneToMany(mappedBy = "achievementId")
    private Set<AchievementTranslation> achievementTranslations;

	@Column(name = "points")
    @NotNull
    private Short points;

	@Column(name = "name", length = 50, unique = true)
    @NotNull
    private String name;

	public Set<Achieved> getAchieveds() {
        return achieveds;
    }

	public void setAchieveds(Set<Achieved> achieveds) {
        this.achieveds = achieveds;
    }

	public Set<AchievementTranslation> getAchievementTranslations() {
        return achievementTranslations;
    }

	public void setAchievementTranslations(Set<AchievementTranslation> achievementTranslations) {
        this.achievementTranslations = achievementTranslations;
    }

	public Short getPoints() {
        return points;
    }

	public void setPoints(Short points) {
        this.points = points;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "achievement_id")
    private Short achievementId;

	public Short getAchievementId() {
        return this.achievementId;
    }

	public void setAchievementId(Short id) {
        this.achievementId = id;
    }
}
