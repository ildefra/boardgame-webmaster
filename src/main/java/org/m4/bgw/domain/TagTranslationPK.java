package org.m4.bgw.domain;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Embeddable
public final class TagTranslationPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "tag_name", nullable = false, length = 20)
    private String tagName;

	@Column(name = "language_code", nullable = false, length = 3)
    private String languageCode;

	public TagTranslationPK(String tagName, String languageCode) {
        super();
        this.tagName = tagName;
        this.languageCode = languageCode;
    }

	public TagTranslationPK() {
        super();
    }

	public String getTagName() {
        return tagName;
    }

	public String getLanguageCode() {
        return languageCode;
    }
	
	
    public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

    public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

    public static TagTranslationPK fromJsonToTagTranslationPK(String json) {
        return new JSONDeserializer<TagTranslationPK>()
        .use(null, TagTranslationPK.class).deserialize(json);
    }

    public static String toJsonArray(Collection<TagTranslationPK> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

    public static String toJsonArray(Collection<TagTranslationPK> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

    public static Collection<TagTranslationPK> fromJsonArrayToTagTranslationPKs(String json) {
        return new JSONDeserializer<List<TagTranslationPK>>()
        .use("values", TagTranslationPK.class).deserialize(json);
    }

    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TagTranslationPK)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        TagTranslationPK rhs = (TagTranslationPK) obj;
        return new EqualsBuilder()
                .append(languageCode, rhs.languageCode)
                .append(tagName, rhs.tagName).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(languageCode).append(tagName).toHashCode();
    }
}
