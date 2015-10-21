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
@Table(name = "achievement_translation")
public class AchievementTranslation {

	@EmbeddedId
    private AchievementTranslationPK id;

	public AchievementTranslationPK getId() {
        return this.id;
    }

	public void setId(AchievementTranslationPK id) {
        this.id = id;
    }

	@ManyToOne
    @JoinColumn(name = "achievement_id", referencedColumnName = "achievement_id", nullable = false, insertable = false, updatable = false)
    private Achievement achievementId;

	@ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "iso_code", nullable = false, insertable = false, updatable = false)
    private Language languageCode;

	@Column(name = "name", length = 50)
    @NotNull
    private String name;

	public Achievement getAchievementId() {
        return achievementId;
    }

	public void setAchievementId(Achievement achievementId) {
        this.achievementId = achievementId;
    }

	public Language getLanguageCode() {
        return languageCode;
    }

	public void setLanguageCode(Language languageCode) {
        this.languageCode = languageCode;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("achievementId", "languageCode").toString();
    }
}
