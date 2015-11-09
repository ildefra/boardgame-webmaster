package org.m4.bgw.domain.translate;

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
import org.m4.bgw.domain.Language;
import org.m4.bgw.domain.UserLevel;


@Entity
@Table(name = "level_translation")
public class LevelTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "name", length = 20)
    @NotNull
    private String name;

    
	@ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "iso_code", nullable = false)
    private Language languageCode;

	@ManyToOne
    @JoinColumn(name = "level_id", referencedColumnName = "level_id", nullable = false)
    private UserLevel levelId;

	
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
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


	@Override
	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
