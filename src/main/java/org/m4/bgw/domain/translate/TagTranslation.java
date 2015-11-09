package org.m4.bgw.domain.translate;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("tagName", "languageCode").toString();
    }

	@ManyToOne
    @JoinColumn(name = "tag_name", referencedColumnName = "name", nullable = false, insertable = false, updatable = false)
    private GameTag tagName;

	@ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "iso_code", nullable = false, insertable = false, updatable = false)
    private Language languageCode;

	@Column(name = "name", length = 20)
    @NotNull
    private String name;

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

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	@EmbeddedId
    private Integer id;

	public Integer getId() {
        return this.id;
    }

	public void setId(Integer id) {
        this.id = id;
    }
}
