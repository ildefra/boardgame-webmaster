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
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

@Entity
@Table(name = "level_translation")
@RooJavaBean
@RooJpaEntity(identifierType = LevelTranslationPK.class, versionField = "", table = "level_translation")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "languageCode", "levelId" })
public class LevelTranslation {

	@EmbeddedId
    private LevelTranslationPK id;

	public LevelTranslationPK getId() {
        return this.id;
    }

	public void setId(LevelTranslationPK id) {
        this.id = id;
    }

	@ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "iso_code", nullable = false, insertable = false, updatable = false)
    private Language languageCode;

	@ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "level_id", nullable = false, insertable = false, updatable = false)
    private UserLevel levelId;

	@Column(name = "name", length = 20)
    @NotNull
    private String name;

	public Language getLanguageCode() {
        return languageCode;
    }

	public void setLanguageCode(Language languageCode) {
        this.languageCode = languageCode;
    }

	public UserLevel getLevelId() {
        return levelId;
    }

	public void setLevelId(UserLevel levelId) {
        this.levelId = levelId;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("languageCode", "levelId").toString();
    }
}
