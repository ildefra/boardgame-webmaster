package org.m4.bgw.domain;
import org.springframework.roo.addon.jpa.annotations.identifier.RooIdentifier;
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
import org.springframework.roo.addon.javabean.annotations.RooEquals;

@Configurable
@Embeddable
@RooIdentifier(dbManaged = true)
@RooEquals
public final class LevelTranslationPK implements Serializable {

	private static final long serialVersionUID = 1L;

	public boolean equals(Object obj) {
        if (!(obj instanceof LevelTranslationPK)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        LevelTranslationPK rhs = (LevelTranslationPK) obj;
        return new EqualsBuilder().append(languageCode, rhs.languageCode).append(levelId, rhs.levelId).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(languageCode).append(levelId).toHashCode();
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static LevelTranslationPK fromJsonToLevelTranslationPK(String json) {
        return new JSONDeserializer<LevelTranslationPK>()
        .use(null, LevelTranslationPK.class).deserialize(json);
    }

	public static String toJsonArray(Collection<LevelTranslationPK> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<LevelTranslationPK> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<LevelTranslationPK> fromJsonArrayToLevelTranslationPKs(String json) {
        return new JSONDeserializer<List<LevelTranslationPK>>()
        .use("values", LevelTranslationPK.class).deserialize(json);
    }

	@Column(name = "level_id", nullable = false)
    private Short levelId;

	@Column(name = "language_code", nullable = false, length = 3)
    private String languageCode;

	public LevelTranslationPK(Short levelId, String languageCode) {
        super();
        this.levelId = levelId;
        this.languageCode = languageCode;
    }

	private LevelTranslationPK() {
        super();
    }

	public Short getLevelId() {
        return levelId;
    }

	public String getLanguageCode() {
        return languageCode;
    }
}
