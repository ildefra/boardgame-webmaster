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
import org.m4.bgw.domain.GameTag;
import org.m4.bgw.domain.Language;


@Entity
@Table(name = "tag_translation")
public class TagTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "name", length = 20)
    @NotNull
    private String name;

    
	@ManyToOne
    @JoinColumn(name = "tag_name", referencedColumnName = "name", nullable = false, insertable = false, updatable = false)
    private GameTag tagName;

	@ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "iso_code", nullable = false, insertable = false, updatable = false)
    private Language languageCode;


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

    
	public GameTag getTagName() {
        return tagName;
    }

	public void setTagName(GameTag tagName) {
        this.tagName = tagName;
    }

	public Language getLanguageCode() {
        return languageCode;
    }

	public void setLanguageCode(Language languageCode) {
        this.languageCode = languageCode;
    }
	
	
    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
}
